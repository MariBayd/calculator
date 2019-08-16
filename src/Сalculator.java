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
    }
    public void buttonC(){
        res = "";
    }
    public void buttonEq(String str){
        // обработка нажатия кнопки =
        if (res != str)  res = str;
        PolishNotation polishNot = new PolishNotation();
        if (polishNot.checkSTR(res)) ui.setTextArea(Float.toString(polishNot.Count(res)));
    }
}
