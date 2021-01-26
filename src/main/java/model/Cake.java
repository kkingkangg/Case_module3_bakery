package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class Cake {
    private Integer cakeId;
    private String name;
    private String size;
    private Timestamp expireDate;
    private int quantity;
    private int price;
    private String image;
    private Category category;

    public Cake() {
    }

    public int getCakeId() {
        return cakeId;
    }

    public Cake(Integer cakeId, String name, String size, Timestamp expireDate, int quantity, int price, String image, Category category) {
        this.cakeId = cakeId;
        this.name = name;
        this.size = size;
        this.expireDate = expireDate;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public void setCakeId(Integer cakeId) {
        this.cakeId = cakeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Timestamp getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Timestamp expireDate) {
        this.expireDate = expireDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}