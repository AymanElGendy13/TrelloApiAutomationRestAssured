package ModulesScenarios;

import Clients.BoardClient;
import Payloads.BoardPayload;
import Pojo.Board;
import Utils.Logs;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static Payloads.BoardPayload.getRandom;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BoardTest extends BaseTest {
    private final BoardClient boardClient = new BoardClient();
    private String createdBoardId;

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
        assertNotNull(createdBoard.getBoardId());
        assertEquals(createdBoard.getBoardName(), boardRequest.getBoardName());
        assertEquals(createdBoard.getBoardDesc(), boardRequest.getBoardDesc());
        createdBoardId = createdBoard.getBoardId();
    }

    @Test(priority = 2)
    public void getBoardByIdTest() {
        Logs.info("Getting board by ID: " + createdBoardId);
        Board retrievedBoard = boardClient.getBoardById(createdBoardId);
        assertNotNull(retrievedBoard);
        assertNotNull(retrievedBoard.getBoardId());
        assertEquals(retrievedBoard.getBoardId(), createdBoardId);
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
        assertNotNull(updatedBoard.getBoardId());
        assertEquals(updatedBoard.getBoardId(), createdBoardId);
        assertEquals(updatedBoard.getBoardName(), boardRequest.getBoardName());
        assertEquals(updatedBoard.getBoardDesc(), boardRequest.getBoardDesc());
    }

    @Test(priority = 4)
    public void deleteBoard() {
        Logs.info("Deleting board with ID: " + createdBoardId);
        boardClient.deleteBoard(createdBoardId);
        Logs.info("Board deleted successfully");
    }

}
