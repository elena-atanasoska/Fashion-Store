import { ChangeStatusPageElements } from './changeStatus.elements';

export class ChangeStatusPageMethods {
  navigateToPostmanLogin() {
    cy.visit('http://localhost:9090');
    cy.get('#MenuItems > li.nav-item > a').click();
    cy.get('#username').type('postman');
    cy.get('#LoginForm > input:nth-child(2)').type('test123!');
    cy.get('#submit').click();
    cy.get('body > div.small-container > h3').should('have.text', 'We wish you pleasant shopping postman!');
  }

  changeOrderStatus(newStatus: string) {
    // Click the menu item to go to the orders page
    cy.get('#MenuItems > li:nth-child(4)').click();

    // Change the order status
    ChangeStatusPageElements.getOrderStatusDropdown()
      .should('exist')
      .select(newStatus);

    // Click the update status button
    ChangeStatusPageElements.getUpdateStatusButton().click();
  }

  verifyOrderStatus(expectedStatus: string) {
    // Verify the updated order status
    ChangeStatusPageElements.getOrderStatusText().should('have.text', expectedStatus);
  }
}
