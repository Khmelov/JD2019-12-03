package by.it.borodachev.Calc;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

abstract  class Report {
     abstract String getDate();
     abstract String getError( Exception e);
  }

  class ReportShort extends Report {
    String getDate() {
        Locale locale = LanguageManager.getLocale();
        Calendar calendar = Calendar.getInstance(locale);
        DateFormat dt = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        return dt.format(calendar.getTime());
      }
      String getError( Exception e)   {
        return String.valueOf(e.getClass());
      }
  }
class ReportLong extends Report {
    String getDate() {
        Locale locale =LanguageManager.getLocale();
        Calendar calendar = Calendar.getInstance(locale);
        DateFormat dt = DateFormat.getDateInstance(DateFormat.LONG, locale);
        return dt.format(calendar.getTime());
    }
    String getError( Exception e)   {
         String retStr;
        retStr=e.getClass()+" "+e.getMessage()+"\n";
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            if (stackTraceElement.getClassName().contains("by.it"))
            {
                retStr += "\n"+stackTraceElement.getLineNumber()+":"+stackTraceElement.getMethodName()+"->"+stackTraceElement.getClassName();
            }
        }
        return retStr;
    }
}