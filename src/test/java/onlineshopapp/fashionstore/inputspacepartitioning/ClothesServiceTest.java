package onlineshopapp.fashionstore.inputspacepartitioning;

import onlineshopapp.fashionstore.model.Clothes;
import onlineshopapp.fashionstore.repository.ClothesRepository;
import onlineshopapp.fashionstore.service.ClothesService;
import onlineshopapp.fashionstore.service.impl.ClothesServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ClothesServiceTest {

    @Mock
    private ClothesRepository clothesRepository;

    @InjectMocks
    private ClothesServiceImpl clothesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Requirement 1: Valid input parameters (happy path) - The final product is not null.
    @Test
    public void testCreateValidClothes() {
        String name = "Product Name";
        String description = "Product Description";
        String image = "https://cdn11.bigcommerce.com/s-15be8/images/stencil/1000x1000/products/10290/57225/jx40031__68011.1645402741.png?c=2";
        String image1 = "https://cdn11.bigcommerce.com/s-15be8/images/stencil/1000x1000/products/10290/57225/jx40031__68011.1645402741.png?c=2";
        String image2 = "image-url-3";
        String image3 = "image-url-4";
        double price = 49.99;
        int grade = 4;
        int quantitySizeS = 10;
        int quantitySizeM = 20;
        int quantitySizeL = 15;
        int quantitySizeXL = 5;

        // Mock repository behavior
        Clothes expectedProduct = new Clothes(name, description, image, image1, image2, image3, price, grade, quantitySizeS, quantitySizeM, quantitySizeL, quantitySizeXL);
        when(clothesRepository.save(expectedProduct)).thenReturn(expectedProduct);

        // Call the method under test
        Clothes actualProduct = clothesService.create(name, description, image, image1, image2, image3, price, grade, quantitySizeS, quantitySizeM, quantitySizeL, quantitySizeXL);

        // Assert the result
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }

    // Requirement 2: Invalid input values (e.g., negative price) - The final product is null.
    @Test
    public void testCreateInvalidClothes() {
        String name = "Invalid Product";
        String description = "Invalid Description";
        String image = "invalid.jpg";
        double price = -10.0; // Negative price

        // Call the method under test
        Clothes actualProduct = clothesService.create(name, description, image, null, null, null, price, 0.0, 0, 0, 0, 0);

        // Assert that the result is null
        assertEquals(null, actualProduct);
    }

    // Requirement 3: Null name - The final product is null.
    @Test
    public void testCreateNullName() {
        String description = "Null Name Product";
        double price = 19.99;

        // Call the method under test
        Clothes actualProduct = clothesService.create(null, description, null, null, null, null, price, 0.0, 0, 0, 0, 0);

        // Assert that the result is null
        assertEquals(null, actualProduct);
    }

    // Requirement 4: Null description - The final product is not null.
    @Test
    public void testCreateNullDescription() {
        String name = "Product Name";
        double price = 29.99;

        // Mock repository behavior
        Clothes expectedProduct = new Clothes(name, null, null, null, null, null, price, 0.0, 0, 0, 0, 0);
        when(clothesRepository.save(expectedProduct)).thenReturn(expectedProduct);

        // Call the method under test
        Clothes actualProduct = clothesService.create(name, null, null, null, null, null, price, 0.0, 0, 0, 0, 0);

        // Assert the result
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }

    // Requirement 5: Valid input parameters with zero grade - The final product is not null.
    @Test
    public void testCreateValidClothesZeroGrade() {
        String name = "Product Name";
        String description = "Product Description";
        String image = "image-url-1";
        double price = 49.99;
        int grade = 0;
        int quantitySizeS = 10;
        int quantitySizeM = 20;
        int quantitySizeL = 15;
        int quantitySizeXL = 5;

        // Mock repository behavior
        Clothes expectedProduct = new Clothes(name, description, image, null, null, null, price, grade, quantitySizeS, quantitySizeM, quantitySizeL, quantitySizeXL);
        when(clothesRepository.save(expectedProduct)).thenReturn(expectedProduct);

        // Call the method under test
        Clothes actualProduct = clothesService.create(name, description, image, null, null, null, price, grade, quantitySizeS, quantitySizeM, quantitySizeL, quantitySizeXL);

        // Assert the result
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }
}
