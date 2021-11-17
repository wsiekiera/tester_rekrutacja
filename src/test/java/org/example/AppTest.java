package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.bytebuddy.build.Plugin;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static WebDriver driver;

    @BeforeClass
    public static void initDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void cleanup() {
        driver.quit();
    }

    @Test
    public void loadMainPage() {
        // Load mainpage
        driver.get("http://ec2-18-202-24-189.eu-west-1.compute.amazonaws.com:8080/");
        // Check if subpage title is "Welcome"
        WebElement title = driver.findElement(By.cssSelector(".xd-container > h2:nth-child(1)"));
        assertEquals("Welcome", title.getText());
    }

    @Test
    public void loadOwnersList() {
        // Load owners page
        driver.get("http://ec2-18-202-24-189.eu-west-1.compute.amazonaws.com:8080/owners/find");
        //List all owners
        WebElement findOwnerButton = driver.findElement(By.xpath("//button[@type='submit']"));
        findOwnerButton.click();
        // Check if subpage title is "Owners"
        WebElement title = driver.findElement(By.cssSelector(".xd-container > h2:nth-child(1)"));
        assertEquals("Owners", title.getText());
        // Get table columns
        List<WebElement> columns = driver
                .findElement(By.cssSelector("#owners > thead:nth-child(1) > tr:nth-child(1)"))
                .findElements(By.tagName("th"));
        // Check if there are 5 columns total
        assertEquals(5, columns.size());
        // Check some column names
        assertEquals("Name", columns.get(0).getText());
        assertEquals("Address", columns.get(1).getText());
        assertEquals("City", columns.get(2).getText());
    }


}
