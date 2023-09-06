describe('Login', () => {
  it('passes', () => {
    cy.visit('http://localhost:9090');
    cy.get('#MenuItems > li.nav-item > a').click()
    cy.get('#username').type('elena')
    cy.get('#LoginForm > input:nth-child(2)').type('elena')
    cy.get('#submit').click()
    cy.get('#MenuItems > li:nth-child(3) > a').should('exist');
  })
})