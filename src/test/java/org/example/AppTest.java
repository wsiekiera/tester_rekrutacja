package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomUtils;
import org.checkerframework.checker.units.qual.min;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static WebDriver driver;

    @BeforeClass
    public static void initDriver() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\darek\\IdeaProjects\\tester_rekrutacja\\chromedriver.exe");
//        driver = new ChromeDriver(chromeOptions);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.addArguments("--no-sandbox", "--ignore-certificate-errors");
        chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver(chromeOptions);
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
    // Metods
    public String getRandomText(int charactersCount) {
        return (RandomStringUtils.randomAlphabetic(charactersCount));
    }


    @Test
    public void newOwnerAndEdit() {
        // Load owners page
        driver.get("http://ec2-18-202-24-189.eu-west-1.compute.amazonaws.com:8080/owners/find");
        // Click Add Owner Button
        WebElement findAddOwnerButton = driver.findElement(By.xpath("/html/body/div/div/a"));
        findAddOwnerButton.click();
        // Input First Name
        WebElement inputFirstName = driver.findElement(By.id("firstName"));
        inputFirstName.sendKeys(getRandomText(10));
        // Input Last Name
        WebElement inputLastName = driver.findElement(By.id("lastName"));
        inputLastName.sendKeys(getRandomText(10));
        WebElement inputAddress = driver.findElement(By.id("address"));
        inputAddress.sendKeys(getRandomText(10));
        WebElement inputCity = driver.findElement(By.id("city"));
        inputCity.sendKeys(getRandomText(5));
        WebElement inputTelephone = driver.findElement(By.id("telephone"));
        inputTelephone.sendKeys("234432234");
        WebElement findOwnerButton = driver.findElement(By.xpath("//button[@type='submit']"));
        findOwnerButton.click();
        WebElement findEditOwnerButton = driver.findElement(By.xpath("/html/body/div/div/a[1]"));
        findEditOwnerButton.click();
        WebElement inputSecondAddress = driver.findElement(By.id("address"));
        inputSecondAddress.sendKeys(getRandomText(4));
        WebElement findUpdateOwnerButton = driver.findElement(By.xpath("//*[@id=\"add-owner-form\"]/div[2]/div/button"));
        findUpdateOwnerButton.click();
        WebElement findAddNewPetButton = driver.findElement(By.xpath("/html/body/div/div/a[2]"));
        findAddNewPetButton.click();
        WebElement inputPetName = driver.findElement(By.id("name"));
        inputPetName.sendKeys(getRandomText(6));

        String dateTime ="2021-09-08";

        WebElement inputBirthDate = driver.findElement(By.id("birthDate"));
        inputBirthDate.sendKeys(dateTime);

//        WebElement groupPet = driver.findElement(By.xpath("//*[@id=\"type\"]"));
//        groupPet.click();

        Select dropPet = new Select(driver.findElement(By.xpath("//*[@id=\"type\"]")));
        dropPet.selectByVisibleText("bird");

        WebElement findAddPetButton = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button"));
        findAddPetButton.click();



    }

    @Test
    public void testAssert() {


        driver.get("http://ec2-18-202-24-189.eu-west-1.compute.amazonaws.com:8080/owners/find");
        String url = driver.getCurrentUrl();
        assertEquals(url, "http://ec2-18-202-24-189.eu-west-1.compute.amazonaws.com:8080/owners/find");


        WebElement findAddOwnerButtonAssert = driver.findElement(By.xpath("/html/body/div/div/a"));
        Assert.assertEquals();
        findAddOwnerButtonAssert.click();
//        WebElement buton
//        Assert.assertFalse("Button should be enabled", buton.isEnabled());


        WebElement inputFirstNameAssert = driver.findElement(By.id("firstName"));
        inputFirstNameAssert.sendKeys(getRandomText(10));

        // Input Last Name
        WebElement inputLastNameAssert = driver.findElement(By.id("lastName"));
        inputLastNameAssert.sendKeys(getRandomText(10));
        WebElement inputAddressAssert = driver.findElement(By.id("address"));
        inputAddressAssert.sendKeys(getRandomText(10));
        WebElement inputCityAssert = driver.findElement(By.id("city"));
        inputCityAssert.sendKeys(getRandomText(5));
        WebElement inputTelephoneAssert = driver.findElement(By.id("telephone"));
        inputTelephoneAssert.sendKeys("234432234");
        WebElement findOwnerButtonAssert = driver.findElement(By.xpath("//button[@type='submit']"));
        findOwnerButtonAssert.click();
        WebElement findAddNewPetButtonAssert = driver.findElement(By.xpath("/html/body/div/div/a[2]"));
        findAddNewPetButtonAssert.click();
        WebElement inputPetNameAssert = driver.findElement(By.id("name"));
        inputPetNameAssert.sendKeys(getRandomText(6));

        String dateTimeAssert ="2021-09-08";

        WebElement inputBirthDateAssert = driver.findElement(By.id("birthDate"));
        inputBirthDateAssert.sendKeys(dateTimeAssert);

//        WebElement groupPet = driver.findElement(By.xpath("//*[@id=\"type\"]"));
//        groupPet.click();

        Select dropPetAssert = new Select(driver.findElement(By.xpath("//*[@id=\"type\"]")));
        dropPetAssert.selectByVisibleText("bird");

        WebElement findAddPetButtonAssert = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button"));
        findAddPetButtonAssert.click();

        WebElement findAddVisitButtonAssert = driver.findElement(By.xpath("/html/body/div/div/table[2]/tbody/tr/td[2]/table/tbody/tr/td[2]/a"));
        findAddVisitButtonAssert.click();

        WebElement inputDescriptionAssert = driver.findElement(By.id("description"));
        inputDescriptionAssert.sendKeys("test");

        WebElement findAddVisitButtonTwoAssert = driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button"));
        findAddVisitButtonTwoAssert.click();


    }



}
