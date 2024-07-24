import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.ResponseUser;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AccountTest extends BaseTest{
    String accessToken;

    @Before
    public void setupUser(){
      User  user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@" + "yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(6));
        user.setName(RandomStringUtils.randomAlphabetic(6));

        ResponseUser responseUser = steps
                .createUser(user)
                .extract().as (ResponseUser.class);
        accessToken = responseUser.getAccessToken();

        HomePage homePage = new HomePage(webDriver);
        homePage.clickLogInToAccountButton();

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.inputLoginInfoUser(user.getEmail(), user.getPassword());
    }
    //после входа пользователь может перейти в Личный кабинет
    @Test
    @DisplayName("transfer to your personal account")
    @Description("after logging in, the user can go to his Personal Account")
    public void transferToPersonalAccount () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        assertTrue(profilePage.exitButtonIsDisplayed());
    }
//из личного кабинета пользователь может перейти в конструктор по кнопке Конструктор
    @Test
    @DisplayName("transfer from your personal account to the constructor")
    @Description("from the personal account, the user can go to the constructor by clicking the Constructor button")
    public void clickOnTheButtonConstructorOn () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.clickConstructorButton();

        assertTrue(homePage.createOrderButtonIsEnabled());
    }
    //из личного кабинета пользователь может перейти в конструктор по клику на логотип
    @Test
    @DisplayName("transfer from your personal account to the constructor")
    @Description("from the personal account, the user can go to the constructor by clicking the Logotype")
    public void clickOnTheButtonLogotype () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.clickLogotypeButton();

        assertTrue(homePage.createOrderButtonIsEnabled());
    }
    // из личного кабинета можно выйти по кнопке Выход
    @Test
    @DisplayName("log out of your personal account")
    @Description("you can log out of your personal account using the Log Out button")
    public void clickOnExitButton () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickPersonalAccountButton();

        ProfilePage profilePage = new ProfilePage(webDriver);
        profilePage.clickExitButton();

        LoginPage loginPage = new LoginPage(webDriver);
        assertTrue(loginPage.loginInfoIsDisplayed());
    }
    @After
    public void deleteUser(){
        steps
                .deleteUser(accessToken)
                .statusCode(202);
    }
}
