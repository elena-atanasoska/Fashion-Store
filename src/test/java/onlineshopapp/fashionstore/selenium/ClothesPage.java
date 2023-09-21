package onlineshopapp.fashionstore.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class ClothesPage extends AbstractPage {

    WebDriverWait wait = new WebDriverWait(driver, 10);
    List<WebElement> clothes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-4")));

    @FindBy(id = "name-sort")
    private WebElement byName;
    @FindBy(id = "price-sort")
    private WebElement byPrice;
    @FindBy(id = "latest-sort")
    private WebElement byDate;
    @FindBy(id = "rating-sort")
    private WebElement byRating;


    public ClothesPage(WebDriver driver) {
        super(driver);
    }

    public static ClothesPage to(WebDriver driver) {
        get(driver, "/products");
        return PageFactory.initElements(driver, ClothesPage.class);
    }

    public void assertElemts(int productsNumber) {
        Assert.assertEquals("rows do not match", productsNumber, this.clothes.size());
    }

    public void assertSortName() {
//        byName.click();
        WebElement dropdownToggle = driver.findElement(By.cssSelector(".col .dropdown div:first-child"));
        dropdownToggle.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdownOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-content")));

        WebElement nameOption = dropdownOptions.findElement(By.id("name-sort"));
        nameOption.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("name-sort")));

        List<WebElement> clothesTmp = driver.findElements(By.className("col-4"));
        Assert.assertNotEquals(clothesTmp, clothes);
    }

    public void assertSortPrice() {
//        byPrice.click();
        WebElement dropdownToggle = driver.findElement(By.cssSelector(".col .dropdown div:first-child"));
        dropdownToggle.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdownOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-content")));

        WebElement priceOption = dropdownOptions.findElement(By.id("price-sort"));
        priceOption.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("price-sort")));


        List<WebElement> clothesTmp = driver.findElements(By.className("col-4"));
        Assert.assertNotEquals(clothesTmp, clothes);
    }

    public void assertSortByDate() {
//        byDate.click();
        WebElement dropdownToggle = driver.findElement(By.cssSelector(".col .dropdown div:first-child"));
        dropdownToggle.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdownOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-content")));

        WebElement latestOption = dropdownOptions.findElement(By.id("latest-sort"));
        latestOption.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-4")));

        List<WebElement> clothesTmp = driver.findElements(By.className("col-4"));
        Assert.assertNotEquals(clothesTmp, clothes);
    }

    public void assertSortByRating() {
//        byRating.click();
        WebElement dropdownToggle = driver.findElement(By.cssSelector(".col .dropdown div:first-child"));
        dropdownToggle.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dropdownOptions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dropdown-content")));

        WebElement ratingOption = dropdownOptions.findElement(By.id("rating-sort"));
        ratingOption.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-4")));

        List<WebElement> clothesTmp = driver.findElements(By.className("col-4"));
        Assert.assertNotEquals(clothesTmp, clothes);
    }

}

