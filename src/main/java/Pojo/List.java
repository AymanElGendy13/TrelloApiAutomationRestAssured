package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class List {
    // Attributes
    @JsonProperty("idBoard")
    private String boardId;
    @JsonProperty("id")
    private String listId;
    @JsonProperty("name")
    private String listName;
    @JsonProperty("pos")
    private String listPosition;

    // Constructors
    public List() {
    }

    public List(String boardId,String listName) {
        this.boardId = boardId;
        this.listName = listName;
    }

    public List(String listId, String updatedListName, String listPosition) {
        this.listId = listId;
        this.listName = updatedListName;
        this.listPosition = listPosition;
    }


    // Getters and Setters
    public String getBoardId() {
        return boardId;
    }
    public void setBoardId(String boardID) {
        this.boardId = boardID;
    }

    public String getListId() {
        return listId;
    }
    public void setListId(String listId) {
        this.listId = listId;
    }
    public String getListName() {
        return listName;
    }
    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListPosition() {
        return listPosition;
    }

    public void setListPosition(String listPosition) {
        this.listPosition = listPosition;
    }

    // toString method
    @Override
    public String toString() {
        return "List{" +
                ", listName='" + listName + '\'' +
                '}';
    }
}
