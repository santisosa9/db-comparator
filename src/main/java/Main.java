import java.util.ArrayList;
import java.util.List;

import builder.DatabaseBuilder;
import builder.MySqlDatabaseBuilder;
import models.Database;
import models.Procedure;
import models.Table;
import models.Trigger;
import models.Column;

public class Main {
  
  public static void main(String[] args) {
    
    String username = "root";
    String password = "root";
    String host = "localhost:3306";
    String databaseName = "proyectobdd2";

    DatabaseBuilder dbBuilder = new MySqlDatabaseBuilder(username, password, host, databaseName);
    DatabaseBuilder dbBuilder2 = new MySqlDatabaseBuilder(username, password, host, "bdd2_ejercicio_1_b");
    Database db1 = dbBuilder.build();
    Database db2 = dbBuilder2.build();

    System.out.println(db1.equals(db2));

    List<Table> tablesdb1 = db1.getTables();
    List<Table> tablesdb2 = db2.getTables();
    //Tablas que comparten nombre, van de a dos. Podemos usar tuplas
    List<Table> equalnamedTables= new ArrayList<>();
    //Tablas que NO comparten nombre, serian las que sobran.
    List<Table> notequalTables= new ArrayList<>();

    for(Table tabledb1: tablesdb1){
      boolean equal= false;
      for(Table tabledb2: tablesdb2){
        //Si comparten nombre las agregamos a las que tienen que ser comparadas
        if(tabledb1.getName().equals(tabledb2.getName())){
          equalnamedTables.add(tabledb1);
          equalnamedTables.add(tabledb2);
          tablesdb2.remove(tabledb2);
          equal=true;
          break;
        } 
      }
      //Si no se encontro ningun igual lo agregamos a notequal
      if(!equal){
        notequalTables.add(tabledb1);
      }
    }
    //Agregamos las tablas que faltan de la db2, las que tienen nombre igual ya fueron eliminadas
    notequalTables.addAll(tablesdb2);
  }

}
