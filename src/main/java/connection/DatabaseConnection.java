package connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

  public Connection connect() throws SQLException;
  
}
