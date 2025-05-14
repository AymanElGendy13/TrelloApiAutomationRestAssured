package ModulesScenarios;

import Clients.BoardClient;
import Clients.CardClient;
import Clients.ChecklistClient;
import Clients.ListClient;
import Payloads.BoardPayload;
import Payloads.CardPayload;
import Payloads.ChecklistPayload;
import Payloads.ListPayload;
import Pojo.Board;
import Pojo.Card;
import Pojo.CheckList;
import Pojo.List;
import Utils.Logs;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Payloads.BoardPayload.getRandom;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ChecklistTest extends BaseTest {

    private final BoardClient boardClient = new BoardClient();
    private final ListClient listClient = new ListClient();
    private final CardClient cardClient = new CardClient();
    private final ChecklistClient checkListClient = new ChecklistClient();
    private String createdBoardId;
    private String createdListId;
    private String createdCardId;
    private String createdChecklistId;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setUp(){
        Logs.info("==============================CheckList Test=============================");
        // Create a board
        Board boardRequest = new Board(
                getRandom(BoardPayload.boardNames),
                getRandom(BoardPayload.boardDescriptions),
                true
        );
        Board createdBoard = boardClient.createBoard(boardRequest);
        Logs.info("Created board: " + createdBoard);
        createdBoardId = createdBoard.getBoardId();

        // Create a list
        List listRequest = new List(
                createdBoardId,
                getRandom(ListPayload.listNames)
        );
        Logs.info("created board id: " + createdBoardId);
        List createdList = listClient.createList(listRequest);
        Logs.info("Created list: " + createdList);
        createdListId = createdList.getListId();

        //Create a Card
        Card cardRequest = new Card(
                createdListId,
                getRandom(CardPayload.cardNames)
        );
        Card createdCard = cardClient.createCard(cardRequest);
        Logs.info("created list id: " + createdListId);
        createdCardId = createdCard.getCardId();

    }

    @Test(priority = 1)
    public void createChecklistTest() {
        CheckList checklistRequest = new CheckList(
                createdCardId,
                getRandom(ChecklistPayload.checklistNames)
        );
        Logs.info("Card id: " + createdCardId);
        Logs.info("Checklist name: " + checklistRequest.getChecklistName());
        CheckList createdChecklist = checkListClient.createCheckList(checklistRequest);
        Logs.info("Created checklist: " + createdChecklist);
        createdChecklistId = createdChecklist.getChecklistId();
        softAssert.assertNotNull(createdChecklist);
        softAssert.assertNotNull(createdChecklist.getChecklistId());
        softAssert.assertNotNull(createdChecklist.getChecklistName());
        softAssert.assertNotNull(createdChecklist.getCardId());
        softAssert.assertEquals(createdChecklist.getCardId(), createdCardId);

    }

    @Test(priority = 2)
    public void getChecklistTest() {
        CheckList checklist = checkListClient.getCheckList(createdChecklistId);
        Logs.info("Get checklist: " + checklist);
        softAssert.assertNotNull(checklist);
        softAssert.assertEquals(checklist.getChecklistId(), createdChecklistId);
    }

    @Test(priority = 3)
    public void updateChecklistTest() {
        CheckList checklistRequest = new CheckList(
                createdCardId,
                getRandom(ChecklistPayload.updatedChecklistNames)
        );
        Logs.info("Card id: " + createdCardId);
        CheckList updatedChecklist = checkListClient.updateCheckList(createdChecklistId,checklistRequest);
        Logs.info("Updated checklist: " + updatedChecklist);
        softAssert.assertNotNull(updatedChecklist);
        softAssert.assertEquals(updatedChecklist.getCardId(), createdCardId);
    }

    @Test(priority = 4)
    public void deleteChecklistTest() {
        checkListClient.deleteCheckList(createdChecklistId);
        Logs.info("Checklist deleted successfully");
    }

    @AfterClass
    public void deleteCardAndBoard()
    {
        // Delete the card
        cardClient.deleteCard(createdCardId);
        Logs.info("Card deleted successfully");

        // Delete the board
        boardClient.deleteBoard(createdBoardId);
        Logs.info("Board deleted successfully");
    }

}