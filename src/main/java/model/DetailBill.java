package model;

public class DetailBill {
    private Bill bill;
    private Cake cake;
    private int billQuantity;
    private int billPrice;
    private Integer detailId;


    public DetailBill() {
    }

    public DetailBill(Bill bill, Cake cake, int billQuantity, int billPrice, Integer detailId) {
        this.bill = bill;
        this.cake = cake;
        this.billQuantity = billQuantity;
        this.billPrice = billPrice;
        this.detailId = detailId;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public int getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(int billQuantity) {
        this.billQuantity = billQuantity;
    }

    public int getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(int billPrice) {
        this.billPrice = billPrice;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }
}