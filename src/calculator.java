import javax . swing.*;
import java.awt.*;
import java.awt.Font;

class calculator {

    calculator() {
       //создание окна
        JFrame jfrm = new JFrame("Calculator");
        jfrm.setSize(250, 300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //текстовое поле
        JTextArea text = new JTextArea();
        Font font = new Font(Font.SERIF, Font.BOLD, 32);
        text.setFont(font);
        JScrollPane scroll = new JScrollPane(text);

        //компановщик
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scroll);
        panel.add(InitUI());

        jfrm.add(panel);
        jfrm.setVisible(true);
    }
    private JPanel InitUI(){

        //вспомогательная панель
        JPanel grid = new JPanel();
        GridLayout layout = new GridLayout(4,4);
        grid.setLayout(layout);
        //создание кнопок
        grid.add(new JButton("1"));
        grid.add(new JButton("2"));
        grid.add(new JButton("3"));
        grid.add(new JButton("/"));
        grid.add(new JButton("4"));
        grid.add(new JButton("5"));
        grid.add(new JButton("6"));
        grid.add(new JButton("*"));
        grid.add(new JButton("7"));
        grid.add(new JButton("8"));
        grid.add(new JButton("9"));
        grid.add(new JButton("+"));
        grid.add(new JButton("C"));
        grid.add(new JButton("0"));
        grid.add(new JButton("="));
        grid.add(new JButton("-"));
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
