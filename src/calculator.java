import javax . swing.*;
import java.awt.*;
import java.awt.Font;

class calculator {
    private JFrame jfrm = new JFrame("Calculator");
    private JTextArea text = new JTextArea();

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
        panel.add(InitUI());
        return panel;
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
