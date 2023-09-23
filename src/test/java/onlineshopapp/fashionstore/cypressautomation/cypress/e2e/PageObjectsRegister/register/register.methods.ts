import { RegisterPageElements } from './register.elements';

export class RegisterPageMethods {
  static navigateToPage(page: string) {
    cy.visit(page);
  }

  static register(username: string, email: string, name: string, password: string) {
    RegisterPageElements.RegisterFormElement.getUsernameInput().type(username);
    RegisterPageElements.RegisterFormElement.getEmailInput().type(email);
    RegisterPageElements.RegisterFormElement.getNameInput().type(name);
    RegisterPageElements.RegisterFormElement.getPasswordInput().type(password);
    RegisterPageElements.RegisterFormElement.getRepeatedPasswordInput().type(password);
    RegisterPageElements.RegisterFormElement.getRegisterButton().click();
  }

  static clickMenuLink() {
    RegisterPageElements.MenuItemsElement.getMenuLink().click();
  }

  static clickRegisterLink() {
    RegisterPageElements.MenuItemsElement.getRegisterLink().click();
  }

  static assertRegisterUrl() {
    cy.url().should('include', '/register');
  }

  static assertHomeUrl() {
    cy.url().should('include', '/home');
  }
}
