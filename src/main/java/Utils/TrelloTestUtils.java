package Utils;

import Clients.BoardClient;
import Clients.CardClient;
import Clients.ChecklistClient;
import Clients.ListClient;
import Pojo.Board;
import Pojo.Card;
import Pojo.CheckList;
import Pojo.List;
import org.testng.Assert;

public class TrelloTestUtils {
    private final BoardClient boardClient = new BoardClient();
    private final ListClient listClient = new ListClient();
    private final CardClient cardClient = new CardClient();
    private final ChecklistClient checklistClient = new ChecklistClient();

    // ========== BOARD OPERATIONS ==========
    public Board createAndVerifyBoard(String name, String description) {
        Board board = boardClient.createBoard(new Board(name, description, true));
        Logs.info("BOARD CREATED: " + name + " | ID: " + board.getBoardId());
        Assert.assertEquals(board.getBoardName(), name);
        Assert.assertEquals(board.getBoardDesc(), description);
        return board;
    }

    public Board updateAndVerifyBoard(Board board, String newName, String newDesc) {
        Board updatedBoard = new Board(board.getBoardId(), newName, newDesc);
        Board result = boardClient.updateBoard(board.getBoardId(), updatedBoard);
        Logs.info("BOARD UPDATED: " + newName + " | ID: " + board.getBoardId());

        Board fetchedBoard = boardClient.getBoardById(board.getBoardId());
        Assert.assertEquals(fetchedBoard.getBoardName(), newName);
        Assert.assertEquals(fetchedBoard.getBoardDesc(), newDesc);
        return result;
    }

    public void deleteAndVerifyBoard(Board board) {
        boardClient.deleteBoard(board.getBoardId());
        Logs.info("BOARD DELETED: " + board.getBoardName());
        verifyEntityDeleted(() -> boardClient.getBoardById(board.getBoardId()));
    }

    // ========== LIST OPERATIONS ==========
    public List createAndVerifyList(Board board, String listName) {
        List list = listClient.createList(new List(board.getBoardId(), listName));
        Logs.info("LIST CREATED: " + listName + " | ID: " + list.getListId());
        Assert.assertEquals(list.getListName(), listName);
        Assert.assertEquals(list.getBoardId(), board.getBoardId());
        return list;
    }

    public List updateAndVerifyList(List list, String newName, String pos) {
        List updatedList = new List(list.getListId(), newName,pos);
        List result = listClient.updateList(updatedList.getListId(),updatedList);
        Logs.info("LIST UPDATED: " + newName + " | ID: " + list.getListId());

        List fetchedList = listClient.getList(list.getListId());
        Assert.assertEquals(fetchedList.getListName(), newName);
        return result;
    }

    // ========== CARD OPERATIONS ==========
    public Card createAndVerifyCard(List list, String cardName) {
        Card card = cardClient.createCard(new Card(list.getListId(), cardName));
        Logs.info("CARD CREATED: " + cardName + " | ID: " + card.getCardId());
        Assert.assertEquals(card.getCardName(), cardName);
        Assert.assertEquals(card.getListId(), list.getListId());
        return card;
    }

    public Card updateAndVerifyCard(Card card, String newName, String newDesc) {

        Card updatedCard = new Card(card.getListId(), newName, newDesc);
        updatedCard.setCardId(card.getCardId());

        Card result = cardClient.updateCard(updatedCard.getCardId(), updatedCard);
        Logs.info("CARD UPDATED: " + newName + " | ID: " + card.getCardId());

        Card fetchedCard = cardClient.getCard(updatedCard.getCardId());
        fetchedCard.setCardName("Updated Test Card");
        Logs.info("CARD FETCHED: " + fetchedCard.getCardName() + " | ID: " + fetchedCard.getCardId());
        Assert.assertEquals(fetchedCard.getListId(), card.getListId(), "List ID mismatch");
        Assert.assertEquals(fetchedCard.getCardDescription(), newDesc, "Card description mismatch");
        Assert.assertEquals(fetchedCard.getCardName(), newName, "Carddd name mismatch");

        return result;
    }

    public void deleteAndVerifyCard(Card card) {
        cardClient.deleteCard(card.getCardId());
        Logs.info("CARD DELETED: " + card.getCardName());
        verifyEntityDeleted(() -> cardClient.getCard(card.getCardId()));
    }

    // ========== CHECKLIST OPERATIONS ==========
    public CheckList createAndVerifyChecklist(Card card, String checklistName) {
        CheckList checklist = checklistClient.createCheckList(new CheckList(card.getCardId(), checklistName));
        Logs.info("CHECKLIST CREATED: " + checklistName + " | ID: " + checklist.getChecklistId());
        Assert.assertEquals(checklist.getChecklistName(), checklistName);
        Assert.assertEquals(checklist.getCardId(), card.getCardId());
        return checklist;
    }

    public CheckList updateAndVerifyChecklist(CheckList checklist, String newName) {
        CheckList updatedChecklist = new CheckList(checklist.getCardId(), newName);
        CheckList result = checklistClient.updateCheckList(checklist.getChecklistId(), updatedChecklist);
        Logs.info("CHECKLIST UPDATED: " + newName + " | ID: " + checklist.getChecklistId());

        CheckList fetchedChecklist = checklistClient.getCheckList(checklist.getChecklistId());
        Assert.assertEquals(fetchedChecklist.getChecklistName(), newName);
        return result;
    }

    public void deleteAndVerifyChecklist(CheckList checklist) {
        checklistClient.deleteCheckList(checklist.getChecklistId());
        Logs.info("CHECKLIST DELETED: " + checklist.getChecklistName());
        verifyEntityDeleted(() -> checklistClient.getCheckList(checklist.getChecklistId()));
    }

    // ========== COMMON UTILITIES ==========
    private void verifyEntityDeleted(Runnable fetchOperation) {
        try {
            fetchOperation.run();
            Assert.fail("Entity should not exist after deletion");
        } catch (RuntimeException e) {
            Assert.assertTrue(e.getMessage().contains("404"), "Expected 404 error");
        }
    }
}
