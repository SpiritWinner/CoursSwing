import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Global {

    public static BookList table;
    public static List<Book> results;
    static ResultTableModel tableModel;

    public static List <String> inpLines (String fileName) {
        List <String> lines = new ArrayList <String>();
        String line;
        try (BufferedReader inp = new BufferedReader(new FileReader(fileName))) {
            while ((line = inp.readLine()) != null) {
                line = line.trim();
                if (line.equals("")) continue;
                lines.add(line);
            }
        } catch (IOException e) {
            return null;
        }
        if (lines.isEmpty())return null;
        return lines;
    }

    public static boolean outLines(String fileName, List<String> lines) {
        if ((lines == null) || lines.isEmpty()) return false;
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                out.println(line.trim());
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void updateJTable(List<Book> res) {
        results.clear();
        results.addAll(res);
        tableModel.fireTableDataChanged();
    }

    public static List<Book> StringsToBooks(List<String> lines) {
        if (lines == null || lines.isEmpty()) return null;
        List<Book> results = new ArrayList<Book>();
        for (String line : lines) {
            String[] words = line.split(",");
            if (words.length != 4) return null;
            Book newBook = new Book();
            newBook.setBookNum(Integer.parseInt(words[0].trim()));
            newBook.setVendorCode(words[1].trim());
            newBook.setMonth(words[2].trim());
            newBook.setCopyCount(Integer.parseInt(words[0].trim()));
            results.add(newBook);
        }
        return results;
    }

    public static List<String> BooksToString(List<Book> results) {
        if (results == null || results.isEmpty()) return null;
        List<String> lines = new ArrayList<String>();
        for (Book el : results)
            lines.add(String.format("%10d, %10s, %5s, %5d", el.getBookNum(),
                    el.getVendorCode(), el.getMonth(), el.getCopyCount()));
        return lines;
    }
}

