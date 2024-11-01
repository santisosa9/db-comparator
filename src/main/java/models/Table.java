package models;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private String type;
    private List<Column> columns;
    private List<Trigger> triggers;

    public Table(String name,String type, List<Column> columns, List<Trigger> triggers) {
        this.name = name;
        this.type = type;
        this.columns = columns;
        this.triggers = triggers;
      }

    public String getName() {
        return name;
      }

    public void setName(String newName) {
        this.name = newName;
      }  

      public String getType() {
        return type;
      }

    public void setType(String newType) {
        this.name = newType;
      } 

    public List<Column> getColumns() {
        return columns;
      }
    
      public void setColumns(List<Column> columns) {
        this.columns = columns;
      }
    
      public List<Trigger> getTriggers() {
        return triggers;
      }
    
      public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
      }   
    
      public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Table other = (Table) obj;
        return name.equals(other.getName()) 
          && columns.equals(other.getColumns()) 
          && type.equals(other.getType())
          && triggers.equals(other.getTriggers());
      }

      public String WriteDifferences(Table table2){
        String result = "Differences found: \\n";

          //Comparacion de tipos
          if(this.getType().equals(table2.getType())){
            result= result + "-Different Types \\n" + //
                              "type of the first column:" +this.getType()  + "\\n" +
                              "type of the second column:" + table2.getType();              
          }
          //Comparacion de columnas
          List<Column> columnstable1 = this.getColumns();
          List<Column> columnstable2 = table2.getColumns();
          //Columnas que comparten nombre, van de a dos. Podemos usar tuplas
          List<Column> equalNamedColumns= new ArrayList<>();
          //Tablas que NO comparten nombre, serian las que sobran.
          List<Column> notequalColumns= new ArrayList<>();
          for(Column column1: columnstable1){
            boolean equal= false;
            for(Column column2: columnstable2){
              //Si comparten nombre las agregamos a las que tienen que ser comparadas
              if(column1.getName().equals(column2.getName())){
                equalNamedColumns.add(column1);
                equalNamedColumns.add(column2);
                columnstable2.remove(column2);
                equal=true;
                break;
              } 
            }
            //Si no se encontro ningun igual lo agregamos a notequal
            if(!equal){
              notequalColumns.add(column1);
            }
          }
          //Agregamos las tablas que faltan de la db2, las que tienen nombre igual ya fueron eliminadas
          notequalColumns.addAll(columnstable2);
        
          
        
    
        return result;
      }


      public String toString() {
        String result = "{ " + "name " + name + 
                  ", type " + type +
                  ", columns ";
                  for (Column col : columns) {
                    result += col + "\n";
                  }
                  result += ", triggers " + triggers +
                  "}";

        return result;
      }


}
