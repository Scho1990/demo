Product Automation Framework

A production-ready, scalable Selenium automation framework built using Java, TestNG, and Maven, designed for real-world e-commerce applications with support for parallel execution, multi-browser testing, local & Selenium Grid execution, Excel-driven test data, and dual reporting (Extent + Allure).

🚀 Key Features

✔ Selenium 4 + Java + TestNG + Maven
✔ Page Object Model (POM) design
✔ Thread-safe parallel execution using ThreadLocal
✔ Local & Remote (Selenium Grid) execution
✔ Multi-browser support (Chrome, Firefox, Edge)
✔ Config-driven execution (no code change)
✔ Auto-wait wrappers for click() & sendKeys()
✔ JS click fallback & stale element retry
✔ Loader wait for cart / checkout flows
✔ Excel-based test data management
✔ Retry Analyzer for flaky tests
✔ ChainTest (Extent) HTML reports
✔ Allure reports with screenshots & steps
✔ Environment-based execution using Maven
✔ CI/CD ready (Jenkins compatible)

🧱 Technology Stack

| Layer          | Technology                  |
| -------------- | --------------------------- |
| Language       | Java 11+                    |
| Automation     | Selenium 4                  |
| Test Framework | TestNG                      |
| Build Tool     | Maven                       |
| Reporting      | Extent (ChainTest) + Allure |
| Data Driven    | Apache POI (Excel)          |
| Execution      | Local & Selenium Grid       |
| Browsers       | Chrome, Firefox, Edge       |


📁 Project Structure

product-automation
│
├── pom.xml
├── README.md
│
├── src/main/java/com/company/automation
│   ├── base            # Driver & Base classes
│   ├── config          # Config reader
│   ├── constants       # Framework constants
│   ├── exceptions      # Custom exceptions
│   ├── pages           # Page Object classes
│   ├── reports         # Extent report setup
│   └── utils           # Wait, Excel, Actions, Retry, Allure utils
│
├── src/test/java/com/company/automation/tests
│   ├── LoginTest.java
│   └── OrderTest.java
│
├── src/test/resources
│   ├── config
│   │   └── config.properties
│   ├── testdata
│   │   └── TestData.xlsx
│   ├── environments
│   │   ├── testng-qa.xml
│   │   └── testng-prod.xml
│   └── allure
│       └── environment.properties
│
├── reports              # Extent reports & screenshots
├── allure-results       # Allure raw results
└── allure-report        # Generated Allure report


⚙️ Configuration

execution.mode=local
browser=chrome
grid.url=http://localhost:4444/wd/hub
base.url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
retry.count=2

Supported Values

| Property       | Values                      |
| -------------- | --------------------------- |
| execution.mode | `local`, `remote`           |
| browser        | `chrome`, `firefox`, `edge` |


🌍 Environment-Based Execution

src/test/resources/environments
├── testng-qa.xml
├── testng-prod.xml

▶ Execution Commands

Default (Local, Chrome, QA)
mvn clean test

Local – Firefox
mvn clean test -Dbrowser=firefox

Local – Edge
mvn clean test -Dbrowser=edge

Remote – Selenium Grid
mvn clean test \
-Dexecution.mode=remote \
-Dbrowser=chrome \
-DsuiteXmlFile=src/test/resources/environments/testng-prod.xml

🧵 Parallel Execution

Parallel execution is enabled via TestNG:

<suite parallel="methods" thread-count="4">


Each test runs with an isolated WebDriver instance using ThreadLocal, making the framework safe for parallel and grid execution.

📊 Reporting
✅ Extent Reports (ChainTest)

Business-friendly HTML report

Location:

/reports/ChainTestReport.html

📸 Screenshots & Attachments

Screenshots and page source are automatically attached:

Extent → on failure

📊 Test Data Management

Excel file location:

src/test/resources/testdata/TestData.xlsx


Accessed using ExcelUtils

String user = ExcelUtils.getData("Login", 1, 0);

🔁 Retry Mechanism

Implemented using TestNG IRetryAnalyzer

Controlled via config:

retry.count=2


Retry attempts are visible in Allure reports

🧪 Sample Test
@Test(retryAnalyzer = RetryAnalyzer.class)
public void validLoginTest() {

    String user = ExcelUtils.getData("Login", 1, 0);
    String pass = ExcelUtils.getData("Login", 1, 1);

    ElementActions.sendKeys(email, user);
    ElementActions.sendKeys(password, pass);
    ElementActions.click(loginBtn);
}

🧠 Design Principles Followed

Single Responsibility Principle

Open / Closed Principle

Thread-safe driver lifecycle

Centralized waits & actions

Config-driven execution

Fail-fast exception handling

CI/CD-ready architecture

🔧 Prerequisites

Java 11+

Maven 3.8+

Chrome / Firefox / Edge

Docker (optional – for Selenium Grid)

Allure CLI (for Allure report)

🔮 Future Enhancements

Dockerized Selenium Grid

Jenkins pipeline

Cucumber BDD

API + UI hybrid execution

Performance & network validation

