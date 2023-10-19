package TestsUI.Pages;

import com.codeborne.selenide.Configuration;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ApiToken extends PageObject {
    private final String URL = "http://localhost/login";
    public ApiToken() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 5000;
    }


    public String get(String username) {
        open(URL);
        final String API_LOCATOR = "//*[@id=\"user-section\"]/div[1]/ul[2]/li[8]/a";
        final String removeButton = "Remove your token";
        final String TOKEN_LOCATOR = "//*[@id=\"user-section\"]/div[2]/p";
        final String regexToken = "Your personal API access token is \"([0-9a-f]{64})\"";

        $(byText(username)).click();
        $(byXpath(API_LOCATOR)).click();

        String elementText = $(By.xpath(TOKEN_LOCATOR)).getText();
        System.out.println(cutoutToken(elementText));
        return cutoutToken(elementText);

    }

    @NotNull
    @Test
    private String cutoutToken(String str){
        int startIndex = str.indexOf("\"") + 1;
        int endIndex = str.lastIndexOf("\"");

        // Extract the token using substring
        return str.substring(startIndex, endIndex);

    }
}
