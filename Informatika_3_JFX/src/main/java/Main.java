import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    static URL url;
    static String urlString = "https://www.dns-shop.ru/product/d1148c3cbeac2eb0/umnaa-rozetka-irbis-irhs10/";
    static String driverPath = Main.class.getResource("chromedriver.exe").getPath();

    static {
        try {
            url = new URL("https://www.dns-shop.ru/product/d1148c3cbeac2eb0/umnaa-rozetka-irbis-irhs10/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver",driverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.dns-shop.ru/product/d1148c3cbeac2eb0/umnaa-rozetka-irbis-irhs10/");
        System.out.println(driver.findElement(By.className("product-buy__price")).getText().split(" ")[0]);
        driver.close();
    }
}
