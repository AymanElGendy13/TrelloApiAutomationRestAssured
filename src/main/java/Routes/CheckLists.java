package Routes;

import Utils.Logs;
import io.qameta.allure.Step;

public class CheckLists {

    @Step("Creating CheckList with Name: {checkListName}")
    public static String createCheckListUrl(String cardId, String checkListName) {
        String url = Routes.getBaseUrl() + "/checklists?idCard=" + cardId
                + "&key=" + Routes.getApiKey()
                + "&token=" + Routes.getToken()
                + "&name=" + checkListName;

        Logs.info("Creating CheckList with Name: " + checkListName);
        return url;
    }

    @Step("Getting CheckList with ID: {checklistId}")
    public static String getCheckListUrl(String checklistId) {
        String url = Routes.getBaseUrl() + "/checklists/" + checklistId
                + "?key=" + Routes.getApiKey()
                + "&token=" + Routes.getToken();

        Logs.info("Getting CheckList with ID: " + checklistId);
        return url;
    }

    @Step("Updating CheckList with ID: {checklistId} and Name: {checkListName}")
    public static String updateCheckListUrl(String checklistId, String checkListName) {
        String url = Routes.getBaseUrl() + "/checklists/" + checklistId
                + "?key=" + Routes.getApiKey()
                + "&token=" + Routes.getToken()
                + "&name=" + checkListName;

        Logs.info("Updating CheckList with ID: " + checklistId);
        return url;
    }

    @Step("Deleting CheckList with ID: {checklistId}")
    public static String deleteCheckListUrl(String checklistId) {
        String url = Routes.getBaseUrl() + "/checklists/" + checklistId
                + "?key=" + Routes.getApiKey()
                + "&token=" + Routes.getToken();

        Logs.info("Deleting CheckList with ID: " + checklistId);
        return url;
    }

}
