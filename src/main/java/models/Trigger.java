package models;

public class Trigger {
  private String name;
  private String triggerEvent;               
  private String triggerTiming; 

  public Trigger(String name, String triggerEvent, String triggerTiming) {
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

  public String toString() {
    return '(' + name + ", " + triggerEvent + ", " + triggerTiming + ')';
  }

}
