package console.gui;

import handler.AnomalyStats;
import log.Logger;
import log.Severity;
import scheduler.Scheduler;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GUI {
    public static JFrame frame;
    public static JTextPane console;
    public static JTextField input;
    public static JScrollPane scroll;

    public static StyledDocument document;

    public static ArrayList<String> recentCommands = new ArrayList<>();
    public static Integer recentId = 0;
    public static Boolean trace = false;
    public static String currentCommand = "";
    public static Boolean sysEx = false;

    public GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            Logger.log("The JFrame lookAnFeel was unable to initialise.", Severity.FATAL);
        }
        frame = new JFrame();
        frame.setTitle("Anomaly " + AnomalyStats.version);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Courier New", Font.PLAIN, 14));
        console.setOpaque(false);

        document = console.getStyledDocument();

        input = new JTextField();
        input.setEditable(true);
        input.setCaretColor(Color.WHITE);
        input.setForeground(Color.WHITE);
        input.setBackground(new Color(28, 28, 28));
        input.setFont(new Font("Courier New", Font.PLAIN, 14));
        scroll = new JScrollPane(console);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setBorder(null);

        frame.add(input, BorderLayout.SOUTH);
        frame.add(scroll, BorderLayout.CENTER);

        frame.getContentPane().setBackground(new Color(28, 28, 28));

        frame.setSize(860, 540);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = input.getText();
                if(inputText.length() > 1) {
                    println("> " + inputText, false, Color.LIGHT_GRAY);
                    new CommandHandler().runCommand(inputText);
                    scrollBottom();
                    input.setText("");
                    /*
                    if(recentCommands.size() != 1 || recentCommands.size() != 0) {
                        System.out.println(recentCommands.size());
                        if(recentCommands.get(recentCommands.size() - 1) != inputText) {
                            recentCommands.add(inputText);
                        }
                    }
                    currentCommand = "";
                    recentId = 0;
                     */
                }
            }
        });

        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(sysEx) {
                    sysExit();
                }

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    if(recentId < (recentCommands.size() - 1)) {
                        recentId += 1;
                        input.setText(recentCommands.get(recentId));
                    }
                } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(recentId > 0) {
                        recentId -= 1;
                        input.setText(recentCommands.get(recentId));
                    } else {
                        input.setText(currentCommand);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    public void scrollTop() {
        console.setCaretPosition(0);
    }

    public void scrollBottom() {
        console.setCaretPosition(console.getDocument().getLength());
    }

    public void doLine(String s, Boolean trace, Color c) {
        Style style = console.addStyle("Style", null);
        StyleConstants.setForeground(style, c);
        String finished;
        if(trace) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();

            finished = caller + " > " + s;
        } else {
            finished = s;
        }
        try {
            document.insertString(document.getLength(), finished, style);
        } catch(Exception e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public void doLine(String s, Boolean trace) {
        doLine(s, trace, new Color(255, 255 ,255));
    }

    public void println(String line, Boolean trace, Color color) {
        doLine(" " + line + "\n", trace, color);
    }

    public void clear() {
        try {
            document.remove(0, document.getLength());
        } catch(Exception e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public void stop() {
        println("Anomaly stopped. Press any key to continue...", false, Color.LIGHT_GRAY);
        sysEx = true;
    }

    public void sysExit() {
        System.exit(-1);
    }
}
