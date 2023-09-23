package onlineshopapp.fashionstore.logiccoverage;

import onlineshopapp.fashionstore.model.Clothes;
import onlineshopapp.fashionstore.model.ClothesGrade;
import onlineshopapp.fashionstore.model.User;
import onlineshopapp.fashionstore.service.ClothesGradeService;
import onlineshopapp.fashionstore.service.ClothesService;
import onlineshopapp.fashionstore.service.UserService;
import onlineshopapp.fashionstore.web.controller.ProductsController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductsControllerTest {

    @InjectMocks
    private ProductsController productsController;

    @Mock
    private ClothesService clothesService;

    @Mock
    private ClothesGradeService clothesGradeService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Predicate: cg != null && (grade >= 0.0 && grade <= 5.0)
    // Clauses:
    // a - cg != null
    // b - grade >= 0.0
    // c - grade <= 5.0

    @Test
    public void testGradeProductCase1() {
        // Case 1 – T, T, T
        // cg != null, grade >= 0.0, grade <= 5.0

        Long id = 1L;
        double grade = 3.0;
        Model model = mock(Model.class);
        HttpServletRequest req = mock(HttpServletRequest.class);

        Clothes clothes = new Clothes();
        User user = new User();
        ClothesGrade cg = new ClothesGrade();
        when(req.getRemoteUser()).thenReturn("username");
        when(clothesService.findById(id)).thenReturn(clothes);
        when(userService.loadUserByUsername("username")).thenReturn(user);
        when(clothesGradeService.findByUserAndClothes(user, clothes)).thenReturn(cg);

        String result = productsController.gradeProduct(id, grade, model, req);

        verify(clothesGradeService).updateGrade(cg, grade);
        assertEquals("redirect:/products/" + id, result);
    }

    @Test
    public void testGradeProductCase5() {
        // Case 5 – F, T, T
        // cg = null, grade >= 0.0, grade <= 5.0

        Long id = 1L;
        double grade = 3.0;
        Model model = mock(Model.class);
        HttpServletRequest req = mock(HttpServletRequest.class);

        Clothes clothes = new Clothes();
        User user = new User();
        ClothesGrade cg = null;
        when(req.getRemoteUser()).thenReturn("username");
        when(clothesService.findById(id)).thenReturn(clothes);
        when(userService.loadUserByUsername("username")).thenReturn(user);
        when(clothesGradeService.findByUserAndClothes(user, clothes)).thenReturn(cg);

        String result = productsController.gradeProduct(id, grade, model, req);

        verify(clothesGradeService).addGrade(user, clothes, grade);
        verify(clothesService).updateFinalGrade(clothes, clothesGradeService.getGradesByClothes(clothes));
        assertEquals("redirect:/products/" + id, result);
    }

    @Test
    public void testGradeProductCase3() {
        // Case 3 – T, F, T
        // cg != null, grade < 0.0, grade <= 5.0

        Long id = 1L;
        double grade = -1.0;
        Model model = mock(Model.class);
        HttpServletRequest req = mock(HttpServletRequest.class);

        Clothes clothes = new Clothes();
        User user = new User();
        ClothesGrade cg = new ClothesGrade();
        when(req.getRemoteUser()).thenReturn("username");
        when(clothesService.findById(id)).thenReturn(clothes);
        when(userService.loadUserByUsername("username")).thenReturn(user);
        when(clothesGradeService.findByUserAndClothes(user, clothes)).thenReturn(cg);

        String result = productsController.gradeProduct(id, grade, model, req);

        verify(clothesGradeService, never()).updateGrade(cg, grade);
        verify(clothesGradeService, never()).addGrade(user, clothes, grade);
        verify(clothesService, never()).updateFinalGrade(clothes, clothesGradeService.getGradesByClothes(clothes));
        assertEquals("redirect:/products/" + id, result);
    }

    @Test
    public void testGradeProductCase2() {
        // Case 2 – T, T, F
        // cg != null, grade >= 0.0, grade > 5.0

        Long id = 1L;
        double grade = 6.0;
        Model model = mock(Model.class);
        HttpServletRequest req = mock(HttpServletRequest.class);

        Clothes clothes = new Clothes();
        User user = new User();
        ClothesGrade cg = new ClothesGrade();
        when(req.getRemoteUser()).thenReturn("username");
        when(clothesService.findById(id)).thenReturn(clothes);
        when(userService.loadUserByUsername("username")).thenReturn(user);
        when(clothesGradeService.findByUserAndClothes(user, clothes)).thenReturn(cg);

        String result = productsController.gradeProduct(id, grade, model, req);

        verify(clothesGradeService, never()).updateGrade(cg, grade);
        verify(clothesGradeService, never()).addGrade(user, clothes, grade);
        verify(clothesService, never()).updateFinalGrade(clothes, clothesGradeService.getGradesByClothes(clothes));
        assertEquals("redirect:/products/" + id, result);
    }

    @Test
    public void testGradeProductCase6() {
        // Case 6 – F, T, F
        // cg = null, grade >= 0.0, grade > 5.0

        Long id = 1L;
        double grade = 6.0;
        Model model = mock(Model.class);
        HttpServletRequest req = mock(HttpServletRequest.class);

        Clothes clothes = new Clothes();
        User user = new User();
        ClothesGrade cg = null;
        when(req.getRemoteUser()).thenReturn("username");
        when(clothesService.findById(id)).thenReturn(clothes);
        when(userService.loadUserByUsername("username")).thenReturn(user);
        when(clothesGradeService.findByUserAndClothes(user, clothes)).thenReturn(cg);

        String result = productsController.gradeProduct(id, grade, model, req);

        verify(clothesGradeService, never()).updateGrade(cg, grade);
        verify(clothesGradeService, never()).addGrade(user, clothes, grade);
        verify(clothesService, never()).updateFinalGrade(clothes, clothesGradeService.getGradesByClothes(clothes));
        assertEquals("redirect:/products/" + id, result);
    }

    @Test
    public void testGradeProductCase7() {
        // Case 7 – F, F, T
        // cg = null, grade < 0.0, grade <= 5.0

        Long id = 1L;
        double grade = -1.0;
        Model model = mock(Model.class);
        HttpServletRequest req = mock(HttpServletRequest.class);

        Clothes clothes = new Clothes();
        User user = new User();
        ClothesGrade cg = null;
        when(req.getRemoteUser()).thenReturn("username");
        when(clothesService.findById(id)).thenReturn(clothes);
        when(userService.loadUserByUsername("username")).thenReturn(user);
        when(clothesGradeService.findByUserAndClothes(user, clothes)).thenReturn(cg);

        String result = productsController.gradeProduct(id, grade, model, req);

        verify(clothesGradeService, never()).updateGrade(cg, grade);
        verify(clothesGradeService, never()).addGrade(user, clothes, grade);
        verify(clothesService, never()).updateFinalGrade(clothes, clothesGradeService.getGradesByClothes(clothes));
        assertEquals("redirect:/products/" + id, result);
    }

}

