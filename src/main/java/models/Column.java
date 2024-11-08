package models;

public class Column {
    private String name;
    private String type;
    private String columnKey; //PRI: Primary Key, UNI: UNIQUE, MUL: Foreign key or multiple index, null: No key constraint
    
    public Column(String name, String type,String columnKey) {
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


  public void setName(String newName) {
    this.name = newName;
  }

  public void setType(String newType) {
    this.type = newType;
  }


  public void setColumnKey(String columnKey) {
    this.columnKey = columnKey;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Column other = (Column) obj;
    return name.equals(other.getName()) && type.equals(other.getType());
  }

  public String WriteDifferences(Column other) {
    String result = "";

    if (!type.equals(other.getType())) {
      result += "Different Types \n" + 
                "   * Type of the first column:" + type + "\n" +
                "   * Type of the second column:" + other.getType() + "\n";               
    }

    return result;
  }

}
