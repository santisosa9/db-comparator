package models;
import java.util.ArrayList;
import java.util.List;

import utils.Par;

public class Procedure{
  private String schema; // Nombre de la db a la que pertenece
  private String nameProcedure; // Nombre del procedimiento.
  private ArrayList<Parametro> params; // Parametros del procedimiento.

  public Procedure(String schema, String name,ArrayList<Parametro> params_param){
    this.schema = schema;
    nameProcedure = name;
    params = params_param;
  }

  public String getNameProcedure(){
    return nameProcedure;
  }

  public ArrayList<Parametro> getParams(){
    return params;
  }

  public String getSchema() {
    return schema;
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
    result += getParamsDifference(other);
    return result;
  }

  private String getParamsDifference(Procedure other) {
    List<Parametro> otherParams = new ArrayList<Parametro>(other.getParams());
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
      result += "- **Parametros adicionales del procedimiento `" + schema + "." + nameProcedure + "`:** \n";
      for (Parametro p : differentParams1) {
        result += "   * " + p.getName() + "\n";
      }
      result = result + "\n";
    }



    if (differentParams2.size() > 0) {
      result += "- **Parametros adicionales del procedimiento `" + other.getSchema() + "." + other.getNameProcedure() + "`:** \n";
      for (Parametro p : differentParams2) {
        result += "   * " + p.getName() + "\n";
      }
      result = result + "\n";
    }

    for (Par<Parametro,Parametro> paramPair : equalNamedParams) {
      result += paramPair.primero().WriteDifferences(paramPair.segundo());
    }

    return result;
  }

}