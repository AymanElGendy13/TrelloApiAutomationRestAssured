package Steps;

import Pojo.List;
import TestData.ListTestData;
import Utils.Constants;
import Utils.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ListSteps {

    @Step("Create list with random data in board: {boardId}")
    public static List createList(String boardId) {
        Map<String, Object> payload = new HashMap<>();
        payload.putAll(ListTestData.randomListPayload(boardId)); // Add all random data
        payload.put("idBoard", boardId); // Now we can modify the map

        Response response = RestClient.postRequest(payload, Constants.LISTS_ENDPOINT);
        RestClient.validateStatusCode(response, 200);
        return response.as(List.class);
    }

    @Step("Get list with ID: {listId}")
    public static List getList(String listId) {
        Response response = RestClient.getRequest(Constants.LISTS_ENDPOINT + "/" + listId);
        RestClient.validateStatusCode(response, 200);
        return response.as(List.class);
    }

    @Step("Update list with ID: {listId}")
    public static List updateList(String listId) {
        Map<String, Object> payload = ListTestData.randomListPayload(listId);

        Response response = RestClient.putRequest(
                payload,
                Constants.LISTS_ENDPOINT + "/" + listId
        );
        RestClient.validateStatusCode(response, 200);
        return response.as(List.class);
    }

    @Step("Archive list with ID: {listId}")
    public static void archiveList(String listId) {
        Map<String, Object> payload = Map.of("closed", true);

        Response response = RestClient.putRequest(
                payload,
                Constants.LISTS_ENDPOINT + "/" + listId
        );
        RestClient.validateStatusCode(response, 200);
    }

    @Step("Move all cards from list {sourceListId} to {targetListId}")
    public static void moveAllCards(String sourceListId, String targetListId) {
        Response response = RestClient.postRequest(
                null,
                Constants.LISTS_ENDPOINT + "/" + sourceListId + "/moveAllCards?idBoard=" + targetListId
        );
        RestClient.validateStatusCode(response, 200);
    }
}
