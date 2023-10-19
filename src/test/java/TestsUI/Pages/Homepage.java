package TestsUI.Pages;

import TestAPI.ApiBackend.ApiUtility;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homepage extends PageObject {
    private final String URL = "http://localhost/";
    public Homepage() {
        super();
        Selenide.open(URL);
    }
    public Homepage createProject(String name){
        By by = By.xpath("/html/body/section/section/div/ul/li[2]/a");
        clickElement(by);
        by = By.xpath("//*[@id=\"form-name\"]");
        enterText(by, name);
        by = By.xpath("//*[@id=\"project-creation-form\"]/div[2]/div/button");
        clickElement(by);
        return this;
    }

    public Homepage loginByAdmin(){
        final String ADMIN = "admin";
        final String PASSWORD = "admin";
        final By USER_INPUT = By.xpath("//*[@id=\"form-username\"]");
        final By PASS_INPUT = By.xpath("//*[@id=\"form-password\"]");
        final By SUBMIT = By.xpath("/html/body/div/form/div[1]/button");
        enterText(USER_INPUT, ADMIN);
        enterText(PASS_INPUT, PASSWORD);
        clickElement(SUBMIT);
        return new Homepage();
    }

    @Test
    public void assertCreatedProject(String name) throws JsonProcessingException {
        ApiUtility api = new ApiUtility();
        Assert.assertNotNull(api.getProjectId(name));
    }
}
