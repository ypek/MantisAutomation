package org.example.mantisautomation;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    Selectors selectors = new Selectors();

    // Método para realizar login
    public void login(String username, String password) {
        selectors.usernameInput.setValue(username);
        clickWithDelay(selectors.loginButton);
        selectors.passwordInput.setValue(password);
        clickWithDelay(selectors.loginButton);
    }

    // Método para reportar um bug
    public void reportBug(String platform, String os, String osVersion, String summary, String description) {
        clickWithDelay(selectors.reportIssueButton);

        selectors.categorySelect.shouldBe(visible).click();
        selectors.categorySelect.selectOptionByValue("1");

        selectors.reproducibilitySelect.shouldBe(visible).click();
        selectors.reproducibilitySelect.selectOption("não se tentou");

        selectors.severitySelect.shouldBe(visible).click();
        selectors.severitySelect.selectOption("grande");

        selectors.prioritySelect.shouldBe(visible).click();
        selectors.prioritySelect.selectOption("urgente");

        // Preenche os detalhes da ISSUE
        selectors.platformInput.setValue(platform);
        selectors.osInput.setValue(os);
        selectors.osVersionInput.setValue(osVersion);
        selectors.summaryInput.setValue(summary);
        selectors.descriptionInput.setValue(description);

        clickWithDelay(selectors.submitIssueButton);
    }

    public void logout() {
        // Clica no menu de usuário
        selectors.usernameInfo.shouldBe(visible).click();

        // Clica no logout
        selectors.logoutButton.shouldBe(visible).click();
    }

    public void clickWithDelay(SelenideElement element) {
        element.shouldBe(visible).click();
        Selenide.sleep(1000);
    }
}
