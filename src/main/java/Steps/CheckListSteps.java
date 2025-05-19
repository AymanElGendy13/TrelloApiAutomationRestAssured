package Steps;

import Pojo.CheckList;
import TestData.ChecklistTestData;
import Utils.Constants;
import Utils.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

public class CheckListSteps {

    @Step("Create checklist with random name for card: {cardId}")
    public static CheckList createChecklist(String cardId) {
        Map<String, Object> payload = ChecklistTestData.randomChecklistPayload(cardId);
        payload.put("idCard", cardId);

        Response response = RestClient.postRequest(payload, Constants.CHECKLISTS_ENDPOINT);
        RestClient.validateStatusCode(response, 200);
        return response.as(CheckList.class);
    }

    @Step("Get checklist with ID: {checklistId}")
    public static CheckList getChecklist(String checklistId) {
        Response response = RestClient.getRequest(Constants.CHECKLISTS_ENDPOINT + "/" + checklistId);
        RestClient.validateStatusCode(response, 200);
        return response.as(CheckList.class);
    }

    @Step("Update checklist name with ID: {checklistId}")
    public static CheckList updateChecklistName(String checklistId) {
        Map<String, Object> payload = Map.of(
                "name", ChecklistTestData.randomName()
        );

        Response response = RestClient.putRequest(
                payload,
                Constants.CHECKLISTS_ENDPOINT + "/" + checklistId
        );
        RestClient.validateStatusCode(response, 200);
        return response.as(CheckList.class);
    }

    @Step("Delete checklist with ID: {checklistId}")
    public static void deleteChecklist(String checklistId) {
        Response response = RestClient.deleteRequest(
                Constants.CHECKLISTS_ENDPOINT + "/" + checklistId
        );
        RestClient.validateStatusCode(response, 200);
    }

}
