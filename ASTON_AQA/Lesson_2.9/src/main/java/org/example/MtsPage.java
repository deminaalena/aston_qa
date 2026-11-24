package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

public class MtsPage {

    private final WebDriver driver;

    private final By paymentBlockLocator = By.xpath("//section[@class='pay']");

    private final WebDriverWait wait;

    private final By paymentTitleLocator = By.xpath(".//h2");

    private final By paymentLogosLocator =
            By.cssSelector(".pay__partners img");

    private final By serviceDetailsLinkLocator =
            By.linkText("Подробнее о сервисе");

    private final By phoneFieldLocator = By.id("connection-phone");
    private final By sumFieldLocator = By.id("connection-sum");
    private final By emailFieldLocator = By.id("connection-email");

    private final By krokButtonLocator =
            By.cssSelector("button[data-method='krok']");

    private final By cardButtonLocator =
            By.cssSelector("button[data-method='card']");

    private final By paymentTypeButtonLocator =
            By.cssSelector(".select__header");

    private final By paymentFrameLocator =
            By.cssSelector("iframe.payment-widget-iframe");

    private final By paymentAmountLocator =
            By.cssSelector(".pay-description__cost");

    private final By paymentDescriptionLocator =
            By.cssSelector(".pay-description__text");

    private final By cardIconsLocator =
            By.cssSelector(".cards-brands img");

    private final By payButtonLocator =
            By.cssSelector("button.colored");

    private final By cardFieldLabelsLocator =
            By.cssSelector("app-card-input label");

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement scrollToPaymentBlock() {
        WebElement paymentBlock = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        paymentBlockLocator
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                paymentBlock
        );

        return paymentBlock;
    }

    public String getPaymentTitle() {
        WebElement paymentBlock = scrollToPaymentBlock();

        return paymentBlock.findElement(paymentTitleLocator)
                .getText()
                .replaceAll("\\s+", " ")
                .trim();
    }

    public List<WebElement> getPaymentLogos() {
        WebElement paymentBlock = scrollToPaymentBlock();
        return paymentBlock.findElements(paymentLogosLocator);
    }

    public void clickServiceDetailsLink() {
        WebElement paymentBlock = scrollToPaymentBlock();

        WebElement detailsLink = paymentBlock.findElement(
                serviceDetailsLinkLocator
        );

        detailsLink.click();
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        WebElement paymentBlock = scrollToPaymentBlock();

        paymentBlock.findElement(phoneFieldLocator).sendKeys(phone);
        paymentBlock.findElement(sumFieldLocator).sendKeys(sum);
        paymentBlock.findElement(emailFieldLocator).sendKeys(email);
    }

    public WebElement getKrokButton() {
        WebElement paymentBlock = scrollToPaymentBlock();
        return paymentBlock.findElement(krokButtonLocator);
    }

    public WebElement getCardButton() {
        WebElement paymentBlock = scrollToPaymentBlock();
        return paymentBlock.findElement(cardButtonLocator);
    }

    public void clickKrokButton() {
        getKrokButton().click();
    }

    public void clickCardButton() {
        getCardButton().click();
    }

    public void selectPaymentType(String paymentType) {
        WebElement paymentBlock = scrollToPaymentBlock();

        paymentBlock.findElement(paymentTypeButtonLocator).click();

        By optionLocator = By.xpath(
                ".//p[@class='select__option' and normalize-space()='"
                        + paymentType + "']"
        );

        WebElement option = paymentBlock.findElement(optionLocator);

        wait.until(ExpectedConditions.elementToBeClickable(option));
        option.click();
    }

    public List<String> getPaymentFieldPlaceholders() {
        WebElement paymentBlock = scrollToPaymentBlock();

        WebElement openedForm = paymentBlock.findElement(
                By.cssSelector(".pay-form.opened")
        );

        List<WebElement> fields = openedForm.findElements(
                By.cssSelector("input[placeholder]")
        );

        return fields.stream()
                .map(field -> field.getAttribute("placeholder"))
                .toList();
    }

    public void switchToCardPaymentFrame() {
        WebElement frame = wait.until(
                ExpectedConditions.presenceOfElementLocated(paymentFrameLocator)
        );
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getPaymentAmount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(paymentAmountLocator)
        ).getText().trim();
    }

    public String getPaymentDescription() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(paymentDescriptionLocator)
        ).getText().replaceAll("\\s+", " ").trim();
    }

    public List<String> getCardFieldLabels() {
        return driver.findElements(By.cssSelector("app-card-input label"))
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public List<WebElement> getCardIcons() {
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(cardIconsLocator)
        );
    }

    public String getPayButtonText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(payButtonLocator)
        ).getText().replaceAll("\\s+", " ").trim();
    }
}
