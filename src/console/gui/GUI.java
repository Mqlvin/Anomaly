package console.gui;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import io.Reader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

public class GUI {
    public static void createFrame() {
        JFrame GUI = new JFrame("Console");
        /*
        JEditorPane browser = new JEditorPane();
        browser.setEditable(false);
        HTMLEditorKit html = new HTMLEditorKit();
        browser.setEditorKit(html);
        StyleSheet css = new StyleSheet();
        css.addRule("body { background-color: rgb(10, 10, 10); font-family: 'Source Code Pro', monospace; }");
        css.addRule(".line { color: rgb(202, 202, 202); padding: 0px 20px; font-size: 20px; }");
        css.addRule("input[type=\"text\"] { background-color: rgb(27, 27, 27); color: white; border: transparent; outline: transparent; width: 98%; height: 30px; font-size: 20px; margin: auto; display: block; font-family: 'Source Code Pro', monospace; }");
        html.setStyleSheet(css);
        Document document = html.createDefaultDocument();
        browser.setDocument(document);
        browser.setText(Reader.readJson(new File("C:\\Users\\henry\\Desktop\\Dev Projects\\random test things\\consoletest.html")));
        browser.setPreferredSize(new Dimension(624, 448));
         */



        JPanel content = new JPanel();
        GUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GUI.add(content, BorderLayout.SOUTH);
        GUI.setSize(624, 448);
        GUI.setResizable(false);
        GUI.setVisible(true);

        System.out.println(content);
    }
}
