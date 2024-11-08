import builder.DatabaseBuilder;
import builder.MySqlDatabaseBuilder;
import models.Database;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
  
  public static void main(String[] args) {
    
    String username = "santi";
    String password = "pass";
    String host = "192.168.1.7:3306";
    String databaseName = "bdd2_ejercicio_1";

    DatabaseBuilder dbBuilder = new MySqlDatabaseBuilder();
    Database db1 = dbBuilder.build(username, password, host, databaseName);
    Database db2 = dbBuilder.build(username, password, host, "prueba");

    System.out.println(db1.equals(db2));
    try (FileWriter writer = new FileWriter("report.txt")) { 
      writer.write(db1.WriteDifferences(db2));
    } catch (IOException e) {
      System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
    }

  }

}
