# TrelloApiAutomationRestAssured
 
<a href="https://rest-assured.io"><img src="https://rest-assured.io/img/logo-transparent.png" width="50" alt="Rest Assured"/></a>

![Java](https://img.shields.io/badge/Java-23-red) ![Rest Assured](https://img.shields.io/badge/RestAssured-5.5.1-green) ![TestNG](https://img.shields.io/badge/TestNG-7.10.2-orange) ![Allure](https://img.shields.io/badge/Reporting-AllureReports-blue)


End-to-end **API test automation framework** for testing **Trello REST APIs** with full CRUD coverage and test data-driven design.

API Documentation
Link: [Trello REST API Docs](https://developer.atlassian.com/cloud/trello/rest/api-group-actions/)

## 🚀 Key Features

- **End-to-End Test Coverage** for Trello:
  - Boards, Lists, Cards, Attachments, Members.
- **Modular Framework Structure** with layered architecture.
- **Reusable Request Builders** and utilities.
- **JSON Data-Driven Testing** for flexible test input.
- **Robust Reporting**:
  - Allure Reports with request/response logs.
  - Log4j2 detailed logging.
- **Advanced Functionalities**:
  - TestNG Listeners.
  - Custom response validators.
  - Dynamic test data generation.
  - CI/CD integration ready (Jenkins/GitHub Actions).

## 🛠️ Technologies Used

| Component          | Technology Stack     |
|--------------------|----------------------|
| API Test Library   | Rest Assured 5.5.1   |
| Language           | Java 23              |
| Test Framework     | TestNG 7.10.2          |
| Data Format        | JSON                 |
| Reporting          | Allure + Log4j2      |
| Build Tool         | Maven                |
| Auth Handling      | API Key & Token      |

## 📂 Project Structure
```
trello-api-automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Pojo/             # POJOs for request/response bodies
│   │   │   ├── Steps/            # Test steps or workflow classes
│   │   │   ├── TestData/         # Test data classes
│   │   │   ├── Utils/            # Utility classes
│   │   │   └── resources/        # Resource files
│   │   │       ├── allure.properties
│   │   │       ├── config.properties
│   │   │       └── log4j2.properties
│   │   └── test/
│   │       ├── java/
│   │       │   ├── EndToEndScenario/  # E2E test scenarios
│   │       │   ├── TrelloE2ETest/    # E2E test classes
│   │       │   ├── ModulesScenarios/ # Module test scenarios
│   │       └── resources/
│   │           └── E2EDataDriven.json # Test data file
│   └── test-outputs/
│       ├── allure-results/       # Allure test results
│       ├── logs/                 # Log files
│       ├── target/               # Build output
│       └── TestNGRunners/       # TestNG runner files
└── pom.xml                      # Maven configuration
```

## 🔧 Installation & Execution
```bash
# Clone the repository
git clone https://github.com/AymanElGendy13/TrelloApiAutomationRestAssured.git

# Navigate into project directory
cd TrelloApiAutomationRestAssured

# Run specific suites via TestNG
mvn test -P"$Profile" ---> Replace Profile with any of the following .xml files: EndToEndAndModules or EndToEnd or Modules
```



