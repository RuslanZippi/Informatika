package org.example.http;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;

public class HTTPParser {
    private static final HTTPParser httpParser = new HTTPParser();

    private HTTPParser() {
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\org\\example\\chromedriver.exe");
    }

    public static HTTPParser getInstance(){
        return httpParser;
    }

    public String[] parse(String link){
        String[] strings = new String[6];
        WebDriver driver = new ChromeDriver();

        driver.get(link);



        strings[0] = driver.findElement(By.className("product-card-top__code")).getText().split(" ")[2];
        strings[1] = link;
        strings[2] = driver.findElement(By.className("product-card-top__title")).getText();
        strings[3] = driver.findElement(By.className("product-buy__price")).getText().split("₽")[0];
        strings[4] = new Date().toString();
        strings[5] = driver.findElement(By.tagName("img")).getAttribute("src");

//        System.out.println(driver.findElement(By.tagName("img")).getAttribute("src"));
        driver.close();
        return strings;

    }


}
