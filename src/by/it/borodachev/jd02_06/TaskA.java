package by.it.borodachev.jd02_06;

import by.it.borodachev.Calc.LogClass;

public class TaskA {
    public static void main(String[] args) {
      try {
          LogClass.getInstance().save2log("Start");
          LogClass.getInstance().save2log("Run");
          LogClass.getInstance().save2log("End");
      } catch (Exception e)
      {
          LogClass.getInstance().save2log(e.getClass()+" "+e.getMessage());
      }
    }
}
