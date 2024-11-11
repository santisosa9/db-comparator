package models;

import java.util.ArrayList;
import java.util.List;

import utils.Par;

public class Table {
  private String schema;
  private String name;
  private String type;
  private List<Column> columns;
  private List <Column> PrimaryKeyColumns;
  private List <Column> UniqueKeyColumns;
  private List <Column> ForeignKeyColumns;
  private List<Index> indexs;
  private List<Trigger> triggers;

  public Table(String schema, String name, String type, List<Column> columns, List<Trigger> triggers, List<Column> PrimarykeyColumns, List<Column> UniqueKeyColumns, List<Column> ForeignKeyColumns, List<Index> indexs) {
    this.schema = schema;
    this.name = name;
    this.type = type;
    this.columns = columns;
    this.triggers = triggers;
    this.PrimaryKeyColumns = PrimarykeyColumns;
    this.UniqueKeyColumns = UniqueKeyColumns;
    this.ForeignKeyColumns = ForeignKeyColumns;
    this.indexs = indexs;
  }

  public List<Column> getPrimaryKeyColumns() {
    return PrimaryKeyColumns;
  }

  public List<Column> getUniqueKeyColumns() {
    return UniqueKeyColumns;
  }

  public List<Column> getForeignKeyColumns() {
    return ForeignKeyColumns;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public List<Column> getColumns() {
    return columns;
  }

  public List<Trigger> getTriggers() {
    return triggers;
  }

  public List<Index> getIndexs() {
    return indexs;
  }

  public String getSchema() {
    return schema;
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
    result += getColumnsDifference(other);
    result += getTriggersDifference(other);
    result += getPrimaryKeysDifference(other);
    result += getUniqueKeysDifference(other);
    result += getForeignKeysDifference(other);
    result += getIndexsDifference(other);
    return result;
  }

  private String getPrimaryKeysDifference(Table other){
    List<Column> otherPrimaryKeyColumns = new ArrayList<Column>(other.getPrimaryKeyColumns());
    List<Par<Column,Column>> equalNamedPrimaryKeys = new ArrayList<Par<Column, Column>>();
    List<Column> differentPrimaryKeys1 = new ArrayList<Column>();
    List<Column> differentPrimaryKeys2 = new ArrayList<Column>();
    
    boolean equal;
    for (Column c1: PrimaryKeyColumns) {
      equal = false;
      for (Column c2 : otherPrimaryKeyColumns) {
        if (c1.getName().equals(c2.getName())) {
          Par<Column, Column> par = new Par<Column,Column>(c1, c2);
          equalNamedPrimaryKeys.add(par);
          otherPrimaryKeyColumns.remove(c2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentPrimaryKeys1.add(c1);
      }
    }
    differentPrimaryKeys2.addAll(otherPrimaryKeyColumns);
    
    String result = "";

    if (differentPrimaryKeys1.size() > 0) {
      result += "**Claves primarias adicionales en `" + schema + "." + name + "`:** \n";
      for (Column c : differentPrimaryKeys1) {
        result += "   * " + c.getName() + "\n";
      }
      result = result + "\n";
    }

    if (differentPrimaryKeys2.size() > 0) {
      result += "**Claves primarias adicionales en `" + other.getSchema() + "." + other.getName() + "`:** \n";
      for (Column c : differentPrimaryKeys2) {
        result += "   * " + c.getName() + "\n";
      }
      result = result + "\n";
    }

    for(Par<Column,Column> keysPair : equalNamedPrimaryKeys){
      result += keysPair.primero().WriteDifferences(keysPair.segundo(), "clave primaria");
    }

    return result;
  }

  private String getUniqueKeysDifference(Table other){
    List<Column> otherUniqueKeyColumns = new ArrayList<Column>(other.getUniqueKeyColumns());
    List<Par<Column,Column>> equalNamedUniqueKeys = new ArrayList<Par<Column, Column>>();
    List<Column> differentUniqueKeys1 = new ArrayList<Column>();
    List<Column> differentUniqueKeys2 = new ArrayList<Column>();
    
    boolean equal;
    for (Column c1: UniqueKeyColumns) {
      equal = false;
      for (Column c2 : otherUniqueKeyColumns) {
        if (c1.getName().equals(c2.getName())) {
          Par<Column, Column> par = new Par<Column,Column>(c1, c2);
          equalNamedUniqueKeys.add(par);
          otherUniqueKeyColumns.remove(c2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentUniqueKeys1.add(c1);
      }
    }
    differentUniqueKeys2.addAll(otherUniqueKeyColumns);
    
    String result = "";

    if (differentUniqueKeys1.size() > 0) {
      result += "**Claves únicas adicionales en `" + schema + "." + name + "`:** \n";
      for (Column c : differentUniqueKeys1) {
        result += "   * " + c.getName() + "\n";
      }
    }

    result = result + "\n";

    if (differentUniqueKeys2.size() > 0) {
      result += "**Claves únicas adicionales en `" + other.getSchema() + "." + other.getName() + "`:** \n";
      for (Column c : differentUniqueKeys2) {
        result += "   * " + c.getName() + "\n";
      }
    }

    for(Par<Column,Column> keysPair : equalNamedUniqueKeys){
      result += keysPair.primero().WriteDifferences(keysPair.segundo(), "clave única");
    }

    return result;
  }

  private String getForeignKeysDifference(Table other){
    List<Column> otherForeignKeyColumns = new ArrayList<Column>(other.getForeignKeyColumns());
    List<Par<Column,Column>> equalNamedForeignKeys = new ArrayList<Par<Column, Column>>();
    List<Column> differentForeignKeys1 = new ArrayList<Column>();
    List<Column> differentForeignKeys2 = new ArrayList<Column>();
    
    boolean equal;
    for (Column c1: ForeignKeyColumns) {
      equal = false;
      for (Column c2 : otherForeignKeyColumns) {
        if (c1.getName().equals(c2.getName())) {
          Par<Column, Column> par = new Par<Column,Column>(c1, c2);
          equalNamedForeignKeys.add(par);
          otherForeignKeyColumns.remove(c2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentForeignKeys1.add(c1);
      }
    }
    differentForeignKeys2.addAll(otherForeignKeyColumns);
    
    String result = "";

    if (differentForeignKeys1.size() > 0) {
      result += "**Claves foraneas adicionales en `" + schema + "." + name + "`:** \n";
      for (Column c : differentForeignKeys1) {
        result += "   * " + c.getName() + "\n";
      }
    }

    result = result + "\n";

    if (differentForeignKeys2.size() > 0) {
      result += "**Claves foraneas adicionales en `" + other.getSchema() + "." + other.getName() + "`:** \n";
      for (Column c : differentForeignKeys2) {
        result += "   * " + c.getName() + "\n";
      }
    }

    for(Par<Column,Column> keysPair : equalNamedForeignKeys){
      result += keysPair.primero().WriteDifferences(keysPair.segundo(), "clave foranea");
    }

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
      result += "**Triggers adicionales en `" + schema + "." + name + "`:** \n";
      for (Trigger t : differentTriggers1) {
        result += "   * " + t.getName() + "\n";
      }
    }

    result = result + "\n";

    if (differentTriggers2.size() > 0) {
      result += "**Triggers adicionales en `" + other.getSchema() + "." + other.getName() + "`:** \n";
      for (Trigger t : differentTriggers2) {
        result += "   * " + t.getName() + "\n";
      }
    }

    for(Par<Trigger,Trigger> par : equalNamedTriggers){
      result += par.primero().WriteDifferences(par.segundo());
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
      result += "**Columnas adicionales en `" + schema + "." + name + "`:** \n";
      for (Column c : differentColumns1) {
        result += "   * " + c.getName() + "\n";
      }
    }

    result = result + "\n";

    if (differentColumns2.size() > 0) {
      result += "**Columnas adicionales en `" + other.getSchema() + "." + other.getName() + "`:** \n";
      for (Column c : differentColumns2) {
        result += "   * " + c.getName() + "\n";
      }
    }

    for(Par<Column,Column> columnPair : equalNamedColumns){
      result += columnPair.primero().WriteDifferences(columnPair.segundo(), "columna");
    }
    
    return result;
  }

  private String getIndexsDifference(Table other) {
    List<Index> otherIndexs = new ArrayList<Index>(other.getIndexs());
    List<Par<Index,Index>> equalNamedIndexs = new ArrayList<Par<Index, Index>>();
    List<Index> differentIndexs1 = new ArrayList<Index>();
    List<Index> differentIndexs2 = new ArrayList<Index>();

    boolean equal;
    for (Index i1 : indexs) {
      equal = false;
      for (Index i2 : otherIndexs) {
        if (i1.getName().equals(i2.getName())) {
          Par<Index, Index> par = new Par<Index,Index>(i1, i2);
          equalNamedIndexs.add(par);
          otherIndexs.remove(i2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentIndexs1.add(i1);
      }
    }
    differentIndexs2.addAll(otherIndexs);

    String result = "";

    if (differentIndexs1.size() > 0) {
      result += "**Indices adicionales en `" + schema + "." + name + "`**: \n";
      for (Index i : differentIndexs1) {
        result += "   * " + i.getName() + "\n";
      }
    }

    result = result + "\n";

    if (differentIndexs2.size() > 0) {
      result += "**Indices adicionales en `" + other.getSchema() + "." + other.getName() + "`:** \n";
      for (Index i : differentIndexs2) {
        result += "   * " + i.getName() + "\n";
      }
    }

    for (Par<Index,Index> par : equalNamedIndexs) {
      result += par.primero().WriteDifferences(par.segundo());
    }
    
    return result;
  }

}
