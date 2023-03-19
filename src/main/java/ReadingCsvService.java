import java.util.List;

public interface ReadingCsvService {
    List<String> readHeaders(String line);

    List<String> readRow(String line);


}
