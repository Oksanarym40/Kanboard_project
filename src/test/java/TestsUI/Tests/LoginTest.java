package TestsUI.Tests;

import TestsUI.Pages.Login;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;

@Listeners({AllureTestNg.class})
public class LoginTest {
    private final String password = "admin";
    private Login loginPage;

    @BeforeClass
    @Parameters({ "browser", "headless" })
    public void setUp(@Optional("chrome") String browser, @Optional("true") boolean headless) {
        Configuration.browser = browser;
        Configuration.headless = false;
        Configuration.timeout = 5000;
        // Prevent closing the browser after the test. TRUE for debug.
        Configuration.holdBrowserOpen = true;
        loginPage = new Login();
        // Open the UI in localhost. Adjust the URL as needed.
        Configuration.baseUrl = "http://localhost:8080";
    }

    @Test(priority = 1)
    public void invalidLogin1() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        String badUsername1 = "qwrt43";
        loginPage.openPage()
                .enterUsername(badUsername1)
                .enterPassword(password)
                .clickLoginButton()
                .assertBadLogin();
    }

    @Test(priority = 2)
    public void invalidLogin2() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        String badUsername2 = "invalidUser";
        loginPage.openPage()
                .enterUsername(badUsername2)
                .enterPassword(password)
                .clickLoginButton()
                .assertBadLogin();
    }

    @Test(priority = 3)
    public void invalidLogin3() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        String badUsername3 = "incorrectUser";
        loginPage.openPage()
                .enterUsername(badUsername3)
                .enterPassword(password)
                .clickLoginButton()
                .assertBadLogin();
    }

    @Test(priority = 4)
    public void validLogin() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        String username = "admin";
        loginPage.openPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton()
                .assertLogin();
    }
}