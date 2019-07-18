import javax . swing.*;
import java.awt.*;
import java.awt.Font;

class calculator {
    private JFrame jfrm = new JFrame("Calculator");
    private JTextArea text = new JTextArea();
    private JButton b1 = new JButton("1");
    private JButton b2 = new JButton("2");
    private JButton b3 = new JButton("3");
    private JButton b4 = new JButton("/");
    private JButton b5 = new JButton("4");
    private JButton b6 = new JButton("5");
    private JButton b7 = new JButton("6");
    private JButton b8 = new JButton("*");
    private JButton b9 = new JButton("7");
    private JButton b10 = new JButton("8");
    private JButton b11 = new JButton("9");
    private JButton b12 = new JButton("+");
    private JButton b13 = new JButton("C");
    private JButton b14 = new JButton("0");
    private JButton b15 = new JButton("=");
    private JButton b16 = new JButton("-");

    calculator() {
        //окно
        setFrame(250, 300);
        //шрифт
        setFont(32);
        //скомпанованный виджет
        jfrm.add(setPanel());
        jfrm.setVisible(true);
    }
    public void setFrame(int width, int height){
        jfrm.setSize(width, height);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setFont(int size){
        Font font = new Font(Font.SERIF, Font.BOLD, size);
        text.setFont(font);
    }
    private JPanel setPanel(){
        //компановщик
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(text);
        panel.add(scroll);
        panel.add(setButtons());
        return panel;
    }
    private JPanel setButtons(){

        //вспомогательная панель
        JPanel grid = new JPanel();
        GridLayout layout = new GridLayout(4,4);
        grid.setLayout(layout);
        //добавление кнопок
        grid.add(b1);
        grid.add(b2);
        grid.add(b3);
        grid.add(b4);
        grid.add(b5);
        grid.add(b6);
        grid.add(b7);
        grid.add(b8);
        grid.add(b9);
        grid.add(b10);
        grid.add(b11);
        grid.add(b12);
        grid.add(b13);
        grid.add(b14);
        grid.add(b15);
        grid.add(b16);
        return grid;
    }

    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new calculator();
            }

        });
    }

}
