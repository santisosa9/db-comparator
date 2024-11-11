import java.io.FileWriter;
import java.io.IOException;

import builder.DatabaseBuilder;
import builder.MySqlDatabaseBuilder;
import config.Config;
import models.Database;

public class Main {
  
  public static void main(String[] args) {
    Config config = new Config("config/input.properties");
    DatabaseBuilder dbBuilder = new MySqlDatabaseBuilder();
    Database db1 = dbBuilder.build(config.getUser1(), config.getPassword1(), config.getHost1(), config.getDb1());
    Database db2 = dbBuilder.build(config.getUser2(), config.getPassword2(), config.getHost2(), config.getDb2());

    try (FileWriter writer = new FileWriter("report.md")) { 
      writer.write(db1.WriteDifferences(db2));
    } catch (IOException e) {
      System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
    }

  }

}
