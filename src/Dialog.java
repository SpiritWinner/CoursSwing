import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*class Dialog extends JDialog
        implements ActionListener {
    //Диалог для подпунктов меню "Справка"
    public Dialog(JFrame parent, String name, String help, String pictureName) { //конструктор
        // вызов конструктора базового класса
        super(parent, name, true);
        //true - модальный диалог
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JButton ok = new JButton("OK");
        ok.addActionListener(this);
        cp.add(ok, BorderLayout.SOUTH);
        ImageIcon img = new ImageIcon(pictureName);
        JLabel L_Img = new JLabel(img, JLabel.CENTER);
        Font font = new Font("Times New Roman", Font.PLAIN, 12);//создали шрифт
        JTextArea helpMSG = new JTextArea(help, 80, 16);
        helpMSG.setFont(font);
        helpMSG.setEditable(false);
        cp.add(helpMSG, BorderLayout.CENTER);
        cp.add(L_Img, BorderLayout.NORTH);
        MainFrame.MSG.setText("    Курсовой проект по дисциплине \"Программирование\". СевГУ - 2016");
        setSize(320, 350);
        setLocation(50, 100);
    }*/

public class Dialog extends JDialog {
    PTextField pole1;
    PTextField pole2;
    PTextField pole3;
    PTextField pole4;

    public Dialog() {
/*
        super("Пример использования JOptionPane");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.put("OptionPane.yesButtonText", "Add");
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.cancelButtonText", "Отмена");


        JOptionPane.showInputDialog(Dialog.this,
                new String[]{"Неверно введен пароль!", "Повторите пароль :"},
                "Авторизация", JOptionPane.WARNING_MESSAGE);
        setSize(500, 140);

*/
        int WindowWidth = 170;
        int WindowHeight = 225;
        setVisible(true);
        setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds((dimension.width - WindowWidth) / 2, (dimension.height - WindowHeight) / 2, WindowWidth, WindowHeight);

        setLayout(new GridLayout(6, 1, 0, 3));
        pole1 = new PTextField("min");
        pole2 = new PTextField("max");
        pole3 = new PTextField("min");
        pole4 = new PTextField("max");
        JButton btn1 = new JButton("Add element");
        JButton btn2 = new JButton("close");

        add(pole1);
        add(pole2);
        add(pole3);
        add(pole4);
        add(btn1);
        add(btn2);


        btn1.addActionListener(e -> add());

        btn2.addActionListener(e -> setVisible(false));
    }

    private void add() {
        int n1, n2;
        String str1, str2, str3, str4;
        str1 = pole1.getText();
        str2 = pole2.getText();
        str3 = pole3.getText();
        str4 = pole4.getText();

        if (str1.equals("") || str2.equals("") || str3.equals("") || str4.equals("")) {
            JOptionPane.showMessageDialog(Dialog.this, "Задайте значения полей");
        }
        try {
            n1 = Integer.parseInt(str1);
            n2 = Integer.parseInt(str4);
        } catch (NumberFormatException e) {
//            MainFrame.MSG.setText("   Задайте правильно число голов");
            return;
        }
//        MainFrame.MSG.setText(
//                "   Запрос на добавление записи в таблицу");
        if (!Global.table.addBook(new Book(n1, str2, str3, n2)))
            JOptionPane.showMessageDialog(Dialog.this, "Запись не добавлена, возможно нарушена уникальность ключа");
        Global.updateJTable(Global.table.getBooks());
        pole1.setText("");
        pole2.setText("");
        pole3.setText("");
        pole4.setText("");
    }
}