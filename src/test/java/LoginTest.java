import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.ResponseUser;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest{
    private User user;
    String accessToken;
    @Before
    public void setupUser(){
        user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" + "yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        ResponseUser responseUser = steps
                .createUser(user)
                .extract().as (ResponseUser.class);
        accessToken = responseUser.getAccessToken();

    }
    //пользователь может зайти в аккаунт по кнопке "Войти в аккаунт" на главной странице
    @Test
    @DisplayName("checking account login")
    @Description("the user can log in to the account by clicking the Log in to account button on the main page")
    public void loginToAccountButtonTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.inputLoginInfoUser(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }
    //пользователь может зайти в аккаунт по кноке "Личный кабинет" на главной странице
    @Test
    @DisplayName("checking account login")
    @Description("the user can log into the account using the Personal Account button on the main page")
    public void loginPersonalAccountButtonTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.inputLoginInfoUser(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }
    //пользователь может зайти в аккаунт по кноке "Войти" из формы регистрации
    @Test
    @DisplayName("checking account login")
    @Description("the user can log into the account using the Log in button from the registration form")
    public void loginFromRegistrationButtonTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickRegisterButton();

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.clickLoginButton();

        loginPage.inputLoginInfoUser(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }
    //пользователь может зайти в аккаунт по кноке "Войти" из формы восстановления пароля
    @Test
    @DisplayName("checking account login")
    @Description("the user can log into the account using the Log in button from the password recovery form")
    public void loginFromRecoverPasswordTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickRecoverPasswordButton();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(webDriver);
        forgotPasswordPage.clickLoginButton();

        loginPage.inputLoginInfoUser(user.getEmail(), user.getPassword());

        assertTrue(homePage.createOrderButtonIsEnabled());
    }

    @After
    public void deleteUser(){
        steps
                .deleteUser(accessToken)
                .statusCode(202);
    }
}
