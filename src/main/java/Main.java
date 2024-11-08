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
    DatabaseBuilder dbBuilder2 = new MySqlDatabaseBuilder(username, password, host, "prueba");
    Database db1 = dbBuilder.build();
    Database db2 = dbBuilder2.build();

    System.out.println(db1.equals(db2));
    System.out.println(db1.WriteDifferences(db2));

  }

}
