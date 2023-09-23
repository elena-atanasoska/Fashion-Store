export class RegisterPageElements {
    static get RegisterFormElement() {
      return {
        getUsernameInput: () => cy.get('#RegForm > input:nth-child(1)'),
        getEmailInput: () => cy.get('#email'),
        getNameInput: () => cy.get('#name'),
        getPasswordInput: () => cy.get('#RegForm > input:nth-child(4)'),
        getRepeatedPasswordInput: () => cy.get('#repeatedPassword'),
        getRegisterButton: () => cy.get('#RegForm > button'),
      };
    }
  
    static get MenuItemsElement() {
      return {
        getMenuLink: () => cy.get('#MenuItems > li.nav-item > a'),
        getRegisterLink: () => cy.get('#LoginForm > div > a'),
      };
    }
  }
  