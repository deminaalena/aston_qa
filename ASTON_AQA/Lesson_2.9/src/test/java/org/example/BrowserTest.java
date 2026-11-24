
package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BrowserTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By paymentBlockLocator = By.xpath("//section[@class='pay']");

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.mts.by/");
        acceptCookiesIfVisible();
    }

    private void acceptCookiesIfVisible() {
        try {
            WebDriverWait cookieWait = new WebDriverWait(
                    driver, Duration.ofSeconds(3)
            );

            WebElement cookieButton = cookieWait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("cookie-agree")
                    )
            );

            cookieButton.click();

        } catch (TimeoutException e) {
            System.out.println("Окно cookies не появилось.");
        }
    }

    private WebElement scrollToPaymentBlock() {
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

    private WebElement fillPaymentForm() {
        WebElement paymentBlock = scrollToPaymentBlock();

        WebElement phoneField = paymentBlock.findElement(
                By.id("connection-phone")
        );

        WebElement sumField = paymentBlock.findElement(
                By.id("connection-sum")
        );

        WebElement emailField = paymentBlock.findElement(
                By.id("connection-email")
        );

        phoneField.sendKeys("297777777");
        sumField.sendKeys("10");
        emailField.sendKeys("test@example.com");

        Assertions.assertEquals("(29)777-77-77", phoneField.getAttribute("value"));
        Assertions.assertEquals("10", sumField.getAttribute("value"));
        Assertions.assertEquals("test@example.com", emailField.getAttribute("value"));

        return paymentBlock;
    }

    @Test
    public void checkOnlineTopUpTitle() {
        WebElement paymentBlock = scrollToPaymentBlock();

        WebElement title = paymentBlock.findElement(By.tagName("h2"));

        String actualTitle = title.getText()
                .replaceAll("\\s+", " ")
                .trim();

        Assertions.assertEquals(
                "ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ",
                actualTitle
        );
    }

    @Test
    public void checkPaymentLogos() {
        WebElement paymentBlock = scrollToPaymentBlock();

        WebElement partners = paymentBlock.findElement(
                By.className("pay__partners")
        );

        List<WebElement> logos = partners.findElements(
                By.cssSelector("img")
        );

        Assertions.assertEquals(6, logos.size());

        for (WebElement logo : logos) {
            Assertions.assertTrue(
                    logo.isDisplayed(),
                    "Логотип не отображается: " + logo.getAttribute("alt")
            );
        }
    }

    @Test
    public void checkServiceDetailsLink() {
        WebElement paymentBlock = scrollToPaymentBlock();

        WebElement detailsLink = paymentBlock.findElement(
                By.linkText("Подробнее о сервисе")
        );

        Assertions.assertTrue(detailsLink.isDisplayed());

        String originalUrl = driver.getCurrentUrl();

        detailsLink.click();

        assert originalUrl != null;
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlToBe(originalUrl)
        ));

        Assertions.assertTrue(
                Objects.requireNonNull(driver.getCurrentUrl()).contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/")
        );
    }

    @Test
    public void checkPaymentForm() {
        WebElement paymentBlock = fillPaymentForm();

        WebElement krokButton = paymentBlock.findElement(
                By.cssSelector("button[data-method='krok']")
        );

        WebElement cardButton = paymentBlock.findElement(
                By.cssSelector("button[data-method='card']")
        );

        Assertions.assertTrue(krokButton.isDisplayed());
        Assertions.assertTrue(krokButton.isEnabled());

        Assertions.assertTrue(cardButton.isDisplayed());
        Assertions.assertTrue(cardButton.isEnabled());
    }

    @Test
    public void checkKrokButton() {
        WebElement paymentBlock = fillPaymentForm();

        WebElement krokButton = paymentBlock.findElement(
                By.cssSelector("button[data-method='krok']")
        );

        Assertions.assertTrue(krokButton.isDisplayed());
        Assertions.assertTrue(krokButton.isEnabled());

        krokButton.click();

        WebElement paymentFrame = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("iframe.payment-widget-iframe")
                )
        );

        driver.switchTo().frame(paymentFrame);

        WebElement paymentTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(), 'Отсканируйте QR-код')]")
                )
        );

        Assertions.assertTrue(paymentTitle.isDisplayed());

        Assertions.assertEquals(
                "Отсканируйте QR-код в мобильном приложении банка",
                paymentTitle.getText()
        );

        driver.switchTo().defaultContent();
    }

    @Test
    public void checkCardButton() {
        WebElement paymentBlock = fillPaymentForm();

        WebElement cardButton = paymentBlock.findElement(
                By.cssSelector("button[data-method='card']")
        );

        Assertions.assertTrue(cardButton.isDisplayed());
        Assertions.assertTrue(cardButton.isEnabled());

        cardButton.click();

        WebElement paymentFrame = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("iframe.payment-widget-iframe")
                )
        );

        driver.switchTo().frame(paymentFrame);

        WebElement paymentAmount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), '10.00 BYN')]")
                )
        );

        Assertions.assertTrue(paymentAmount.isDisplayed());

        driver.switchTo().defaultContent();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
