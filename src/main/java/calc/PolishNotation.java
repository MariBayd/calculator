package calc;

import javax.swing.*;
import java.util.Stack;

public class PolishNotation {
    PolishNotation() {
    }

    public float count(String str) {
        str = correctStr(str); //скипаем пробелы, заменяем запятые на точки
        //проверка корректности строки
        str = polisNotation(str); //преобразование к польской нотации
        return readPolishNotation(str); //результат
    }

    //проверка правильности введённой строки
    public boolean checkSTR(String str) {
        //если строка пустая
        if (str.length() == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (checkPoint(str.charAt(str.length() - 1)) || checkSymbol(str.charAt(str.length() - 1))) { //если последний символ - это запятая, точка или операция
            JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //если введён неверный символ
        else {
            char c = 0;
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                //проверка положения скобки (
                if (checkBracketOpen(c)) {
                    if (i == 0 || checkSymbol(str.charAt(i - 1))) continue;
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                //проверка положения скобки )
                if (checkBracketClose(c)) {
                    if (i == str.length() - 1 || checkSymbol(str.charAt(i + 1))) continue;
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                if (checkSymbol(c) || checkNumb(c)) continue;
                // если точка стоит в некорректном месте
                if (checkPoint(c)) {
                    if (!(i == 0) && checkNumb(str.charAt(i + 1)) && checkNumb(str.charAt(i - 1))) continue;
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }

    //скипает пробелы и энтеры, заменяет запятые на точки
    private String correctStr(String str) {
        if (str.contains(",")) {
            str = str.replaceAll(",", ".");
        }
        if (str.contains(" ")) {
            str = str.replaceAll(" ", "");
        }
        if (str.contains("\n")) {
            str = str.replaceAll("\n", "");
        }
        return str;
    }

    //проверка является ли знаком
    private boolean checkSymbol(char s) {
        if (s == '*' || s == '/' || s == '+' || s == '-') {
            return true;
        } else {
            return false;
        }
    }

    //проверка является ли числом
    private boolean checkNumb(char s) {
        int c = (int) s;
        if (c < 48 || c > 57) {
            return false;
        } else {
            return true;
        }
    }

    //проверка является ли открывающейся скобкой
    private boolean checkBracketOpen(char c) {
        if (c == '(') {
            return true;
        } else {
            return false;
        }
    }

    //проверка является ли закрывающейся скобкой
    private boolean checkBracketClose(char c) {
        if (c == ')') {
            return true;
        } else {
            return false;
        }
    }

    //проверка приоритета
    private int checkPriority(char c) {
        if ((int) c < 47 && (int) c > 57) {
            return 0;
        }
        if (c == '(') {
            return 1;
        }
        if (c == '+' || c == '-') {
            return 2;
        }
        if (c == '*' || c == '/') {
            return 3;
        } else return 0;
    }

    //проверяет является ли точкой или запятой
    private boolean checkPoint(char c) {
        if (c == '.' || c == ',') {
            return true;
        } else {
            return false;
        }
    }

    //преобразование строки к польской нотации
    public String polisNotation(String str) {
        char sym = 0;
        String result = "";
        char bracket = '(';
        Stack<Character> stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            sym = str.charAt(i);
            //если точка, то в выходную строк
            if (checkPoint(sym)) {
                result += sym;
            }
            //если цифра, то в выходную строку
            if (checkNumb(sym)) {
                result += sym;
            }
            //если (
            if (checkBracketOpen(sym)) {
                stack.push(sym);
            }
            //если )
            if (checkBracketClose(sym)) {
                if (stack.search(bracket) == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Несогласованные скобки!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                    return "-1";
                } else {
                    while (stack.peek() != bracket) {
                        result += '|';
                        result += stack.pop();
                        result += '|';
                    }
                    stack.pop(); // удаляем (
                }

            }
            //если знак операции
            if (checkSymbol(sym)) {
                if (checkNumb(str.charAt(i - 1))) {
                    result += '|';
                }
                //проверка приоритета
                while (!stack.empty() && checkPriority(stack.peek()) >= checkPriority(sym)) {
                    result += stack.pop();
                    result += '|';
                }
                stack.push(sym);
            }
        }
        //запись оставшихся знаков в строку
        while (!stack.empty()) {
            if (checkSymbol(stack.peek())) {
                result += '|';
                result += stack.pop();
                result += '|';
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "ошибка ввода!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return "-1";

            }
        }
        return result;
    }

    // вычисление результата по польской нотации
    private float readPolishNotation(String str) {
        char sym;
        float a, b, res = 0;
        int j = 0;
        Stack<Float> stack = new Stack<>();
        String cur_str = "";
        for (int i = 0; i < str.length(); i++) {
            sym = str.charAt(i);
            //если знак операции, то подсчёт
            if (checkSymbol(sym)) {
                a = stack.pop();
                b = stack.pop();
                switch (sym) {
                    case ('+'):
                        res = a + b;
                        stack.push(res);
                        break;
                    case ('-'):
                        res = b - a;
                        stack.push(res);
                        break;
                    case ('*'):
                        res = a * b;
                        stack.push(res);
                        break;
                    case ('/'):
                        res = b / a;
                        stack.push(res);
                        break;
                }
            } //если цифра, то читаем всё число
            else if (checkNumb(sym)) {
                j = i;
                while (str.charAt(j) != '|') {
                    cur_str += str.charAt(j);
                    j++;
                }
                stack.push(Float.parseFloat(cur_str));
                cur_str = "";
                i = j;
            }
        }
        return res;
    }
}