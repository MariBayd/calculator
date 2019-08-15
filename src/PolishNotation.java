import javax.swing.*;
import java.util.Stack;

public class PolishNotation {
    PolishNotation() {
    }

    public float Count(String str) {
        str = correctStr(str); //скипаем пробелы, заменяем запятые на точки
        //проверка корректности строки
        str = polisNotation(str); //преобразование к польской нотации
        return readPolishNotation(str); //результат
    }

    public boolean checkSTR(String str) {
        //проверка правильности введённой строки
            if (str.length() == 0) //если строка пустая
            {
                JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (checkPoint(str.charAt(str.length() - 1)) || checkSymbol(str.charAt(str.length() - 1))) { //если последний символ - это запятая, точка или операция
                JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else { //если введён неверный символ
                char c = 0;
                for (int i = 0; i < str.length(); i++) {
                    c = str.charAt(i);
                    if (checkBracketOpen(c)){ //проверка положения скобки (
                        if (i == 0 || checkSymbol(str.charAt(i-1))) continue;
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                    JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                    if (checkBracketClose(c)){ //проверка положения скобки )
                        if (i == str.length() || checkSymbol(str.charAt(i+1))) continue;
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                    JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    }
                    if (checkSymbol(c) || checkNumb(c)) continue;
                    if (checkPoint(c)) { // если точка стоит в некорректном месте
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
    private String correctStr(String str){
        //скипает пробелы и энтеры, заменяет запятые на точки
        if (str.contains(",")) str = str.replaceAll(",",".");
        if (str.contains(" ")) str = str.replaceAll(" ", "");
        if (str.contains("\n")) str = str.replaceAll("\n", "");
        return str;
    }
    private boolean checkSymbol(char s){
        //проверка является ли знаком
        if (s == '*' || s == '/' || s == '+' || s == '-') return true;
        else return false;
    }
    private boolean checkNumb(char s){
        //проверка является ли числом
        int c = (int)s;
        if (c < 48 || c > 57) return false;
        else return true;
    }
    private boolean checkBracketOpen(char c){
        //проверка является ли открывающейся скобкой
        if (c == '(') return true;
        else return false;
    }
    private boolean checkBracketClose(char c){
        //проверка является ли закрывающейся скобкой
        if (c == ')') return true;
        else return false;
    }
    private int checkPriority(char c){
        //проверка приоритета
        if ((int)c < 47 && (int)c > 57) return 0;
        if (c == '(' ) return 1;
        if (c == '+' || c =='-' ) return 2;
        if (c == '*' || c == '/') return 3;
        else return 0;
    }
    private boolean checkPoint(char c){
        //проверяет является ли точкой или запятой
        if (c == '.' || c == ',') return true;
        else return false;
    }
    public String polisNotation(String str) {
        //преобразование строки к польской нотации
        char sym = 0;
        String result = "";
        char bracket = '(';
        Stack<Character> stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            sym = str.charAt(i);
            if (checkPoint(sym)) result += sym; //если точка, то в выходную строку
            if (checkNumb(sym)) result += sym; //если цифра, то в выходную строку
            if (checkBracketOpen(sym)) stack.push(sym); //если (
            if (checkBracketClose(sym)) { //если )
                if (stack.search(bracket) == -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Несогласованные скобки!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                    return "-1";
                }
                else {
                    while (stack.peek() != bracket){
                        result += '|';
                        result += stack.pop();
                        result += '|';
                    }
                    stack.pop(); // удаляем (
                }

            }
            if (checkSymbol(sym)) { //если знак операции
                if (checkNumb(str.charAt(i-1))) result += '|';
                while (!stack.empty() && checkPriority(stack.peek()) >= checkPriority(sym) ) { //проверка приоритета
                    result += stack.pop();
                    result += '|';
                }
                stack.push(sym);
            }
        }
        while (!stack.empty()) { //запись оставшихся знаков в строку
            if (checkSymbol(stack.peek())){
                result += '|';
                result += stack.pop();
                result += '|';
            }
            else {
                JOptionPane.showMessageDialog(new JFrame(), "ошибка ввода!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return "-1";

            }
        }
        return result;
    }
    private float readPolishNotation (String str){
        // вычисление результата по польской нотации
        char sym;
        float a, b, res = 0;
        int j = 0;
        Stack <Float> stack = new Stack<>();
        String cur_str = "";
        for (int i = 0; i < str.length(); i++){
            sym = str.charAt(i);
            if (checkSymbol(sym)){ //если знак операции, то подсчёт
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
            }
            else if (checkNumb(sym)) { //если цифра, то читаем всё число
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
        return  res;
    }
}
