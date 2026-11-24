
package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BrowserTest {

    private WebDriver driver;
    private MtsPage mtsPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        mtsPage = new MtsPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));

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

    private void fillPaymentForm() {
        mtsPage.fillPaymentForm(
                "297777777",
                "10",
                "test@example.com"
        );
    }

    @Test
    public void checkOnlineTopUpTitle() {

        String actualTitle = mtsPage.getPaymentTitle();

        Assertions.assertEquals(
                "ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ",
                actualTitle
        );
    }

    @Test
    public void checkPaymentLogos() {
        List<WebElement> logos = mtsPage.getPaymentLogos();

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
        String originalUrl = driver.getCurrentUrl();

        mtsPage.clickServiceDetailsLink();

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
        fillPaymentForm();

        WebElement krokButton = mtsPage.getKrokButton();
        WebElement cardButton = mtsPage.getCardButton();

        Assertions.assertTrue(krokButton.isDisplayed());
        Assertions.assertTrue(krokButton.isEnabled());

        Assertions.assertTrue(cardButton.isDisplayed());
        Assertions.assertTrue(cardButton.isEnabled());
    }

    @Test
    public void checkKrokButton() {
        fillPaymentForm();

        WebElement krokButton = mtsPage.getKrokButton();

        Assertions.assertTrue(krokButton.isDisplayed());
        Assertions.assertTrue(krokButton.isEnabled());

        mtsPage.clickKrokButton();

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
        fillPaymentForm();

        WebElement cardButton = mtsPage.getCardButton();

        Assertions.assertTrue(cardButton.isDisplayed());
        Assertions.assertTrue(cardButton.isEnabled());

        mtsPage.clickCardButton();

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

    @ParameterizedTest
    @CsvSource({
            "'Услуги связи', 'Номер телефона', 'Сумма', 'E-mail для отправки чека'",
            "'Домашний интернет', 'Номер абонента', 'Сумма', 'E-mail для отправки чека'",
            "'Рассрочка', 'Номер счета на 44', 'Сумма', 'E-mail для отправки чека'",
            "'Задолженность', 'Номер счета на 2073', 'Сумма', 'E-mail для отправки чека'"
    })
    public void checkPaymentPlaceholders(
            String paymentType,
            String firstField,
            String secondField,
            String thirdField
    ) {
        mtsPage.selectPaymentType(paymentType);

        List<String> actualPlaceholders =
                mtsPage.getPaymentFieldPlaceholders();

        Assertions.assertEquals(
                List.of(firstField, secondField, thirdField),
                actualPlaceholders
        );
    }

    @Test
    public void checkCardPaymentWindow() {
        mtsPage.fillPaymentForm(
                "297777777",
                "10",
                "test@example.com"
        );

        mtsPage.clickCardButton();

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
    }

    @Test
    public void checkCardPaymentDetails() {
        fillPaymentForm();

        mtsPage.clickCardButton();
        mtsPage.switchToCardPaymentFrame();

        try {
            Assertions.assertEquals(
                    "10.00 BYN",
                    mtsPage.getPaymentAmount()
            );

            String paymentDescription = mtsPage.getPaymentDescription();

            Assertions.assertTrue(
                    paymentDescription.contains("Услуги связи"),
                    paymentDescription
            );

            Assertions.assertTrue(
                    paymentDescription.contains("375297777777"),
                    paymentDescription
            );

            Assertions.assertEquals(
                    List.of(
                            "Номер карты",
                            "Срок действия",
                            "CVC",
                            "Имя и фамилия на карте",
                            "Сохранить данные карты для последующих оплат"
                    ),
                    mtsPage.getCardFieldLabels()
            );

            List<WebElement> cardIcons = mtsPage.getCardIcons();

            long displayedIcons = cardIcons.stream()
                    .filter(WebElement::isDisplayed)
                    .count();

            Assertions.assertTrue(
                    displayedIcons >= 3,
                    "Отображается меньше трёх иконок платёжных систем"
            );

            Assertions.assertTrue(
                    mtsPage.getPayButtonText().contains("10.00 BYN")
            );

        } finally {
            mtsPage.switchToDefaultContent();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
