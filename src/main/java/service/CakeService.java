package service;

import model.Cake;
import model.Category;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class CakeService  {

    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String INSERT_CAKE_SQL = "insert into cakeS (name, size, expiredate, quantity, price, image, categoryname) Values (?,?,?,?,?,?,?);";
    private static final String SELECT_CAKE_BY_ID = "select * from cakes where cakeid = ?";
    private static final String SELECT_ALL_CAKES = "select * from cakes";
    private static final String DELETE_CAKE_SQL = "DELETE FROM cakes WHERE cakeID =?";
    private static final String UPDATE_CAKES_SQL = "UPDATE cakes SET NAME=?,size=?,expireDate=?, quantity=?, price=?, image=?, categoryname=? WHERE cakeID=?";
    private static final String UPDATE_CAKES_WHEN_BUY = "UPDATE cakes SET quantity=? WHERE cakeID=?";

    private static final String SELECT_CATEGORY_BY_ID = "select * from category where categoryName = ?";


    public CakeService() {

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



    public void insertCake(Cake cake) throws SQLException {
        System.out.println(INSERT_CAKE_SQL);

        Connection conection = getConnection();
        PreparedStatement preparedStatement = conection.prepareStatement(INSERT_CAKE_SQL);

        preparedStatement.setString(1, cake.getName());
        preparedStatement.setString(2, cake.getSize());
        preparedStatement.setTimestamp(3, cake.getExpireDate());
        preparedStatement.setInt(4, cake.getQuantity());
        preparedStatement.setInt(5, cake.getPrice());
        preparedStatement.setString(6, cake.getImage());
        preparedStatement.setString(7, cake.getCategory().getCategoryName());

        System.out.println(preparedStatement);
        int rs = preparedStatement.executeUpdate();

        System.out.println("inserted: " + rs);
    }



    public Cake selectCake(int id) {
        Cake cake = null;

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAKE_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

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


    public List<Cake> selectAllCakes() {
        List<Cake> cakeList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CAKES);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("cakeid");
                String name = rs.getString("name");
                String size = rs.getString("size");
                Timestamp expireDate = rs.getTimestamp("expireDate");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String categoryName = rs.getString("categoryName");

                Category category = new Category(categoryName);
                cakeList.add(new Cake(id, name, size, expireDate, quantity, price, image, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cakeList;
    }


    public boolean deleteCake(int id) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAKE_SQL);
        preparedStatement.setInt(1, id);
        rowDelete = preparedStatement.executeUpdate() >0;
        return rowDelete;
    }


    public boolean updateCake(Cake cake) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAKES_SQL);
        preparedStatement.setString(1, cake.getName());
        preparedStatement.setString(2, cake.getSize());
        preparedStatement.setTimestamp(3, cake.getExpireDate());
        preparedStatement.setInt(4, cake.getQuantity());
        preparedStatement.setInt(5, cake.getPrice());
        preparedStatement.setString(6, cake.getImage());
        preparedStatement.setString(7, cake.getCategory().getCategoryName());
        preparedStatement.setInt(8, cake.getCakeId());
        System.out.println(preparedStatement.toString());
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

    public boolean updateCakeWhenBuy(Cake cake, int quantity) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAKES_WHEN_BUY);
        preparedStatement.setInt(1, quantity);
        preparedStatement.setInt(2, cake.getCakeId());

        rowUpdate = preparedStatement.executeUpdate()>0;
        return rowUpdate;
    }

    public List<Cake> searchCake(String search){
        List<Cake> cakeList= new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("select * from cakes where Name like concat('%',?,'%');");
            preparedStatement.setString(1, search);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("cakeid");
                String name = rs.getString("name");
                String size = rs.getString("size");
                Timestamp expireDate = rs.getTimestamp("expireDate");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                String image = rs.getString("image");
                String categoryName = rs.getString("categoryName");

                Category category = new Category(categoryName);
                cakeList.add(new Cake(id, name, size, expireDate, quantity, price, image, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cakeList;
    }

}
