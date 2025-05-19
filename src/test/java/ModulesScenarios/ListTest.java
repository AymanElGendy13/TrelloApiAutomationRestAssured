package ModulesScenarios;

import Pojo.List;
import Steps.BoardSteps;
import Steps.ListSteps;
import Pojo.Board;
import Utils.Logs;
import Utils.TrelloTestUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ListTest extends BaseTest {
    private final TrelloTestUtils trelloUtils = new TrelloTestUtils();
    private SoftAssert softAssert = new SoftAssert();

    private String createdBoardId;
    private String createdListId;

    @BeforeClass
    public void setUp() {
        Logs.info("===================================List Test===================================");

        // Create board with random test data
        Board board = trelloUtils.createAndVerifyBoard();
        createdBoardId = board.getBoardId();
        Logs.info("Created board ID: " + createdBoardId);
    }

    @Test(priority = 1)
    public void createListTest() {
        if (createdBoardId == null) {
            throw new IllegalStateException("Board must be created first");
        }

        List list = trelloUtils.createAndVerifyList(BoardSteps.getBoard(createdBoardId));
        createdListId = list.getListId();
        Logs.info("Created list ID: " + createdListId);

        softAssert.assertNotNull(list, "List should not be null");
        softAssert.assertNotNull(createdListId, "List ID should not be null");
        softAssert.assertEquals(list.getBoardId(), createdBoardId, "List should belong to the created board");
    }

    @Test(priority = 2)
    public void getListTest() {
        if (createdListId == null) {
            throw new IllegalStateException("List must be created first");
        }

        List list = ListSteps.getList(createdListId);
        Logs.info("Retrieved list: " + list);

        softAssert.assertNotNull(list, "List should exist");
        softAssert.assertEquals(list.getListId(), createdListId, "List ID should match");
        softAssert.assertEquals(list.getBoardId(), createdBoardId, "List should still belong to the original board");
    }

    @Test(priority = 3)
    public void updateListTest() {
        if (createdListId == null) {
            throw new IllegalStateException("List must be created first");
        }

        List updatedList = trelloUtils.updateAndVerifyList(ListSteps.getList(createdListId));
        Logs.info("Updated list: " + updatedList);

        // Verify the update persisted
        List fetchedList = ListSteps.getList(createdListId);
        softAssert.assertEquals(fetchedList.getListName(),
                updatedList.getListName(), "List name should be updated");
        softAssert.assertEquals(fetchedList.getListId(), createdListId,
                "List ID should remain the same");
    }

    @AfterClass
    public void cleanUp() {
        Logs.info("Starting test cleanup");

        try {
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