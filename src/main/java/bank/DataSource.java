package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.DoubleAdder;

public class DataSource {
  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("We are connected to database");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static Customer getCustomer(String username) {
    String sqlQueryToGetCustomer = "select * from customers where username= ?";
    Customer customer = null;
    try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryToGetCustomer)) {
      preparedStatement.setString(1, username);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        customer = new Customer(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId) {
    String sqlQueryToGetAccount = "select * from accounts where id= ?";
    Account account = null;
    try (Connection connection = connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryToGetAccount)) {
      preparedStatement.setInt(1, accountId);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        account = new Account(resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return account;
  }

  public static void updateAccountBalance(int accountId, double balance) {
    String sqlQueryToGetAccount = "update accounts set balance = ? where id = ?";
    try (
      Connection connection = connect();
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryToGetAccount);
    ){
      preparedStatement.setDouble(1, balance);
      preparedStatement.setInt(2, accountId);
      preparedStatement.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    
  }
}
