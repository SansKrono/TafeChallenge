package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;
    private String url;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.url = "https://www.tafensw.edu.au/";
    }
    
    // Navigate to the home page
    public void navigateToHomePage() {
        driver.get(url);
    }

    // Enter text in the search bar
    public void enterSearchText(String searchText) {
        WebElement searchInput = driver.findElement(By.id("homePageSearch"));
        searchInput.clear();
        searchInput.sendKeys(searchText);
    }

    // Click the submit button
    public void clickSubmitButton() {
        WebElement submitButton = driver.findElement(By.cssSelector("[aria-label='Submit search']"));
        submitButton.click();
    }
}