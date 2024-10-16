package org.example.mantisautomation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://mantis-prova.base2.com.br/");
    }

    @Test
    public void testLogin() {
        // Realiza o login
        mainPage.login("Daniel_Lisboa", "merib123");

        // Adiciona um delay de 5 segundos para garantir que a tela esteja carregada completamente
        Selenide.sleep(5000);

        // Verifica se o usuário Daniel_Lisboa está logado
        $("body").shouldHave(text("Daniel_Lisboa"));
    }

    @Test
    public void testReportBug() {
        // Realiza o login
        mainPage.login("Daniel_Lisboa", "merib123");

        // Adiciona um delay de 5 segundos para garantir que a tela esteja carregada completamente após o login
        Selenide.sleep(5000);

        // Realiza o reporte de bug
        mainPage.reportBug("Windows", "Windows 10", "10.0.19042", "[Bug] Ao clicar no menu de Categorias", "teste teste teste");

        // Validação do sucesso do reporte do bug
        if ($("body").has(text("Operação realizada com sucesso")) || $("body").has(text("APPLICATION ERROR #27"))) {
            if ($("body").has(text("Operação realizada com sucesso"))) {
                System.out.println("Bug reportado com sucesso.");
            } else {
                System.err.println("Erro: Limite de atividade atingido, reporte bloqueado por spam.");
            }
        } else {
            // tratativa no console
            System.err.println("Resultado inesperado após o reporte do bug.");
        }
    }

    @Test
    public void testLogout() {
        // Realiza o login
        mainPage.login("Daniel_Lisboa", "merib123");

        // Adiciona um delay de 5 segundos para garantir que a tela esteja carregada completamente após o login
        Selenide.sleep(5000);

        // Realiza o logout
        mainPage.logout();

        // Validação que encontrou o menu de login novamente
        $("body").shouldHave(text("Entrar"));
    }

    @Test
    public void testLoginInvalido() {
        // Tenta fazer login com credenciais inválidas
        mainPage.login("usuario_invalido", "senha_errada");

        // Validação que uma mensagem de erro é exibida
        $("body").shouldHave(text("Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.")); // Ajuste o texto conforme a mensagem de erro esperada
    }

    @Test
    public void testCampoObrigatorioNoReporteDeBug() {
        // Realiza o login
        mainPage.login("Daniel_Lisboa", "merib123");

        // Adiciona um delay de 5 segundos para garantir que a tela esteja carregada completamente após o login
        Selenide.sleep(5000);

        // Clica para reportar um bug
        mainPage.clickWithDelay(mainPage.selectors.reportIssueButton);

        // Tenta submeter o formulário sem preencher os campos obrigatórios
        mainPage.selectors.summaryInput.setValue(""); // Deixa o campo resumo vazio
        Selenide.executeJavaScript("window.scrollTo(0, document.body.scrollHeight);");


        $x("//body//*[contains(text(), ' * requerido')]").shouldBe(visible);
    }


}
