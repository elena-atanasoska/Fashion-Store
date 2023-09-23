package onlineshopapp.fashionstore.graphcoverage;

import onlineshopapp.fashionstore.model.OrderedClothes;
import onlineshopapp.fashionstore.model.ShoppingCart;
import onlineshopapp.fashionstore.service.ShoppingCartService;
import onlineshopapp.fashionstore.web.controller.ShoppingCartController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetShoppingCartPagePath1() {
        // Path [1,3,5,9] – error==null || error.isEmpty(), listAllProducts.isEmpty, return cart

        String error = null;
        HttpServletRequest req = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        ShoppingCart emptyShoppingCart = new ShoppingCart();
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart("username")).thenReturn(emptyShoppingCart);
        when(shoppingCartService.listAllProductsInShoppingCart(anyLong())).thenReturn(new ArrayList<>());

        String result = shoppingCartController.getShoppingCartPage(error, req, model);

        verify(model).addAttribute("empty", null);
        assertEquals("cart", result);
    }

    @Test
    public void testGetShoppingCartPagePath2() {
        // Path [1,2,3,5,9] – error!=null && !error.isEmpty(), listAllProducts.isEmpty, return cart

        String error = "someError";
        HttpServletRequest req = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        ShoppingCart emptyShoppingCart = new ShoppingCart();
        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart("username")).thenReturn(emptyShoppingCart);
        when(shoppingCartService.listAllProductsInShoppingCart(anyLong())).thenReturn(new ArrayList<>());

        String result = shoppingCartController.getShoppingCartPage(error, req, model);

        verify(model).addAttribute("hasError", true);
        verify(model).addAttribute("error", error);
        verify(model).addAttribute("empty", null);
        assertEquals("cart", result);
    }


    @Test
    public void testGetShoppingCartPagePath3() {
        // Path [1,3,4,6,7,6,8,9] – error==null || error.isEmpty(), !listAllProducts.isEmpty, hasNext, !hasNext, return cart

        String error = null;
        HttpServletRequest req = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        ShoppingCart nonEmptyCart = new ShoppingCart();
        OrderedClothes c = mock(OrderedClothes.class);
        when(c.getPrice()).thenReturn(10.0);

        List<OrderedClothes> orderedClothesList = new ArrayList<>();
        orderedClothesList.add(c);

        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart("username")).thenReturn(nonEmptyCart);
        when(shoppingCartService.listAllProductsInShoppingCart(nonEmptyCart.getId())).thenReturn(orderedClothesList);

        String result = shoppingCartController.getShoppingCartPage(error, req, model);

        verify(c, times(1)).getPrice();
        verify(model).addAttribute("products", orderedClothesList);
        verify(model).addAttribute("bodyContents", "shoppingCart");
        verify(model).addAttribute("total", 10.0);
        verify(model, never()).addAttribute("empty", null);
        assertEquals("cart", result);
    }

    @Test
    public void testGetShoppingCartPagePath4() {
        // Path [1,3,4,6,7,6,7,6,8,9] - error==null || error.isEmpty(), !listAllProducts.isEmpty, hasNext, hasNext, !hasNext, return cart

        String error = null;
        HttpServletRequest req = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        ShoppingCart nonEmptyCart = new ShoppingCart();
        OrderedClothes c1 = mock(OrderedClothes.class);
        OrderedClothes c2 = mock(OrderedClothes.class);

        when(c1.getPrice()).thenReturn(10.0);
        when(c2.getPrice()).thenReturn(15.0);

        List<OrderedClothes> orderedClothesList = new ArrayList<>();
        orderedClothesList.add(c1);
        orderedClothesList.add(c2);

        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart("username")).thenReturn(nonEmptyCart);
        when(shoppingCartService.listAllProductsInShoppingCart(nonEmptyCart.getId())).thenReturn(orderedClothesList);

        String result = shoppingCartController.getShoppingCartPage(error, req, model);

        verify(c1, times(1)).getPrice();
        verify(c2, times(1)).getPrice();
        verify(model).addAttribute("products", orderedClothesList);
        verify(model).addAttribute("bodyContents", "shoppingCart");
        verify(model).addAttribute("total", 25.0);
        verify(model, never()).addAttribute("empty", null);
        assertEquals("cart", result);
    }

    @Test
    public void testGetShoppingCartPagePath5() {
        // Path [1,2,3,4,6,7,6,8,9] – error!=null && !error.isEmpty(), !listAllProducts.isEmpty, hasNext, !hasNext, return cart

        String error = "someError";
        HttpServletRequest req = mock(HttpServletRequest.class);
        Model model = mock(Model.class);

        ShoppingCart nonEmptyCart = new ShoppingCart();
        OrderedClothes c = mock(OrderedClothes.class);
        when(c.getPrice()).thenReturn(10.0);

        List<OrderedClothes> orderedClothesList = new ArrayList<>();
        orderedClothesList.add(c);

        when(req.getRemoteUser()).thenReturn("username");
        when(shoppingCartService.getActiveShoppingCart("username")).thenReturn(nonEmptyCart);
        when(shoppingCartService.listAllProductsInShoppingCart(nonEmptyCart.getId())).thenReturn(orderedClothesList);

        String result = shoppingCartController.getShoppingCartPage(error, req, model);

        verify(c, times(1)).getPrice();
        verify(model).addAttribute("products", orderedClothesList);
        verify(model).addAttribute("bodyContents", "shoppingCart");
        verify(model).addAttribute("total", 10.0);
        verify(model, never()).addAttribute("empty", null);
        verify(model).addAttribute("hasError", true);
        verify(model).addAttribute("error", error);
        assertEquals("cart", result);
    }

}