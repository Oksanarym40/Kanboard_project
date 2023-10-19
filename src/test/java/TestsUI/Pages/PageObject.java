package TestsUI.Pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;


public class PageObject {
    private static final int WAIT_TIME_SEC = 1;

    public PageObject() {
        timeout = WAIT_TIME_SEC * 5000; // Convert seconds to milliseconds
    }

    public SelenideElement getElement(By by) {
        return $(by).shouldBe(visible);
    }
    public SelenideElement getElement(String text){
        return $(byText(text)).shouldBe(visible);
    }

    public PageObject clickElement(By by) {
        $(by).shouldBe(visible).shouldBe(enabled).click();
        return this;
    }

    public PageObject clickElementByText(String text){
        $(byText(text)).shouldBe(visible).shouldBe(enabled).click();
        return this;
    }

    public boolean elementExist(By by){
        return this.getElement(by).exists();
    }

    public PageObject enterText(By by, String text) {
        $(by).shouldBe(visible).shouldBe(enabled).clear();
        $(by).shouldBe(visible).shouldBe(enabled).val(text);
        return this;
    }

    public String getUrl() throws InterruptedException {
        Wait().wait(1000);
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}