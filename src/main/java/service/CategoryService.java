package service;

import model.Cake;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String INSERT_CATEGORY_SQL = "insert into CATEGORY ( CATEGORYname) Values (?);";
    private static final String SELECT_CATEGORY_BY_NAME = "select * from CATEGORY where caTEGORYNAME = ?";
    private static final String SELECT_ALL_CATEGORY = "select * from CATEGORY";
    private static final String DELETE_CATEGORYS_SQL = "DELETE FROM CATEGORY WHERE CATEGORYNAME =?";
//    private static final String UPDATE_CATEGORY_SQL = "UPDATE CATEGORY SET CATEGORYNAME=? WHERE categoryid=?";

    public CategoryService() {

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



    public void insertCategory(Category category) throws SQLException {
        System.out.println(INSERT_CATEGORY_SQL);

        Connection conection = getConnection();
        PreparedStatement preparedStatement = conection.prepareStatement(INSERT_CATEGORY_SQL);

        preparedStatement.setString(1, category.getCategoryName());

        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();
    }


    public Category selectCategory(String name) {
        Category category = null;

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME);
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                category = new Category(name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }


    public List<Category> selectAllCategorys() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name = rs.getString("categoryname");
                categoryList.add(new Category(name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categoryList;
    }


    public boolean deleteCategory(String name) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORYS_SQL);
        preparedStatement.setString(1, name);
        rowDelete = preparedStatement.executeUpdate() >0;
        return rowDelete;
    }


//    public boolean updateCategory(Category category) throws SQLException {
//        boolean rowUpdate;
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL);
//        preparedStatement.setString(1, category.getCatgoryName());
//        preparedStatement.setInt(2, category.getCategoryId());
//
//        rowUpdate = preparedStatement.executeUpdate()>0;
//        return rowUpdate;
//    }

}
