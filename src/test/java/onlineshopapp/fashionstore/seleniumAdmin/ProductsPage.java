package onlineshopapp.fashionstore.seleniumAdmin;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

@Getter
public class ProductsPage extends AbstractPage {

    WebDriverWait wait = new WebDriverWait(driver, 10);
    List<WebElement> clothes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("col-4")));

    @FindBy(id = "delete-product")
    private List<WebElement> deleteButtons;

    @FindBy(className = "edit-item")
    private List<WebElement> editButtons;

    @FindBy(linkText = "Add new product")
    private List<WebElement> addProductButtons;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public static ProductsPage to(WebDriver driver) {
        get(driver, "/products");
        return PageFactory.initElements(driver, ProductsPage.class);
    }

    public void assertElements(int productsNumber, int deleteButtons, int editButtons, int addProductButtons) throws InterruptedException {
        Assert.assertEquals("products do not match", productsNumber, this.clothes.size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addProductButtons, this.getAddProductButtons().size());
    }

    public WebElement findProductDivByName(String productName) {
        for (WebElement productDiv : clothes) {
            WebElement productNameElement = productDiv.findElement(By.cssSelector("h4"));
            String productText = productNameElement.getText();

            if (productText.equals(productName)) {
                return productDiv;
            }
        }

        throw new NoSuchElementException("Product with name " + productName + " not found");
    }

    public ProductsPage deleteProductByName(String productName) {
        WebElement productDiv = findProductDivByName(productName);
        WebElement deleteButton = productDiv.findElement(By.id("delete-product"));
        deleteButton.click();

        return PageFactory.initElements(driver, ProductsPage.class);
    }

    public ProductsPage editProductByName(String productName, String newProductName) {
        WebElement productDiv = findProductDivByName(productName);
        WebElement editButton = productDiv.findElement(By.className("edit-item"));
        editButton.click();

        WebElement nameInput = driver.findElement(By.name(productName));
        nameInput.clear();
        nameInput.sendKeys(newProductName);

        WebElement saveButton = driver.findElement(By.id("submit"));
        saveButton.click();

        return PageFactory.initElements(driver, ProductsPage.class);
    }

    public boolean isProductNameDisplayed(String productName) {
        for (WebElement productDiv : clothes) {
            WebElement productNameElement = productDiv.findElement(By.cssSelector("h4"));
            String productText = productNameElement.getText();

            if (productText.equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
