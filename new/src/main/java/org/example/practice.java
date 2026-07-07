package org.example;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class practice {
    public static void main(String[] args){
    WebDriver driver = new ChromeDriver();
    driver.get("https://google.com");

    WebElement ele= driver.findElement(By.id("input"));
    ele.click();
//    Actions actions= new Actions(driver);
//    actions.contextClick(ele).perform();
}
}