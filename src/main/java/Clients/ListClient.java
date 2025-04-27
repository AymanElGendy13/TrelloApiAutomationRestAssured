package Clients;

import Pojo.List;
import Routes.Lists;
import Utils.Logs;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ListClient {

    @Step("Creating List with name: {listRequest.listName}")
    public List createList(List listRequest)
    {
        Logs.info("Creating list with name: " + listRequest.getListName());
        Response response = given()
                .contentType("application/json")
                .when()
                .post(Lists.createListUrl(listRequest.getBoardId(),listRequest.getListName()));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(List.class);
    }

    @Step("Deleting list with id: {listId}")
    public List getList(String listId)
    {
        Logs.info("Getting list with id: " + listId);
        Response response = given()
                .contentType("application/json")
                .when()
                .get(Lists.getListUrl(listId));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(List.class);
    }

    @Step("Updating list with id: {listId}")
    public List updateList(String listId,List listRequest)
    {
        Logs.info("Updating list with id: " + listRequest.getListId());
        Response response = given()
                .contentType("application/json")
                .when()
                .put(Lists.updateListUrl(listId,listRequest.getListName(),listRequest.getListPosition()));
        // First check status code
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(List.class);
    }



}
