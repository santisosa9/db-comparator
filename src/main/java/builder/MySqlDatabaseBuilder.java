package builder;

import connection.DatabaseConnection;
import connection.MySqlDatabaseConnection;

import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDatabaseBuilder implements DatabaseBuilder {

  private Database database;
  private DatabaseConnection dbConnection;

  public MySqlDatabaseBuilder(String username, String password, String host, String name) {
    this.dbConnection = new MySqlDatabaseConnection(username, password, host, name); 
    this.reset();
  }

  @Override
  public void reset() {
    this.database = new Database();
  }

  @Override
  public void setTables() {
    // Aca va la lógica para obtener tablas en mysql
    // this.database.setTables(tables);
  }

  @Override
  public void setTriggers() {
    try (Connection connection = dbConnection.connect()) {
      String query = "SELECT TRIGGER_NAME, EVENT_MANIPULATION AS TRIGGER_EVENT, ACTION_TIMING AS TRIGGER_TIMING FROM information_schema.TRIGGERS WHERE TRIGGER_SCHEMA = ?";

      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, connection.getCatalog()); // Aca le asigna el nombre de la db a la consulta
        ResultSet resultSetTriggers = preparedStatement.executeQuery();

        List<Trigger> result = new ArrayList<Trigger>();
        
        while (resultSetTriggers.next()) {
            String triggerName = resultSetTriggers.getString("TRIGGER_NAME");
            String triggerEvent = resultSetTriggers.getString("TRIGGER_EVENT");
            String triggerTiming = resultSetTriggers.getString("TRIGGER_TIMING");
            
            Trigger trigger = new Trigger(triggerName, triggerEvent, triggerTiming);
            result.add(trigger);
        }

        resultSetTriggers.close();
        this.database.setTriggers(result);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setProcedures() {
    // Aca va la lógica para obtener los procedimientos en mysql
    // this.database.setProcedures(procedures);
  }

  @Override
  public Database build() {
    setTables();
    setTriggers();
    setProcedures();
    Database result = this.database;
    this.reset(); 
    return result;
  }

}
