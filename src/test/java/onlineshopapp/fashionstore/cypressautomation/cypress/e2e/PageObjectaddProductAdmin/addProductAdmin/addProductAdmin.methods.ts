import { AddProductAdminPageElements } from './addProductAdmin.elements';

export class AddProductAdminPageMethods {
    navigateToAdminLogin() {
        cy.visit('http://localhost:9090');
        cy.get('#MenuItems > li.nav-item > a').click();
        cy.get('#username').type('admin');
        cy.get('#LoginForm > input:nth-child(2)').type('admin');
        cy.get('#submit').click();
        cy.get('body > div.small-container > h3').should('have.text', 'We wish you pleasant shopping admin!');
    }

    clickAddProductButton() {
        AddProductAdminPageElements.getProductButton().click();
        cy.url().should('include', '/products/add');
    }

    fillProductForm() {
        AddProductAdminPageElements.getNameInput().type('dress test');
        AddProductAdminPageElements.getDescriptionInput().type('desc test');
        AddProductAdminPageElements.getImageInput().type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg');
        AddProductAdminPageElements.getImage1Input().type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg');
        AddProductAdminPageElements.getImage2Input().type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg');
        AddProductAdminPageElements.getImage3Input().type('https://assets.ajio.com/medias/sys_master/root/20230624/d2lM/64966b12a9b42d15c9ddcd02/-473Wx593H-465410816-burgundy-MODEL.jpg');
        AddProductAdminPageElements.getPriceInput().type('100');
        AddProductAdminPageElements.getGradeInput().type('9');
        AddProductAdminPageElements.getQuantitySizeSInput().type('3');
        AddProductAdminPageElements.getQuantitySizeMInput().type('4');
        AddProductAdminPageElements.getQuantitySizeLInput().type('5');
        AddProductAdminPageElements.getQuantitySizeXLInput().type('6');
    }

    submitProductForm() {
        cy.get('#submit').click();
        cy.url().should('include', '/products');
    }
}
