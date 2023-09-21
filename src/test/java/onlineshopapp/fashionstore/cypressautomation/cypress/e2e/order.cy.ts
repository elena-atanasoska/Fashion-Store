import { LoginMethods } from "./pageObjects/login/login.methods"
import { AddItemMethods } from "./pageObjects/addToCart/addToCart.methods"

describe('Login', () => {
    const loginM = new LoginMethods();
    const addtoCart = new AddItemMethods();

    it('User should be able to login with POM', () => {
        loginM.navigateToPage('http://localhost:9090/login');
        loginM.login("elena", "elena");
        loginM.verifyUserSuccessfullyLogin();
        // cy.visit('http://localhost:9090/orders');
        addtoCart.addItem('#1');
        cy.get('body > div.small-container.cart-page > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > button').click();
        cy.get('body > div.small-container > form > div > div.col-4 > p:nth-child(1) > input[type=text]').type('Elena');
        cy.get('body > div.small-container > form > div > div.col-4 > p:nth-child(3) > input[type=text]').type("Atanasoska");
        cy.get('body > div.small-container > form > div > div.col-4 > p:nth-child(5) > input[type=text]').type('Bul. Partizanski Odredi 100/1');
        cy.get('#select').select('NewYork');
        cy.get('body > div.small-container > form > div > div.col-4 > p:nth-child(9) > input[type=text]').type('071234567');
        cy.get('body > div.small-container > form > div > div.col-4 > p:nth-child(11) > input[type=text]').type('1544444766375703');
        cy.get('body > div.small-container > form > div > div:nth-child(2) > p:nth-child(3) > input[type=text]').type('3');
        cy.get('body > div.small-container > form > div > div:nth-child(2) > button:nth-child(5)').click();
        cy.get('body > div.small-container > form > div > div:nth-child(2) > button:nth-child(10)').click();
        cy.url().should('include', '/orders');
    });  
})