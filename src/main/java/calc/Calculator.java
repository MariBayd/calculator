package calc;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Calculator implements ListenerClick {
    public CalculatorPanel calculatorPanel;
    public String inputStr = new String();
    public String outputString;
    public History history = new History();
    public HistoryWriter historyWriter = new HistoryWriter();

    //�������������� ������
    public Calculator() {
        calculatorPanel = new CalculatorPanel(300, 400, 32, this);
    }

    public void buttonC() {
        inputStr = "";
    }

    // ��������� ������� ������ =
    public void buttonEq(String str) {
        if (inputStr != str) {
            inputStr = str;
        }
        PolishNotation polishNot = new PolishNotation();
        if (polishNot.checkSTR(inputStr)) {
            outputString = Float.toString(polishNot.Count(inputStr));
            calculatorPanel.setTextToArea(outputString);
            history = historyWriter.setHistory(inputStr, outputString);
            historyWriter.writeHistoryToFile(history, "history.txt");
        }
    }
    public boolean checkFile(String filePathName){
        if (Files.isRegularFile(Paths.get(filePathName))){
            return true;
        }
        else return false;

    }
    //��������� ������� ������ "�������� �������"
    public void buttonShowHistory() {
        Object[] columnNames = {"���� � �����", "������� ������", "���������"};
        String fileName = "history.txt";
        String filePathName = "C:\\JavaProjects\\CALC\\history.txt";
        if (checkFile(filePathName))   {
            Object[][] date = historyWriter.dateForTableFromFile(fileName);
            HistoryPanel historyPanel = new HistoryPanel(date, columnNames);
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "������� �����", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //��������� ������� ������ "��������� �� �����"
    public void buttonReadFromFile() {
        String fileName = "";
        FileDialog fld = new FileDialog(calculatorPanel.jfrm);
        fld.setDirectory("C:\\JavaProjects\\CALC");
        fld.setVisible(true);
        fileName = fld.getDirectory();
        fileName += fld.getFile();
        Object[] columnNames = {"���� � �����", "������� ������", "���������"};
        Object[][] date = historyWriter.dateForTableFromFile(fileName);
        HistoryPanel historyPanel = new HistoryPanel(date, columnNames);
    }

    //��������� ������� ������ "��������� � ����"
    public void buttonSaveToFile() {
        String filePathName = "";
        String text = "";
        FileDialog fld = new FileDialog(calculatorPanel.jfrm);
        fld.setDirectory("C:\\JavaProjects\\CALC");
        fld.setVisible(true);
        filePathName = fld.getDirectory();
        filePathName += fld.getFile();
        if (checkFile("C:\\JavaProjects\\CALC\\history.txt")) {
            text = historyWriter.readFile("history.txt");
            History[] histories = historyWriter.stringToHistory(text);
            for (int i = 0; i < historyWriter.AmountHistoryRecord - 1; i++) {
                historyWriter.writeHistoryToFile(histories[i], filePathName);
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "������� �����", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}