package org.example.http;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HTTPParser {
    String link;

    public HTTPParser(String link) {
        this.link = link;
    }

    public String[] parse(){
        String[] strings = new String[5];


        System.out.println(this.getClass().getClassLoader().getResource("chromedriver.exe").getPath());
        System.setProperty("webdriver.gecko.driver", this.getClass().getResource("chromedriver.exe").toString());

        WebDriver driver = new ChromeDriver();

        driver.get(link);

        System.out.println(driver.findElement(By.className("product-buy__price")).getText().split(" ")[0]);
        driver.close();


        return strings;

    }
}
