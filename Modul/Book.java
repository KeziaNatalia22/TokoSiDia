package Modul;

import java.util.Date;

public class Book extends Product implements Product_Interface{
    private String author, synopsis;
    private Date releaseDate;
    private int pageNum;
    
    public Book(String name, String idProduct, double price, double discount, int stock, String author,
            String synopsis, Date releaseDate, int pageNum) {
        super(name, idProduct, price, discount, stock);
        this.author = author;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.pageNum = pageNum;
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

    @Override
    public Product addProduct (Product product) {
        Dummy.listProducts.add(product);
        return product;
    }
}