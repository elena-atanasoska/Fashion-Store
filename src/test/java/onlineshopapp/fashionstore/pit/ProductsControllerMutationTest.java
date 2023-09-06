package onlineshopapp.fashionstore.pit;

import onlineshopapp.fashionstore.web.controller.ProductsController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import onlineshopapp.fashionstore.model.*;
import onlineshopapp.fashionstore.service.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.List;

public class ProductsControllerMutationTest {

    @Mock
    private ClothesService clothesService;

    @Mock
    private UserService userService;

    @Mock
    private ClothesCommentService clothesCommentService;

    @Mock
    private ClothesGradeService clothesGradeService;

    @Mock
    private CommentUserLikeService commentUserLikeService;

    @InjectMocks
    private ProductsController productsController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
    }

    @Test
    public void testShowProductsMutation() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));
    }

    @Test
    public void testShowDetailsMutation() throws Exception {
        Long productId = 1L;
        Clothes clothes = new Clothes();
        when(clothesService.findById(productId)).thenReturn(clothes);

        mockMvc.perform(get("/products/" + productId))
                .andExpect(status().isOk())
                .andExpect(view().name("product-details"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("comments"))
                .andExpect(model().attributeExists("grade"))
                .andExpect(model().attributeExists("produkti"));
    }

    @Test
    public void testSearchProductsMutation() throws Exception {
        String nameSearch = "searchQuery";
        List<Clothes> searchResults = Collections.singletonList(new Clothes());
        when(clothesService.listProductsByName(nameSearch)).thenReturn(searchResults);

        mockMvc.perform(get("/products/searchProducts").param("nameSearch", nameSearch))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attributeExists("totalItems"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("currentPage"))
                .andExpect(model().attributeExists("totalPages"))
                .andExpect(model().attributeExists("sortField"))
                .andExpect(model().attributeExists("sortDir"));
    }

    @Test
    public void testAddCommentMutation() throws Exception {
        // Mocking user and clothes
        User user = new User();
        Clothes clothes = new Clothes();
        when(userService.loadUserByUsername(anyString())).thenReturn(user);
        when(clothesService.findById(anyLong())).thenReturn(clothes);

        // Perform the addComment request
        mockMvc.perform(post("/products/1/comment")
                        .param("comment", "Test comment"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products/1"));
    }

    @Test
    public void testGradeProductMutation() throws Exception {
        // Mocking user and clothes
        User user = new User();
        Clothes clothes = new Clothes();
        when(userService.loadUserByUsername(anyString())).thenReturn(user);
        when(clothesService.findById(anyLong())).thenReturn(clothes);

        // Perform the gradeProduct request
        mockMvc.perform(post("/products/1/grade")
                        .param("grade", "4.5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products/1"));
    }

    // More mutation tests for other methods can be added similarly
}
