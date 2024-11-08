package builder;

import models.Database;

public interface DatabaseBuilder {
  
  public void reset();

  public void setTables();
  
  public void setProcedures();
  
  public Database build(String username, String password, String host, String name);
  
}
