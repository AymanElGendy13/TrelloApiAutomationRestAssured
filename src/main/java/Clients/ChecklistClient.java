package Clients;

import Pojo.Card;
import Pojo.CheckList;
import Routes.CheckLists;
import Utils.Logs;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ChecklistClient {

    @Step("Creating CheckList with name: {checkListRequest.checklistName}")
    public CheckList createCheckList(CheckList checkListRequest) {
        Logs.info("Creating CheckList: " + checkListRequest);
        Response response = given()
                .contentType("application/json")
                .when()
                .post(CheckLists.createCheckListUrl(
                        checkListRequest.getCardId(),
                        checkListRequest.getChecklistName()
                ));

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("API request failed with status: " +
                    response.getStatusCode() + "\nResponse: " + response.asString());
        }
        // Then try parsing JSON
        return response.as(CheckList.class);

    }

    @Step("Getting CheckList with ID: {checklistId}")
    public CheckList getCheckList(String checklistId) {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(CheckLists.getCheckListUrl(checklistId));

        if(response.getStatusCode() == 200) {
            return response.as(CheckList.class);
        } else {
            throw new RuntimeException("Failed to get checklist: " + response.getStatusLine());
        }
    }

    @Step("Updating CheckList with ID: {checklistId}")
    public CheckList updateCheckList(String checklistId,CheckList checkListRequest) {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .put(CheckLists.updateCheckListUrl(checklistId,checkListRequest.getChecklistName()));

        if(response.getStatusCode() == 200) {
            return response.as(CheckList.class);
        } else {
            throw new RuntimeException("Failed to update checklist: " + response.getStatusLine());
        }
    }

    @Step("Deleting CheckList with ID: {checklistId}")
    public void deleteCheckList(String checklistId) {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .delete(CheckLists.deleteCheckListUrl(checklistId));

        if(response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to delete checklist: " + response.getStatusLine());
        }
    }


}
