import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorTest extends BaseTest{
    //На главной странице работает переход к разделу Соусы
    @Test
    @DisplayName("On the main page, the transition to the Sauces section works")
    @Description("To go to the Sauces menu, click on the Sauces heading")
    public void transferOnSaucesTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickSaucesConstructorButton();
        Assert.assertEquals("Соусы", homePage.menuSectionIsTransfer());
    }
    //На главной странице работает переход к разделу Булки
    @Test
    @DisplayName("On the main page, the transition to the Buns section works")
    @Description("To go to the Buns menu, click on the Buns heading")
    public void transferOnBunsTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickSaucesConstructorButton();
        homePage.clickBunsConstructorButton();
        Assert.assertEquals("Булки", homePage.menuSectionIsTransfer());
    }
    //На главной странице работает переход к разделу Начинки
    @Test
    @DisplayName("On the main page, the transition to the Fillings section works")
    @Description("To go to the Fillings menu, click on the Fillings heading")
    public void transferOnFillingsTest () {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickFillingsConstructorButton();
        Assert.assertEquals("Начинки", homePage.menuSectionIsTransfer());
    }
}
