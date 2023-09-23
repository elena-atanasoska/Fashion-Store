package onlineshopapp.fashionstore.seleniumAdmin;

import onlineshopapp.fashionstore.model.Clothes;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.service.ClothesService;
import onlineshopapp.fashionstore.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    ClothesService clothesService;
    @Autowired
    UserService userService;

    private HtmlUnitDriver driver;

    private static boolean dataInitialized = false;

    public static List<Clothes> clothes;
    public static Clothes product1;
    public static Clothes product2;
    private static User adminUser;

    @BeforeEach
    public void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    public void initData() {
        if (!dataInitialized) {
            // adminUser = userService.register("admin", "admina", "admin", "admin", Role.ROLE_ADMIN, "adminadmin@gmail.com");
            //  product1 = this.clothesService.create("test", "test", "test","test","test","test",0.0,0.0,10,10,10,10);
            //  product2 = this.clothesService.create("test", "test", "test","test","test","test",0.0,0.0,10,10,10,10);
            dataInitialized = true;
        }
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAdminLogin() throws Exception {
        ProductsPage productsPage = ProductsPage.to(this.driver);
        productsPage.assertElements(3, 0, 0, 0);

        LoginPage loginPage = LoginPage.openLogin(this.driver);
        productsPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");

        productsPage.assertElements(3, 3, 3, 1);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAddProduct() throws Exception {
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        ProductsPage productsPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");

        AddOrEditProduct addOrEditProduct = AddOrEditProduct.openAddProductPage(this.driver);
        productsPage = addOrEditProduct.addProduct(this.driver, "name", "description", "image1", "image2", "image3", "image4", 33.3, 3, 44, 55, 66, 77);

        productsPage.assertElements(4, 4, 4, 1);
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testEditProduct() throws Exception {
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        ProductsPage productsPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");

        String productNameToEdit = "name";
        String newProductName = "newName";
        productsPage = productsPage.editProductByName(productNameToEdit, newProductName);

        assertTrue(productsPage.isProductNameDisplayed(newProductName));
        productsPage.assertElements(4, 4, 4, 1);
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testDeleteProduct() throws Exception {
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        ProductsPage productsPage = LoginPage.doLogin(this.driver, loginPage, "admin", "admin");
        String productNameToDelete = "newName";
        productsPage = productsPage.deleteProductByName(productNameToDelete);
        productsPage.assertElements(3, 3, 3, 1);
    }
}
