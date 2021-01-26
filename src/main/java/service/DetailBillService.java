package service;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailBillService {

    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String INSERT_DETAILBILL_SQL = "insert into DETAILBILL (BILLID, CAKEID, BILLQUANTITY, BILLPRICE) Values (?,?,?,?);";
    private static final String SELECT_DETAILBILL_BY_ID = "select * from DETAILBILL where DETAILid = ?";
    private static final String SELECT_ALL_DETAILBILLS = "select * from DETAILBILL";
    private static final String DELETE_DETAILBILL_SQL = "DELETE FROM DETAILBILL WHERE DETAILID =?";
    private static final String UPDATE_DETAILBILL_SQL = "UPDATE DETAILBILL SET BILLID=?, CAKEID=?, BILLQUANTITY=?, BILLPRICE=? WHERE DETAILID=?";

    private static final String SELECT_CAKE_BY_ID = "select * from CAKES where CAKEid = ?";
    private static final String SELECT_BILL_BY_ID = "select * from BILL where BILLid = ?";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from CUSTOMERS where customerid = ?";

    public DetailBillService() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }



    public void insertDetailBill(DetailBill detailBill) throws SQLException {
        System.out.println(INSERT_DETAILBILL_SQL);

        Connection conection = getConnection();
        PreparedStatement preparedStatement = conection.prepareStatement(INSERT_DETAILBILL_SQL);

        preparedStatement.setInt(1, detailBill.getBill().getBillId());
        preparedStatement.setInt(2, detailBill.getCake().getCakeId());
        preparedStatement.setInt(3, detailBill.getBillQuantity());
        preparedStatement.setInt(4, detailBill.getBillPrice());
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }


    public DetailBill selectDetailBill(int id) {
        DetailBill detailBill = null;

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DETAILBILL_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int billId = rs.getInt("billId");
                int cakeId = rs.getInt("cakeId");
                int billQuantity = rs.getInt("billQuantity");
                int billPrice = rs.getInt("billPrice");

                Bill bill = findBillById(billId);
                Cake cake = findCakeById(cakeId);
                detailBill = new DetailBill(bill, cake, billQuantity, billPrice, id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detailBill;
    }


    public List<DetailBill> selectAllDetailBill() {
        List<DetailBill> detailBillList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DETAILBILLS);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int billId = rs.getInt("billId");
                int cakeId = rs.getInt("cakeId");
                int quantity = rs.getInt("billQuantity");
                int price = rs.getInt("billprice");
                int detailId = rs.getInt("detailId");

                Bill bill = findBillById(billId);
                Cake cake = findCakeById(cakeId);
                detailBillList.add(new DetailBill(bill, cake, quantity, price, detailId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return detailBillList;
    }


    public boolean deleteDetailBill(int id) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DETAILBILL_SQL);
        preparedStatement.setInt(1, id);
        rowDelete = preparedStatement.executeUpdate() >0;
        return rowDelete;
    }


    public boolean updateDetailBill(DetailBill detailBill) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DETAILBILL_SQL);
        preparedStatement.setInt(1, detailBill.getBill().getBillId());
        preparedStatement.setInt(2, detailBill.getCake().getCakeId());
        preparedStatement.setInt(3, detailBill.getBillQuantity());
        preparedStatement.setInt(4, detailBill.getBillPrice());
        preparedStatement.setInt(5, detailBill.getDetailId());

        rowUpdate = preparedStatement.executeUpdate()>0;
        return rowUpdate;
    }


    public Cake findCakeById(int id) {
        Cake cake = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAKE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String size = rs.getString("size");
                Timestamp expireDate = rs.getTimestamp("expireDate");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String categoryName = rs.getString("categoryName");

                Category category = new Category(categoryName);
                cake = new Cake(id, name, size, expireDate, quantity, price, image, category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cake;
    }

    public Bill findBillById(int id) {
        Bill bill = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILL_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Timestamp billDate = rs.getTimestamp("billDate");
                int customerId = rs.getInt("customerId");
                Customer customer = findCustomerById(customerId);
                bill = new Bill(id, billDate, customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bill;
    }

    public Customer findCustomerById(int id) {
        Customer customer = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                customer = new Customer(id, name, phone, address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }
}
