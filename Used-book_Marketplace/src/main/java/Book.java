public class Book {

    // private variables of the information of a book
    private String sellerId;
    private String ISBN;
    private String name;
    private String author;
    private String publisher;
    private String year;
    private String cost;
    private String status;

    // make setters, getters of private variables of the information of a book
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public String getSellerId() {
        return sellerId;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getISBN() {
        return ISBN;
    }

    public void setName(String bookName) {
        this.name = bookName;
    }
    public String getName() {
        return name;
    }

    public void setAuthor(String bookAuthor) {
        this.author = bookAuthor;
    }
    public String getAuthor() {
        return author;
    }

    public void setPublisher(String bookPublisher) {
        this.publisher = bookPublisher;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setYear(String bookYear) {
        this.year = bookYear;
    }
    public String getYear() {
        return year;
    }

    public void setCost(String bookCost) {
        this.cost = bookCost;
    }
    public String getCost() {
        return cost;
    }

    public void setStatus(String bookStatus) {
        this.status = bookStatus;
    }
    public String getStatus() {
        return status;
    }

    // make a form of the information of a book
    public String information() {
        return (sellerId + ":" + ISBN + ":" + name + ":" + author + ":" + publisher + ":" + year + ":" + cost + ":" + status);
    }
    public void printBookInformation() {
        System.out.println(this.information());
    }
}
