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
}
