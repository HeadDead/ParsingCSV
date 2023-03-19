import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reading extends Search implements ReadingCsvService {
    private static final String DELIMITER = ",";

    private static final String CSV_FILE_PATH = "src/main/resources/airports.csv";

    private static final int BUFFER_SIZE = 7 * 1024 * 1024;

    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH), BUFFER_SIZE)) {
            List<String> headers = readHeaders(reader.readLine());
            List<List<String>> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(readRow(line));
            }
            System.out.println("Loaded " + rows.size() + " rows");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter search text (type '!quit' to exit): ");
                String searchText = scanner.nextLine();
                if ("!quit".equals(searchText)) {
                    break;
                }
                long startTime = System.currentTimeMillis();
                List<List<String>> results = search(rows, headers, searchText);
                long endTime = System.currentTimeMillis();
                System.out.println("Found " + results.size() + " results in " + (endTime - startTime) + " ms");
                for (List<String> result : results) {
                    System.out.println(String.join(DELIMITER, result));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> readHeaders(String line) {
        String[] headerArray = line.split(DELIMITER);
        List<String> headers = new ArrayList<>(headerArray.length);
        for (String header : headerArray) {
            headers.add(header.trim());
        }
        return headers;
    }

    @Override
    public List<String> readRow(String line) {
        String[] rowArray = line.split(DELIMITER);
        List<String> row = new ArrayList<>(rowArray.length);
        for (String value : rowArray) {
            row.add(value.trim());
        }
        return row;
    }
}
