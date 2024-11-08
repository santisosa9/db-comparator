package models;

public class Column {
  private String name;
  private String type;
    
  public Column(String name, String type) {
    this.name = name;
    this.type = type;
  }
    
  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public void setType(String newType) {
    this.type = newType;
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
