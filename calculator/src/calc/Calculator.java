package calc;

public class Calculator implements ListenerClick {
    public CalculatorPanel ui;
    public String inputStr = new String();

    //скомпанованный виджет
    public Calculator() {
        ui = new CalculatorPanel(300, 400, 32, this);
    }

    public void buttonNUMBclick(String numb) {
        inputStr += numb;
    }

    public void buttonC() {
        inputStr = "";
    }

    // обработка нажатия кнопки =
    public void buttonEq(String str) {
        if (inputStr != str) {
            inputStr = str;
        }
        PolishNotation polishNot = new PolishNotation();
        if (polishNot.checkSTR(inputStr)) {
            ui.setTextToArea(Float.toString(polishNot.Count(inputStr)));
        }
    }
}