public class Book {

    // private variables of the information of a book
    private String sellerId;
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private String year;
    private String price;
    private String status;

    // make setters, getters of private variables of the information of a book
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public String getSellerId() {
        return this.sellerId;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getISBN() {
        return this.ISBN;
    }

    public void setTitle(String bookTitle) {
        this.title = bookTitle;
    }
    public String getTitle() {
        return this.title;
    }

    public void setAuthor(String bookAuthor) {
        this.author = bookAuthor;
    }
    public String getAuthor() {
        return this.author;
    }

    public void setPublisher(String bookPublisher) {
        this.publisher = bookPublisher;
    }
    public String getPublisher() {
        return this.publisher;
    }

    public void setYear(String bookYear) {
        this.year = bookYear;
    }
    public String getYear() {
        return this.year;
    }

    public void setPrice(String bookPrice) {
        this.price = bookPrice;
    }
    public String getPrice() {
        return this.price;
    }

    public void setStatus(String bookStatus) {
        this.status = bookStatus;
    }
    public String getStatus() {
        return this.status;
    }

    // make a form of the information of a book
    public String information() {
        return ("* Book Title: " + title + "\n* ISBN: " + ISBN + "\n* Author: " + author + "\n* Publisher: " + publisher + "\n* Publish Year: " + year);
    }
}
