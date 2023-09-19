describe('Login, add product to cart and delete it', () => {
    it('can add and remove a product from the cart', () => {
        //Visit the login page
        cy.visit('http://localhost:9090');
        cy.get('#MenuItems > li.nav-item > a').click()
        cy.get('#username').type('elena')
        cy.get('#LoginForm > input:nth-child(2)').type('elena')
        cy.get('#submit').click()
        cy.get('#MenuItems > li:nth-child(3) > a').should('exist');

        // Add product to cart
        cy.get('#2').click();
        cy.get('body > div.small-container.single-product > div > div:nth-child(2) > form:nth-child(4) > button').click();

        // Assert that the cart icon displays the correct number of items
        cy.get('body > div.small-container.cart-page > table > tbody > tr:nth-child(2) > td:nth-child(2)').should('have.text', '1')

        // Remove product from cart
        cy.get('body > div.small-container.cart-page > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > form > button').click();

        // Assert that the cart is now empty
        cy.get('body > div.small-container.cart-page > p').should('have.text', 'There are no items in the cart!');

    })
})