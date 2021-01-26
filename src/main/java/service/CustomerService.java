package service;

import model.Customer;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String INSERT_CUSTOMER_SQL = "insert into customers (name, phone, address) Values (?,?,?);";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from customers where customerid = ?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from customers";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customers WHERE customerID =?";
    private static final String UPDATE_CUSTOMERS_SQL = "UPDATE customers SEt NAME=?,phone=?, address=? WHERE customerID=?";

    public CustomerService() {

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


    public void insertCustomer(Customer customer) throws SQLException {
        Connection conection = getConnection();
        PreparedStatement preparedStatement = conection.prepareStatement(INSERT_CUSTOMER_SQL);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getPhone());
        preparedStatement.setString(3, customer.getAddress());

        preparedStatement.executeUpdate();
    }


    public Customer selectCustomer(int id) {
        Customer customer = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("PHONE");
                String address = rs.getString("address");

                customer = new Customer(id, name, phone, address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }


    public List<Customer> selectAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("customerid");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                customerList.add(new Customer(id, name, phone, address));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }


    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL);
        preparedStatement.setInt(1, id);
        rowDelete = preparedStatement.executeUpdate() > 0;
        return rowDelete;
    }


    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);
        preparedStatement.setInt(4, customer.getCustomerId());
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getPhone());
        preparedStatement.setString(3, customer.getAddress());
        rowUpdate = preparedStatement.executeUpdate() > 0;
        return rowUpdate;
    }

}
