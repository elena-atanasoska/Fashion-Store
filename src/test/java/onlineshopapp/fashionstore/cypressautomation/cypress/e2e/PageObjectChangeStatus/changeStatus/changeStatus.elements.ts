export class ChangeStatusPageElements {
    static getOrderStatusDropdown() {
      return cy.get('body > div.small-container > div > div > table > tbody > tr > td:nth-child(11) > select');
    }
  
    static getUpdateStatusButton() {
      return cy.get('body > div.small-container > div > div > table > tbody > tr > td:nth-child(12) > button');
    }
  
    static getOrderStatusText() {
      return cy.get('body > div.small-container > div > div > table > tbody > tr > td:nth-child(7)');
    }
  }
  