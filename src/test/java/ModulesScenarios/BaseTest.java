package ModulesScenarios;

import Utils.TestNGListeners;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(TestNGListeners.class)
public class BaseTest {
    @BeforeClass
    @Step("Setting up Request and Response")
    public void setup() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
