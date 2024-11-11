package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.Config;

public class MySqlDatabaseConnection implements DatabaseConnection {
  private String username;
  private String password;
  private String host;
  private String name;

  public MySqlDatabaseConnection(String username, String password, String host, String name) {
    this.username = username;
    this.password = password;
    this.host = host;
    this.name = name;
  }

  public Connection connect() throws SQLException {

    Config config = new Config("config/mysql.properties");

    try {
      String driver = config.getDriver();
      Class.forName(driver); 
    } catch (ClassNotFoundException e) {
      throw new SQLException("Driver not found: " + e.getMessage());
    }
    
    String url = config.getBaseURL() + host + '/' + name;

    System.out.println(url);
    return DriverManager.getConnection(url, username, password);
  }
}