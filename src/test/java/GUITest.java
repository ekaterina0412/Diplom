import com.codeborne.selenide.logevents.SelenideLogger;
import data.Info;
import data.InvalidDataInfo;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pageobjects.PageObjectsTravel;

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
    @Description("We check the form through the \"Buy\" button.")
    @Test
    void paymentTestApproved() {

        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields("4444 4444 4444 4441", Info.getMonth(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick();

        travel.notificationStatusOK();

        assertEquals(SQL.getPaymentStatus(), "APPROVED");
    }

    @DisplayName("By to payment")
    @Description("We check the form through the \"Buy\" button.")
    @Test
    void paymentTestDeclined() {

        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields("4444 4444 4444 4442", Info.getMonth(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick();

        travel.notificationStatusError();

        assertEquals(SQL.getPaymentStatus(), "DECLINED");
    }

    @DisplayName("By to credit")
    @Description("We check the form through the \"buy in credit\" button.")
    @Test
    void creditTestApproved() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields("4444 4444 4444 4441", Info.getMonth(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick();

        travel.notificationStatusOK();

        assertEquals(SQL.getCreditStatus(), "APPROVED");
    }

    @DisplayName("By to credit")
    @Description("We check the form through the \"buy in credit\" button.")
    @Test
    void creditTestDeclined() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields("4444 4444 4444 4442", Info.getMonth(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick();

        travel.notificationStatusError();

        assertEquals(SQL.getCreditStatus(), "DECLINED");
    }

    @DisplayName("By to payment with unknown card")
    @Description("Check the form through the \"Buy\" button. Use unknown card number")
    @Test
    void paymentWithUnknownCardNumber() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(InvalidDataInfo.CardFields.getUnknownFormatCardNumber(), Info.getMonth(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .cardNumberFieldError();
    }

    @DisplayName("By to credit with unknown card")
    @Description("Check the form through the \"buy in credit\" button. Use unknown card number")
    @Test
    void creditWithUnknownCardNumber() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(InvalidDataInfo.CardFields.getUnknownFormatCardNumber(), Info.getMonth(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .cardNumberFieldError();
    }

    @DisplayName("Empty fields payment form")
    @Description("All fields is empty, choose payment and push button \"next")
    @Test
    void emptyFieldsPaymentCheck() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .continueButtonClick()
                .cardNumberFieldError()
                .monthFieldError()
                .yearFieldErrorFormat()
                .ownerFieldError()
                .cvcFieldError();
    }

    @DisplayName("Empty fields credit form")
    @Description("All fields is empty, choose credit and push button \"next")
    @Test
    void emptyFieldsCreditCheck() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .continueButtonClick()
                .cardNumberFieldError()
                .monthFieldError()
                .yearFieldErrorFormat()
                .ownerFieldError()
                .cvcFieldError();
    }

    @DisplayName("Wrong year to valid date fields")
    @Description("Set past year")
    @Test
    void invalidYearFieldPaymentCheck1() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), InvalidDataInfo.CardFields.getPastYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .yearFieldErrorValidate();
    }

    @DisplayName("Wrong year to valid date fields")
    @Description("Set past year")
    @Test
    void invalidYearFieldCreditCheck1() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), InvalidDataInfo.CardFields.getPastYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .yearFieldErrorValidate();
    }

    @DisplayName("Wrong year to valid date fields")
    @Description("Set very old year")
    @Test
    void invalidYearFieldPaymentCheck2() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), InvalidDataInfo.CardFields.getVeryOldYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .yearFieldErrorValidate();
    }

    @DisplayName("Wrong year to valid date fields")
    @Description("Set very old year")
    @Test
    void invalidYearFieldCreditCheck2() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), InvalidDataInfo.CardFields.getVeryOldYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .yearFieldErrorValidate();
    }

    @DisplayName("Wrong year to valid date fields")
    @Description("Set 00 year")
    @Test
    void invalidYearFieldPaymentCheck00() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), "00", Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .yearFieldErrorValidate();
    }

    @DisplayName("Wrong year to valid date fields")
    @Description("Set 00 year")
    @Test
    void invalidYearFieldCreditCheck00() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), "00", Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .yearFieldErrorValidate();
    }

    @DisplayName("Wrong format month 1")
    @Description("Set unreal month 1")
    @Test
    void invalidMonthFormatPayment1() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), InvalidDataInfo.CardFields.getInvalidMonth1(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .monthFieldError();
    }

    @DisplayName("Wrong format month 1")
    @Description("Set unreal month 1")
    @Test
    void invalidMonthFormatCredit1() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), InvalidDataInfo.CardFields.getInvalidMonth1(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .monthFieldError();
    }

    @DisplayName("Wrong format  month2 to valid date fields")
    @Description("Set unreal month 2")
    @Test
    void invalidMonthFormatPayment2() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), InvalidDataInfo.CardFields.getInvalidMonth2(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .monthFieldError();
    }

    @DisplayName("Wrong format  month2 to valid date fields")
    @Description("Set unreal month 2")
    @Test
    void invalidMonthFormatCredit2() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), InvalidDataInfo.CardFields.getInvalidMonth2(), Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .monthFieldError();
    }

    @DisplayName("Wrong format  month 00 to valid date fields")
    @Description("Set unreal month 00")
    @Test
    void invalidMonthFormatPayment00() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), "00", Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .monthFieldError();
    }

    @DisplayName("Wrong format  month 00 to valid date fields")
    @Description("Set unreal month 00")
    @Test
    void invalidMonthFormatCredit00() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), "00", Info.getYear(), Info.getRandomOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .monthFieldError();
    }

    @DisplayName("Wrong format cvc")
    @Description("Set unreal month 1")
    @Test
    void invalidCVCFormatPayment() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), Info.getYear(), Info.getRandomOwner(), InvalidDataInfo.CardFields.getInvalidCvcCode())
                .continueButtonClick()
                .cvcFieldError();
    }

    @DisplayName("Wrong format cvc")
    @Description("Set unreal month 1")
    @Test
    void invalidCVCFormatCredit() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), Info.getYear(), Info.getRandomOwner(), InvalidDataInfo.CardFields.getInvalidCvcCode())
                .continueButtonClick()
                .cvcFieldError();
    }

    @DisplayName("Wrong format owner")
    @Description("Don't set owner")
    @Test
    void invalidOwnerFormatPayment() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), Info.getYear(), "", Info.getRandomCvcCode())
                .continueButtonClick()
                .ownerFieldError();
    }

    @DisplayName("Wrong format owner")
    @Description("Don't set owner")
    @Test
    void invalidOwnerFormatCredit() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), Info.getYear(), "", Info.getRandomCvcCode())
                .continueButtonClick()
                .ownerFieldError();
    }

    @DisplayName("Wrong format owner")
    @Description("Set russian owner")
    @Test
    void invalidOwnerRusFormatPayment() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .paymentButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), Info.getYear(), InvalidDataInfo.CardFields.getInvalidRusOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .notificationStatusError();
    }

    @DisplayName("Wrong format owner")
    @Description("Set russian owner")
    @Test
    void invalidOwnerRusFormatCredit() {
        PageObjectsTravel travel = new PageObjectsTravel()
                .creditButtonClick()
                .setFields(Info.getFirstCard(), Info.getMonth(), Info.getYear(), InvalidDataInfo.CardFields.getInvalidRusOwner(), Info.getRandomCvcCode())
                .continueButtonClick()
                .notificationStatusError();
    }
}