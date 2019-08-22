package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPanel extends JPanel implements ActionListener {

    public JTextArea textArea = new JTextArea();
    public int windowWidth = 250;
    public int windowHeight = 300;
    public int sizeFont = 32;
    public int menuBarHeight = 18;
    public ListenerClick listenerClick;
    public JFrame jfrm = new JFrame("Calculator");
    public JMenuBar menuBar = new JMenuBar();
    public JMenu history = new JMenu("�������");
    public JMenuItem saveTofile = new JMenuItem("��������� � ����");
    public JMenuItem readFromFile = new JMenuItem("��������� �� �����");
    public JMenuItem showHistory = new JMenuItem("�������� �������");
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

    CalculatorPanel() {
        //��������� ���� �� ���������
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        jfrm.add(this);
        jfrm.setVisible(true);
    }

    CalculatorPanel(int windowWidth, int windowHeight, int sizeFont, ListenerClick listenerClick) {
        //��������� ���� � �����������
        setFrame(windowWidth, windowHeight);
        setFont(sizeFont);
        this.listenerClick = listenerClick;
        addListener();
        setPanel();
        jfrm.add(this);
        jfrm.setVisible(true);
    }

    private JPanel setHistory() {
        JPanel panelHistiry = new JPanel();
        panelHistiry.setLayout(new FlowLayout(FlowLayout.LEADING));
        history.add(saveTofile);
        history.add(readFromFile);
        history.add(showHistory);
        menuBar.add(history);
        panelHistiry.add(menuBar);
        panelHistiry.setMaximumSize(new Dimension(jfrm.getWidth(), menuBarHeight));
        return panelHistiry;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //���� ������ C
        if (e.getSource() == bC) {
            textArea.setText("");
            listenerClick.buttonC();
            return;
        }
        if (e.getSource() == bEquality) {
            listenerClick.buttonEq(textArea.getText());
            return;
        }
        if (e.getSource() == showHistory) {
           listenerClick.buttonShowHistory();
        }
        if (e.getSource() == readFromFile) {
            listenerClick.buttonReadFromFile();
        }
        if (e.getSource() == saveTofile) {
            listenerClick.buttonSaveToFile();
        }
        //���� ������ ����� ��� ����
        if (e.getSource() == b0 || e.getSource() == b1 || e.getSource() == b2
                || e.getSource() == b3 || e.getSource() == b4 || e.getSource() == b5
                || e.getSource() == b6 || e.getSource() == b7 || e.getSource() == b8
                || e.getSource() == b9 || e.getSource() == bAdd || e.getSource() == bC
                || e.getSource() == bDiv || e.getSource() == bEquality || e.getSource() == bMult
                || e.getSource() == bSub) {
            textArea.append(e.getActionCommand());
            //   listenerClick.buttonNUMBclick(e.getActionCommand());
        }
    }

    public void setTextToArea(String str) {
        this.textArea.setText(str);
    }
    public String getTextArea(){
        return textArea.getText();
    }

    private void addListener() {
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
        showHistory.addActionListener(this);
        readFromFile.addActionListener(this);
        saveTofile.addActionListener(this);
    }

    private void setPanel() {
        //�����������
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(textArea);
        add(setHistory());
        add(scroll);
        add(setButtons());
    }

    public void setFrame(int width, int height) {
        //��������� �������� ����
        jfrm.setSize(width, height);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setFont(int size) {
        //��������� ������� ������ ��������� � ����������
        Font font = new Font(Font.SERIF, Font.BOLD, size);
        textArea.setFont(font);
    }

    private JPanel setButtons() {
        //��������������� ������
        JPanel btnsPanel = new JPanel();
        GridLayout layout = new GridLayout(4, 4);
        btnsPanel.setLayout(layout);
        //���������� ������
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