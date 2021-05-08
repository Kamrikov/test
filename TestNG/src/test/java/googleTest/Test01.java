package googleTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Test01 {

    @Test //Открыть google.com
    public void test01_1 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://google.com");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Test01_1 - fail");
        } finally {
            System.out.println("Test01_1 - end");
            driver.quit();
        }
    }
    @Test //Открыть google.com, осуществить поиск по слову "машина", сравнить фактический результат с ожидаемым
    public void test01_2 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://google.com");
            driver.findElement(By.xpath("//input[1]")).sendKeys("машина" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement result = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div.yuRUbf > a > h3"));
            String expectedResult = "Машина — Википедия";
            String actualResult = result.getText();
            Assert.assertEquals("Неверное значение", expectedResult, actualResult);
        } catch (Exception e) {
            System.out.println("Test01_2 - fail");
        } finally {
            System.out.println("Test01_2 - end");
            driver.quit();
        }
    }
    @Test //Открыть google.com, осуществить поиск по слову "машина", перейти по первой нерекламной ссылке
    public void test01_3 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://google.com");
            driver.findElement(By.xpath("//input[1]")).sendKeys("машина" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement element = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div.yuRUbf > a > h3"));
            element.click();
            Thread.sleep(3000);

            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            //WebDriver element = (new WebDriverWait(driver, Duration.ofSeconds(10))
                    //.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=/'w3bYAd/']/div/div/div/div[2]/a[3]"))));

        } catch (Exception e) {
            System.out.println("Test01_3 - fail");
        } finally {
            System.out.println("Test01_3 - end");
            driver.quit();
        }
    }

}
