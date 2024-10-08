package models;

import java.util.ArrayList;
import java.util.List;

public class Database {

  private List<Table> tables;
  private List<Trigger> triggers;
  private List<Procedure> procedures;

  public Database() {
    tables = new ArrayList<Table>();
    triggers = new ArrayList<Trigger>();
    procedures = new ArrayList<Procedure>();
  }

  public List<Table> getTables() {
    return tables;
  }

  public void setTables(List<Table> tables) {
    this.tables = tables;
  }

  public List<Trigger> getTriggers() {
    return triggers;
  }

  public void setTriggers(List<Trigger> triggers) {
    this.triggers = triggers;
  }

  public List<Procedure> getProcedures() {
    return procedures;
  }

  public void setProcedures(List<Procedure> procedures) {
    this.procedures = procedures;
  }

  public boolean equals(Database db) {
    return tables.equals(db.getTables()) 
      && triggers.equals(db.getTriggers()) 
      && procedures.equals(db.getProcedures());
  }
}
