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
    String result = "";
    result += getParamsDifference(other.getParams());
    return result;
  }

  private String getParamsDifference(List<Parametro> _otherParams) {
    List<Parametro> otherParams = new ArrayList<Parametro>(_otherParams);
    List<Par<Parametro,Parametro>> equalNamedParams = new ArrayList<Par<Parametro,Parametro>>();
    List<Parametro> differentParams1 = new ArrayList<Parametro>();
    List<Parametro> differentParams2 = new ArrayList<Parametro>();

    boolean equal;
    for (Parametro p1 : params) {
      equal = false;
      for (Parametro p2 : otherParams) {
        if (p1.getName().equals(p2.getName())) {
          Par<Parametro,Parametro> paramPair = new Par<Parametro,Parametro>(p1, p2);
          equalNamedParams.add(paramPair);
          otherParams.remove(p2);
          equal = true;
          break;
        }
      }
      if (!equal) {
        differentParams1.add(p1);
      }
    }
    differentParams2.addAll(otherParams);

    String result = "";
    
    if (differentParams1.size() > 0) {
      result += "Parametros sobrantes del primer procedimiento: \n";
      for (Parametro p : differentParams1) {
        result += "* " + p.getName() + "\n";
      }
    }

    for (Par<Parametro,Parametro> paramPair : equalNamedParams) {
      result += paramPair.primero().WriteDifferences(paramPair.segundo());
    }

    if (differentParams2.size() > 0) {
      result += "Parametros sobrantes del segundo procedimiento: \n";
      for (Parametro p : differentParams2) {
        result += "* " + p.getName() + "\n";
      }
    }

    return result;
  }

}