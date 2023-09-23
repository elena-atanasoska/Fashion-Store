import { RegisterPageMethods } from "./register/register.methods";

describe('Registration Spec', () => {
    const register = new RegisterPageMethods();

    it('User should be able to register with POM', () => {
        RegisterPageMethods.navigateToPage('http://localhost:9090');
        RegisterPageMethods.clickMenuLink();
        RegisterPageMethods.clickRegisterLink();
        RegisterPageMethods.assertRegisterUrl();
        RegisterPageMethods.register("postman", "postman@gmail.com", "postman", "test123!");
        RegisterPageMethods.assertHomeUrl();
    });
});
