import java.io.FileWriter;
import java.io.IOException;

import builder.DatabaseBuilder;
import builder.MySqlDatabaseBuilder;
import models.Database;

public class Main {
  
  public static void main(String[] args) {
    
    String username = "root";
    String password = "root";
    String host = "localhost:3306";
    String databaseName = "estacionamiento";

    DatabaseBuilder dbBuilder = new MySqlDatabaseBuilder();
    Database db1 = dbBuilder.build(username, password, host, databaseName);
    Database db2 = dbBuilder.build(username, password, host, "practico1");

    System.out.println(db1.equals(db2));
    try (FileWriter writer = new FileWriter("report.md")) { 
      writer.write(db1.WriteDifferences(db2));
    } catch (IOException e) {
      System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
    }

  }

}
