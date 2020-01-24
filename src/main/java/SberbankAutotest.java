
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * 1. Перейти на страницу http://www.sberbank.ru/ru/person
 * 2. Нажать на кнопку выбора региона
 * 3. В появившемся «окне» при помощи поиска найти и выбрать Нижегородская область
 * 4. Проверить, что на главной странице отображается выбранная область
 * 5. Сделать скролл до footer объекта на главной странице.
 * 6. Проверить, что footer содержит значки социальных сетей
 */

public class SberbankAutotest {

    public static WebDriver webDriver = null;

    @BeforeClass
    public static void testUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public static void cleanUp() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void sberbank() {
        webDriver.get("http://www.sberbank.ru/ru/person");
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        //Нажимаем на регион
        webDriver.findElement(By.xpath("//a[@class='hd-ft-region']/div/span")).click();

        //Вбиваем в поиск "Нижегородская область" и нажимаем
        WebElement findRegion = webDriver.findElement(By.xpath("//input[contains(@class,'kit-input') and contains(@type,'search')]"));
        findRegion.sendKeys("Нижегородская область");
        webDriver.findElement(By.xpath("//a[contains(text(),'Ниж')]")).click();

        //Ждем пока на странице отобразится выбранный регион
        WebElement element = webDriver.findElement(By.xpath("//a[@class='hd-ft-region']/div/span"));
        wait.until(ExpectedConditions.visibilityOf(element));
        String title = element.getText();
        Assert.assertTrue(title.contains("Нижегородская область"));

        //Скролим до футера вниз с помощью JavaScript
        element = webDriver.findElement(By.xpath("//footer"));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView();", element);

        //Проверяем видимость значка facebook
        element = webDriver.findElement(By.xpath("//footer//span[contains(@class,'social_fb')]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());

        //Проверяем видимость значка tweeter
        element = webDriver.findElement(By.xpath("//footer//span[contains(@class,'social_tw')]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());

        //Проверяем видимость значка youtube
        element = webDriver.findElement(By.xpath("//footer//span[contains(@class,'social_yt')]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());

        //Проверяем видимость значка instagram
        element = webDriver.findElement(By.xpath("//footer//span[contains(@class,'social_ins')]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());

        //Проверяем видимость значка вконтакте
        element = webDriver.findElement(By.xpath("//footer//span[contains(@class,'social_vk')]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());

        //Проверяем видимость значка одноклассники
        element = webDriver.findElement(By.xpath("//footer//span[contains(@class,'social_ok')]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertTrue(element.isDisplayed());

    }
}
