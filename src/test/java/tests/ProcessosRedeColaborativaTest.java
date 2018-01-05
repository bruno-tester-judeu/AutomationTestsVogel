package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ProcessosRedeColaborativaTest {

    protected String baseURL = "https://hlgredecolaborativa.vogeltelecom.com";

    @Test
    public void testProcessoChamadoBug() {

        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bruno.machado\\IdeaProjects\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        this.login("teste4 ", "vogel@123", navegador);

        //Validar que dentro do elemento com class "me" está o texto "Hi, Bruno Tester"
        /*WebElement me = navegador.findElement(By.id("logged-user-name"));
        String textoNoElementoMe = me.getText();
        assertEquals("default user", textoNoElementoMe);*/

        //Clicar no ícone lateral >
        navegador.findElement(By.cssSelector("[wcm-menu-content] span")).click();

        //Clicar em Processos
        navegador.findElement(By.linkText("Processos")).click();

        //Expandir Categoria de Processos
        navegador.findElement(By.cssSelector("tr[data-tt-id='Suporte'] a")).click();

        //Selecionar processo
        navegador.findElement(By.cssSelector("tr[data-process-id='D0009-chamado_suporte'] span[data-open-view]")).click();
       // navegador.findElement(By.cssSelector("data-tt-id=\"process_Suporte.Chamado de Suporte (BUG)\"")).click();
        /*
       //Pesquisar pelo processo
        navegador.findElement(By.id("process-start-search-input")).sendKeys("Chamado de Suporte (BUG)");

        Clicar no processo
        navegador.findElement(By.linkText("Chamado de Suporte (BUG)")).click(); */

        //Entrar no Iframe
        navegador.switchTo().frame("workflowView-cardViewer");

        //Preencher o campo "Módulo"
       // navegador.findElement(By.id("modulo")).click();
       // navegador.findElement(By.cssSelector("#modulo > option:nth-child(21)")).click();
        WebElement campoType =  navegador.findElement(By.id("modulo"));
        new Select(campoType).selectByVisibleText("Financeiro");

        //Preencher o campo "URL"
       navegador.findElement(By.id("url")).sendKeys(this.baseURL + "/portal/p/Vogel/pageworkflowview?processID=D0009-chamado_suporte");

        //Preencher o campo "Contato do Solicitante"
        navegador.findElement(By.id("ramal")).sendKeys("1260");

        //Preencher o campo "URL"
        navegador.findElement(By.name("descricao")).sendKeys("Teste equipe sistemas.\n" +
                "Qualquer dúvida entrar em contato no ramal 1260.");

        //Sair do Iframe
        navegador.switchTo().defaultContent();

        //Clicar em "Enviar"
        navegador.findElement(By.cssSelector("#workflowActions > button:nth-child(1)")).click();

        //Número solicitação
        navegador.findElement(By.cssSelector("#workflowView-conclusion-modal > div > div > div.modal-body > p:nth-child(1) > span")).getText();

        String idSolicitacao = navegador.findElement(By.cssSelector("#workflowView-conclusion-modal > div > div > div.modal-body > p:nth-child(1) > span")).getText();

        //Fechar navegador
        //navegador.quit();

        //Abrindo o navegador
        //navegador = new ChromeDriver();

        //this.login("djunior", "1", navegador);


    }

    private void login(String user, String password, WebDriver navegador){
        //Navegando para a página da Rede Colaborativa!
        navegador.get(this.baseURL + "/portal/home");

        //digitar no campo com name "j_username" o texto "teste4"
        navegador.findElement(By.name("j_username")).sendKeys(user);

        //digitar no campo com name "password" o texto "vogel@123"
        navegador.findElement(By.name("j_password")).sendKeys(password);

        //clicar no link com o id "submitLogin"
        navegador.findElement(By.id("submitLogin")).click();
        //navegador.get("http://www.juliodelima.com.br/taskit");
    }

}
