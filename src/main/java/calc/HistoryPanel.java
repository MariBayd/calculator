package calc;

import javax.swing.*;

public class HistoryPanel {
    private JTable tableHistory;
    private JFrame windowHistory = new JFrame("History");
    private HistoryWriter historyWriter = new HistoryWriter();

    HistoryPanel() {
        tableHistory = new JTable();
        windowHistory.add(new JScrollPane(tableHistory));
        windowHistory.setSize(600, 500);
        windowHistory.setVisible(true);
    }

    HistoryPanel(Object[][] date, Object[] columNames) {
        tableHistory = new JTable(date, columNames);
        windowHistory.add(new JScrollPane(tableHistory));
        windowHistory.setSize(600, 500);
        windowHistory.setVisible(true);
    }

}
