export class AddItem {
    static get LoginFormElement(){
        return{
            getTxtUsername: () => cy.get('#username'),
            getTxtPassword: () => cy.get('#LoginForm > input:nth-child(2)'),
            getBtnLogin: () => cy.get('#submit'),
            getBtnMyOrders: () => cy.get('#MenuItems > li:nth-child(3) > a')
        }
    }

    static get AddItemElement(){
        return{
            getItem: (id: string) =>cy.get(id),
            getAddButton: () => cy.get('body > div.small-container.single-product > div > div:nth-child(2) > form:nth-child(4) > button'),
            getMessage: () => cy.get('body > div.small-container.cart-page > table > tbody > tr:nth-child(2) > td:nth-child(2)')
        }
    }

    static get DeleteItemElement(){
        return{
            getDeleteButton: () => cy.get('body > div.small-container.cart-page > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > form > button'),
            getMessage: () => cy.get('body > div.small-container.cart-page > p')
        }
    }

}