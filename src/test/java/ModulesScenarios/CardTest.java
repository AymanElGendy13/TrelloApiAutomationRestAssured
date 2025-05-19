package ModulesScenarios;

import Pojo.List;
import Steps.BoardSteps;
import Steps.CardSteps;
import Steps.ListSteps;
import Pojo.Board;
import Pojo.Card;
import Utils.Logs;
import Utils.TrelloTestUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CardTest extends BaseTest {
    private final TrelloTestUtils trelloUtils = new TrelloTestUtils();
    private SoftAssert softAssert = new SoftAssert();

    private String createdBoardId;
    private String createdListId;
    private String createdCardId;

    @BeforeClass
    public void setUp() {
        Logs.info("========================================Card Test=========================================");

        // Create board with random test data
        Board board = trelloUtils.createAndVerifyBoard();
        createdBoardId = board.getBoardId();
        Logs.info("Created board ID: " + createdBoardId);

        // Create list in the board
        List list = trelloUtils.createAndVerifyList(board);
        createdListId = list.getListId();
        Logs.info("Created list ID: " + createdListId);
    }

    @Test(priority = 1)
    public void createCardTest() {
        Card card = trelloUtils.createAndVerifyCard(ListSteps.getList(createdListId));
        createdCardId = card.getCardId();
        Logs.info("Created card ID: " + createdCardId);

        softAssert.assertNotNull(card, "Card should not be null");
        softAssert.assertNotNull(createdCardId, "Card ID should not be null");
        softAssert.assertEquals(card.getListId(), createdListId, "Card should belong to the created list");
    }

    @Test(priority = 2)
    public void getCardTest() {
        Card card = CardSteps.getCard(createdCardId);
        Logs.info("Retrieved card: " + card);

        softAssert.assertNotNull(card, "Card should exist");
        softAssert.assertEquals(card.getCardId(), createdCardId, "Card ID should match");
        softAssert.assertEquals(card.getListId(), createdListId, "Card should still belong to the original list");
    }

    @Test(priority = 3)
    public void updateCardTest() {
        String newDescription = "Updated description " + System.currentTimeMillis();
        Card updatedCard = CardSteps.updateCardDescription(createdCardId, newDescription);
        Logs.info("Updated card: " + updatedCard);

        // Verify the update persisted
        Card fetchedCard = CardSteps.getCard(createdCardId);
        softAssert.assertEquals(fetchedCard.getCardDescription(), newDescription,
                "Card description should be updated");
        softAssert.assertEquals(fetchedCard.getCardId(), createdCardId,
                "Card ID should remain the same");
    }

    @AfterClass
    public void cleanUp() {
        Logs.info("Starting test cleanup");

        try {
            // Delete card if it exists
            if (createdCardId != null) {
                Logs.info("Deleting card with ID: " + createdCardId);
                trelloUtils.deleteAndVerifyCard(CardSteps.getCard(createdCardId));
            }

            // Delete board if it exists
            if (createdBoardId != null) {
                Logs.info("Deleting board with ID: " + createdBoardId);
                trelloUtils.deleteAndVerifyBoard(BoardSteps.getBoard(createdBoardId));
            }
        } catch (Exception e) {
            Logs.error("Cleanup failed: " + e.getMessage());
        }

        softAssert.assertAll();
    }
}