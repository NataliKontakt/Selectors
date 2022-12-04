import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectorTest {

    private WebDriver driver;

    @BeforeAll
    static void SetUpAll() {
        System.setProperty("webdriver.chrome.driver", ".\\driver\\win\\chromedriver.exe");
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
    void shouldTestV1() throws InterruptedException {
        driver.get("http://localhost:9999");
//        List<WebElement> inputs = driver.findElements(By.tagName("input"));
//        inputs.get(0).sendKeys("Петров Иван");
//        inputs.get(1).sendKeys("+89128466666");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+89128466666");

        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success")).getText().trim();
        assertEquals(expected, actual);
    }

}
