package models;

/*
 * La clase Parametro tendra la informacion de los parametros de los procedimientos.
 */
public class Parametro {
  private String procedure; // Nombre del procedimiento al que pertenece
  private String name_param; // Nombre del parametro.
  private String type_param; // Tipo de entrada del parametro. 
  private String data_param; // Tipo de dato del parametro.

  public Parametro(String procedure, String name, String type, String data) {
    this.procedure = procedure;
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

  public String WriteDifferences(Parametro other){
    String result = "";

    if (!type_param.equals(other.getType())) {
      result += "**El parametro `" + name_param + "` del procedimiento `" + procedure + "` tiene direcciones distintas:** \n" + 
                "   * ("  + type_param + ", " + other.getType() + ")\n\n";        
    }

    if (!data_param.equals(other.getData())) {
      result += "**El parametro `" + name_param + "` del procedimiento `" + procedure + "` tiene tipos distintos:** \n" + 
                "   * ("  + data_param + ", " + other.getData() + ")\n\n"; 
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
