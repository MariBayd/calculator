import javax . swing.*;
import javax.swing.JOptionPane;
import java.util.Stack;


interface ListenerClick {

    public void buttonNUMBclick(String numb);
    public void buttonC();
    public void buttonEq(String str);
}

class Calculator implements ListenerClick {
    public UI ui;
    public String res = new String();
    public String res_sign = new String();
    Calculator() {
        //скомпанованный виджет
        ui = new UI(300, 400, 32, this );
    }

    public void buttonNUMBclick(String numb){

        res += numb;
        System.out.println("текущая строка:" + res);

    }
    public void buttonC(){
        res = "";
    }

    private void checkSTR(String str) {
        //проверка правильности ввода
        if (str.length() == 0) //если строка пустая
        {
            JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else { //если введён неверный символ
            int a = 0;
            for (int i = 0; i < str.length(); i++) {
                a = str.charAt(i);
                System.out.println("номера символов:" + a);
                if (a < 46 || a > 57) {
                    if (a == 42 || a == 43 || a == 45 || a == 40 || a == 41) return;
                    JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
    public void buttonEq(String str){
        // обработка кнопки =
        if (res != str)  res = str;
        checkSTR(res);
        System.out.println("Польская нотация:" + polisNotation(res));
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
        if ((int)c < 47 || (int)c > 57) return 0;
        if (c == '(' ) return 1;
        if (c == '+' || c =='-' ) return 2;
        else return 3;
    }
    public String polisNotation(String str){
        //преобразование строки к польской нотации
        Stack <Character> stack = new Stack();
        char sym = 0;
        String result = "";
        char bracket = '(';
        for (int i = 0; i < str.length(); i++){
            sym = str.charAt(i);
            if (checkNumb(sym)) result += sym; // если число, то в выходную строку
            if (checkBracketOpen(sym)) stack.push(sym); //если ( то в стек
            if (checkBracketClose(sym)) { // если символ - это )
                if (stack.search( bracket) == -1){
                    JOptionPane.showMessageDialog(new JFrame(), "Несогласованные скобки!", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                    return "-1";
                }
                while (stack.peek() != '('){
                    result += stack.pop();
                }
                stack.pop(); //удаляем из стека (
            }
            if (checkSymbol(sym)){ //если символ является знаком
                while (!stack.empty() && checkPriority(sym) <= checkPriority(stack.peek()))
                {
                    res += stack.pop();
                }
                stack.push(sym);
            }
        }
        while (!stack.empty()){
            System.out.println("stack:" + stack.peek());
            if (checkSymbol(stack.peek())) result += stack.pop();
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Несогласованные скобки!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return "-1";
            }
        }
        return result;
    }
    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }

}