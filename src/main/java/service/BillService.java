package service;

import model.Bill;
import model.Cake;
import model.Category;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String INSERT_BILL_SQL = "insert into BILL (BILLDATE, CUSTOMERID) Values (?,?);";
    private static final String SELECT_BILL_BY_ID = "select * from BILL where BILLid = ?";
    private static final String SELECT_ALL_BILL = "select * from BILL";
    private static final String DELETE_BILL_SQL = "DELETE FROM BILL WHERE BILLID =?";
    private static final String UPDATE_BILLS_SQL = "UPDATE BILL SET BILLDATE=?, customerId=? WHERE BILLID=?";

    private static final String SELECT_CUSTOMER_BY_ID = "select * from CUSTOMERS where customerid = ?";


    public BillService() {

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

    public void insertBill(Bill bill) throws SQLException {
        Connection conection = getConnection();

        PreparedStatement preparedStatement = conection.prepareStatement(INSERT_BILL_SQL);
        preparedStatement.setTimestamp(1, bill.getBillDate());
        preparedStatement.setInt(2, bill.getCustomer().getCustomerId());
        preparedStatement.executeUpdate();
    }


    public Bill selectBill(int id) {
        Bill bill = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILL_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

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


    public List<Bill> selectAllBills() {
        List<Bill> billList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BILL);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("billId");
                Timestamp billDate = rs.getTimestamp("billDate");
                int customerId = rs.getInt("customerId");

                Customer customer = findCustomerById(customerId);
                billList.add(new Bill(id, billDate,customer));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return billList;
    }


    public boolean deleteBill(int id) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BILL_SQL);
        preparedStatement.setInt(1, id);
        rowDelete = preparedStatement.executeUpdate() >0;
        return rowDelete;
    }


    public boolean updateBill(Bill bill) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BILLS_SQL);
        preparedStatement.setTimestamp(1,  bill.getBillDate());
        preparedStatement.setInt(2, bill.getCustomer().getCustomerId());
        preparedStatement.setInt(3, bill.getBillId());

        rowUpdate = preparedStatement.executeUpdate()>0;
        return rowUpdate;
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
