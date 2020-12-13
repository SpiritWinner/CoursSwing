public class Book {
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
        if (ob != this) {
            if (ob == null || getClass() != ob.getClass()) return false;
            Book book = (Book) ob;
            return (bookNum == book.bookNum && vendorCode.equals(book.vendorCode)
                    && month.equals(book.month) && copyCount == book.copyCount);
        } else {
            return true;
        }
    }
}
