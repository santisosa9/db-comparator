package builder;

import models.*;

public class MySqlDatabaseBuilder implements DatabaseBuilder {

  private Database database;

  public MySqlDatabaseBuilder() {
    this.reset();
  }

  @Override
  public void reset() {
    this.database = new Database();
  }

  @Override
  public void setTables() {
    // Aca va la lógica para obtener tablas en mysql
    this.database.setTables(tables);
  }

  @Override
  public void setTriggers() {
    // Aca va la lógica para obtener los triggers en mysql
    this.database.setTriggers(triggers);
  }

  @Override
  public void setProcedures() {
    // Aca va la lógica para obtener los procedimientos en mysql
    this.database.setProcedures(procedures);
  }

  @Override
  public Database getProduct() {
    Database result = this.database;
    this.reset(); 
    return result;
  }

}
