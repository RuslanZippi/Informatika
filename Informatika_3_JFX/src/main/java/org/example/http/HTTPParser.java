package org.example.http;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HTTPParser {
    private static final HTTPParser httpParser = new HTTPParser();

    private HTTPParser() {
        System.setProperty("webdriver.chrome.driver","Informatica\\files\\chromedriver.exe");
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
        strings[4] = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date());
        strings[5] = driver.findElement(By.tagName("img")).getAttribute("src");
        driver.close();
        return strings;

    }

    public ArrayList<String[]> parseLinks(ArrayList<String> linkList){
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] strings;
        WebDriver driver = new ChromeDriver();
        for (int x = 0; x< linkList.size(); x++){
            strings = new String[6];
            driver.navigate().to(linkList.get(x));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            strings[0] = driver.findElement(By.className("product-card-top__code")).getText().split(" ")[2];
            strings[1] = linkList.get(x);
            strings[2] = driver.findElement(By.className("product-card-top__title")).getText();
            strings[3] = driver.findElement(By.className("product-buy__price")).getText().split("₽")[0];
            strings[4] = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(new Date());
            strings[5] = driver.findElement(By.tagName("img")).getAttribute("src");
            arrayList.add(strings);
        }
        driver.close();
        return arrayList;
    }


}
