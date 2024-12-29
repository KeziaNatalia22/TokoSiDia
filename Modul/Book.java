package Modul;

import java.util.Date;

public class Book extends Product implements Product_Interface{
    private String author, synopsis, title;
    private Date releaseDate;
    private int pageNum;

    public Book(String author, int pageNum, Date releaseDate, String synopsis, String title, String idProduct, String sellerName, String name, int stock, double discount, double price, String photoProduct) {
        super(idProduct, sellerName, name, stock, discount, price, photoProduct);
        this.author = author;
        this.pageNum = pageNum;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Product addProduct (Product product) {
        Dummy.listProducts.add(product);
        return product;
    }
}