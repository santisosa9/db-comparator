import java.util.ArrayList;
import java.util.List;

import builder.DatabaseBuilder;
import builder.MySqlDatabaseBuilder;
import models.Database;
import models.Procedure;
import models.Table;
import models.Trigger;
import models.Par;

public class Main {
  
  public static void main(String[] args) {
    
    String username = "root";
    String password = "root";
    String host = "localhost:3306";
    String databaseName = "practico1";

    DatabaseBuilder dbBuilder = new MySqlDatabaseBuilder(username, password, host, databaseName);
    DatabaseBuilder dbBuilder2 = new MySqlDatabaseBuilder(username, password, host, "ciudad");
    Database db1 = dbBuilder.build();
    Database db2 = dbBuilder2.build();

    System.out.println(db1.equals(db2));

    List<Table> tablesdb1 = db1.getTables();
    List<Table> tablesdb2 = db2.getTables();
    //Tablas que comparten nombre, van de a dos. Podemos usar tuplas
    List<Par<Table,Table>> equalNamedTables = new ArrayList<Par<Table,Table>>();
    //Tablas que NO comparten nombre, serian las que sobran.
    List<Table> notequalTables= new ArrayList<>();

    for(Table tabledb1: tablesdb1){
      boolean equal= false;
      for(Table tabledb2: tablesdb2){
        //Si comparten nombre las agregamos a las que tienen que ser comparadas
        if(tabledb1.getName().equals(tabledb2.getName())){
          Par<Table,Table> par = new Par<Table,Table>(tabledb1, tabledb2);
          equalNamedTables.add(par);
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
    
    String TableComparison= "Comparacion de tablas iguales:  \\n";
    //Ciclado de tablas iguales
    for(Par<Table,Table> tables : equalNamedTables){
      TableComparison= TableComparison + tables.primero().WriteDifferences(tables.segundo());
    }

    TableComparison= TableComparison + "Tablas sobrantes: \\n";
    for(Table table : notequalTables){
      TableComparison= TableComparison + table.toString();
    }

    List<Procedure> proceduresdb1 = db1.getProcedures();
    List<Procedure> proceduresdb2 = db2.getProcedures();

    List<Par<Procedure,Procedure>> equalProcedures = new ArrayList<Par<Procedure,Procedure>>();
    List<Procedure> notEqualsProcedures = new ArrayList<>();

    for(Procedure proceduredb1 : proceduresdb1){
      boolean equal = false;
      for(Procedure proceduredb2 : proceduresdb2){
        if(proceduredb1.getNameProcedure().equals(proceduredb2.getNameProcedure())){
          Par<Procedure,Procedure> par = new Par<Procedure,Procedure>(proceduredb1, proceduredb2);
          equalProcedures.add(par);
          equal = true;
          break;
        }
      }
      if(!equal){
        notEqualsProcedures.add(proceduredb1);
      }
    }
    notEqualsProcedures.addAll(proceduresdb2);
  }

}
