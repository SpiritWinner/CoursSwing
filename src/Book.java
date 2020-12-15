public class Book {
    private final static String BOOK_FORMAT_STRING = "%10d | %10s | %10s | %8d |";

    private int bookNum;
    private String vendorCode;
    private String month;
    private int copyCount;

    public Book() {
        this.bookNum = 0;
        this.vendorCode = "";
        this.month = "";
        this.copyCount = 0;
    }

    public Book(int bookNum, String vendorCode, String month, int copyCount) {
        this.bookNum = bookNum;
        this.vendorCode = vendorCode;
        this.month = month;
        this.copyCount = copyCount;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(int copyCount) {
        this.copyCount = copyCount;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == this) return true;
        if (ob == null) return false;
        if (getClass() != ob.getClass()) return false;
        Book book = (Book) ob;
        return (bookNum == book.bookNum);
    }

    @Override
    public String toString() {
        return String.format(BOOK_FORMAT_STRING, bookNum, vendorCode, month, copyCount);
    }
}
