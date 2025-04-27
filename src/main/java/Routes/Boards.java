package Routes;

import Utils.Logs;
import io.qameta.allure.Step;

public class Boards {

    @Step("Creating board with name: {name}, description: {desc}, defaultLists: {defaultLists}")
    public static String createBoardUrl(String name, String desc, boolean defaultLists) {
        String url = Routes.getBaseUrl() + "/boards?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken() +
                "&name=" + name +
                "&desc=" + desc +
                "&defaultLists=" + defaultLists;

        Logs.info("Creating board with URL: " + url);
        return url;
    }

    @Step("Getting board with ID: {boardId}")
    public static String getBoardUrl(String boardId) {
        Logs.info("Getting board with ID: " + boardId);
        return Routes.getBaseUrl() + "/boards/" + boardId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken();
    }

    @Step("Updating board with ID: {boardId}, name: {name}, description: {desc}")
    public static String updateBoardUrl(String boardId, String name, String desc) {
        Logs.info("Updating board with ID: " + boardId);
        return Routes.getBaseUrl() + "/boards/" + boardId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken() +
                "&name=" + name
                + "&desc=" + desc;
    }

    @Step("Deleting board with ID: {boardId}")
    public static String deleteBoardUrl(String boardId) {
        Logs.info("Deleting board with ID: " + boardId);
        return Routes.getBaseUrl() + "/boards/" + boardId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken();
    }


}