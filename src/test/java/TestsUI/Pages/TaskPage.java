package TestsUI.Pages;

import TestAPI.ApiBackend.ApiUtility;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.open;

public class TaskPage extends PageObject {
    private ApiUtility api;
    private String token;
    private final String URL = "http://localhost/login";
    private final By LOC_USERNAME_INPUT = By.xpath("//*[@id=\"form-username\"]");
    private final By LOC_PASSWORD_INPUT = By.xpath("//*[@id=\"form-password\"]");
    private final By LOC_SUBMIT_BUTT = By.xpath("/html/body/div/form/div[1]/button");
    public TaskPage() {
        super();
        this.api = new ApiUtility();
    }

    public TaskPage openPage(String username, String password){
        open(URL);
        super.enterText(LOC_USERNAME_INPUT,username);
        super.enterText(LOC_PASSWORD_INPUT,password);
        super.clickElement(LOC_SUBMIT_BUTT);
        return this;
    }


    public TaskPage addUser(String username, String password) throws JsonProcessingException {
        api.createUser(username,password);
        return this;
    }

    public TaskPage addProject(String name,String user, String tok) throws JsonProcessingException {
        api.createPrivateProject(name,user,tok);
        return this;
    }

    public TaskPage addTask(String title){
        final By LOC_ADD_BACKLOG = By.xpath("//*[@id=\"board\"]/tbody/tr[1]/th[1]/div[2]/div/a/i");
        final By LOC_TASK_TIT = By.xpath("//*[@id=\"form-title\"]");
        final By LOC_SAVE_TASK = By.xpath("//*[@id=\"modal-content\"]/form/div/div[4]/div/div/button");
        final By LOC_MY_PROJ = By.xpath("//*[@id=\"dashboard\"]/div[1]/ul/li[2]/a");
        final By LOC_PROJECT = By.xpath("//*[@id=\"dashboard\"]/div[2]/div[2]/div[2]/div[1]/span/a");

        super.clickElement(LOC_MY_PROJ);
        super.clickElement(LOC_PROJECT);
        super.clickElement(LOC_ADD_BACKLOG);
        super.enterText(LOC_TASK_TIT,title);
        super.clickElement(LOC_SAVE_TASK);

        return this;
    }

    public TaskPage addComment(String text){
        final By LOC_COMMENT = By.xpath("//*[@id=\"task-view\"]/div[2]/details[6]");
        final By LOC_COMMENT_BLOCK = By.xpath("//*[@id=\"comments\"]/form/div[1]/div/div[2]/textarea");
        final By LOC_TASK = By.xpath("//*[@id=\"board\"]/tbody/tr[2]/td[1]/div[1]/div/div[2]");
        final By LOC_SAVE = By.xpath("//*[@id=\"comments\"]/form/div[2]/div/button");

        super.clickElement(LOC_TASK);
        super.clickElement(LOC_COMMENT);
        super.enterText(LOC_COMMENT_BLOCK,text);
        super.clickElement(LOC_SAVE);

        return this;
    }

    public TaskPage closeTask(){
        final By LOC_CLOSE_TASK = By.xpath("//*[@id=\"task-view\"]/div[1]/ul[2]/li[14]/a");
        final By LOC_YES = By.xpath("//*[@id=\"modal-confirm-button\"]");
        super.clickElement(LOC_CLOSE_TASK);
        super.clickElement(LOC_YES);
        return this;
    }

    public void assertTaskClosed(){
        final By LOC_STATUS = By.xpath("//*[@id=\"task-summary\"]/div/div/div[1]/ul/li[1]");
        final String STATUS = "Status: closed";
        SelenideElement taskClosed = super.getElement(LOC_STATUS);
        Assert.assertEquals(taskClosed.getText().trim(),STATUS);
    }

    public void assertTaskAdd() throws JsonProcessingException {
        final By LOC_TASK = By.xpath("//*[@id=\"board\"]/tbody/tr[2]/td[1]/div[1]");
        Assert.assertTrue(super.elementExist(LOC_TASK));
    }

    public void assertCommentAdd(String comment){
        SelenideElement comm = super.getElement(comment);
        Assert.assertNotNull(comm);
    }

    public TaskPage logout(){
        By loc_menu = By.xpath("/html/body/header/div[3]/div[2]/a/i");
        By loc_logout = By.xpath("//*[@id=\"dropdown\"]/ul/li[10]");
        super.clickElement(loc_menu);
        super.clickElement(loc_logout);
        return this;
    }

    public String getUserToken(String username){
        final String USERS_URL = "http://localhost/users";
        final By API_LOCATOR = By.xpath("//*[@id=\"user-section\"]/div[1]/ul[2]/li[8]/a");
        final By LOC_GEN_TOK = By.xpath("//*[@id=\"user-section\"]/div[2]/a");;
        final By TOKEN_LOCATOR = By.xpath("//*[@id=\"user-section\"]/div[2]/p");
        final String regexToken = "Your personal API access token is \"([0-9a-f]{64})\"";

        open(USERS_URL);
        super.clickElementByText(username);
        super.clickElement(API_LOCATOR);
        super.clickElement(LOC_GEN_TOK);
        String elementText = getElement(TOKEN_LOCATOR).getText();
        return cutoutToken(elementText);
    }


    private String cutoutToken(String str){
        int startIndex = str.indexOf("\"") + 1;
        int endIndex = str.lastIndexOf("\"");
        // Extract the token using substring
        return str.substring(startIndex, endIndex);
    }
}
