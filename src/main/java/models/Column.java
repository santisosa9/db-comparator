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

  public String WriteDifferences(Column column2){
    String result = "Differences found: \\n";

    if(!this.getName().equals(column2.getName())){
       result= result + "-Different Columns \\n" + //
                        "name of the first column:" +this.getName() + "\\n" +
                        "name of the second column:" + column2.getName();
    }else{
      boolean equals = true;
      //Comparacion de tipos
      if(this.getType().equals(column2.getType())){
        result= result + "-Different Types \\n" + //
                          "type of the first column:" +this.getType()  + "\\n" +
                          "type of the second column:" + column2.getType();
        equals= false;                  
      }
      //Comparacion de IsNullable
      if(this.getIsNullable().equals(column2.getIsNullable())){
        result= result + "-Different Nullable conditions \\n" + //
                          "IsNullable of the first column:" +this.getIsNullable()  + "\\n" +
                          "IsNullable of the second column:" + column2.getIsNullable();
        equals= false;                  
      }
      //Comparacion de Collumn Key
      if(this.getColumnKey().equals(column2.getColumnKey())){
        result= result + "-Different Column Key \\n" + //
                          "Key of the first column:" +this.getColumnKey()  + "\\n" +
                          "Key of the second column:" + column2.getColumnKey();
        equals= false;                  
      }
      //Comparacion de Default
      if(this.getColumnDefault().equals(column2.getColumnDefault())){
        result= result + "-Different Defaults \\n" + //
                          "Default of the first column:" +this.getColumnDefault()  + "\\n" +
                          "Default of the second column:" + column2.getColumnDefault();
        equals= false;                  
      }
      //Comparacion de extra
      if(this.getExtra().equals(column2.getExtra())){
        result= result + "-Different Extra info \\n" + //
                          "Extra of the first column:" +this.getColumnDefault()  + "\\n" +
                          "Extra of the second column:" + column2.getColumnDefault();
        equals= false;                  
      }
      
      if(equals){
        result= "No differences found";
      }
    }

    return result;
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


