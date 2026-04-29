package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TitlePage {
    SelenideElement pageTitle = $x("//h2[contains(@class, 'heading_size_l') and contains(text(), 'Путешествие дня')]");
    SelenideElement buyInCreditButton = $x("//button[contains(., 'Купить в кредит')]");

    public void TransitionToCredit() {
        pageTitle.should(Condition.visible);
        buyInCreditButton.click();
    }
}
