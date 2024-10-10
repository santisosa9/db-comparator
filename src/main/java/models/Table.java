package models;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private String type;
    private List<Column> columns;
    private List<Trigger> triggers;

    public Table(String name,String type, List<Column> columns,List<Trigger> triggers) {
        this.name = name;
        this.type = type;
        this.columns = new ArrayList<Column>();;
        this.triggers = new ArrayList<Trigger>();;
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
    
      public boolean equals(Table other) {
        return name.equals(other.getName()) 
          && columns.equals(other.getColumns()) 
          && triggers.equals(other.getTriggers());
      }

}
