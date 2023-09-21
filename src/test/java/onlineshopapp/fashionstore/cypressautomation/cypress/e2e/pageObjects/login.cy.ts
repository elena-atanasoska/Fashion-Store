import { LoginMethods } from "./login/login.methods"

describe('template spec', () => {
    const loginM = new LoginMethods();

    it('User should be able to login with POM', () => {
        loginM.navigateToPage('http://localhost:9090/login');
        loginM.login("elena", "elena");
        loginM.verifyUserSuccessfullyLogin();
    });
});