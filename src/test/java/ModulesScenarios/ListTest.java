package ModulesScenarios;

import Clients.BoardClient;
import Clients.ListClient;
import Payloads.BoardPayload;
import Payloads.ListPayload;
import Pojo.Board;
import Pojo.List;
import Utils.Logs;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Payloads.BoardPayload.getRandom;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class ListTest extends BaseTest {

    private final BoardClient boardClient = new BoardClient();
    private final ListClient listClient = new ListClient();
    private String createdBoardId;
    private String createdListId;

    @BeforeClass
    public void setUp() {
        Logs.info("===================================List Test===================================");
        // Create a board to associate with the list
        Board boardRequest = new Board(
                getRandom(BoardPayload.boardNames),
                getRandom(BoardPayload.boardDescriptions),
                true
        );
        Board createdBoard = boardClient.createBoard(boardRequest);
        Logs.info("Created board: " + createdBoard);
        createdBoardId = createdBoard.getBoardId();
        Logs.info(createdBoardId);
    }

    @Test(priority = 1)
    public void createListTest() {
        List listRequest = new List(
                createdBoardId,
                getRandom(ListPayload.listNames)
        );
        List createdList = listClient.createList(listRequest);
        Logs.info("Created list: " + createdList);
        assertNotNull(createdList.getListId());
        assertNotNull(createdList.getListName());
        assertEquals(createdList.getBoardId(),createdBoardId);
        assertEquals(createdList.getBoardId(), listRequest.getBoardId());
        createdListId = createdList.getListId();
    }

    @Test(priority = 2)
    public void getListTest() {
        Logs.info("Getting list with ID: " + createdListId);
        List retrievedList = listClient.getList(createdListId);
        Logs.info("Retrieved list: " + retrievedList);
        assertNotNull(retrievedList.getListId());
        assertNotNull(retrievedList.getListName());
        assertEquals(retrievedList.getListId(), createdListId);
    }

    @Test(priority = 3)
    public void updateListTest() {
        List updatedListRequest = new List(
                createdListId,
                getRandom(ListPayload.updatedListNames),
                getRandom(ListPayload.listPositions)
        );
        Logs.info("Updating list with ID: " + createdListId);
        List updatedList = listClient.updateList(createdListId,updatedListRequest);
        Logs.info("Updated list: " + updatedList);
        assertNotNull(updatedList.getListId());
        assertNotNull(updatedList.getListName());
        assertEquals(updatedList.getBoardId(), createdBoardId);
        assertEquals(updatedList.getListId(), updatedListRequest.getListId());
    }

    @AfterClass
    public void deleteBoard(){
        Logs.info("Deleting board with ID: " + createdBoardId);
        boardClient.deleteBoard(createdBoardId);
        Logs.info("Board deleted successfully");
    }
}
