import java.util.List;

public interface SearchService {
    List<List<String>> search(List<List<String>> rows, List<String> headers, String searchText);
}
