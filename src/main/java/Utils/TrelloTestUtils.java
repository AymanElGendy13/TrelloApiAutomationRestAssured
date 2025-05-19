package Utils;

import Steps.BoardSteps;
import Steps.CardSteps;
import Steps.CheckListSteps;
import Steps.ListSteps;
import Pojo.Board;
import Pojo.Card;
import Pojo.CheckList;
import Pojo.List;
import org.testng.asserts.SoftAssert;

public class TrelloTestUtils {
    private final SoftAssert softAssert = new SoftAssert();

    // ========== BOARD OPERATIONS ==========
    public Board createAndVerifyBoard() {
        Board board = BoardSteps.createBoard();
        Logs.info("BOARD CREATED: " + board.getBoardName() + " | ID: " + board.getBoardId());

        Board fetchedBoard = BoardSteps.getBoard(board.getBoardId());
        softAssert.assertNotNull(board.getBoardId(), "Board ID should not be null");
        softAssert.assertNotNull(board.getBoardName(), "Board name should not be null");
        softAssert.assertEquals(fetchedBoard.getBoardName(), board.getBoardName());
        softAssert.assertEquals(fetchedBoard.getBoardDesc(), board.getBoardDesc());
        return board;
    }

    public Board updateAndVerifyBoard(Board board) {
        Board updatedBoard = BoardSteps.updateBoard(board.getBoardId());
        Logs.info("BOARD UPDATED: " + updatedBoard.getBoardName() + " | ID: " + board.getBoardId());

        Board fetchedBoard = BoardSteps.getBoard(board.getBoardId());
        softAssert.assertEquals(fetchedBoard.getBoardName(), updatedBoard.getBoardName());
        softAssert.assertEquals(fetchedBoard.getBoardDesc(), updatedBoard.getBoardDesc());
        return updatedBoard;
    }

    public void deleteAndVerifyBoard(Board board) {
        BoardSteps.deleteBoard(board.getBoardId());
        Logs.info("BOARD DELETED: " + board.getBoardName());
        verifyEntityDeleted(() -> BoardSteps.getBoard(board.getBoardId()));
    }

    // ========== LIST OPERATIONS ==========
    public List createAndVerifyList(Board board) {
        List list = ListSteps.createList(board.getBoardId());
        Logs.info("LIST CREATED: " + list.getListName() + " | ID: " + list.getListId());

        List fetchedList = ListSteps.getList(list.getListId());
        softAssert.assertEquals(fetchedList.getListName(), list.getListName());
        softAssert.assertEquals(fetchedList.getBoardId(), board.getBoardId());
        return list;
    }

    public List updateAndVerifyList(List list) {
        List updatedList = ListSteps.updateList(list.getListId());
        Logs.info("LIST UPDATED: " + updatedList.getListName() + " | ID: " + list.getListId());

        List fetchedList = ListSteps.getList(list.getListId());
        softAssert.assertEquals(fetchedList.getListName(), updatedList.getListName());
        return updatedList;
    }


    // ========== CARD OPERATIONS ==========
    public Card createAndVerifyCard(List list) {
        Card card = CardSteps.createCard(list.getListId());
        Logs.info("CARD CREATED: " + card.getCardName() + " | ID: " + card.getCardId());

        Card fetchedCard = CardSteps.getCard(card.getCardId());
        softAssert.assertEquals(fetchedCard.getCardName(), card.getCardName());
        softAssert.assertEquals(fetchedCard.getListId(), list.getListId());
        return card;
    }

    public Card updateAndVerifyCard(Card card) {
        String newDescription = "Updated description " + System.currentTimeMillis();
        Card updatedCard = CardSteps.updateCardDescription(card.getCardId(), newDescription);
        Logs.info("CARD UPDATED: " + card.getCardName() + " | ID: " + card.getCardId());

        Card fetchedCard = CardSteps.getCard(card.getCardId());
        softAssert.assertEquals(fetchedCard.getCardDescription(), newDescription);
        return fetchedCard;
    }

    public void deleteAndVerifyCard(Card card) {
        CardSteps.deleteCard(card.getCardId());
        Logs.info("CARD DELETED: " + card.getCardName());
        verifyEntityDeleted(() -> CardSteps.getCard(card.getCardId()));
    }

    // ========== CHECKLIST OPERATIONS ==========
    public CheckList createAndVerifyChecklist(Card card) {
        CheckList checklist = CheckListSteps.createChecklist(card.getCardId());
        Logs.info("CHECKLIST CREATED: " + checklist.getChecklistName() + " | ID: " + checklist.getChecklistId());

        CheckList fetchedChecklist = CheckListSteps.getChecklist(checklist.getChecklistId());
        softAssert.assertEquals(fetchedChecklist.getChecklistName(), checklist.getChecklistName());
        softAssert.assertEquals(fetchedChecklist.getCardId(), card.getCardId());
        return checklist;
    }

    public CheckList updateAndVerifyChecklist(CheckList checklist) {
        CheckList updatedChecklist = CheckListSteps.updateChecklistName(checklist.getChecklistId());
        Logs.info("CHECKLIST UPDATED: " + updatedChecklist.getChecklistName() + " | ID: " + checklist.getChecklistId());

        CheckList fetchedChecklist = CheckListSteps.getChecklist(checklist.getChecklistId());
        softAssert.assertEquals(fetchedChecklist.getChecklistName(), updatedChecklist.getChecklistName());
        return updatedChecklist;
    }

    public void deleteAndVerifyChecklist(CheckList checklist) {
        CheckListSteps.deleteChecklist(checklist.getChecklistId());
        Logs.info("CHECKLIST DELETED: " + checklist.getChecklistName());
        verifyEntityDeleted(() -> CheckListSteps.getChecklist(checklist.getChecklistId()));
    }

    // ========== COMMON UTILITIES ==========
    private void verifyEntityDeleted(Runnable fetchOperation) {
        try {
            fetchOperation.run();
            softAssert.fail("Entity should not exist after deletion");
        } catch (RuntimeException e) {
            softAssert.assertTrue(e.getMessage().contains("404") || e.getMessage().contains("not found"),
                    "Expected not found error but got: " + e.getMessage());
        }
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}