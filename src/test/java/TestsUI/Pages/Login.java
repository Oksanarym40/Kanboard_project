package TestsUI.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Login extends PageObject {
    private final By usernameInput = By.xpath("//*[@id=\"form-username\"]");
    private final By passwordInput = By.xpath("//*[@id=\"form-password\"]");
//    private final By loginButton = By.xpath("//button[text()='']");
    private final By loginButton = By.xpath("/html/body/div/form/div[1]/button");
    private final By errorMessage = By.cssSelector(".error-message");
    private final String URL = "http://localhost/login";


    public Login() {
        super();
    }

    public Login openPage(){
        open(URL);
        return this;
    }

    public Login enterUsername(String username) {
        enterText(usernameInput, username);
        return this;
    }

    public Login enterPassword(String password) {
        enterText(passwordInput, password);
        return this;
    }

    public Login clickLoginButton() {
        clickElement(loginButton);
        return this;
    }

    public Login assertLogin(){
        Assert.assertEquals(URL, URL, "Login failed.");
        return this;
    }

    public Login assertBadLogin(){
        final By LOC_ERRORMSG = By.xpath("/html/body/div/p");
        final String ERRORMSG = "Bad username or password";
        Assert.assertEquals(super.getElement(LOC_ERRORMSG).getText().trim(),ERRORMSG);
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return $(errorMessage).isDisplayed();
    }

    public String getErrorMessageText() {
        return $(errorMessage).shouldBe(visible).getText();
    }
}
