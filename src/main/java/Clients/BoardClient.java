package Clients;

import Pojo.Board;
import Routes.*;
import Utils.Logs;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BoardClient {

    @Step("Creating Board with name: {boardRequest.boardName}")
    public Board createBoard(Board boardRequest) {
        Logs.info("Creating board with name: " + boardRequest.getBoardName());
        Response response = given()
                .contentType("application/json")
                .when()
                .post(Boards.createBoardUrl(
                        boardRequest.getBoardName(),
                        boardRequest.getBoardDesc(),
                        boardRequest.getBoardDefaultLists()
                ));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Board.class);
    }

    @Step("Getting board with ID: {boardId}")
    public Board getBoardById(String boardId) {
        Logs.info("Getting board with ID: " + boardId);
        Response response = given()
                .contentType("application/json")
                .when()
                .get(Boards.getBoardUrl(boardId));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Board.class);
    }

    @Step("Updating board with ID: {boardId}")
    public Board updateBoard(String boardId,Board boardRequest) {
        Logs.info("Updating board with ID: " + boardRequest.getBoardId());
        Response response = given()
                .contentType("application/json")
                .when()
                .put(Boards.updateBoardUrl(boardId, boardRequest.getBoardName(),boardRequest.getBoardDesc()));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Board.class);
    }

    @Step("Deleting board with ID: {boardId}")
    public Board deleteBoard(String boardId) {
        Logs.info("Deleting board with ID: " + boardId);
        Response response = given()
                .contentType("application/json")
                .when()
                .delete(Boards.deleteBoardUrl(boardId));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(Board.class);
    }
}