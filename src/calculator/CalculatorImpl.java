package calculator;

public class CalculatorImpl implements Calculator {

    // Объявление лексем
    final int NONE = 0;         //  FAIL
    final int DELIMITER = 1;    //  Разделитель(+-*/^=, ")", "(" )
    final int VARIABLE = 2;     //  Переменная
    final int NUMBER = 3;       //  Число

    // Лексема, определяющая конец выражения
    final String EOF = "\0";

    private String exp;     //  Ссылка на строку с выражением
    private int explds;     //  Текущий индекс в выражении
    private String token;   //  Сохранение текущей лексемы
    private int tokType;    //  Сохранение типа лексемы


    public String toString() {
        return String.format("Exp = {0}\nexplds = {1}\nToken = {2}\nTokType = {3}", exp, explds,
                token, tokType);
    }

    // Получить следующую лексему
    // При выполнении проверки сохраняем символ в переменную класса token
    private void getToken() {
        tokType = NONE;
        token = "";

        //  Проверка на окончание выражения
        if (explds == exp.length()) {
            token = EOF;
            return;
        }
        // Проверка на пробелы, если есть пробел - игнорируем его.
        // Character.isWhitespace() — определяет в Java, является ли указанное значение типа char
        // пустым пространством, которое включает в себя пробел, табуляцию или новую строку.
        // charAt() — возвращает символ, расположенный по указанному индексу строки
        while (explds < exp.length() && Character.isWhitespace(exp.charAt(explds)))
            ++explds;
        if (isOperation(exp.charAt(explds))) {
            token += exp.charAt(explds);
            explds++;
            tokType = DELIMITER;
            // Character.isLetter() — в Java определяет, является ли указанное значение типа char буквой.
        } else if (Character.isLetter(exp.charAt(explds))) {
            while (!isOperation(exp.charAt(explds))) {
                token += exp.charAt(explds);
                explds++;
                //Выходим из цикла если текущий индекс в выражении вышел за пределы нашей строки
                if (explds >= exp.length())
                    break;
            }
            tokType = VARIABLE;
            // Character.isDigit() — определяет, является ли указанное значение типа char цифрой
        } else if (Character.isDigit(exp.charAt(explds))) {
            while (!isOperation(exp.charAt(explds))) {
                token += exp.charAt(explds);
                explds++;
                //Выходим из цикла если текущий индекс в выражении вышел за пределы нашей строки
                if (explds >= exp.length())
                    break;
            }
            tokType = NUMBER;
        } else {
            token = EOF;
        }
    }

    //Проверка является ли данный символ арифметической операцией
    // charAt() — возвращает символ, расположенный по указанному индексу строки
    private boolean isOperation(char charAt) {
        return (" +-/*%^=()".indexOf(charAt)) != -1;
    }

    //  Точка входа анализатора
    public Double evaluate(String expstr) {

        double result;
        //Сохраняем нашу строку в переменную класса
        exp = expstr;
        explds = 0;
        //Получаем следующий символ
        getToken();

        if (token.equals(EOF))
            return null;  // Нет выражения

        // Анализ и вычисление выражения
        try {
            result = evalExp2();
        } catch (CalcExeption calcExeption) {
            return null;
        }

        if (!token.equals(EOF))
            return null;

        return result;
    }

    //  Сложить или вычислить два терма
    private double evalExp2() throws CalcExeption {

        // Переменная для хранения символа
        char op;
        // Результат
        double result;
        // Частичный результат
        double partialResult;
        result = evalExp3();
        // charAt() — возвращает символ, расположенный по указанному индексу строки
        while ((op = token.charAt(0)) == '+' ||
                op == '-') {
            //Получаем следующий символ
            getToken();
            partialResult = evalExp3();
            switch (op) {
                case '-':
                    result -= partialResult;
                    break;
                case '+':
                    result += partialResult;
                    break;
            }
        }
        return result;
    }

    //  Умножить или разделить два фактора
    private Double evalExp3() throws CalcExeption {
        // Переменная для хранения символа
        char op;
        // Результат
        double result;
        // Частичный результат
        double partialResult;

        result = evalExp4();
        // charAt() — возвращает символ, расположенный по указанному индексу строки
        while ((op = token.charAt(0)) == '*' ||
                op == '/') {
            //Получаем следующий символ
            getToken();
            partialResult = evalExp4();
            switch (op) {
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if (partialResult == 0.0)
                        return null;
                    result /= partialResult;
                    break;
            }
        }
        return result;
    }

    //  Определить унарные + или -
    private double evalExp4() throws CalcExeption {
        // Результат
        double result;
        // Переменная для хранения символа
        String op = " ";

        if ((tokType == DELIMITER) && token.equals("+") ||
                token.equals("-")) {
            op = token;
            //Получаем следующий символ
            getToken();
        }
        result = evalExp5();
        if (op.equals("-"))
            result = -result;
        return result;
    }

    //  Обработать выражение в скобках
    private Double evalExp5() throws CalcExeption {
        // Результат
        double result;

        if (token.equals("(")) {
            //Получаем следующий символ
            getToken();
            result = evalExp2();
            if (!token.equals(")"))
                return null;
            //Получаем следующий символ
            getToken();
        } else
            result = atom();
        return result;
    }

    //  Получить значение числа
    private Double atom() throws CalcExeption {
        // Результат
        double result = 0.0;

        if (tokType == NUMBER) {
            try {
                result = Double.parseDouble(token);
            } catch (NumberFormatException exc) {
                throw new CalcExeption();
            }
            getToken();
        } else {
            throw new CalcExeption();
        }
        return result;
    }
}
