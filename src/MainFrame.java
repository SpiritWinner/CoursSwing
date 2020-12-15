import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

public class MainFrame implements ActionListener {
    String directoryName = "C:/";
    String fileName = "";
    File curFile;
    static JFrame frame;
    JPanel pMain;
    JTable VIS_TABLE;
    ControlPanel controlPanel;
    JLabel jFileName;
    java.util.List<String> LINES;
    //Текстовые массивы, которые содержат сообщения, выдаваемые в окнах меню "Справка"
/*    static String  helpArr1="\n     Систему разработал студент группы ИВТ/б-21-о\n "+
            "    Алексеев Иван Петрович:\n"+
            "     СевГУ - 2016.\n";

    static String  helpArr2="\n     Информационная система осуществляет хранение и\n"+
            "     обработку данных о результативности команд\n"+
            "     в соревнованиях.\n" */;

    public MainFrame() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

        Global.table = new BookList("Список записей о результатах");
        Global.results = new ArrayList<Book>();
        Global.tableModel = new ResultTableModel(Global.results);
        VIS_TABLE = new JTable(Global.tableModel);
        JScrollPane scrtable = new JScrollPane(VIS_TABLE);
        VIS_TABLE.setShowGrid(true);

        Box vBox1 = Box.createVerticalBox();
        Box hBox = Box.createHorizontalBox();
        Box vBox2 = Box.createVerticalBox();


        controlPanel = new ControlPanel();
        int WindowWidth = 700;
        int WindowHeight = 550;
        frame = new JFrame("Данные о закупках книг за год");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container myC = frame.getContentPane();
        myC.setLayout(new BorderLayout(5, 5));
        MenuIS s = new MenuIS();
        frame.setJMenuBar(s.menuBar);

        s.newFile.addActionListener(this);
        s.openFile.addActionListener(this);
        s.saveFile.addActionListener(this);
        s.saveAsFile.addActionListener(this);
        s.closeFile.addActionListener(this);

        pMain = new JPanel();
        pMain.setLayout(new BorderLayout());
        jFileName = new JLabel("Без имени", JLabel.CENTER);
        jFileName.setOpaque(true);
        jFileName.setSize(20, 5);

        vBox1.add(new JScrollPane(jFileName));
        vBox1.add(scrtable);
        vBox2.add(controlPanel);
        hBox.add(vBox1);
        hBox.add(vBox2);

        pMain.add(hBox);

        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane spMain = new JScrollPane(pMain, v, h);
        myC.add(spMain, BorderLayout.CENTER);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds((dimension.width - WindowWidth) / 2, (dimension.height - WindowHeight) / 2, WindowWidth, WindowHeight);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void NewFile() {
        Global.table.getBooks().clear();
        Global.results.clear();
        Global.tableModel.fireTableDataChanged();
    }

    public void setFileFilter(JFileChooser fch) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Data base", "mysql", "db");
        fch.setFileFilter(filter);
    }

    public void OpenFile() {
        int n;
        JFileChooser fch = new JFileChooser(directoryName);
        fch.setDialogTitle("Открытие файла");
        setFileFilter(fch);
        int rez = fch.showDialog(frame, "Open");
        if (rez == JFileChooser.APPROVE_OPTION) {
            curFile = fch.getSelectedFile();
            fileName = curFile.getAbsolutePath();
            n = fileName.lastIndexOf('\\');
            directoryName = fileName.substring(0, n + 1);
            try {
                LINES = Global.inpLines(fileName);
            } catch (Exception ignored) {
            }
            java.util.List<Book> res = Global.StringsToBooks(LINES);
            Global.table.getBooks().clear();
            for (Book r : res) Global.table.addBook(r);
            Global.updateJTable(Global.table.getBooks());
            jFileName.setText(fileName);
        }
    }

    private void SaveDialog() {
        int n;
        JFileChooser fch = new JFileChooser(directoryName);
        fch.setDialogTitle("Сохранение файла");
        setFileFilter(fch);
        int rez = fch.showDialog(frame, "Save");
        if (rez == JFileChooser.APPROVE_OPTION) {
            curFile = fch.getSelectedFile();
            fileName = curFile.getAbsolutePath();
            n = fileName.lastIndexOf('\\');
            directoryName = fileName.substring(0, n + 1);
        }
    }

    public void SaveFile(boolean fs) {
        String old_file_name = fileName;
        if (fs) SaveDialog();
        else if (fileName.equals("")) SaveDialog();
        if (curFile == null) return;
        if ((!curFile.exists()) || fileName.equals(old_file_name)) {
            LINES = Global.BooksToString(Global.table.getBooks());
            try {
                boolean f = Global.outLines(fileName, LINES);
                if (f) {
                    jFileName.setText(fileName);
                }
            } catch (Exception ignored) {
            }
        } else {
            JOptionPane.showMessageDialog(
                    frame, "Ошибка: файл с заданным именем " + fileName + " существует");
            fileName = old_file_name;
        }
    }

    public void CloseWindow() {
        frame.dispose();
    }


    public void actionPerformed(ActionEvent e) {
        if ("New".equals(e.getActionCommand())) NewFile();
        else if ("Open".equals(e.getActionCommand())) OpenFile();
        else if ("Save".equals(e.getActionCommand())) SaveFile(false);
        else if ("Save as..".equals(e.getActionCommand())) SaveFile(true);
        else if ("Close".equals(e.getActionCommand())) CloseWindow();
        else if ("О программе".equals(e.getActionCommand())) ;
/*            HelpDialog helpMSG = new HelpDialog(MainFrame.frame,"О программе",helpArr1, "динозавр.gif");
            helpMSG.setVisible(true); }
        else  if ("Описание ИС".equals(e.getActionCommand())){
            HelpDialog helpMSG1=new HelpDialog(MainFrame.frame,"Описание информационной системы",
                    helpArr2, "лошадь.gif");
            helpMSG1.setVisible(true);}*/

    }

}