import javax . swing.*;
import java.awt.*;
import java.awt.Font;

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
        jfrm.add(this);
        jfrm.setVisible(true);
    }
    UI(int windowWidth, int windowHeight, int sizeFont){
        //настройки окна с параметрами
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        setPanel();
        jfrm.add(this);
      //  addListener();
        jfrm.setVisible(true);
    }
   /* private void addListener(){
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

    }*/
    private void setPanel(){
        //компановщик
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);
        add(setButtons());
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
        JPanel btnsPanel = new JPanel();
        GridLayout layout = new GridLayout(4,4);
        btnsPanel.setLayout(layout);
        //добавление кнопок
        btnsPanel.add(b1);
        btnsPanel.add(b2);
        btnsPanel.add(b3);
        btnsPanel.add(bDiv);
        btnsPanel.add(b4);
        btnsPanel.add(b5);
        btnsPanel.add(b6);
        btnsPanel.add(bMult);
        btnsPanel.add(b7);
        btnsPanel.add(b8);
        btnsPanel.add(b9);
        btnsPanel.add(bAdd);
        btnsPanel.add(bC);
        btnsPanel.add(b0);
        btnsPanel.add(bEquality);
        btnsPanel.add(bSub);
        return btnsPanel;
    }
}