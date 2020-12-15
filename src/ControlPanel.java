import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.ParameterizedType;

public class ControlPanel extends JPanel {
    PTextField filter;
    PTextField min;
    PTextField max;

    public ControlPanel() {
        setLayout(new GridLayout(14, 1, 0, 3));
        JLabel labelNamePanel = new JLabel("Control Panel");
        JButton btnShowAll = new JButton("Show All");
        JButton btnAdd = new JButton("Add");
        JButton btnSort = new JButton("Sort");
        JButton btnRemoveBy = new JButton("Remove by condition");
        JButton btnFirstResult = new JButton("Result 1");
        JButton btnSecondResult = new JButton("Result 2");
        JButton btnAygCountCopy = new JButton("Ayg count copies");
        JLabel labelSelect = new JLabel("Select by min and max count copies");
        labelSelect.setHorizontalAlignment(JLabel.CENTER);
        min = new PTextField("min");
        max = new PTextField("max");
        JButton btnCommit = new JButton("Commit");
        filter = new PTextField("Enter Filter");
        JButton btnFilter = new JButton("Filter");
        Font font = new Font("Segoe Script", Font.BOLD, 14);
        labelNamePanel.setVerticalAlignment(JLabel.CENTER);
        labelNamePanel.setHorizontalAlignment(JLabel.CENTER);
        labelNamePanel.setFont(font);

        add(labelNamePanel);
        add(btnShowAll);
        add(btnAdd);
        add(btnSort);
        add(btnRemoveBy);
        add(btnFirstResult);
        add(btnSecondResult);
        add(btnAygCountCopy);
        add(labelSelect);
        add(min);
        add(max);
        add(btnCommit);
        add(filter);
        add(btnFilter);

        btnShowAll.addActionListener(e -> showAll());
        btnFilter.addActionListener(e -> showFilter());

        btnAdd.addActionListener(e -> {new Dialog();
        });


    }

    private void showAll(){
        Global.updateJTable(Global.table.getBooks());
    }

    private void showFilter(){
        String filters = filter.getText();
        if (filters.equals("")) {
            return;
        }
        Global.updateJTable(Global.table.filter(filters).getBooks());
        filter.setText("Enter Filter");
        filter.setForeground(Color.gray);
    }


}