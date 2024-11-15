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

  public MySqlDatabaseBuilder() {
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
        
        while(resultSetTables.next()){
          String tableName = resultSetTables.getString("TABLE_NAME");
          String tableType = resultSetTables.getString("TABLE_TYPE"); //Tabla base o VIEW
          
          List<Column> resultColumns = new ArrayList<Column>();
          List<Column> PrimarykeyColumns = new ArrayList<Column>();
          List<Column> ForeignkeyColumns = new ArrayList<Column>();
          List<Column> UniquekeyColumns = new ArrayList<Column>();
          
          try(PreparedStatement columnStatement = connection.prepareStatement(columnsQuery)) {
            columnStatement.setString(1, connection.getCatalog());
            columnStatement.setString(2, tableName); 
            ResultSet resultSetColumns = columnStatement.executeQuery();
            while(resultSetColumns.next()){
              String columnName = resultSetColumns.getString("COLUMN_NAME");
              String dataType = resultSetColumns.getString("DATA_TYPE");
              String columnKey = resultSetColumns.getString("COLUMN_KEY");
              Column column = new Column(tableName, columnName, dataType, columnKey);
              //If its foreign key
              if (columnKey.equals("MUL")){
                ForeignkeyColumns.add(column);
              }
              //If its primary key
              if (columnKey.equals("PRI")){
                PrimarykeyColumns.add(column);
              }  
              //If its Unique key
              if (columnKey.equals("UNI")){
                UniquekeyColumns.add(column);
              }    
              resultColumns.add(column);
            }
            resultSetColumns.close();
          }catch (SQLException e) {
            e.printStackTrace();
          }
          List<Trigger> triggers = getTriggers(tableName);
          List<Index> indexs = getIndexs(tableName);
          Table table = new Table(connection.getCatalog(), tableName, tableType, resultColumns, triggers, PrimarykeyColumns, UniquekeyColumns, ForeignkeyColumns, indexs);
          result.add(table);
        }
        resultSetTables.close();
        this.database.setTables(result);
      }
  }catch (SQLException e) {
    e.printStackTrace();
  }  
  }

  private List<Trigger> getTriggers(String tableName) { 
    List<Trigger> result = new ArrayList<Trigger>();
    try (Connection connection = dbConnection.connect()) {
      String query = "SELECT TRIGGER_NAME, EVENT_MANIPULATION AS TRIGGER_EVENT, ACTION_TIMING AS TRIGGER_TIMING " +
                    "FROM information_schema.TRIGGERS " +
                    "WHERE TRIGGER_SCHEMA = ? AND EVENT_OBJECT_TABLE = ?";

      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, connection.getCatalog()); 
        preparedStatement.setString(2, tableName); 

        ResultSet resultSetTriggers = preparedStatement.executeQuery();

        while (resultSetTriggers.next()) {
          String triggerName = resultSetTriggers.getString("TRIGGER_NAME");
          String triggerEvent = resultSetTriggers.getString("TRIGGER_EVENT");
          String triggerTiming = resultSetTriggers.getString("TRIGGER_TIMING");
          
          Trigger trigger = new Trigger(tableName, triggerName, triggerEvent, triggerTiming);
          result.add(trigger);
        }

        resultSetTriggers.close();
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  private List<Index> getIndexs(String tableName) { 
    List<Index> result = new ArrayList<Index>();
    try (Connection connection = dbConnection.connect()) {
      String query = "SELECT INDEX_NAME, COLUMN_NAME, INDEX_TYPE FROM information_schema.STATISTICS " +
                    "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";

      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, connection.getCatalog()); 
        preparedStatement.setString(2, tableName); 

        ResultSet resultSetIndexs = preparedStatement.executeQuery();

        while (resultSetIndexs.next()) {
          String name = resultSetIndexs.getString("INDEX_NAME");
          String columnName = resultSetIndexs.getString("COLUMN_NAME");
          String type = resultSetIndexs.getString("INDEX_TYPE");
          
          Index index = new Index(tableName, name, columnName, type);
          result.add(index);
        }

        resultSetIndexs.close();
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public void setProcedures() {
    try(Connection connection = dbConnection.connect()){
      String query = "SELECT ROUTINE_NAME FROM information_schema.ROUTINES WHERE ROUTINE_SCHEMA = ?;";
      String queryParams = "SELECT PARAMETER_NAME, PARAMETER_MODE, DATA_TYPE FROM information_schema.PARAMETERS WHERE SPECIFIC_SCHEMA = ? AND SPECIFIC_NAME = ?;";
      
      try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
        preparedStatement.setString(1, connection.getCatalog());
        ResultSet resultSetRoutine = preparedStatement.executeQuery();

        List<Procedure> list = new ArrayList<Procedure>();
        
        while(resultSetRoutine.next()){
          String routineName = resultSetRoutine.getString("ROUTINE_NAME");
          
          ArrayList<Parametro> parametros = new ArrayList<Parametro>();
          try(PreparedStatement paramStatement = connection.prepareStatement(queryParams)){
            paramStatement.setString(1,connection.getCatalog());
            paramStatement.setString(2,routineName);
            ResultSet resultSetParams = paramStatement.executeQuery();
            
            while(resultSetParams.next()){
                String nameParam = resultSetParams.getString("PARAMETER_NAME");
                String inputParam = resultSetParams.getString("PARAMETER_MODE");
                String dataParam = resultSetParams.getString("DATA_TYPE");
                Parametro param = new Parametro(routineName, nameParam, inputParam, dataParam);
                parametros.add(param);
              }
            resultSetParams.close();
            
          } catch (SQLException e){
            e.printStackTrace();
          }
          Procedure routine = new Procedure(connection.getCatalog(),routineName,parametros);
          list.add(routine);
        }
        resultSetRoutine.close();
        this.database.setProcedures(list);

      }
    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  @Override
  public Database build(String username, String password, String host, String name) {
    this.dbConnection = new MySqlDatabaseConnection(username, password, host, name); 
    setTables();
    setProcedures();
    Database result = this.database;
    this.reset(); 
    result.setName(name);
    return result;
  }

}
