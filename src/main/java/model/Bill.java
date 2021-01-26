package model;

import java.sql.Timestamp;
import java.util.Date;

public class Bill {
    private Integer billId;
    private Timestamp billDate;
    private Customer customer;

    public Bill() {
    }

    public Bill(Integer billId, Timestamp billDate, Customer customer) {
        this.billId = billId;
        this.billDate = billDate;
        this.customer = customer;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Timestamp getBillDate() {
        return billDate;
    }

    public void setBillDate(Timestamp billDate) {
        this.billDate = billDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
