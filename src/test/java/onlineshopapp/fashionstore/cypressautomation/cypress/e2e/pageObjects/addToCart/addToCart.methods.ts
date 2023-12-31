import { AddItem } from "./addToCart.elements";

export class AddItemMethods{
    navigateToPage(page: string){
        cy.visit(page);
    }
    login(username:string, password:string){
        AddItem.LoginFormElement.getTxtUsername().type(username);
        AddItem.LoginFormElement.getTxtPassword().type(password);
        AddItem.LoginFormElement.getBtnLogin().click();
    }
    verifyUserSuccessfullyLogin(){
        AddItem.LoginFormElement.getBtnMyOrders().should('exist');
    }
    addItem(id: string){
        AddItem.AddItemElement.getItem(id).click();
        AddItem.AddItemElement.getAddButton().click();
    }
    confirm(){
        AddItem.AddItemElement.getMessage().should('have.text', '1');
    }
    deleteItem(){
       AddItem.DeleteItemElement.getDeleteButton().click();
       AddItem.DeleteItemElement.getMessage().should('have.text', 'There are no items in the cart!');
    }
}