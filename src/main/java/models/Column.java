package models;

public class Column {
  private String table;
  private String name;
  private String type;
  private String columnKey; //PRI: Primary Key, UNI: UNIQUE, MUL: Foreign key or multiple index, null: No key constraint
  
  public Column(String table, String name, String type,String columnKey) {
    this.table = table;
    this.name = name;
    this.type = type;
    this.columnKey = columnKey;
  }
    
  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getColumnKey() {
    return columnKey;
  }

  public String getTable() {
    return table;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Column other = (Column) obj;
    return name.equals(other.getName()) && type.equals(other.getType());
  }

  public String WriteDifferences(Column other, String obj) {
    String result = "";
    if (!type.equals(other.getType())) {
      result += "La " + obj + " '" + name + "' de la tabla en común '" + table + "' tiene tipos distintos: \n" + 
                "   * ("  + type + ", " + other.getType() + ")\n";              
    }
    return result;
  }

}
