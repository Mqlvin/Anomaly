package console;

import handler.AnomalyStats;
import log.Logger;
import log.Severity;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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

        input.addActionListener(e -> {
            String inputText = input.getText();
            if(inputText.length() > 1) {
                println("> " + inputText, false, Color.LIGHT_GRAY);
                new CommandHandler().runCommand(inputText);
                println(" ", false, Color.BLACK);
                scrollBottom();
                /*
                Integer currentSize = recentCommands.size();
                if(currentSize != 1 || currentSize != 0) {
                    System.out.println(recentCommands.size());
                    if(recentCommands.get(recentCommands.size() - 1) != inputText) {
                        recentCommands.add(inputText);
                    }
                }
                 */
                input.setText("");
                currentCommand = "";
                recentId = 0;
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
        if(line.contains("§")) {
            String[] strings = line.split("§");
            doLine(" ", false, Color.BLACK);
            for(String oneLine : strings) {
                if(oneLine.startsWith("red")) {
                    doLine(oneLine.substring(3), false, new Color(248, 60, 60));
                } else if(oneLine.toLowerCase().startsWith("dark_red")) {
                    doLine(oneLine.substring(8), false, new Color(159, 0, 0));
                } else if(oneLine.toLowerCase().startsWith("orange")) {
                    doLine(oneLine.substring(6), false, new Color(243, 120, 23));
                } else if(oneLine.toLowerCase().startsWith("yellow")) {
                    doLine(oneLine.substring(6), false, new Color(248, 244, 16));
                } else if(oneLine.toLowerCase().startsWith("green")) {
                    doLine(oneLine.substring(5), false, new Color(44, 208, 15));
                } else if(oneLine.toLowerCase().startsWith("dark_green")) {
                    doLine(oneLine.substring(10), false, new Color(10, 133, 6));
                } else if(oneLine.toLowerCase().startsWith("cyan")) {
                    doLine(oneLine.substring(4), false, new Color(30, 138, 160));
                } else if(oneLine.toLowerCase().startsWith("aqua")) {
                    doLine(oneLine.substring(4), false, new Color(24, 227, 203));
                } else if(oneLine.toLowerCase().startsWith("blue")) {
                    doLine(oneLine.substring(4), false, new Color(15, 98, 236));
                } else if(oneLine.toLowerCase().startsWith("dark_blue")) {
                    doLine(oneLine.substring(9), false, new Color(0, 42, 255));
                } else if(oneLine.toLowerCase().startsWith("pink")) {
                    doLine(oneLine.substring(4), false, new Color(218, 24, 234));
                } else if(oneLine.toLowerCase().startsWith("purple")) {
                    doLine(oneLine.substring(6), false, new Color(116, 25, 219));
                } else if(oneLine.toLowerCase().startsWith("dark_purple")) {
                    doLine(oneLine.substring(11), false, new Color(87, 0, 153));
                } else if(oneLine.toLowerCase().startsWith("black")) {
                    doLine(oneLine.substring(5), false, new Color(0, 0, 0));
                } else if(oneLine.toLowerCase().startsWith("white")) {
                    doLine(oneLine.substring(5), false, new Color(255, 255, 255));
                } else if(oneLine.toLowerCase().startsWith("dark_grey")) {
                    doLine(oneLine.substring(9), false, new Color(60, 60, 60));
                } else if(oneLine.toLowerCase().startsWith("light_grey")) {
                    doLine(oneLine.substring(10), false, new Color(165, 165, 165));
                }
            }
            doLine("\n", trace, new Color(0, 0, 0));
        } else {
            doLine(" " + line + "\n", trace, color);
        }
    }

    public void clear() {
        try {
            document.remove(0, document.getLength());
        } catch(Exception e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }

    public void stop() {
        println("§redAnomaly stopped. §light_greyPress any key to continue...", false, Color.LIGHT_GRAY);
        sysEx = true;
        Console.ended = true;
    }

    public void sysExit() {
        System.exit(-1);
    }

    public void delLast() {
        try {
            String content = console.getDocument().getText(0, document.getLength());
            List<String> contentParsed = Arrays.asList(content.split("\n"));
            String rebuild = contentParsed.get(contentParsed.size() - 1);
            document.remove(content.length() - rebuild.length() - 2, rebuild.length() + 2);
            Console.println(" ", false, Color.WHITE);
        } catch(BadLocationException e) {
            Logger.log(e.toString(), Severity.FATAL);
        }
    }
}
