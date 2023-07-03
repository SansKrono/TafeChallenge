package tests;

import java.time.Duration;
import java.util.List;

import pages.searchResults.*;
import pages.searchResults.utils.*;
import pages.home.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchAndFilterCourse {

	public static void main(String[] args) throws InterruptedException {
		
        // 0. Test Setup
		System.setProperty(
                "webdriver.chrome.driver",
                "/Users/aaron/Documents/dev/drivers/chromedriver_mac_arm64/chromedriver"
        );
        WebDriver driver = new ChromeDriver();
        
        // Storing the desired course as a variable increases the reusability of the proceeding code
        String desiredCourse = "Advanced Barista Skills";
        SearchFilter[] desiredFilters = {
                new SearchFilter(
                        "Delivery",
                        List.of(
                            new SearchFilterOption("On campus", "on-campus")
                            )
                ),
            };
        
        // 1. After arriving on the https://www.tafensw.edu.au/ website
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        
        // 2. Search for a course using the search box e.g.: Advanced Barista Skills
        homePage.enterSearchText(desiredCourse);

        // 3. Click on the search button to submit the search
        homePage.clickSubmitButton();
        
        // Wait for the search results page to load
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        
        // 4. Verify that the course that you searched for appears in the results
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        boolean isCourseInResults = searchResultsPage.isCourseNameInSearchResults(desiredCourse);
        
        if (isCourseInResults) {
        	System.out.printf("PASS: \"%s\" course found in results%n", desiredCourse);
        } else {
            throw new AssertionError(String.format("FAIL: \"%s\" course not found in results", desiredCourse));
        }
        
        Thread.sleep(2000);
        // 5. Apply the "delivery filter" by setting the filter to "On Campus"

        // Find the filter category to click ("Delivery")
        for (SearchFilter category : desiredFilters) {
        	String categoryType = category.categoryName();
        	List<SearchFilterOption> filters = category.filterOption();

            // Once the dropdown list is opened, select the filter option ("On campus")
            for (SearchFilterOption filter : filters) {
            	searchResultsPage.selectFilterOption(categoryType, filter.filterId());
            }
        }
        
        Thread.sleep(2000);
        // 6. Verify that the filter has been applied correctly to your chosen course
        List<WebElement> filterTags = searchResultsPage.getAppliedFilters();

        // Assert that at least one element contains the desired text
        for (SearchFilter category : desiredFilters ) {
        	List<SearchFilterOption> filters = category.filterOption();
        	for (SearchFilterOption filter : filters) {
                boolean isFilterPresent = filterTags.stream()
                        .map(WebElement::getText)
                        .anyMatch(text -> text.contains(filter.filterName()));
                if (isFilterPresent) {
                	System.out.printf("PASS: \"%s\" filter is applied to search%n", filter.filterName());
                } else {
                    throw new AssertionError(String.format("FAIL: \"%s\" filter is NOT applied to search", filter.filterName()));
                }
        	}
        }
        
        Thread.sleep(2000);
        driver.quit();
	}

}