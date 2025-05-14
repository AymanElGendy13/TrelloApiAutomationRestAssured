package ModulesScenarios;

import Clients.BoardClient;
import Clients.CardClient;
import Clients.ListClient;
import Payloads.BoardPayload;
import Payloads.CardPayload;
import Payloads.ListPayload;
import Pojo.Board;
import Pojo.Card;
import Pojo.List;
import Utils.Logs;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Payloads.BoardPayload.getRandom;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CardTest extends BaseTest {

    private final BoardClient boardClient = new BoardClient();
    private final ListClient listClient = new ListClient();
    private final CardClient cardClient = new CardClient();
    private String createdBoardId;
    private String createdListId;
    private String createdCardId;
    private String createdAttachmentId;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void setUp(){
        Logs.info("========================================Card Test=========================================");
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
        Logs.info("created list id: " + createdListId);
    }

    @Test(priority = 1)
    public void createCardTest() {
        Card cardRequest = new Card(
                createdListId,
                getRandom(CardPayload.cardNames)
        );
        Card createdCard = cardClient.createCard(cardRequest);
        Logs.info("Created card: " + createdCard);
        createdCardId = createdCard.getCardId();
        softAssert.assertNotNull(createdCard);
        softAssert.assertNotNull(createdCardId);

    }

    @Test(priority = 2)
    public void getCardTest() {
        Card card = cardClient.getCard(createdCardId);
        Logs.info("Get card: " + card);
        softAssert.assertNotNull(card);
        softAssert.assertEquals(card.getCardId(), createdCardId);
    }

    @Test(priority = 3)
    public void updateCardTest() {
        Card cardRequest = new Card(
                createdCardId,
                getRandom(CardPayload.updatedCardDescriptions)
        );
        Logs.info("Card id: " + createdCardId);
        Card updatedCard = cardClient.updateCard(createdCardId,cardRequest);
        Logs.info("Updated card: " + updatedCard);
        softAssert.assertNotNull(updatedCard);
        softAssert.assertEquals(updatedCard.getCardId(), createdCardId);
    }

    @AfterClass
    public void deleteBoardAndCard()
    {
        Logs.info("Deleting Card with ID: " + createdCardId);
        cardClient.deleteCard(createdCardId);
        Logs.info("Card deleted successfully");

        //Delete Board
        Logs.info("Deleting Board with ID: " + createdBoardId);
        boardClient.deleteBoard(createdBoardId);
        Logs.info("Board deleted successfully");
    }

}