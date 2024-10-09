package models;

/*
 * La clase Tupla tendra la informacion de los parametros de los procedimientos.
 */
public class Parametro<A,B,C> {

  private A name_param; // Nombre del parametro.
  private B type_param; // Tipo de entrada del parametro. 
  private C data_param; // Tipo de dato del parametro.

  public Parametro(A name,B type, C data){
    name_param = name;
    type_param = type;
    data_param = data;
  }
  
  public A getName(){
    return name_param;
  }

  public B getType(){
    return type_param;
  }

  public C getData(){
    return data_param;
  }

  public void setType(A new_type){
    this.name_param = new_type;
  }

  public void setName(B new_name){
    this.type_param = new_name;
  }

  public void setData(C new_data){
    this.data_param = new_data;
  }
}
