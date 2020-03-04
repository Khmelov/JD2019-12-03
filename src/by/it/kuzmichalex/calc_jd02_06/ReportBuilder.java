package by.it.kuzmichalex.calc_jd02_06;

/**
 * Интерфейс для отчётостроителя
 */
abstract class ReportBuilder {
    abstract String buildHeaderString();

    abstract String buildBodyString(String msg, CalcException e);
}

/**
 * Конкретная реализация полного отчётостроителя
 */
class ReportBuilderFull extends ReportBuilder {

    @Override
    String buildHeaderString() {
        return "Заголовок Полный отчёт";
    }

    /**
     * @param message Сообщение для лога
     * @param e Исключение, информация из которого испорльзуется дл строительства полного лога
     *          Может быть пустым.
     */
    @Override
    String buildBodyString(String message, CalcException e) {
        StringBuilder retStr = new StringBuilder();
        retStr.append(LanguageManager.getLocaleDate());
        retStr.append(" ");
        retStr.append(LanguageManager.getLocaleTime());
        retStr.append(" ");
        if (e != null) {
            retStr.append(LanguageManager.get(Messages.error)).append(" ");
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                if (stackTrace[i].getClassName().contains("by.it.")) {
                    retStr.append("class: ").append(stackTrace[i].getClassName()).append(": ");
                    break;
                }
            }
        }

        retStr.append(message);
        return retStr.toString();
    }
}

/**
 * Реализация сокращённого отчётостроителя
 */
class ReportBuilderShort extends ReportBuilder {

    @Override
    String buildHeaderString() {
        return "Заголовок Сокращённый отчёт";
    }

    /**
     * @param message Сообщение для лога
     * @param e Исключение, информация из которого испорльзуется дл строительства полного лога
     *          Может быть пустым.
     */
    @Override
    String buildBodyString(String message, CalcException e) {
        StringBuilder retStr = new StringBuilder();
        retStr.append(LanguageManager.getLocaleDate());
        retStr.append(" ");
        retStr.append(LanguageManager.getLocaleTime());
        retStr.append(" ");
        retStr.append(message);
        return retStr.toString();
    }
}

class Report {

}

