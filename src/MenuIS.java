import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MenuIS {
    JMenuItem newFile;
    JMenuItem openFile;
    JMenuItem saveFile;
    JMenuItem saveAsFile;
    JMenuItem closeFile;
    JMenuItem help;
    JMenuItem about;

    JMenu menuFiles;
    JMenu menuAbout;
    JMenuBar menuBar;

    public MenuIS() {
        menuFiles = new JMenu("Files");
        newFile = new JMenuItem("New");
        menuFiles.add(newFile);
        openFile = new JMenuItem("Open");
        menuFiles.add(openFile);
        saveFile = new JMenuItem("Save");
        menuFiles.add(saveFile);
        saveAsFile = new JMenuItem("Save as..");
        menuFiles.add(saveAsFile);
        closeFile = new JMenuItem("Close");
        menuFiles.add(closeFile);

        menuAbout = new JMenu("Справка");
        help = new JMenuItem("О программе");
        menuAbout.add(help);
        about = new JMenuItem("Описание ИС");
        menuAbout.add(about);

        menuBar = new JMenuBar();
        menuBar.add(menuFiles);
        menuBar.add(menuAbout);
    }
}
