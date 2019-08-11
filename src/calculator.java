import javax . swing.*;
import java.awt.event.ActionEvent;


class ListenerClick {

    ListenerClick(){
        System.out.println("d");

    }
}

class Calculator extends ListenerClick {
    public UI ui;
    ListenerClick listener = new ListenerClick();
    Calculator() {
        //скомпанованный виджет
        ui = new UI(300, 400, 32 );
    }
    //не работает:(
    void click_button(ActionEvent e){
        ui.textArea.append(e.getActionCommand());
    }

    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }

}