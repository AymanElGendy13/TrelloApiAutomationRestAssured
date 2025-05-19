package ModulesScenarios;

import Pojo.CheckList;
import Pojo.List;
import Steps.BoardSteps;
import Steps.CardSteps;
import Steps.CheckListSteps;
import Pojo.Board;
import Pojo.Card;
import Utils.Logs;
import Utils.TrelloTestUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ChecklistTest extends BaseTest {
    private final TrelloTestUtils trelloUtils = new TrelloTestUtils();
    private SoftAssert softAssert = new SoftAssert();

    private String createdBoardId;
    private String createdListId;
    private String createdCardId;
    private String createdChecklistId;

    @BeforeClass
    public void setUp() {
        Logs.info("==============================CheckList Test=============================");

        // Create board with random test data
        Board board = trelloUtils.createAndVerifyBoard();
        createdBoardId = board.getBoardId();
        Logs.info("Created board ID: " + createdBoardId);

        // Create list in the board
        List list = trelloUtils.createAndVerifyList(board);
        createdListId = list.getListId();
        Logs.info("Created list ID: " + createdListId);

        // Create card in the list
        Card card = trelloUtils.createAndVerifyCard(list);
        createdCardId = card.getCardId();
        Logs.info("Created card ID: " + createdCardId);
    }

    @Test(priority = 1)
    public void createChecklistTest() {
        if (createdCardId == null) {
            throw new IllegalStateException("Card must be created first");
        }

        CheckList checklist = trelloUtils.createAndVerifyChecklist(CardSteps.getCard(createdCardId));
        createdChecklistId = checklist.getChecklistId();
        Logs.info("Created checklist ID: " + createdChecklistId);

        softAssert.assertNotNull(checklist, "Checklist should not be null");
        softAssert.assertNotNull(createdChecklistId, "Checklist ID should not be null");
        softAssert.assertEquals(checklist.getCardId(), createdCardId, "Checklist should belong to the created card");
    }

    @Test(priority = 2)
    public void getChecklistTest() {
        if (createdChecklistId == null) {
            throw new IllegalStateException("Checklist must be created first");
        }

        CheckList checklist = CheckListSteps.getChecklist(createdChecklistId);
        Logs.info("Retrieved checklist: " + checklist);

        softAssert.assertNotNull(checklist, "Checklist should exist");
        softAssert.assertEquals(checklist.getChecklistId(), createdChecklistId, "Checklist ID should match");
        softAssert.assertEquals(checklist.getCardId(), createdCardId, "Checklist should still belong to the original card");
    }

    @Test(priority = 3)
    public void updateChecklistTest() {
        if (createdChecklistId == null) {
            throw new IllegalStateException("Checklist must be created first");
        }

        CheckList updatedChecklist = trelloUtils.updateAndVerifyChecklist(
                CheckListSteps.getChecklist(createdChecklistId)
        );
        Logs.info("Updated checklist: " + updatedChecklist);

        // Verify the update persisted
        CheckList fetchedChecklist = CheckListSteps.getChecklist(createdChecklistId);
        softAssert.assertEquals(fetchedChecklist.getChecklistName(),
                updatedChecklist.getChecklistName(), "Checklist name should be updated");
        softAssert.assertEquals(fetchedChecklist.getChecklistId(), createdChecklistId,
                "Checklist ID should remain the same");
    }

    @Test(priority = 4)
    public void deleteChecklistTest() {
        if (createdChecklistId == null) {
            throw new IllegalStateException("Checklist must be created first");
        }

        trelloUtils.deleteAndVerifyChecklist(CheckListSteps.getChecklist(createdChecklistId));
        Logs.info("Checklist deleted successfully");

        // Verify deletion
        try {
            CheckListSteps.getChecklist(createdChecklistId);
            softAssert.fail("Checklist should not exist after deletion");
        } catch (RuntimeException e) {
            softAssert.assertTrue(
                    e.getMessage().contains("404") || e.getMessage().contains("not found"),
                    "Expected not found error"
            );
        }
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