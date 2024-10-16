package org.example.mantisautomation;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Selectors {

    // Elementos para o fluxo de login
    public SelenideElement usernameInput = $x("//input[contains(@id,'username')]");
    public SelenideElement passwordInput = $x("//input[contains(@id,'password')]");
    public SelenideElement loginButton = $x("//input[contains(@type,'submit')]");

    // Elementos para o fluxo de reportar bug
    public SelenideElement reportIssueButton = $x("//a[@href='bug_report_page.php']");
    public SelenideElement categorySelect = $x("//select[contains(@id,'category_id')]");
    public SelenideElement reproducibilitySelect = $x("//select[contains(@id,'reproducibility')]");
    public SelenideElement severitySelect = $x("//select[contains(@id,'severity')]");
    public SelenideElement prioritySelect = $x("//select[contains(@id,'priority')]");

    // Elementos para detalhes do bug
    public SelenideElement platformInput = $x("//input[contains(@id,'platform')]");
    public SelenideElement osInput = $x("//input[@id='os']");
    public SelenideElement osVersionInput = $x("//input[contains(@id,'os_build')]");
    public SelenideElement summaryInput = $x("//input[contains(@id,'summary')]");
    public SelenideElement descriptionInput = $x("//textarea[contains(@id,'description')]");
    public SelenideElement submitIssueButton = $x("//input[@type='submit']");

    // Elementos para logout
    public SelenideElement usernameInfo = $x("//span[contains(@class,'user-info')]"); // Menu de usuário
    public SelenideElement logoutButton = $x("//*[@id=\"navbar-container\"]/div[2]/ul/li[2]/ul/li[4]/a"); // Botão de logout
}
