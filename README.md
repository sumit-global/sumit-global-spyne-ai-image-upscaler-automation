**Spyne.ai Image Upscaler Automation Project**

This project automates the core functionalities of the Spyne.ai Image Upscaler using Selenium WebDriver, TestNG, Maven, and Extent Reports.
It verifies several critical functionalities like navigation, file upload, image upscaling and UI validation.

**Tools & Technologies**

* **Programming Language:** Java
* **Automation Framework:** Selenium WebDriver
* **Test Runner:** TestNG
* **Reporting:** Extent Reports
* **Build Tool:** Maven
* **Browsers:** Chrome (configurable for other browsers)

**Prerequisites**

Before running the tests, ensure the following are installed:

* **Java JDK** (version 8 or above)
* **Maven** (version 3 or above)
* **Google Chrome** or any other browser you want to test on

**Project Setup**

1. Clone the repository:

   git clone https://github.com/sumit-global/sumit-global-spyne-ai-image-upscaler-automation.git
   cd spyne-ai-image-upscaler-automation

2. Install dependencies: Run the following command to download all the project dependencies:
   `mvn clean install`

3. Run the tests: You can execute the tests by running:
   `mvn test -Dbrowser=chrome -Dheadless=true`

**Reports**
The project uses Extent Reports to generate a detailed HTML report for test execution. After running the tests, you can find the report under the Reports folder:
 `./Reports/SpyneTestReport.html`

This report includes:

* Test execution results (pass/fail)
* Screenshots of failed test cases
* Detailed logs


