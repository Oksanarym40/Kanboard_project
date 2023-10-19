package TestsUI.Tests;

import TestAPI.ApiBackend.ApiUtility;
import TestsUI.Pages.Homepage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;



public class HomepageTest {

    ApiUtility api;
    Homepage homepage;

    @BeforeClass
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("true") boolean headless) {
        Configuration.browser = browser;
        Configuration.headless = false;
        Configuration.timeout = 5000;
        // Prevent closing the browser after the test. TRUE for debug.
        Configuration.holdBrowserOpen = false;
        this.api = new ApiUtility();
        this.homepage = new Homepage();
    }

    @Test
    public void createProject() throws JsonProcessingException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        final String name = "UI_TEST_PROJECT";
        homepage.loginByAdmin();
        homepage.createProject(name);
        homepage.assertCreatedProject(name);
    }

    @AfterTest
    public void cleanUp() throws JsonProcessingException {
        api.removeAllProjects();
    }
}
