import javax . swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UI extends JPanel implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //если нажали C
        if (e.getSource() == bC) {
            textArea.setText("");
            listenerClick.buttonC();
            return;
        }
        if (e.getSource() == bEquality){
            listenerClick.buttonEq(textArea.getText());
            return;
        }
        //если нажали цифру или знак
        textArea.append(e.getActionCommand());
        listenerClick.buttonNUMBclick(e.getActionCommand());
    }
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
    public ListenerClick listenerClick;

    UI(){
        //настройки окна по умолчанию
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        jfrm.add(this);
        jfrm.setVisible(true);
    }
    UI(int windowWidth, int windowHeight, int sizeFont, ListenerClick listener){
        //настройки окна с параметрами
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        listenerClick = listener;
        addListener();
        setPanel();
        jfrm.add(this);
        jfrm.setVisible(true);
    }

    public void setTextArea(String str) {
        this.textArea.setText(str);
    }
    private void addListener(){
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        bAdd.addActionListener(this);
        bDiv.addActionListener(this);
        bEquality.addActionListener(this);
        bMult.addActionListener(this);
        bSub.addActionListener(this);
        bC.addActionListener(this);
    }
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
