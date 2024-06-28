package PageObjects;

import Data.Info;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.open;

public class PageObjectsTravel{

    public static final int PAYMENT = 0;
    public static final int CREDIT = 1;

    public static final int APPROVED_CARD = 0;
    public static final int DECLINED_CARD = 1;
    public static final int UNKNOWN_CARD = 2;

    int type;
    int typeCard;

    public PageObjectsTravel() {
        open("http://localhost:8080/");
        Constant.TITLE.waitUntil(Condition.visible, 15000);
        Constant.PROMO_PHOTO.isDisplayed();
        Constant.COUNTRY.isDisplayed();
        Constant.PAYMENT_BUTTON.isDisplayed();
        Constant.CREDIT_BUTTON.isDisplayed();
    }

    public void choosePayMethod(int type) {
        this.type = type;

        if (this.type == PAYMENT) {
            Constant.PAYMENT_CARD_TITLE.shouldHave(Condition.text("Оплата по карте"));
        } else if (this.type == CREDIT) {
            Constant.CREDIT_CARD_TITLE.shouldHave(Condition.text("Кредит по данным карты"));
        }

        Constant.CARD_NUMBER_INPUT.isDisplayed();
        Constant.MONTH_INPUT.isDisplayed();
        Constant.YEAR_INPUT.isDisplayed();
        Constant.OWNER_INPUT.isDisplayed();
        Constant.CVC_CODE_INPUT.isDisplayed();
        Constant.CONTINUE_BUTTON.isDisplayed();
    }

    public PageObjectsTravel setFields(int typeCard) {
        this.typeCard = typeCard;

        if (typeCard == APPROVED_CARD) {
            Constant.CARD_NUMBER_INPUT.click();
            Constant.CARD_NUMBER_INPUT.sendKeys(Constant.APPROVED_CARD_NUMBER);
        } else if (typeCard == DECLINED_CARD) {
            Constant.CARD_NUMBER_INPUT.click();
            Constant.CARD_NUMBER_INPUT.sendKeys(Constant.DECLINED_CARD_NUMBER);
        } else if (typeCard == UNKNOWN_CARD) {
            Constant.CARD_NUMBER_INPUT.click();
            Constant.CARD_NUMBER_INPUT.sendKeys((Constant.UNKNOWN_CARD_NUMBER));
        }
        Constant.MONTH_INPUT.click();
        Constant.MONTH_INPUT.sendKeys(Info.getRandomMonth());
        Constant.YEAR_INPUT.click();
        Constant.YEAR_INPUT.sendKeys(Info.getRandomYear());
        Constant.OWNER_INPUT.click();
        Constant.OWNER_INPUT.sendKeys(Info.getRandomOwner());
        Constant.CVC_CODE_INPUT.click();
        Constant.CVC_CODE_INPUT.sendKeys(Info.getRandomCvcCode());
        return this;
    }

    public PageObjectsTravel paymentButtonClick() {
        Constant.PAYMENT_BUTTON.click();
        choosePayMethod(PAYMENT);
        return this;
    }

    public PageObjectsTravel creditButtonClick() {
        Constant.CREDIT_BUTTON.click();
        choosePayMethod(CREDIT);
        return this;
    }
}