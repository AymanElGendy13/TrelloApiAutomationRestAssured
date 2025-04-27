package Pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    // Attributes
    @JsonProperty("idList")
    private String listId;

    @JsonProperty("name")
    private String cardName;

    @JsonProperty("id")
    private String cardId;

    @JsonProperty("desc")
    private String cardDescription;

    @JsonAlias("id")
    private String attachmentId;


    // Constructor
    public Card() {

    }

    public Card(String listId, String cardName) {
        this.listId = listId;
        this.cardName = cardName;
    }

    public Card(String listId, String cardName, String cardDescription) {
        this.listId = listId;
        this.cardName = cardName;
        this.cardDescription = cardDescription;
    }

    // Getters and Setters
    public String getListId() {
        return listId;
    }
    public void setListId(String listId) {
        this.listId = listId;
    }
    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getCardDescription() {
        return cardDescription;
    }
    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    // toString method
    @Override
    public String toString() {
        return "Card{" +
                "listId='" + listId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardId='" + cardId + '\'' +
                ", cardDescription='" + cardDescription + '\'' +
                '}';
    }

}
