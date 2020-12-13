import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList() {
        this.books = new ArrayList<Book>();
    }

    public BookList(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean addBook(Book book){
        if (getBook(book)!=null)
            return false;
        return books.add(book);
    }

    public boolean delBook(Book book){
        return books.remove(book);
    }

    public boolean updateBook(Book book){
        Book r = getBook(book);
        if (r!=null) {
            r.setMonth(book.getMonth());
            r.setCopyCount(book.getCopyCount());
            return true;
        }
        return false;
    }

    public Book getBook (Book book){
        for (Book el : books)
            if (el.equals(book)) return el;
        return null;
    }

    public List<String> result1() {
        var result = new ArrayList<String>();

        var books = new ArrayList<Integer>();
        for (var book : getBooks()) {
            boolean flag = false;
            for (var el : books) {
                if (book.getBookNum() == el) {
                    flag = true;
                    break;
                }
            }
            if (!flag) books.add(book.getBookNum());
        }

        for (var el : books) {
            var i = 0;
            for (var book : getBooks()) {
                if (el == book.getBookNum()) {
                    i += book.getCopyCount();
                }
            }
            result.add(String.format("Book: \"%s\" has %d copies", el, i));
        }
        return result;
    } // result 1

    public int getCountOfVendors() {
        var vendors = new ArrayList<String>();
        for (var book : getBooks()) {
            boolean flag = false;
            for (var el : vendors) {
                if (book.getVendorCode().equals(el)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) vendors.add(book.getVendorCode());
        }
        return vendors.size();
    } // result 2

    public BookList sortByBookNumAndCopyCounts() {
        Comparator<Book> comparator = Comparator.comparing(Book::getBookNum).reversed().thenComparing(Book::getCopyCount).reversed();
        BookList books = new BookList(this.books);
        books.books.sort(comparator);
        return books;
    }

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
