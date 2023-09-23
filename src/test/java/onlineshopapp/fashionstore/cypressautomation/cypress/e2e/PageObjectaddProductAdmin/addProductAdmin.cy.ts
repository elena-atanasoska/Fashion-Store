import { AddProductAdminPageMethods } from './addProductAdmin/addProductAdmin.methods';

describe('Add Product as an Admin', () => {
    const addProductAdmin = new AddProductAdminPageMethods();

    it('should add a product as an admin', () => {
        addProductAdmin.navigateToAdminLogin();
        addProductAdmin.clickAddProductButton();
        addProductAdmin.fillProductForm();
        addProductAdmin.submitProductForm();
    });
});
