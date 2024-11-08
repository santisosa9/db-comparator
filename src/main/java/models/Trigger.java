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

  public String WriteDifferences(Trigger other){
    String result = "Differences found: \n";

    if (!name.equals(other.getName())) {
       result +=  "- Different Names \n" + 
                  "name of the first trigger:" + name + "\n" +
                  "name of the second trigger:" + other.getName() + "\n";
    } else {
      boolean equals = true;

      if(triggerEvent.equals(other.getTriggerEvent())){
        result += "- Different Trigger Events \n" + 
                  "event of the first trigger:" + triggerEvent + "\n" +
                  "event of the second trigger:" + other.getTriggerEvent() + "\n";
        equals = false;                  
      }
      
      if(triggerTiming.equals(other.getTriggerTiming())){
        result += "- Different Trigger Events \n" + 
                  "timing of the first trigger:" + triggerEvent + "\n" +
                  "timing of the second trigger:" + other.getTriggerEvent() + "\n";
        equals = false;                  
      }
      
      if (equals) {
        result = "No differences found";
      }
    }

    return result;
  }

  public String toString() {
    return '(' + name + ", " + triggerEvent + ", " + triggerTiming + ')';
  }

}
