describe('Register', () => {
  it('passes', () => {
    cy.visit('http://localhost:9090');
    cy.get('#MenuItems > li.nav-item > a').click();
    cy.get('#LoginForm > div > a').click();
    cy.url().should('include', '/register');
    cy.get('#RegForm > input:nth-child(1)').type('postman');
    cy.get('#email').type('postman@gmail.com');
    cy.get('#name').type('postman');
    cy.get('#RegForm > input:nth-child(4)').type('test123!');
    cy.get('#repeatedPassword').type('test123!');
    cy.get('#RegForm > button').click();
    cy.url().should('include', '/home');
    
  })
})