package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class CardIssuancePage {
    private SelenideElement amount = $x("//span[@data-test-id='amount']//input");
    private SelenideElement from = $x("//span[@data-test-id='from']//input");
    private SelenideElement errorMessage = $x("//*[@data-test-id='error-notification']");
    private SelenideElement buttonSend = $x("//button[@data-test-id='action-transfer']");
    private SelenideElement buttonCancel = $x("//button[@data-test-id='action-cancel']");
    String deleteText = Keys.chord(Keys.CONTROL + "A", Keys.BACK_SPACE);

    public CardIssuancePage() {
    }

    public DashboardPage transferValue(DataHelper.CardNumbInfo info, int value) {
        amount.sendKeys(deleteText);
        amount.setValue(String.valueOf(value));
        from.sendKeys(deleteText);
        from.setValue(info.getNumbCard());
        buttonSend.click();
        return new DashboardPage();
    }

    public void errorTransfer(DataHelper.CardNumbInfo info, int value) {
        transferValue(info, value);
        errorMessage.shouldHave(exactText("Недостаточно средств на карте № " + info.getNumbCard()));
    }
}