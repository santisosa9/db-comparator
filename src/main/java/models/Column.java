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
        this.type= newType;
      }
    
      public boolean equals(Column other) {
        if (other == null) return false; 
        return name.equals(other.getName()) && type.equals(other.getType());
      }

}
