package onlineshopapp.fashionstore.seleniumAdmin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditProduct extends AbstractPage {

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "description")
    private WebElement descriptionInput;

    @FindBy(id = "image")
    private WebElement imageInput;

    @FindBy(id = "image1")
    private WebElement image1Input;

    @FindBy(id = "image2")
    private WebElement image2Input;

    @FindBy(id = "image3")
    private WebElement image3Input;

    @FindBy(id = "price")
    private WebElement priceInput;

    @FindBy(id = "grade")
    private WebElement gradeInput;

    @FindBy(id = "quantitySizeS")
    private WebElement quantitySizeSInput;

    @FindBy(id = "quantitySizeM")
    private WebElement quantitySizeMInput;

    @FindBy(id = "quantitySizeL")
    private WebElement quantitySizeLInput;

    @FindBy(id = "quantitySizeXL")
    private WebElement quantitySizeXLInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public AddOrEditProduct(WebDriver driver) {
        super(driver);
    }

    public static AddOrEditProduct openAddProductPage(WebDriver driver) {
        get(driver, "/products/add");
        return PageFactory.initElements(driver, AddOrEditProduct.class);
    }


    public ProductsPage addProduct(WebDriver driver, String name, String description, String image, String image1, String image2, String image3, double price, int grade, int quantitySizeS, int quantitySizeM, int quantitySizeL, int quantitySizeXL) {
        nameInput.sendKeys(name);
        descriptionInput.sendKeys(description);
        imageInput.sendKeys(image);
        image1Input.sendKeys(image1);
        image2Input.sendKeys(image2);
        image3Input.sendKeys(image3);
        priceInput.sendKeys(String.valueOf(price));
        gradeInput.sendKeys(String.valueOf(grade));
        quantitySizeSInput.sendKeys(String.valueOf(quantitySizeS));
        quantitySizeMInput.sendKeys(String.valueOf(quantitySizeM));
        quantitySizeLInput.sendKeys(String.valueOf(quantitySizeL));
        quantitySizeXLInput.sendKeys(String.valueOf(quantitySizeXL));
        submitButton.click();
        return PageFactory.initElements(driver, ProductsPage.class);
    }

    public ProductsPage editProduct( String name, String description, String image, String image1, String image2, String image3, double price, int grade, int quantitySizeS, int quantitySizeM, int quantitySizeL, int quantitySizeXL) {
        nameInput.sendKeys(name);
        descriptionInput.sendKeys(description);
        imageInput.sendKeys(image);
        image1Input.sendKeys(image1);
        image2Input.sendKeys(image2);
        image3Input.sendKeys(image3);
        priceInput.sendKeys(String.valueOf(price));
        gradeInput.sendKeys(String.valueOf(grade));
        quantitySizeSInput.sendKeys(String.valueOf(quantitySizeS));
        quantitySizeMInput.sendKeys(String.valueOf(quantitySizeM));
        quantitySizeLInput.sendKeys(String.valueOf(quantitySizeL));
        quantitySizeXLInput.sendKeys(String.valueOf(quantitySizeXL));
        submitButton.click();
        return PageFactory.initElements(driver, ProductsPage.class);
    }
}