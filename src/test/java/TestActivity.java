import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestActivity {

    private WebDriver webDriver ;

    @BeforeAll
    public void setupAll(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
    }

    @BeforeEach
    public void setupEach(){
        this.webDriver = new ChromeDriver();
    }

    @AfterAll
    public void fecharBrowser(){
        webDriver.close();
    }

    @Test
    //Abrindo página
    public void abrePagina(){
        webDriver.get("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/app_Login/");
        Assertions.assertEquals("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/app_Login/",webDriver.getCurrentUrl());
    }

    //Teste de homologação do usuário do sistema
    @Test
    public void inserirDados(){
        webDriver.get("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/app_Login/");
        webDriver.findElement(By.id("inputEmail")).sendKeys("admin");
        webDriver.findElement(By.id("inputPassword")).sendKeys("admin"+ Keys.ENTER);
        Assertions.assertEquals("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/app_Login/",webDriver.getCurrentUrl());
    }

    @Test
    public void gerarRelatorioBi(){
        webDriver.get("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/chart_new/?nmgp_outra_jan=true&nmgp_start=SC&905");
        WebElement opcaoRelatorioBiMenu = webDriver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/div[2]/table/tbody/tr/td/div/div[2]/ul/li[4]/a"));
        opcaoRelatorioBiMenu.click();

    }

    //Buscando dado em uma consulta e fazendo submit
    @Test
    public void buscar(){
        webDriver.get("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/grid_new/?nmgp_outra_jan=true&nmgp_start=SC&1979");
        WebElement opcaoRelatorioBiMenu = webDriver.findElement(By.xpath("/html/body/table/tbody/tr/td/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/span/span/input"));
        opcaoRelatorioBiMenu.sendKeys("15");
        WebElement enviarDado =  webDriver.findElement(By.id("SC_fast_search_submit_top"));
        enviarDado.click();
    }

    //Inspecionando botão de geração de .PDF
    @Test
    public void botaoGerarPdf(){
        webDriver.get("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/grid_new/?nmgp_outra_jan=true&nmgp_start=SC&1979");
        Actions actions = new Actions(webDriver);
        WebElement botao = webDriver.findElement(By.id("sc_btgp_btn_group_1_top"));
        actions.moveToElement(botao).build().perform();
        Assertions.assertTrue(botao.isDisplayed());
    }

    //mudança para o tipo de visualização do gráfico para gráfico de barras horizontais
    @Test
    public void botaoTiposDeGrafico(){
        webDriver.get("https://www.joaopedro.myscriptcase.com/scriptcase9/app/postManager/chart_new/?nmgp_outra_jan=true&nmgp_start=SC&329");
        Actions actions = new Actions(webDriver);
        WebElement botao = webDriver.findElement(By.id("chart_type_top"));
        actions.moveToElement(botao).build().perform();
        Assertions.assertFalse(botao.isSelected());
        botao.click();
        botao = webDriver.findElement(By.id("sc-id-chart-bar2d"));
        botao.click();
    }


}
