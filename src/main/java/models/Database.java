package models;

import java.util.ArrayList;
import java.util.List;

import utils.Par;

public class Database {

  private String name;
  private List<Table> tables;
  private List<Procedure> procedures;

  public Database() {
    tables = new ArrayList<Table>();
    procedures = new ArrayList<Procedure>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Table> getTables() {
    return tables;
  }

  public void setTables(List<Table> tables) {
    this.tables = tables;
  }

  public List<Procedure> getProcedures() {
    return procedures;
  }

  public void setProcedures(List<Procedure> procedures) {
    this.procedures = procedures;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Database other = (Database) obj;
    return tables.equals(other.getTables()) 
      && procedures.equals(other.getProcedures());
  }

  public String WriteDifferences(Database other) {
    String result = "";
    result += getTablesDifference(other);
    result += getProceduresDifference(other);
    return result;
  }

  private String getTablesDifference(Database other) {
    List<Table> otherTables = new ArrayList<Table>(other.getTables());
    List<Par<Table,Table>> equalNamedTables = new ArrayList<Par<Table,Table>>();
    List<Table> differentTables1 = new ArrayList<Table>();
    List<Table> differentTables2 = new ArrayList<Table>();

    boolean equal;
    for (Table t1 : tables) {
      equal = false;
      for (Table t2 : otherTables) {
        if (t1.getName().equals(t2.getName())) {
          Par<Table,Table> par = new Par<Table,Table>(t1, t2);
          equalNamedTables.add(par);
          otherTables.remove(t2);
          equal = true;
          break;
        } 
      }
      if (!equal) {
        differentTables1.add(t1);
      }
    }
    differentTables2.addAll(otherTables);
    
    String result = "\n";
    
    if (differentTables1.size() > 0) {
      result += "**Tablas adicionales en `" + name + "`:** \n";
      for (Table t : differentTables1) {
        result += "   * " + t.getName() + "\n";
      }
      result = result + "\n";
    }

    if (differentTables2.size() > 0) {
      result += "**Tablas adicionales en `" + other.getName() + "`:** \n";
      for (Table t : differentTables2) {
        result += "   * " + t.getName() + "\n";
      }
      result = result + "\n";
    }

    for (Par<Table,Table> tablePair : equalNamedTables) {
      result += tablePair.primero().WriteDifferences(tablePair.segundo());
    }

    return result;
  }

  private String getProceduresDifference(Database other) {
    List<Procedure> otherProcedures = new ArrayList<Procedure>(other.getProcedures());
    List<Par<Procedure,Procedure>> equalNamedProcedures = new ArrayList<Par<Procedure,Procedure>>();
    List<Procedure> differentProcedures1 = new ArrayList<Procedure>();
    List<Procedure> differentProcedures2 = new ArrayList<Procedure>();

    boolean equal;
    for (Procedure p1 : procedures) {
      equal = false;
      for (Procedure p2 : otherProcedures) {
        if (p1.getNameProcedure().equals(p2.getNameProcedure())) {
          Par<Procedure,Procedure> par = new Par<Procedure,Procedure>(p1, p2);
          equalNamedProcedures.add(par);
          otherProcedures.remove(p2);
          equal = true;
          break;
        }
      }
      if (!equal) {
        differentProcedures1.add(p1);
      }
    }
    differentProcedures2.addAll(otherProcedures);

    String result = "\n";

    if (differentProcedures1.size() > 0) {
      result += "**Procedimientos adicionales en `" + name + "`:** \n";
      for (Procedure p : differentProcedures1) {
        result += "   * " + p.getNameProcedure() + "\n";
      }
      result = result + "\n";
    }

    if (differentProcedures2.size() > 0) {
      result += "**Procedimientos adicionales en `" + other.getName() + "`:** \n";
      for (Procedure p : differentProcedures2) {
        result += "   * " + p.getNameProcedure() + "\n";
      }
      result = result + "\n";
    }

    for (Par<Procedure,Procedure> procedurePair : equalNamedProcedures) {
      result += procedurePair.primero().WriteDifferences(procedurePair.segundo());
    }

    return result;
  }

}
