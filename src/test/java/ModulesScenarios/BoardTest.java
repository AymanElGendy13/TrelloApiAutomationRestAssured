package ModulesScenarios;

import Clients.BoardClient;
import Payloads.BoardPayload;
import Pojo.Board;
import Utils.Logs;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static Payloads.BoardPayload.getRandom;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BoardTest extends BaseTest {
    private final BoardClient boardClient = new BoardClient();
    private String createdBoardId;
    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    public void createBoardTest() {
        Logs.info("==================================Board Test==================================");
        Board boardRequest = new Board(
                getRandom(BoardPayload.boardNames),
                getRandom(BoardPayload.boardDescriptions),
                true
        );

        Board createdBoard = boardClient.createBoard(boardRequest);
        Logs.info("Created board: " + createdBoard);
        softAssert.assertNotNull(createdBoard.getBoardId());
        softAssert.assertEquals(createdBoard.getBoardName(), boardRequest.getBoardName());
        softAssert.assertEquals(createdBoard.getBoardDesc(), boardRequest.getBoardDesc());
        createdBoardId = createdBoard.getBoardId();
    }

    @Test(priority = 2)
    public void getBoardByIdTest() {
        Logs.info("Getting board by ID: " + createdBoardId);
        Board retrievedBoard = boardClient.getBoardById(createdBoardId);
        softAssert.assertNotNull(retrievedBoard);
        softAssert.assertNotNull(retrievedBoard.getBoardId());
        softAssert.assertEquals(retrievedBoard.getBoardId(), createdBoardId);
    }

    @Test(priority = 3)
    public void updateBoardByIdTest() {
        Logs.info("Updating board with ID: " + createdBoardId);
        Board boardRequest = new Board(
                createdBoardId,
                getRandom(BoardPayload.updatedBoardNames),
                getRandom(BoardPayload.updatedBoardDescriptions)
        );

        Board updatedBoard = boardClient.updateBoard(createdBoardId,boardRequest);
        Logs.info("Updated board: " + updatedBoard);
        softAssert.assertNotNull(updatedBoard.getBoardId());
        softAssert.assertEquals(updatedBoard.getBoardId(), createdBoardId);
        softAssert.assertEquals(updatedBoard.getBoardName(), boardRequest.getBoardName());
        softAssert.assertEquals(updatedBoard.getBoardDesc(), boardRequest.getBoardDesc());
    }

    @Test(priority = 4)
    public void deleteBoard() {
        Logs.info("Deleting board with ID: " + createdBoardId);
        boardClient.deleteBoard(createdBoardId);
        Logs.info("Board deleted successfully");
    }

}