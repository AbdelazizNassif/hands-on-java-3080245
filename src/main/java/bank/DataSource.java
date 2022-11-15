package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect () 
  {
    String db_file = "jdbc:sqllite:resources/bank.db";
    Connection connection = null;
    
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection(db_file);
      System.out.println("We are connected to database");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return connection;
  }
  public static void main(String[] args) {
    connect () ;
  }
}
