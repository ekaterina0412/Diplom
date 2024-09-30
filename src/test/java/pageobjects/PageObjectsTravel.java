package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageObjectsTravel{

    private static final SelenideElement PAYMENT_BUTTON = $$("button").find(Condition.exactText("Купить"));
    private static final SelenideElement CREDIT_BUTTON = $$("button").find(Condition.exactText("Купить в кредит"));

    private static final SelenideElement CREDIT_CARD_TITLE = $$(".heading").find(Condition.exactText("Кредит по данным карты"));
    private static final SelenideElement PAYMENT_CARD_TITLE = $$(".heading").find(Condition.exactText("Оплата по карте"));

    private static final SelenideElement CARD_NUMBER_INPUT = $("[placeholder='0000 0000 0000 0000']");
    private static final SelenideElement MONTH_INPUT = $("[placeholder='08']");
    private static final SelenideElement YEAR_INPUT = $("[placeholder='22']");
    private static final SelenideElement OWNER_INPUT = $("div:nth-child(3) > span > span:nth-child(1) input");
    private static final SelenideElement CVC_CODE_INPUT = $("[placeholder='999']");
    private static final SelenideElement CONTINUE_BUTTON = $$("button").find(Condition.exactText("Продолжить"));;

    private static final SelenideElement NOTIFICATION_STATUS_OK = $(".notification_status_ok");
    private static final SelenideElement NOTIFICATION_STATUS_ERROR = $(".notification_status_error");

    private static final SelenideElement CARD_NUMBER_FIELD_ERROR = $("div:nth-child(1) > span > span > span.input__sub");
    private static final SelenideElement MONTH_FIELD_ERROR = $("div:nth-child(2) > span > span:nth-child(1) .input__sub");
    private static final SelenideElement YEAR_FIELD_ERROR_UNKNOWN_FORMAT = $("div:nth-child(2) > span > span:nth-child(2) .input__sub");
    private static final SelenideElement YEAR_FIELD_DATE_VALIDATE_ERROR = $(byText("Истёк срок действия карты"));
    private static final SelenideElement OWNER_FIELD_ERROR = $(byText("Поле обязательно для заполнения"));
    private static final SelenideElement CVC_FIELD_ERROR = $("div:nth-child(3) > span > span:nth-child(2) .input__sub");

    public PageObjectsTravel() {
        open("http://localhost:8080/");
    }

    public PageObjectsTravel setFields(String cardNumber, String month, String year, String owner, String cvc) {
        CARD_NUMBER_INPUT.click();
        CARD_NUMBER_INPUT.sendKeys(cardNumber);

        MONTH_INPUT.click();
        MONTH_INPUT.sendKeys(month);

        YEAR_INPUT.click();
        YEAR_INPUT.sendKeys(year);

        OWNER_INPUT.click();
        OWNER_INPUT.sendKeys(owner);

        CVC_CODE_INPUT.click();
        CVC_CODE_INPUT.sendKeys(cvc);

        return this;
    }

    public PageObjectsTravel paymentButtonClick() {
        PAYMENT_BUTTON.click();
        PAYMENT_CARD_TITLE.shouldHave(Condition.text("Оплата по карте"));
        return this;
    }

    public PageObjectsTravel creditButtonClick() {
        CREDIT_BUTTON.click();
        CREDIT_CARD_TITLE.shouldHave(Condition.text("Кредит по данным карты"));
        return this;
    }

    public PageObjectsTravel continueButtonClick() {
        CONTINUE_BUTTON.click();
        return this;
    }

    public void notificationStatusOK() {
        NOTIFICATION_STATUS_OK.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public PageObjectsTravel notificationStatusError() {
        NOTIFICATION_STATUS_ERROR.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return this;
    }

    public PageObjectsTravel cardNumberFieldError() {
        CARD_NUMBER_FIELD_ERROR.shouldBe(Condition.visible);
        return this;
    }

    public PageObjectsTravel monthFieldError() {
        MONTH_FIELD_ERROR.shouldBe(Condition.visible);
        return this;
    }

    public PageObjectsTravel yearFieldErrorFormat() {
        YEAR_FIELD_ERROR_UNKNOWN_FORMAT.shouldBe(Condition.visible);
        return this;
    }

    public PageObjectsTravel yearFieldErrorValidate() {
        YEAR_FIELD_DATE_VALIDATE_ERROR.shouldBe(Condition.visible);
        return this;
    }

    public PageObjectsTravel ownerFieldError() {
        OWNER_FIELD_ERROR.shouldBe(Condition.visible);
        return this;
    }

    public PageObjectsTravel cvcFieldError() {
        CVC_FIELD_ERROR.shouldBe(Condition.visible);
        return this;
    }

}