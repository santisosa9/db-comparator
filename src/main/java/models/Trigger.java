package models;

public class Trigger {
  private String table;
  private String name;
  private String triggerEvent;               
  private String triggerTiming; 

  public Trigger(String table, String name, String triggerEvent, String triggerTiming) {
    this.table = table;
    this.name = name;
    this.triggerEvent = triggerEvent;
    this.triggerTiming = triggerTiming;
  }

  public String getName() {
    return name;
  }

  public String getTriggerEvent() {
    return triggerEvent;
  }

  public String getTriggerTiming() {
    return triggerTiming;
  }

  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Trigger other = (Trigger) obj;
    return name.equals(other.getName()) && triggerEvent.equals(other.getTriggerEvent()) && triggerTiming.equals(other.getTriggerTiming());
  }

  public String WriteDifferences(Trigger other) {
    String result = "";

    if (!triggerEvent.equals(other.getTriggerEvent())) {
      result += "El trigger '" + name + "' de la tabla '" + table + "' tiene eventos de disparo distintos: \n" + 
                "   * (" + triggerEvent + ", " + other.getTriggerEvent() + ")\n";      
    }
    
    if (!triggerTiming.equals(other.getTriggerTiming())) {
      result += "El trigger '" + name + "' de la tabla '" + table + "' tiene tiempos de disparo distintos: \n" + 
                "   * (" + triggerTiming + ", " + other.getTriggerTiming() + ")\n";                    
    }
    
    return result;
  }

}
