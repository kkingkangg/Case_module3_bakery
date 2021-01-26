package model;

public class Customer {
    private Integer customerId;
    private String name;
    private String Phone;
    private String address;

    public Customer() {
    }

    public Customer(Integer customerId, String name, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        Phone = phone;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
