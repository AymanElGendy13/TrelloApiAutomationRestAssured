package Clients;

import Pojo.Card;
import Routes.Cards;
import Utils.Logs;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CardClient {

    @Step("Creating card with name: {cardRequest.cardName} and list ID: {cardRequest.listId}")
    public Card createCard(Card cardRequest) {
    Logs.info("Creating card with request: " + cardRequest);

    Response response = given()
            .contentType("application/json")
            .when()
            .post(Cards.createCardUrl(
                    cardRequest.getListId(),
                    cardRequest.getCardName()
            ));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Card.class);

    }

    @Step("Getting card with ID: {cardId}")
    public Card getCard(String cardId) {
        Logs.info("Getting card with ID: " + cardId);

        Response response = given()
                .contentType("application/json")
                .when()
                .get(Cards.getCardUrl(cardId));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Card.class);
    }

    @Step("Updating card with ID: {cardId} and request: {cardRequest}")
    public Card updateCard(String cardId,Card cardRequest) {
        Logs.info("Updating card with request: " + cardRequest);
        Logs.info("Updating card with name: " + cardRequest.getCardName());

        Response response = given()
                .contentType("application/json")
                .when()
                .put(Cards.updateCardUrl(
                        cardId,
                        cardRequest.getCardDescription()
                ));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Card.class);
    }

    @Step("Deleting card with ID: {cardId}")
    public void deleteCard(String cardId) {
        Logs.info("Deleting card with ID: " + cardId);

        Response response = given()
                .contentType("application/json")
                .when()
                .delete(Cards.deleteCardUrl(cardId));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
    }

}
