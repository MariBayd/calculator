import javax . swing.*;

class calculator {
    calculator() {
        JFrame jfrm = new JFrame("Calculator");
        jfrm.setSize(275, 100);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jlab = new JLabel("!");
        jfrm.add(jlab);
        jfrm.setVisible(true);

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
