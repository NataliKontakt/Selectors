import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectorTest {

    private static WebDriver driver;



    @BeforeAll
    static void SetUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/win/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    void SetUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestPositiveV1() throws InterruptedException {
        driver.get("http://localhost:9999");
//        List<WebElement> inputs = driver.findElements(By.tagName("input"));
//        inputs.get(0).sendKeys("Петров Иван");
//        inputs.get(1).sendKeys("+89128466666");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+89128466666");

        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegativeV1() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Petrov Ivan");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("span[data-test-id=name] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegativeV2() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Перов + Иван");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("span[data-test-id=name] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegativeV3() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("89128466666");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("span[data-test-id=phone] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldTestNegativeV4() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+8912846666666");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("span[data-test-id=phone] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegativeV5() throws InterruptedException {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+89128466666");

        driver.findElement(By.className("button")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("label")).getText().trim();
        assertEquals(expected, actual);
    }

}
