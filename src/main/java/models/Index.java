package models;

public class Index {
  private String table;
  private String name;
  private String columnName; 
  private String type;

  public Index(String table, String name, String columnName, String type) { 
    this.table = table;
    this.name = name;
    this.columnName = columnName;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public String getColumnName() {
    return columnName;
  }

  public String getType() {
    return type;
  }

  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Index other = (Index) obj;
    return name.equals(other.getName()) && 
      columnName.equals(other.getColumnName()) && 
      type.equals(other.getType());
  }

  public String WriteDifferences(Index other) {
    String result = "";

    if (!columnName.equals(other.getColumnName())) {
      result += "**El indice `" + name + "` de la tabla en común `" + table + "` esta asociado a columnas distintas:** \n" + 
              "   * ("  + columnName + ", " + other.getColumnName() + ")\n\n";       
    }

    if (!type.equals(other.getType())) {
      result += "**El indice `" + name + "` de la tabla en común `" + table + "` tiene estructuras distintas:** \n" + 
                "   * ("  + columnName + ", " + other.getColumnName() + ")\n\n";                   
    }
    
    return result;
  }

}
