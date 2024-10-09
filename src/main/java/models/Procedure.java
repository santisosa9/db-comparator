package models;
import java.util.ArrayList;

public class Procedure{

  private String nameProcedure; // Nombre del procedimiento.
  private ArrayList<Parametro<String,Integer,String>> params; // Parametros del procedimiento.

  public Procedure(String name){
    nameProcedure = name;
    params = new  ArrayList<Parametro<String,Integer,String>>();
  }

  public String getNameProcedure(){
    return nameProcedure;
  }

  public void setNameProcedure(String new_name){
    nameProcedure = new_name;
  }

  public ArrayList<Parametro<String,Integer,String>> getParams(){
    return params;
  }

  public void setParams(String name,Integer tpye,String data){
    Parametro<String,Integer,String> param = new Parametro<>(name,tpye,data);
    params.add(param);
  }


  public boolean equals(Procedure other){
    if(other == null) return false;
    if(getNameProcedure() == other.getNameProcedure() && equalsParams(other.getParams())){
      return true;
    }
    return false;
  }

  public boolean equalsParams(ArrayList<Parametro<String,Integer,String>> other_params){

    for(int i = 0; i < params.size();i++){
      if(!params.get(i).getName().equals(other_params.get(i).getName()) || 
          !params.get(i).getType().equals(other_params.get(i).getType()) || 
            !params.get(i).getData().equals(other_params.get(i).getData())){
            return false;
      }
    }
    return true;
  }

}