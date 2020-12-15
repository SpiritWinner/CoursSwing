import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookList {
    private List<Book> books;
    private String name;

    public BookList() {
        name = "";
        books = new ArrayList<Book>();
    }

    public BookList(String name) {
        this.name = name;
        books = new ArrayList<Book>();
    }

    public BookList(String name, List list) {
        this.name = name;
        books = new ArrayList<Book>(list);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean addBook(Book Book) {
        if (getBook(Book) != null)
            return false;
        return books.add(Book);
    }

    public boolean delBook(Book Book) {
        return books.remove(Book);
    }

    public boolean updateBook(Book Book) {
        Book r = getBook(Book);
        if (r != null) {
            r.setMonth(Book.getMonth());
            r.setCopyCount(Book.getCopyCount());
            return true;
        }
        return false;
    }

    public Book getBook(Book Book) {
        for (Book el : books)
            if (el.equals(Book)) return el;
        return null;
    }

 /*   public List<String> result1() {
        var Book = new ArrayList<String>();

        var books = new ArrayList<Integer>();
        for (var Book : getBooks()) {
            boolean flag = false;
            for (var el : books) {
                if (Book.getBookNum() == el) {
                    flag = true;
                    break;
                }
            }
            if (!flag) books.add(Book.getBookNum());
        }

        for (var el : books) {
            var i = 0;
            for (var Book : getBooks()) {
                if (el == Book.getBookNum()) {
                    i += Book.getCopyCount();
                }
            }
            Book.add(String.format("Book: \"%s\" has %d copies", el, i));
        }
        return Book;
    } // Book 1*/

    public int getCountOfVendors() {
        var vendors = new ArrayList<String>();
        for (var Book : getBooks()) {
            boolean flag = false;
            for (var el : vendors) {
                if (Book.getVendorCode().equals(el)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) vendors.add(Book.getVendorCode());
        }
        return vendors.size();
    } // Book 2

/*    public BookList sortByBookNumAndCopyCounts() {
        Comparator<Book> comparator = Comparator.comparing(Book::getBookNum).reversed().thenComparing(Book::getCopyCount).reversed();
        BookList books = new BookList(this.books);
        books.books.sort(comparator);
        return books;
    }*/

    public double getAvg() {
        var value = 0.0;
        var i = 0;
        for (var el : this.getBooks()) {
            value += el.getCopyCount();
            i++;
        }
        return value / i;
    }

    public void removeLessThenAvg() {
        var value = getAvg();
        this.getBooks().removeIf(el -> (el.getCopyCount() < value));
    }

    public BookList filter(String value) {
        var books = new BookList();
        for (var el : this.getBooks()) {
            if (el.getVendorCode().toLowerCase().startsWith(value.toLowerCase())) {
                books.addBook(el);
            }
        }
        return books;
    }

    public BookList selectionByMinMaxCopyCounts(int min, int max) {
        BookList books = new BookList();
        for (var el : this.books) {
            if (el.getCopyCount() > min && el.getCopyCount() < max) books.addBook(el);
        }
        return books;
    }
}
