package models;

/*
 * La clase Parametro tendra la informacion de los parametros de los procedimientos.
 */
public class Parametro {

  private String name_param; // Nombre del parametro.
  private String type_param; // Tipo de entrada del parametro. 
  private String data_param; // Tipo de dato del parametro.

  public Parametro(String name, String type, String data) {
    name_param = name;
    type_param = type;
    data_param = data;
  }
  
  public String getName() {
    return name_param;
  }

  public String getType() {
    return type_param;
  }

  public String getData() {
    return data_param;
  }

  public void setName(String new_name) {
    this.name_param = new_name;
  }

  public void setType(String new_type) {
    this.type_param = new_type;
  }

  public void setData(String new_data) {
    this.data_param = new_data;
  }

  public String WriteDifferences(Parametro other){
    String result = "";

    if (!name_param.equals(other.getName())) {
      result += "Different Names \n" +
                "   * Name of the first parameter: " + name_param + "\n" +
                "   * Name of the second parameter: " + other.getName() + "\n";
    }

    if (!type_param.equals(other.getType())) {
      result += "Different Types \n" + 
                "   * Type of the first parameter: " + type_param + "\n" +
                "   * Type of the second pameter: " + other.getType() + "\n";
    }

    if (!data_param.equals(other.getData())) {
      result += "Different Dates \n" + 
                "   * Data of the first parameter: " + data_param + "\n" +
                "   * Data of the second pameter: " + other.getData() + "\n";
    }

    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;  // Verifica si es el mismo objeto
    if (obj == null || getClass() != obj.getClass()) return false;  // Verifica si es null o de clase diferente

    Parametro other = (Parametro) obj;  // Castea el objeto al tipo Parametro

    // Compara cada uno de los atributos para asegurarse que sean iguales
    return (name_param.equals(other.getName()))
           && (type_param.equals(other.getType()))
           && (data_param.equals(other.getData()));
  }
}
