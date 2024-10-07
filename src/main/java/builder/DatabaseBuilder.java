package builder;

import models.Database;

public interface DatabaseBuilder {
    
    public void reset();

    public void setTables();

    public void setTriggers();
    
    public void setProcedures();
    
    public Database getProduct();
}
