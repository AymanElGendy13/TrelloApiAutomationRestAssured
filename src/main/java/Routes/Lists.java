package Routes;

import Utils.Logs;
import io.qameta.allure.Step;

public class Lists {

    @Step("Creating List with Name: {listName}")
    public static String createListUrl(String boardId, String listName) {
        String url = Routes.getBaseUrl() + "/boards/" + boardId +
                "/lists?key=" +
                Routes.getApiKey()
                + "&token="
                + Routes.getToken()
                + "&name=" + listName;
        Logs.info("Create List URL: " + url);
        return url;
    }

    @Step("Getting List with ID: {listId}")
    public static String getListUrl(String listId) {
        String url = Routes.getBaseUrl() + "/lists/" + listId +
                "?key=" +
                Routes.getApiKey()
                + "&token="
                + Routes.getToken();
        Logs.info("Get List URL: " + url);
        return url;
    }

    @Step("Updating List with ID: {listId}")
    public static String updateListUrl(String listId, String listName, String listPos) {
        String url = Routes.getBaseUrl() + "/lists/" + listId +
                "?key=" +
                Routes.getApiKey()
                + "&token="
                + Routes.getToken()
                + "&name=" + listName
                + "&pos=" + listPos;
        Logs.info("Update List URL: " + url);
        return url;
    }


}