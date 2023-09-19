import { AddItemMethods } from "./addToCart/addToCart.methods"

describe('template spec', () => {
    const addtoCart = new AddItemMethods();

    it('User should be able to addd and delete item from cart with POM', () => {
        addtoCart.navigateToPage('http://localhost:9090/login');
        addtoCart.login("elena", "elena");
        addtoCart.verifyUserSuccessfullyLogin();
        addtoCart.addItem();
        addtoCart.deleteItem();
    });
});