import { LoginElements } from "./login.elements";

export class LoginMethods{
    navigateToPage(page: string){
        cy.visit(page);
    }
    login(username:string, password:string){
        LoginElements.LoginFormElement.getTxtUsername().type(username);
        LoginElements.LoginFormElement.getTxtPassword().type(password);
        LoginElements.LoginFormElement.getBtnLogin().click();
    }
    verifyUserSuccessfullyLogin(){
        LoginElements.LoginFormElement.getBtnMyOrders().should('exist');
    }
}