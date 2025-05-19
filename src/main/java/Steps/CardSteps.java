package Steps;

import Pojo.Card;
import TestData.CardTestData;
import Utils.Constants;
import Utils.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;

public class CardSteps {

    @Step("Create card with random data in list: {listId}")
    public static Card createCard(String listId) {
        Map<String, Object> payload = CardTestData.randomCardPayload();
        payload.put("idList", listId);

        Response response = RestClient.postRequest(payload, Constants.CARDS_ENDPOINT);
        RestClient.validateStatusCode(response, 200);
        return response.as(Card.class);
    }

    @Step("Get card with ID: {cardId}")
    public static Card getCard(String cardId) {
        Response response = RestClient.getRequest(Constants.CARDS_ENDPOINT + "/" + cardId);
        RestClient.validateStatusCode(response, 200);
        return response.as(Card.class);
    }

    @Step("Update card with ID: {cardId}")
    public static Card updateCard(String cardId) {
        Map<String, Object> payload = CardTestData.randomCardPayload();

        Response response = RestClient.putRequest(
                payload,
                Constants.CARDS_ENDPOINT + "/" + cardId
        );
        RestClient.validateStatusCode(response, 200);
        return response.as(Card.class);
    }

    @Step("Update card description with ID: {cardId}")
    public static Card updateCardDescription(String cardId, String newDescription) {
        Map<String, Object> payload = Map.of("desc", newDescription);

        Response response = RestClient.putRequest(
                payload,
                Constants.CARDS_ENDPOINT + "/" + cardId
        );
        RestClient.validateStatusCode(response, 200);
        return response.as(Card.class);
    }

    @Step("Delete card with ID: {cardId}")
    public static void deleteCard(String cardId) {
        Response response = RestClient.deleteRequest(
                Constants.CARDS_ENDPOINT + "/" + cardId
        );
        RestClient.validateStatusCode(response, 200);
    }

    @Step("Add label to card: {cardId}")
    public static void addLabelToCard(String cardId, String labelId) {
        Map<String, Object> payload = Map.of("value", labelId);

        Response response = RestClient.postRequest(
                payload,
                Constants.CARDS_ENDPOINT + "/" + cardId + "/idLabels"
        );
        RestClient.validateStatusCode(response, 200);
    }
}
