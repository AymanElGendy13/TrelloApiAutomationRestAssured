package Pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

    // Attributes
    @JsonProperty("id")
    private String boardId;
    @JsonProperty("name")
    private String boardName;
    @JsonProperty("desc")
    private String boardDesc;
    @JsonProperty("defaultLists")
    private boolean boardDefaultLists;

    // Constructors
    public Board() {
    }

    public Board(String boardName, String boardDesc, boolean boardDefaultLists) {
        this.boardName = boardName;
        this.boardDesc = boardDesc;
        this.boardDefaultLists = boardDefaultLists;
    }

    public Board(String boardId, String updatedBoardName, String updatedBoardDescription) {
        this.boardId = boardId;
        this.boardName = updatedBoardName;
        this.boardDesc = updatedBoardDescription;
    }

    // Getters and Setters
    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public boolean getBoardDefaultLists() {
        return boardDefaultLists;
    }

    public void setBoardDefaultLists(boolean boardDefaultLists) {
        this.boardDefaultLists = boardDefaultLists;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }


    // toString method
    @Override
    public String toString() {
        return "Board{" +
                "name='" + boardName + '\'' +
                ", defaultLists=" + boardDefaultLists +
                ", desc='" + boardDesc + '\'' +
                '}';
    }
}