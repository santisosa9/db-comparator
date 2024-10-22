package models;

public class Column {
    private String name;
    private String type;
    private String isNullable; //The metadata sets this as YES, or NO. Its easier to use as a string rather than a bool, but could be done as bool
    private String columnKey; //PRI: Primary Key, UNI: UNIQUE, MUL: Foreign key or multiple index, null: No key constraint
    private String columnDefault; // Default value for the column
    private String extra;        // Extra info like auto_increment, etc.
    
    public Column(String name, String type, String isNullable, String columnKey, String columnDefault, String extra) {
      this.name = name;
      this.type = type;
      this.isNullable = isNullable;
      this.columnKey = columnKey;
      this.columnDefault = columnDefault;
      this.extra = extra;
  }
    
  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getIsNullable() {
    return isNullable;
  }

  public String getColumnKey() {
    return columnKey;
  }

  public String getColumnDefault() {
    return columnDefault;
  }

  public String getExtra() {
    return extra;
  }


  public void setName(String newName) {
    this.name = newName;
  }

  public void setType(String newType) {
    this.type = newType;
  }

  public void setIsNullable(String isNullable) {
    this.isNullable = isNullable;
  }

  public void setColumnKey(String columnKey) {
    this.columnKey = columnKey;
  }

  public void setColumnDefault(String columnDefault) {
    this.columnDefault = columnDefault;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }


    
 @Override
 public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Column other = (Column) obj;
     return name.equals(other.getName()) &&
            type.equals(other.getType()) &&
            isNullable.equals(other.getIsNullable()) &&
            columnKey.equals(other.getColumnKey()) &&
            ((columnDefault == null && other.getColumnDefault() == null) || (columnDefault != null && columnDefault.equals(other.getColumnDefault()))) &&
            ((extra == null && other.getExtra() == null) || (extra != null && extra.equals(other.getExtra())));
  }

  @Override
    public String toString() {
        return "Column {" +
                " name " + name +
                ", type " + type +
                ", isNullable " + isNullable +
                ", columnKey " + columnKey +
                ", columnDefault " + columnDefault +
                ", extra " + extra +
                '}';
    }
}


