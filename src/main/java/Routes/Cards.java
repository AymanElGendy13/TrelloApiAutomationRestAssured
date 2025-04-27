package Routes;

import Utils.Logs;
import io.qameta.allure.Step;

public class Cards {

    @Step("Creating card with name: {cardName}")
    public static String createCardUrl(String listId, String cardName){
        String url = Routes.getBaseUrl() + "/cards?idList=" + listId +
                "&key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken() +
                "&name=" + cardName;

        Logs.info("Creating card with name: " + cardName);
        return url;
    }

    @Step("Getting card with ID: {cardId}")
    public static String getCardUrl(String cardId){
        String url = Routes.getBaseUrl() + "/cards/" + cardId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken();

        Logs.info("Getting card with ID: " + cardId);
        return url;
    }

    @Step("Updating card with ID: {cardId} and description: {cardDescription}")
    public static String updateCardUrl(String cardId, String cardDescription){
        String url = Routes.getBaseUrl() + "/cards/" + cardId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken() +
                "&desc=" + cardDescription;

        Logs.info("Updating card with ID: " + cardId);
        return url;
    }

    @Step("Deleting card with ID: {cardId}")
    public static String deleteCardUrl(String cardId){
        String url = Routes.getBaseUrl() + "/cards/" + cardId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken();

        Logs.info("Deleting card with ID: " + cardId);
        return url;
    }

    public static String createAttachmentOnCardUrl(String cardId, String urlLink, String attachmentName){
        String url = Routes.getBaseUrl() + "/cards/" + cardId +
                "/attachments?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken() +
                "&url=" + urlLink +
                "&name=" + attachmentName;

        Logs.info("Creating attachment on card with ID: " + cardId);
        return url;
    }

    public static String getAttachmentOnCardUrl(String cardId, String attachmentId){
        String url = Routes.getBaseUrl() + "/cards/" + cardId +
                "/attachments/" + attachmentId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken();

        Logs.info("Getting attachment on card with ID: " + cardId);
        return url;
    }

    public static String deleteAttachmentOnCardUrl(String cardId, String attachmentId){
        String url = Routes.getBaseUrl() + "/cards/" + cardId +
                "/attachments/" + attachmentId +
                "?key=" + Routes.getApiKey() +
                "&token=" + Routes.getToken();

        Logs.info("Deleting attachment on card with ID: " + cardId);
        return url;
    }

}
