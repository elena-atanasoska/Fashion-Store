export class AddProductAdminPageElements {
    static getProductButton() {
        return cy.get('body > div.small-container > button > a');
    }

    static getNameInput() {
        return cy.get('#name');
    }

    static getDescriptionInput() {
        return cy.get('#description');
    }

    static getImageInput() {
        return cy.get('#image');
    }

    static getImage1Input() {
        return cy.get('#image1');
    }

    static getImage2Input() {
        return cy.get('#image2');
    }

    static getImage3Input() {
        return cy.get('#image3');
    }

    static getPriceInput() {
        return cy.get('#price');
    }

    static getGradeInput() {
        return cy.get('#grade');
    }

    static getQuantitySizeSInput() {
        return cy.get('#quantitySizeS');
    }

    static getQuantitySizeMInput() {
        return cy.get('#quantitySizeM');
    }

    static getQuantitySizeLInput() {
        return cy.get('#quantitySizeL');
    }

    static getQuantitySizeXLInput() {
        return cy.get('#quantitySizeXL');
    }

    static getSubmitButton() {
        return cy.get('#submit');
    }
}
