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

  public String WriteDifferences(Table other) {
    String result = "";
    if (!type.equals(other.getType())) {
      result += "Different Types \n" + 
                "* Type of the first column: " + type  + "\n" +
                "* Type of the second column: " + other.getType() + "\n";              
    }
    result += getColumnsDifference(other);
    result += getTriggersDifference(other);
    return result;
  }

  private String getTriggersDifference(Table other) {
    List<Trigger> otherTriggers = new ArrayList<Trigger>(other.getTriggers());
    List<Par<Trigger,Trigger>> equalNamedTriggers = new ArrayList<Par<Trigger, Trigger>>();
    List<Trigger> differentTriggers1 = new ArrayList<Trigger>();
    List<Trigger> differentTriggers2 = new ArrayList<Trigger>();

    boolean equal;
    for (Trigger t1: triggers) {
      equal = false;
      for (Trigger t2 : otherTriggers) {
        if (t1.getName().equals(t2.getName())) {
          Par<Trigger, Trigger> par = new Par<Trigger,Trigger>(t1, t2);
          equalNamedTriggers.add(par);
          otherTriggers.remove(t2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentTriggers1.add(t1);
      }
    }
    differentTriggers2.addAll(otherTriggers);

    String result = "";

    
    if (differentTriggers1.size() > 0) {
      result += "Triggers sobrantes de la tabla " + name + ": \n";
      for (Trigger t : differentTriggers1) {
        result += "* " + t.getName() + "\n";
      }
    }

    for(Par<Trigger,Trigger> par : equalNamedTriggers){
      result += par.primero().WriteDifferences(par.segundo());
    }

    if (differentTriggers2.size() > 0) {
      result += "Triggers sobrantes de la tabla " + other.getName() + ": \n";
      for (Trigger t : differentTriggers2) {
        result += "* " + t.getName() + "\n";
      }
    }
    
    return result;
  }

  private String getColumnsDifference(Table other) {
    List<Column> otherColumns = new ArrayList<Column>(other.getColumns());
    List<Par<Column, Column>> equalNamedColumns = new ArrayList<Par<Column, Column>>();
    List<Column> differentColumns1 = new ArrayList<Column>();
    List<Column> differentColumns2 = new ArrayList<Column>();

    boolean equal;
    for (Column c1 : columns) {
      equal = false;
      for (Column c2 : otherColumns) {
        if (c1.getName().equals(c2.getName())) {
          Par<Column, Column> columnPair = new Par<Column,Column>(c1, c2);
          equalNamedColumns.add(columnPair);
          otherColumns.remove(c2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentColumns1.add(c1);
      }
    }
    differentColumns2.addAll(otherColumns);

    String result = "";

    if (differentColumns1.size() > 0) {
      result += "Columnas sobrantes de la tabla " + name + ": \n";
      for (Column c : differentColumns1) {
        result += "* " + c.getName() + "\n";
      }
    }

    for(Par<Column,Column> columnPair : equalNamedColumns){
      result += columnPair.primero().WriteDifferences(columnPair.segundo());
    }

    if (differentColumns2.size() > 0) {
      result += "Columnas sobrantes de la tabla " + other.getName() + ": \n";
      for (Column c : differentColumns2) {
        result += "* " + c.getName() + "\n";
      }
    }
    
    return result;
  }

}
