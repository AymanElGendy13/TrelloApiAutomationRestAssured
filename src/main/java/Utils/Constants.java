package Utils;

import java.util.Map;

public class Constants {

    public static final String BASE_URL = "https://api.trello.com/1";
    public static final String BOARDS_ENDPOINT = "/boards";
    public static final String CARDS_ENDPOINT = "/cards";
    public static final String LABELS_ENDPOINT = "/labels";
    public static final String LISTS_ENDPOINT = "/lists";
    public static final String CHECKLISTS_ENDPOINT = "/checklists";
    public static final String CHECKITEMS_ENDPOINT = "/checkItems";

    // Common query parameters for all requests
    public static Map<String, String> getAuthParams() {
        return Map.of(
                "key", API_KEY,
                "token", TOKEN
        );
    }
}
