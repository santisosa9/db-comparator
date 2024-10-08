import builder.DatabaseBuilder;
import builder.MySqlDatabaseBuilder;
import models.Database;

public class Main {
  
  public static void main(String[] args) {
    
    String username = "santi";
    String password = "pass";
    String host = "192.168.1.7:3306";
    String databaseName = "bdd2_ejercicio_1";

    DatabaseBuilder dbBuilder = new MySqlDatabaseBuilder(username, password, host, databaseName);
    Database db = dbBuilder.build();

    System.out.println(db.getTriggers().toString());

  }

}
