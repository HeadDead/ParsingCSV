import java.util.List;

public interface Reading {
    List<String> readHeaders(String line);

    List<String> readRow(String line);


}
