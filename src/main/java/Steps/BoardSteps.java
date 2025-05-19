package Steps;

import Pojo.Board;
import TestData.BoardTestData;
import Utils.Constants;
import Utils.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;

public class BoardSteps {

    @Step("Create board with random data")
    public static Board createBoard() {
        Map<String, Object> payload = BoardTestData.randomBoardPayload();

        Response response = RestClient.postRequest(payload,Constants.BOARDS_ENDPOINT);
        RestClient.validateStatusCode(response, 200);
        return response.as(Board.class);
    }

    @Step("Get board with ID: {boardId}")
    public static Board getBoard(String boardId) {
        Response response = RestClient.getRequest(Constants.BOARDS_ENDPOINT + "/" + boardId);
        RestClient.validateStatusCode(response, 200);
        return response.as(Board.class);
    }

    @Step("Update board with ID: {boardId}")
    public static Board updateBoard(String boardId) {
        Map<String, Object> payload = BoardTestData.randomBoardPayload();

        Response response = RestClient.putRequest(payload,Constants.BOARDS_ENDPOINT + "/" + boardId);

        RestClient.validateStatusCode(response, 200);
        return response.as(Board.class);
    }

    @Step("Delete board: {boardId}")
    public static void deleteBoard(String boardId) {
        Response response = RestClient.deleteRequest(Constants.BOARDS_ENDPOINT + "/" + boardId);
        RestClient.validateStatusCode(response, 200);
    }

}
