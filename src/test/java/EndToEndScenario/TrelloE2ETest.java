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
    private final TrelloTestUtils trelloUtils = new TrelloTestUtils();
    private JsonUtils testData;

    @BeforeClass
    @Step("Setting up Request and Response")
    public void setup() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        testData = new JsonUtils("E2EDataDriven");
    }

    @Test
    public void basicUserFlow() {
        Logs.info("Starting Basic User Flow Test");

        // Create entities
        Board board = trelloUtils.createAndVerifyBoard();
        List list = trelloUtils.createAndVerifyList(board);
        Card card = trelloUtils.createAndVerifyCard(list);
        CheckList checklist = trelloUtils.createAndVerifyChecklist(card);

        // Cleanup
        trelloUtils.deleteAndVerifyChecklist(checklist);
        trelloUtils.deleteAndVerifyCard(card);
        trelloUtils.deleteAndVerifyBoard(board);

        trelloUtils.assertAll();
    }

    @Test
    public void trelloFullUserFlow() {
        Logs.info("Starting Complete User Flow Test");

        // Create and update board
        Board board = trelloUtils.createAndVerifyBoard();
        Board updatedBoard = trelloUtils.updateAndVerifyBoard(board);

        // Create and update list
        List list = trelloUtils.createAndVerifyList(updatedBoard);
        trelloUtils.updateAndVerifyList(list);


        // Create new active list
        List activeList = trelloUtils.createAndVerifyList(updatedBoard);

        // Create and update card
        Card card = trelloUtils.createAndVerifyCard(activeList);
        Card updatedCard = trelloUtils.updateAndVerifyCard(card);

        // Create and update checklist
        CheckList checklist = trelloUtils.createAndVerifyChecklist(updatedCard);
        CheckList updatedChecklist = trelloUtils.updateAndVerifyChecklist(checklist);

        // Cleanup
        trelloUtils.deleteAndVerifyChecklist(updatedChecklist);
        trelloUtils.deleteAndVerifyCard(updatedCard);
        trelloUtils.deleteAndVerifyBoard(updatedBoard);

        trelloUtils.assertAll();
    }

    @Test
    public void dataDrivenFlow() {
        Logs.info("Starting Data-Driven User Flow Test");

        // Create board with test data
        Board board = trelloUtils.createAndVerifyBoard();
        board.setBoardName(testData.getJsonData("data.boardName"));
        board.setBoardDesc(testData.getJsonData("data.boardDescription"));

        // Create list with test data
        List list = trelloUtils.createAndVerifyList(board);
        list.setListName(testData.getJsonData("data.listName"));

        // Create card with test data
        Card card = trelloUtils.createAndVerifyCard(list);
        card.setCardName(testData.getJsonData("data.cardName"));

        // Create checklist with test data
        CheckList checklist = trelloUtils.createAndVerifyChecklist(card);
        checklist.setChecklistName(testData.getJsonData("data.checklistName"));

        // Cleanup
        trelloUtils.deleteAndVerifyChecklist(checklist);
        trelloUtils.deleteAndVerifyCard(card);
        trelloUtils.deleteAndVerifyBoard(board);

        trelloUtils.assertAll();
    }
}