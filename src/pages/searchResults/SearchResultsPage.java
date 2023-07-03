package pages.searchResults;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchResultsPage {
    private final WebDriver driver;

    // Constructor
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     Check if a search result matches the desired text
     @return True if the course name was found in the search results
     */
    public boolean isCourseNameInSearchResults(String courseName) {
        try {
        	new WebDriverWait(driver, Duration.ofSeconds(5))
      	          .until(driver -> driver.findElement(By.xpath("//h4[contains(text(), '" + courseName + "')]")));
        	return true;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No element found with the expected string: " + courseName);
        }
    }
    
    /**
     Get all applied filter buttons from the search results
     @return    A list of WebElements present on the page
     */
    public List<WebElement> getAppliedFilters() {
        WebElement searchResults = driver.findElement(By.className("items-start"));
        return searchResults.findElements(By.tagName("button"));
    }

    /**
     Expand the search filter category and select the specified filter option
     */
    public void selectFilterOption(String filterCategory, String filterId) {
    	String filterCategoryXPath = String.format("//*[contains(text(),'%s')]", filterCategory);
    	
        WebElement filterDropdown = driver.findElement(By.xpath(filterCategoryXPath));
        filterDropdown.click();
        WebElement filterOption = driver.findElement(By.id(filterId));
        filterOption.click();
    }
}


