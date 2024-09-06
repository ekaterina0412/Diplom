import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageobjects.PageObjectsTravel;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GUITest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
    }

    @BeforeEach
    @DisplayName("Clear base table")
    void cleanTable() {
        SQL.clearTables();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("By to payment")
    @Description("We check the form through the \"Buy\" button. We serve different card numbers")
    @ParameterizedTest
    @CsvFileSource(resources = "./NotificationCheck.csv", numLinesToSkip = 1)
    void paymentTest(int cardNumber, String baseStatus) {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(cardNumber)
                .continueButtonClick();
        $(Selectors.withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        assertEquals(SQL.getPaymentStatus(), baseStatus);
    }

    @DisplayName("By to credit")
    @Description("We check the form through the \"buy in credit\" button. We serve different card numbers")
    @ParameterizedTest
    @CsvFileSource(resources = "./NotificationCheck.csv", numLinesToSkip = 1)
    void creditTest(int cardNumber, String baseStatus) {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(cardNumber)
                .continueButtonClick();
        $(Selectors.withText("Успешно")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        assertEquals(SQL.getCreditStatus(), baseStatus);
    }

    @DisplayName("By to payment with unknown card")
    @Description("Check the form through the \"Buy\" button. Use unknown card number")
    @Test
    void paymentWithUnknownCardNumber() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(2)
                .continueButtonClick();
    }

    @DisplayName("By to credit with unknown card")
    @Description("Check the form through the \"buy in credit\" button. Use unknown card number")
    @Test
    void creditWithUnknownCardNumber() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(2)
                .continueButtonClick();
    }

    @DisplayName("Empty fields payment form")
    @Description("All fields is empty, choose payment and push button \"next")
    @Test
    void emptyFieldsPaymentCheck() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .continueButtonClick();
    }

    @DisplayName("Empty fields credit form")
    @Description("All fields is empty, choose credit and push button \"next")
    @Test
    void emptyFieldsCreditCheck() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .continueButtonClick();
    }

    @DisplayName("Wrong format 1 to valid date fields")
    @Description("Set past year and unreal month 1")
    @Test
    void invalidDateFieldsPaymentCheck1() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setInvalidFields1()
                .continueButtonClick();
    }

    @DisplayName("Wrong format 2 to valid date fields")
    @Description("Set past year and unreal month 2")
    @Test
    void invalidDateFieldsPaymentCheck2() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setInvalidFields2()
                .continueButtonClick();
    }
}