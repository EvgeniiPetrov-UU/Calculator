import java.util.Scanner;

public class Calculator {
    public static void main (String[] args){

        Scanner in = new Scanner(System.in);
        System.out.println("Введите математическое выражение с двумя целыми числами от 1 до 10 включительно:");
        String conversion = in.nextLine();  //Ввод выражения
        try {
            System.out.println("Результат вычисления: " + (calc(conversion)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc (String input){

        String[] parts = input.split(" ");  //Разделение вводной строки на операнды и оператор
        if (parts.length > 3){
            try {
                throw new Exception("Недопустимый формат выражения. Допускаются два операнда и один оператор (+, -, /, *)");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        String num1 = parts[0];  //Первый операнд
        String num2 = parts[2];  //Второй операнд
        String operation = parts[1];  //Оператор

        int arab1 = 0, arab2 = 0;
        String num1Question = romanOrArab(num1);  //Римское или арабское число?
        String num2Question = romanOrArab(num2);
        if (num1Question.equals("Арабское") && num2Question.equals("Арабское")){
            arab1 = Integer.parseInt(num1);
            arab2 = Integer.parseInt(num2);
        } else if (num1Question.equals("Римское") && num2Question.equals("Римское")){
            arab1 = Integer.parseInt(romanToArab(num1));  //Конвертация римского числа в int
            arab2 = Integer.parseInt(romanToArab(num2));
        } else {
            try {
                throw new Exception("Допускается вводить либо арабские, либо римские числа");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }

        }

        int result = 0;
        switch(operation) {
            case "+":
                result = arab1 + arab2;
                break;
            case "-":
                result = arab1 - arab2;
                break;
            case "/":
                result = arab1 / arab2;
                break;
            case "*":
                result = arab1 * arab2;
                break;
            default:
                try {
                    throw new Exception("Поддерживаются только операции типа +,-,/,*, повторите ввод");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }

        String result1 = "";
        if (num1Question.equals("Римское") && num2Question.equals("Римское")){
            if (result > 0){
                result1 = arabToRoman(Integer.toString(result));
            } else {
                try {
                    throw new Exception("Результат вычислений оказался меньше или равен нулю. " +
                            "Вывод в римском виде невозможен");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
        } else {
            result1 = Integer.toString(result);
        }
        return result1;
    }

    //Метод для перевода римских в арабские
    static String romanToArab (String inputRoman){

        String str = inputRoman.toUpperCase();  //Преобразование в верхний регистр
        char[] inputChar = new char[str.length()];
        for (int i = 0; i < str.length(); i++){  //Перевели введенную строку в символьный массив и перевернули
            inputChar[i] = str.charAt(str.length() - 1 - i);
        }

        int resultArab = 0, num2 = 0;
        for (char arr: inputChar){  //Перебор всего массива, состоящего из входной строки
            String strArr = Character.toString(arr);  //Перевели текущую римскую в строку
            Number roman = Number.valueOf(strArr);  //Вывели константу, схожую с данной римской
            int num1 = roman.getValue();  //Сохранили арабское значение
            if (num2 <= num1){ //Проверили, чтобы следующая римская цифра была меньше или равна
                resultArab += num1;
                num2 = num1;  //Присвоили значение текущей римской, чтобы сравнить со следующей
            } else {
                resultArab -= num1;
                num2 = num1;  //Присвоили значение текущей римской, чтобы сравнить со следующей
            }
        }
        return Integer.toString(resultArab);  //Вывод арабского числа
    }

    //Метод для перевода арабских в римские
    static String arabToRoman (String inputArab) {

        String resultRoman = "";
        int num3 = Integer.parseInt(inputArab), num4;
        Number[] romanArr = Number.values();  //Создаем массив констант Enum'a
        while (num3 != 0) {
            for (Number romanNum : romanArr) {  //Перебор всех констант Enum'a
                String romanNumStr = romanNum.getStr();  //Перевели текущее римское число в строку
                num4 = romanNum.getValue();  //Сохранили арабское значение
                if (num4 <= num3) {
                    resultRoman = resultRoman + romanNumStr;  //Собираем итоговое римское число
                    num3 -= num4;
                    break;
                }
            }
        }
        return resultRoman;  //Вывод римского числа
    }

    //Метод для выявления арабских и римских чисел
    static String romanOrArab (String inputRom){

        int number;
        String typeOfNum;
        try {
            number = Integer.parseInt(inputRom);  //Конвертация в int
            typeOfNum = "Арабское";
        }catch (NumberFormatException e) {  //Если конвертировать в int не удалось, значит пользователь ввел римское число
            number = Integer.parseInt(romanToArab(inputRom));
            typeOfNum = "Римское";
        }

        if (number < 1 || number > 10) {
            try {
                throw new Exception("Разрешены числа от 1 до 10 или от I до X включительно");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return typeOfNum;
    }
}


