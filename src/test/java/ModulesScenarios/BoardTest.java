package ModulesScenarios;

import Steps.BoardSteps;
import TestData.BoardTestData;
import Pojo.Board;
import Utils.Logs;
import Utils.TrelloTestUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BoardTest extends BaseTest {
    private final TrelloTestUtils trelloUtils = new TrelloTestUtils();
    private String createdBoardId;
    private SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    public void createBoardTest() {
        Logs.info("==================================Board Test==================================");

        // Create board with random test data
        Board createdBoard = trelloUtils.createAndVerifyBoard();
        Logs.info("Created board: " + createdBoard);

        createdBoardId = createdBoard.getBoardId();

        // Additional verifications if needed
        softAssert.assertNotNull(createdBoardId, "Board ID should not be null");
        softAssert.assertNotNull(createdBoard.getBoardName(), "Board name should not be null");
    }

    @Test(priority = 2)
    public void getBoardByIdTest() {
        Logs.info("Getting board by ID: " + createdBoardId);

        Board retrievedBoard = BoardSteps.getBoard(createdBoardId);
        Logs.info("Retrieved board: " + retrievedBoard);

        softAssert.assertEquals(retrievedBoard.getBoardId(), createdBoardId, "Board ID mismatch");
        softAssert.assertNotNull(retrievedBoard.getBoardName(), "Board name should not be null");
    }

    @Test(priority = 3)
    public void updateBoardByIdTest() {
        Logs.info("Updating board with ID: " + createdBoardId);

        // Update board with random test data
        Board updatedBoard = trelloUtils.updateAndVerifyBoard(BoardSteps.getBoard(createdBoardId));
        Logs.info("Updated board: " + updatedBoard);

        softAssert.assertEquals(updatedBoard.getBoardId(), createdBoardId, "Board ID should remain the same");

        // Verify update persisted
        Board fetchedBoard = BoardSteps.getBoard(createdBoardId);
        softAssert.assertEquals(fetchedBoard.getBoardName(), updatedBoard.getBoardName(),
                "Board name not updated properly");
        softAssert.assertEquals(fetchedBoard.getBoardDesc(), updatedBoard.getBoardDesc(),
                "Board description not updated properly");
    }

    @Test(priority = 4)
    public void deleteBoardTest() {
        Logs.info("Deleting board with ID: " + createdBoardId);

        trelloUtils.deleteAndVerifyBoard(BoardSteps.getBoard(createdBoardId));
        Logs.info("Board deleted successfully");

        // Verify deletion
        try {
            BoardSteps.getBoard(createdBoardId);
            softAssert.fail("Board should not exist after deletion");
        } catch (RuntimeException e) {
            softAssert.assertTrue(e.getMessage().contains("404") || e.getMessage().contains("not found"),
                    "Expected not found error");
        }

        softAssert.assertAll();
    }
}