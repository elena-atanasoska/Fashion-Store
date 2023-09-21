export class LoginElements {
    static get LoginFormElement() {
        return {
            getTxtUsername: () => cy.get('#username'),
            getTxtPassword: () => cy.get('#LoginForm > input:nth-child(2)'),
            getBtnLogin: () => cy.get('#submit'),
            getBtnMyOrders: () => cy.get('#MenuItems > li:nth-child(3) > a')
        }
    }
}