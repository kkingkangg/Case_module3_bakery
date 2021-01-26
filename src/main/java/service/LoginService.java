package service;

import model.Cake;
import model.Category;
import model.Login;

import java.sql.*;

public class LoginService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bakery?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String SELECT_LOGIN_BY_NAME = "select * from LOGIN where userName = ?";

    public LoginService() {
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

    public Login selectLogin(String name) {
        Login login = null;

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOGIN_BY_NAME);
            preparedStatement.setString(1, name);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String password = rs.getString("password");
                login =  new Login(name, password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return login;
    }

}
