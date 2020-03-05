package by.it.shulga.jd01.jd01_06;

public class TaskC1 {
    public static void main(String[] args) {
        String myString = by.it.shulga.jd01.jd01_06.Poem.text; //Строка-стихотворение
        String[] myStringArray = myString.split("\n"); // разделим строку на массив строк по \n
        int maxValue = myStringArray[0].length(); //здесь будет максимальная длина строки.
        for (int i = 1; i < myStringArray.length; i++) {
            if (maxValue < myStringArray[i].length())
                maxValue = myStringArray[i].length();  // циклом найдем самую длинную строку
        }
        for (int i = 0; i < myStringArray.length; i++) { //Проходим по строкам массива myArrayString
            int currentLengthString = myStringArray[i].replaceAll("\\s", "").length(); //Найдем текущую длину строки без пробелов
            String[] currentString = myStringArray[i].split("\\s"); // создаем массив слов из текущей строки
            while (currentLengthString < maxValue) { //повторять пока длина текущей строки меньше maxValue
                for (int j = 0; j < currentString.length - 1; j++) { //В цикле проходим по словам, не включая последнее
                    currentString[j] += " ";                         //и добавляем пробел к слову
                    currentLengthString++;                           //Увеличиваем на 1 длину текущей строки
                    if (currentLengthString == maxValue) break;      //Если длины строк сравнялись, выходим с цикла
                }
            }
            myStringArray[i]=joinString(currentString,""); //с помощью метода joinString создаем строку и сохряняем её по индексу массива MyStringArray
        }
        String resultString = joinString(myStringArray,"\n"); //с помощью метода joinString создаем общую строку с переносом по строкам
        System.out.println(resultString); // выводим в консоль
    }

    private static String joinString(String[] array, String joiner) {
        StringBuilder sb1 = new StringBuilder();
        for (String elem : array) {
            sb1.append(elem).append(joiner); //Добавляем к каждому элементу массива joiner
        }
        return sb1.toString(); //Вернем полученую строку
    }
}
