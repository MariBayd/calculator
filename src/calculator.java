import javax . swing.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;


interface ListenerClick {

    public void buttonNUMBclick(String numb);
    public void buttonC();
    public void buttonEq(String str);
}

class Calculator implements ListenerClick {
    public UI ui;
    public String res = new String();
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
                if (a < 47 || a > 57) {
                    if (a == 42 || a == 43 || a == 45) return;
                    JOptionPane.showMessageDialog(new JFrame(), "Ошибка ввода!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
    public void buttonEq(String str){
        if (res != str)  res = str;
        checkSTR(res);
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