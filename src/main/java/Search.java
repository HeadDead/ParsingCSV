import java.util.ArrayList;
import java.util.List;

public class Search {

    private static final String DELIMITER = ",";

    public static List<List<String>> search(List<List<String>> rows, List<String> headers, String searchText) {
        List<List<String>> results = new ArrayList<>();
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i).toLowerCase().contains(searchText.toLowerCase())) {
                    results.add(row);
                    break;
                }
            }
        }
        return results;
    }


}
