package Utils;

import java.util.Map;

public class Constants {

    public static final String BASE_URL = "https://api.trello.com/1";
    public static final String API_KEY = "86f8154388dd109632bf2b9f8e117238";
    public static final String TOKEN = "ATTAb58babbb6592bf27695f95cf22f0fc20697cea70442ea87fc79b9721cc1dfd4078E564E8";
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
