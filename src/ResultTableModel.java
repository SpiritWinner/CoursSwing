import javax.swing.table.AbstractTableModel;
import java.util.*;

public class ResultTableModel extends AbstractTableModel {
    List<Book> results;

    public ResultTableModel(List<Book> results) {
        super();
        this.results = results;
    }

    @Override
    public int getRowCount() {
        return results.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return switch (c) {
            case 0 -> results.get(r).getBookNum();
            case 1 -> results.get(r).getVendorCode();
            case 2 -> results.get(r).getMonth();
            case 3 -> results.get(r).getCopyCount();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int c) {
        return switch (c) {
            case 0 -> "Number book";
            case 1 -> "Code vendor";
            case 2 -> "Month";
            case 3 -> "Count copy";
            default -> "";
        };
    }
}