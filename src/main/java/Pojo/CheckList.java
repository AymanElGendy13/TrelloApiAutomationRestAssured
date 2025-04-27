package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckList {
    // Attributes
    @JsonProperty("id")
    private String checklistId;
    @JsonProperty("name")
    private String checklistName;
    @JsonProperty("idCard")
    private String cardId;

    // Constructors
    public CheckList() {
    }

    public CheckList(String cardId,String checklistName ) {
        this.cardId = cardId;
        this.checklistName = checklistName;
    }

    // Getters and Setters
    public String getChecklistId() {
        return checklistId;
    }
    public void setChecklistId(String checklistId) {
        this.checklistId = checklistId;
    }
    public String getChecklistName() {
        return checklistName;
    }
    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


    // toString method
    @Override
    public String toString() {
        return "CheckList{" +
                "checklistId='" + checklistId + '\'' +
                ", checklistName='" + checklistName + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
