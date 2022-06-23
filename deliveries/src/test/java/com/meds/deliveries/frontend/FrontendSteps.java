package com.meds.deliveries.frontend;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FrontendSteps {

    private WebDriver driver;

    @Given("an admin wants to check the revenues lately to check if the company is growing in {string}")
    public void adminNavigatesToDashboard(final String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @When("he opens {string} tab")
    public void openRidersTab() {
        driver.findElement(By.id("tabRiders")).click();
    }

    @Then("he sees how many {string} there are")
    public void checkStatistics(String data) {
        if (data.contains("Orders")) {
            assertThat(driver.findElement(By.id("newOrders")).getText(), containsString(data));
        } else if (data.contains("Clients")) {
            assertThat(driver.findElement(By.id("clientsReview")).getText(), containsString(data));
        } else if (data.contains("User")) {
            assertThat(driver.findElement(By.id("newUsers")).getText(), containsString(data));
        } else if (data.contains("Riders")) {
            assertThat(driver.findElement(By.id("newRiders")).getText(), containsString(data));
        }
    }

    // Scenario: Search for a rider
    @Given("an admin that wants to consult information about a rider in {string}")
    public void adminNavigatesToRiders(final String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @And("he searches for {string}")
    public void searchRider(String rider) {
        driver.findElement(By.name("keyword")).click();
        driver.findElement(By.name("keyword")).sendKeys(rider);
        driver.findElement(By.name("keyword")).sendKeys(Keys.ENTER);
    }

    @Then("he sees details of {string}")
    public void riderDetails(String riderName) {
        assertThat(driver.findElement(By.id(riderName)).getText(), containsString(riderName));
    }

    // Scenario: Consulting information about a rider

    @And("he clicks on the {string} button")
    public void clickButton(String button) {
        
        if (button.equals("plus")){
            driver.findElement(By.id("moreInfo")).click();
        }
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}
