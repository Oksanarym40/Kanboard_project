package TestsUI.Tests;

import TestAPI.ApiBackend.ApiUtility;
import TestsUI.Pages.TaskPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TaskTest {

    public static final String COMMENT = "Task Comment. Kanboard final exam.";
    TaskPage page;
    private ApiUtility api;
    private String ADMIN = "admin";
    private final String USERNAME = "Oksana";
    private final String PASSWORD ="Oksana";
    private final String PROJECT = "Long PROJECT";
    public static final String STUPID_TASK = "Daily Task";
    private String token;

    @BeforeClass
    public void startUp() throws JsonProcessingException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 5000;
        // Prevent closing the browser after the test
        Configuration.holdBrowserOpen = false;

        this.page = new TaskPage();
        this.api = new ApiUtility();
        api.removeAllUsers();
        api.removeAllProjects();
        page.openPage(ADMIN,ADMIN);
        page.openPage(USERNAME,PASSWORD);
        page.addUser(USERNAME,PASSWORD);

        this.token = page.getUserToken(USERNAME);
        page.logout();

        page.addProject(PROJECT,USERNAME,this.token);
    }

    @AfterClass
    public void clean() throws JsonProcessingException {
        api.removeAllProjects();
        api.removeAllUsers();
    }

    @Test(priority = 1)
    public void userAbleCreateTask() throws JsonProcessingException {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        page.addTask(STUPID_TASK);
        page.assertTaskAdd();
    }

    @Test(priority = 2)
    public void userAbleAddTaskComments(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        page.addComment(COMMENT);
        page.assertCommentAdd(COMMENT);
    }

    @Test(priority = 3)
    public void userAbleCloseTask(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        page.closeTask().assertTaskClosed();
    }
}

