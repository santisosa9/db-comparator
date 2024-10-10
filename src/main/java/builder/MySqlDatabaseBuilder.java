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
    try (Connection connection = dbConnection.connect()) {

      String query = "SELECT TABLE_NAME, TABLE_TYPE FROM information_schema.TABLES WHERE TABLE_SCHEMA = ?";  //QUERY DE TABLAS
      String columnsQuery = "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_KEY, COLUMN_DEFAULT, EXTRA " +  //QUERY DE COLUMNAS
      "FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
      try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, connection.getCatalog());
        ResultSet resultSetTables = preparedStatement.executeQuery();

        List<Table> result = new ArrayList<Table>();
        List<Column> resultColumns = new ArrayList<Column>();

        while(resultSetTables.next()){
          String tableName = resultSetTables.getString("TABLE_NAME");
          String tableType = resultSetTables.getString("TABLE_TYPE"); //Tabla base o VIEW

          
          try(PreparedStatement columnStatement = connection.prepareStatement(columnsQuery)) {
            columnStatement.setString(2, tableName); //Seteo el query para la tabla actual
            ResultSet resultSetColumns = columnStatement.executeQuery();
            while(resultSetColumns.next()){
              String columnName = resultSetColumns.getString("COLUMN_NAME");
              String dataType = resultSetColumns.getString("DATA_TYPE");
              String isNullable = resultSetColumns.getString("IS_NULLABLE");
              String columnKey = resultSetColumns.getString("COLUMN_KEY");
              String columnDefault = resultSetColumns.getString("COLUMN_DEFAULT");
              String extra = resultSetColumns.getString("EXTRA");
              Column column = new Column(columnName,dataType,isNullable,columnKey,columnDefault,extra);
              resultColumns.add(column);
            }
            resultSetColumns.close();
          }catch (SQLException e) {
            e.printStackTrace();
          }
          
          Table table = new Table(tableName,tableType,resultColumns,null);
          result.add(table);
          resultColumns.clear();
        }
        resultSetTables.close();
        this.database.setTables(result);
      }
  }catch (SQLException e) {
    e.printStackTrace();
  }  
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
