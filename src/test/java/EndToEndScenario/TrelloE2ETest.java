package EndToEndScenario;

import Pojo.*;
import Utils.JsonUtils;
import Utils.Logs;
import Utils.TestNGListeners;
import Utils.TrelloTestUtils;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class TrelloE2ETest {

    private final TrelloTestUtils TrelloApiClient = new TrelloTestUtils();
    JsonUtils testData;

    @BeforeClass
    @Step("Setting up Request and Response")
    public void setup() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        testData = new JsonUtils("E2EDataDriven");
    }

    @Test
    public void BasicUserFlow() {
        Logs.info("Starting Basic User Flow Test");

        Board board = TrelloApiClient.createAndVerifyBoard(
                testData.getJsonData("data.boardName"),
                testData.getJsonData("data.boardDescription")
        );

        List list = TrelloApiClient.createAndVerifyList(board, testData.getJsonData("data.listName"));
        Card card = TrelloApiClient.createAndVerifyCard(list, testData.getJsonData("data.cardName"));
        CheckList checkList = TrelloApiClient.createAndVerifyChecklist(card, testData.getJsonData("data.checklistName"));

        TrelloApiClient.deleteAndVerifyChecklist(checkList);
        TrelloApiClient.deleteAndVerifyCard(card);
        TrelloApiClient.deleteAndVerifyBoard(board);
    }

    @Test
    public void TrelloFullUserFlow() {
        Logs.info("Starting Complete User Flow Test");

        Board board = TrelloApiClient.createAndVerifyBoard(testData.getJsonData("data.boardName"),testData.getJsonData("data.boardDescription"));
        Board updatedBoard = TrelloApiClient.updateAndVerifyBoard(board, testData.getJsonData("data.updatedBoardName"), testData.getJsonData("data.updatedBoardDescription"));
        List list = TrelloApiClient.createAndVerifyList(board, testData.getJsonData("data.listName"));
        TrelloApiClient.updateAndVerifyList(list, testData.getJsonData("data.updatedListName"), testData.getJsonData("data.NewPosition"));
        Card card = TrelloApiClient.createAndVerifyCard(list, testData.getJsonData("data.cardName"));
        Card updatedCard = TrelloApiClient.updateAndVerifyCard(card, testData.getJsonData("data.updatedCardName"), testData.getJsonData("data.updatedCardDescription"));
        CheckList checkList = TrelloApiClient.createAndVerifyChecklist(card, testData.getJsonData("data.checklistName"));
        CheckList updatedCheckList = TrelloApiClient.updateAndVerifyChecklist(checkList, testData.getJsonData("data.updatedChecklistName"));

        TrelloApiClient.deleteAndVerifyChecklist(updatedCheckList);
        TrelloApiClient.deleteAndVerifyCard(updatedCard);
        TrelloApiClient.deleteAndVerifyBoard(updatedBoard);
    }

}