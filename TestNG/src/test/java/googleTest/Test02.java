package googleTest;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Test02 {

    @Test //Осуществить поиск в google и ya по слову "машина", сравнить фразы друг с другом
    public void test02_1 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor)driver;

        try {

            driver.get("https://google.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[1]")).sendKeys("машина" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement element1 = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div.yuRUbf > a > h3"));
            Thread.sleep(1000);
            String actualResultGoogle = element1.getText();

            //Открыть новое окно (вкладку)
            String window1 = driver.getWindowHandle();
            js.executeScript("window.open()");
            Set<String> currentWindows = driver.getWindowHandles();
            String window2 = null;
            for (String window : currentWindows) {
                if(!window.equals(window1)) {
                    window2 = window;
                    break;
                }
            };

            driver.switchTo().window(window2);
            driver.get("https://ya.ru");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"text\"]")).sendKeys("машина" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement element2 = driver.findElement(By.xpath("//*[@id=\"search-result\"]/li[1]/div/h2"));
            Thread.sleep(1000);
            String actualResultYa = element2.getText();

            Assert.assertEquals("Сравнение", actualResultGoogle, actualResultYa);

            driver.close();
            Thread.sleep(1000);
            driver.switchTo().window(window1);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test02_1 - fail");
        } finally {
            System.out.println("Test02_1 - end");
            driver.quit();
        }
    }

    @Test //Показать результаты первых нерекламных результатов поиска по слову "машина" в google и ya
    public void test02_2 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor)driver;

        try {

            driver.get("https://google.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[1]")).sendKeys("машина" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement element1 = driver.findElement(By.xpath("//*[@id=\"tads\"]/div[1]/div/div/div[1]/a/div[1]"));
            Thread.sleep(1000);
            System.out.println("Результат 1-й строки в Google: " + element1.getText());

            //Открыть новое окно (вкладку)
            String window1 = driver.getWindowHandle();
            js.executeScript("window.open()");
            Set<String> currentWindows = driver.getWindowHandles();
            String window2 = null;
            for (String window : currentWindows) {
                if(!window.equals(window1)) {
                    window2 = window;
                    break;
                }
            };

            driver.switchTo().window(window2);
            driver.get("https://ya.ru");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"text\"]")).sendKeys("машина" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement element2 = driver.findElement(By.xpath("//*[@id=\"search-result\"]/li[1]/div/h2"));
            Thread.sleep(1000);
            System.out.println("Результат 1-й строки в Ya: " + element2.getText());

            driver.close();
            Thread.sleep(1000);
            driver.switchTo().window(window1);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test02_2 - fail");
        } finally {
            System.out.println("Test02_2 - end");
            driver.quit();
        }
    }

    @Test //Открыть дудл на google через кнопку "Мне повезет" и открыть первую ссылку в ya после поиска по слову "дудл"
    public void test02_3 () {
        System.setProperty("webdriver.chrome.driver", "F:\\dev\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor)driver;

        try {

            driver.get("https://google.com");
            Thread.sleep(1000);
            WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[2]"));
            element.click();
            Thread.sleep(1000);

            //Открыть новое окно (вкладку)
            String window1 = driver.getWindowHandle();
            js.executeScript("window.open()");
            Set<String> currentWindows = driver.getWindowHandles();
            String window2 = null;
            for (String window : currentWindows) {
                if(!window.equals(window1)) {
                    window2 = window;
                    break;
                }
            };

            driver.switchTo().window(window2);
            driver.get("https://ya.ru");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"text\"]")).sendKeys("дудл" + Keys.ENTER);
            Thread.sleep(1000);
            WebElement element2 = driver.findElement(By.xpath("//*[@id=\"search-result\"]/li[1]/div/h2/a"));
            Thread.sleep(1000);
            element2.click();
            Thread.sleep(1000);

            driver.close();
            Thread.sleep(1000);
            driver.switchTo().window(window1);
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Test02_3 - fail");
        } finally {
            System.out.println("Test02_3 - end");
            driver.quit();
        }
    }
}
