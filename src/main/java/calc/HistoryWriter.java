package calc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryWriter{
    HistoryWriter() {

    }

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    int AmountHistoryRecord = 0;

    //запись в файл
    public void writeHistoryToFile(History history, String fileName) {
        boolean existFile = false;
        if (AmountHistoryRecord > 0) existFile = true;
        try {
            FileWriter writer = new FileWriter(fileName, existFile);
            writer.append(gson.toJson(history));
            writer.append(" ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AmountHistoryRecord++;
    }

    //чтение из файла
    public String readFile(String fileName) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    //количество пробелов (разделителей) в строке
    private int numbersOfSpace(String text) {
        int count = text.replaceAll("[^ ]", "").length();
        return count;
    }

    //конвертирование строки в объекты класса History
    public History[] stringToHistory(String text) {
        AmountHistoryRecord = numbersOfSpace(text);
        History[] date = new History[AmountHistoryRecord];
        char c;
        String currebtStr = "";
        String dateStr = "";
        int count = 0;
        for (int j = 0; j < text.length(); j++) {
            c = text.charAt(j);
            if (c == ' ') {
                dateStr = currebtStr;
                date[count] = gson.fromJson(dateStr, History.class);
                count++;
                currebtStr = "";
                j++;
                if (j == text.length()) break;
            }
            currebtStr += text.charAt(j);
        }

        return date;
    }

    //установка значений объекта
    public History setHistory(String input, String out) {
        History data = new History();
        data.data = LocalDateTime.now();
        data.inputString = input;
        data.result = out;
        return data;
    }

    //конвертирование объектов History[] в Object[][]
    private Object[][] historyToObject(History[] histories) {
        Object[][] date = new Object[AmountHistoryRecord][3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (int i = 0; i < AmountHistoryRecord; i++) {
            date[i][0] = formatter.format(histories[i].data);
            date[i][1] = histories[i].inputString;
            date[i][2] = histories[i].result;
        }
        return date;
    }

    //получение данных для заполнения таблицы из файла
    public Object[][] dateForTableFromFile(String fileName) {
        String text = readFile(fileName);
        History[] histories = stringToHistory(text);
        Object[][] date = historyToObject(histories);
        return date;
    }
}
