import javax.swing.*;

class App{
    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
