package models;
import java.util.ArrayList;
import java.util.List;

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


  public String WriteDifferences(Procedure other){
    String result = "Names of procedures equals with " + nameProcedure +" but in parameters" + 
                    "Differences found: \\n";

    ArrayList<Parametro> otherParams = other.params;
    List<Parametro> notEqualsParams = new ArrayList<>();

    for(Parametro param : params){
      for(Parametro otherParam : otherParams){
        if(param.equals(otherParam)){
          params.remove(param);
          otherParams.remove(otherParam);
        }
      }
    }
    notEqualsParams.addAll(params);
    notEqualsParams.addAll(otherParams);

    return result;
  }

  @Override
  public String toString() {
      return "Procedure {" +
              " nameProcedure " + nameProcedure +
              ", params " + params +
              '}';
  }


}