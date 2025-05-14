package Utils;

import org.testng.*;

import java.io.File;
import static Utils.PropertiesUtil.loadProperties;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    File allureResults = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/Logs");

    @Override
    public void onExecutionStart() {
        Logs.info("Text Execution Started");
        loadProperties();
        FilesUtil.deleteFiles(allureResults);
        FilesUtil.cleanDirectory(logs);
        FilesUtil.createDirectory(allureResults);
        FilesUtil.createDirectory(logs);
    }

    @Override
    public void onExecutionFinish() {
        Logs.info("Text Execution Finished");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if(method.isTestMethod()) {
            Logs.info("Test Method " + method.getTestMethod().getMethodName() + " Finished");
            AllureUtil.attachLogsToAllureReport();
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> {
                    Logs.info("Test Method " + method.getTestMethod().getMethodName() + " Passed");
                }
                case ITestResult.FAILURE -> {
                    Logs.info("Test Method " + method.getTestMethod().getMethodName() + " Failed");
                }
                case ITestResult.SKIP -> {
                    Logs.info("Test Method " + method.getTestMethod().getMethodName() + " Skipped");
                }
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("Test Case " + result.getName() + " Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("Test Case " + result.getName() + " Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("Test Case " + result.getName() + " Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("Test Case " + result.getName() + " Skipped");
    }

}
