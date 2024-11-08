package models;

import java.util.ArrayList;
import java.util.List;

public class Database {

  private List<Table> tables;
  private List<Procedure> procedures;

  public Database() {
    tables = new ArrayList<Table>();
    procedures = new ArrayList<Procedure>();
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
    result += getTablesDifference(other.getTables());
    result += getProceduresDifference(other.getProcedures());
    return result;
  }

  private String getTablesDifference(List<Table> _otherTables) {
    List<Table> otherTables = new ArrayList<Table>(_otherTables);
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
    
    String result = "";
    
    if (differentTables1.size() > 0) {
      result += "Tablas sobrantes de la primera db: \n";
      for (Table t : differentTables1) {
        result += "* " + t.getName() + "\n";
      }
    }
    
    for (Par<Table,Table> tablePair : equalNamedTables) {
      result += tablePair.primero().WriteDifferences(tablePair.segundo());
    }

    if (differentTables2.size() > 0) {
      result += "Tablas sobrantes de la segunda db: \n";
      for (Table t : differentTables2) {
        result += "* " + t.getName() + "\n";
      }
    }

    return result;
  }

  private String getProceduresDifference(List<Procedure> _otherProcedures) {
    List<Procedure> otherProcedures = new ArrayList<Procedure>(_otherProcedures);
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

    String result = "";

    if (differentProcedures1.size() > 0) {
      result += "Procedimientos sobrantes de la primera db: \n";
      for (Procedure p : differentProcedures1) {
        result += "* " + p.getNameProcedure() + "\n";
      }
    }
    
    for (Par<Procedure,Procedure> procedurePair : equalNamedProcedures) {
      result += procedurePair.primero().WriteDifferences(procedurePair.segundo());
    }

    if (differentProcedures2.size() > 0) {
      result += "Procedimientos sobrantes de la segunda db: \n";
      for (Procedure p : differentProcedures2) {
        result += "* " + p.getNameProcedure() + "\n";
      }
    }

    return result;
  }

}
