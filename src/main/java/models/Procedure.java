package models;
import java.util.ArrayList;

public class Procedure{

  private String nameProcedure; // Nombre del procedimiento.
  private ArrayList<Parametro> params; // Parametros del procedimiento.

  public Procedure(String name,ArrayList<Parametro> params_param){
    nameProcedure = name;
    params = params_param;
  }

  public String getNameProcedure(){
    return nameProcedure;
  }

  public void setNameProcedure(String new_name){
    nameProcedure = new_name;
  }

  public ArrayList<Parametro> getParams(){
    return params;
  }

  public void setParams(String name,String tpye,String data){
    Parametro param = new Parametro(name,tpye,data);
    params.add(param);
  }

  @Override
  public boolean equals(Object obj){
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Procedure other = (Procedure) obj;
    return nameProcedure.equals(other.getNameProcedure()) && params.equals(other.getParams());
  }

  @Override
  public String toString() {
      return "Procedure {" +
              " nameProcedure " + nameProcedure +
              ", params " + params +
              '}';
  }


}