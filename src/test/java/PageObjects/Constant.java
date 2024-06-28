package PageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Constant {
    public static final String APPROVED_CARD_NUMBER = "4444444444444441";
    public static final String DECLINED_CARD_NUMBER = "4444444444444442";
    public static final String UNKNOWN_CARD_NUMBER = "1111111111111111";

    public static final SelenideElement TITLE = $(byText("Путешествие дня"));
    public static final SelenideElement PROMO_PHOTO = $(".Order_cardImage__Q69ii");
    public static final SelenideElement COUNTRY = $("h3.heading");
    public static final SelenideElement PAYMENT_BUTTON = $$("button").find(Condition.exactText("Купить"));
    public static final SelenideElement CREDIT_BUTTON = $$("button").find(Condition.exactText("Купить в кредит"));

    public static final SelenideElement CREDIT_CARD_TITLE = $$(".heading").find(Condition.exactText("Кредит по данным карты"));
    public static final SelenideElement PAYMENT_CARD_TITLE = $$(".heading").find(Condition.exactText("Оплата по карте"));

    public static final SelenideElement CARD_NUMBER_INPUT = $("[placeholder='0000 0000 0000 0000']");
    public static final SelenideElement MONTH_INPUT = $("[placeholder='08']");
    public static final SelenideElement YEAR_INPUT = $("[placeholder='22']");
    public static final SelenideElement OWNER_INPUT = $("div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    public static final SelenideElement CVC_CODE_INPUT = $("[placeholder='999']");
    public static final SelenideElement CONTINUE_BUTTON = $$("button").find(Condition.exactText("Продолжить"));;

    public static final SelenideElement NOTIFICATION_STATUS_OK = $(".notification_status_ok");
    public static final SelenideElement NOTIFICATION_STATUS_ERROR = $(".notification_status_error");

    public static final SelenideElement CARD_NUMBER_FIELD_ERROR = $("div:nth-child(1) > span > span > span.input__sub");
    public static final SelenideElement MONTH_FIELD_ERROR = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    public static final SelenideElement YEAR_FIELD_ERROR_UNKNOWN_FORMAT = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    public static final SelenideElement YEAR_FIELD_DATE_VALIDATE_ERROR = $(byText("Истёк срок действия карты"));
    public static final SelenideElement OWNER_FIELD_ERROR = $(byText("Поле обязательно для заполнения"));
    public static final SelenideElement CVC_FIELD_ERROR = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");
}