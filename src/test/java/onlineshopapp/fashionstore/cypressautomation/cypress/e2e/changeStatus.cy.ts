describe('Change status as postman', () => {
    it('passes', () => {
            //Visit the login page
            cy.visit('http://localhost:9090');
            cy.get('#MenuItems > li.nav-item > a').click()
            cy.get('#username').type('postman')
            cy.get('#LoginForm > input:nth-child(2)').type('test123!')
            cy.get('#submit').click()
            cy.get('body > div.small-container > h3').should('have.text', 'We wish you pleasant shopping postman!');

            //change status in postman orders
            cy.get('#MenuItems > li:nth-child(4)').click()
             // Locate the dropdown by its ID
            cy.get('body > div.small-container > div > div > table > tbody > tr > td:nth-child(11) > select')
            .should('exist') // Ensure the dropdown exists
            .select('Confirmed'); // Select the desired option by its value
            cy.get('body > div.small-container > div > div > table > tbody > tr > td:nth-child(12) > button').click()
            cy.get('body > div.small-container > div > div > table > tbody > tr > td:nth-child(7)').should('have.text', 'Confirmed');
      
    })
  })