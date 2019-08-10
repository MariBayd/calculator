import javax . swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


interface Listen{


}
class UI extends JPanel{
    private JFrame jfrm = new JFrame("Calculator");
    public JTextArea textArea = new JTextArea();

    public int windowWidth = 250;
    public int windowHeight = 300;
    public int sizeFont = 32;

    private JButton b1 = new JButton("1");
    private JButton b2 = new JButton("2");
    private JButton b3 = new JButton("3");
    private JButton bDiv = new JButton("/");
    private JButton b4 = new JButton("4");
    private JButton b5 = new JButton("5");
    private JButton b6 = new JButton("6");
    private JButton bMult = new JButton("*");
    private JButton b7 = new JButton("7");
    private JButton b8 = new JButton("8");
    private JButton b9 = new JButton("9");
    private JButton bAdd = new JButton("+");
    private JButton bC = new JButton("C");
    private JButton b0 = new JButton("0");
    private JButton bEquality = new JButton("=");
    private JButton bSub = new JButton("-");


    UI(){
        //настройки окна по умолчанию
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        jfrm.add(setPanel());
        jfrm.setVisible(true);
    }
    UI(int windowWidth, int windowHeight, int sizeFont){
        //настройки окна с параметрами
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        jfrm.add(setPanel());
        addListener();
        jfrm.setVisible(true);
    }
    private void addListener(){
       ListenerAction listenerAction = new ListenerAction();
       b0.addActionListener(listenerAction);
       b1.addActionListener(listenerAction);
       b2.addActionListener(listenerAction);
       b3.addActionListener(listenerAction);
       b4.addActionListener(listenerAction);
       b5.addActionListener(listenerAction);
       b6.addActionListener(listenerAction);
       b7.addActionListener(listenerAction);
       b8.addActionListener(listenerAction);
       b9.addActionListener(listenerAction);
       bAdd.addActionListener(listenerAction);
       bDiv.addActionListener(listenerAction);
       bEquality.addActionListener(listenerAction);
       bMult.addActionListener(listenerAction);
       bSub.addActionListener(listenerAction);

    }
    private JPanel setPanel(){
        //компановщик
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(textArea);
        panel.add(scroll);
        panel.add(setButtons());
        return panel;
    }

    public void setFrame(int width, int height){
        //изменение размеров окна
        jfrm.setSize(width, height);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setFont(int size){
        //изменение размера шрифта вводимого с клавиатуры
        Font font = new Font(Font.SERIF, Font.BOLD, size);
        textArea.setFont(font);
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
        grid.add(bDiv);
        grid.add(b4);
        grid.add(b5);
        grid.add(b6);
        grid.add(bMult);
        grid.add(b7);
        grid.add(b8);
        grid.add(b9);
        grid.add(bAdd);
        grid.add(bC);
        grid.add(b0);
        grid.add(bEquality);
        grid.add(bSub);
        return grid;
    }
}
class ListenerAction implements ActionListener {
//   calculator calc = new calculator();
    public void actionPerformed(ActionEvent e) {
        System.out.println("Нажатие кнопки! От - "+
                e.getActionCommand() + "\n");
   //     calc.click_button(e);

    }

}

class calculator  {
    public UI ui;
    calculator() {
        //скомпанованный виджет
        ui = new UI(300, 400, 32);
    }
//не работает:(
    void click_button(ActionEvent e){
        ui.textArea.append(e.getActionCommand());
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
