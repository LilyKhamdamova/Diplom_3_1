package UIObjects;

import org.openqa.selenium.By;

public class ButtonClass {

    // Главная страница

    // Кнопка "Личный кабинет"
    public By personalAccountButton = By.xpath("//nav/a/p");

    // Лоадер
    public By loader = By.cssSelector("div.Modal_modal_overlay__x2ZCr");

    // Кнопка "Войти в аккаунт"
    public By enterToAccountButton = By.xpath("//section[2]/div/button");

    // Логотип
    public By logo = By.xpath("//nav/div/a");


    // Страница входа

    // Кнопка "Зарегестрироваться"
    public By registrationButton = By.xpath("//div/p[1]/a");

    // Кнопка "Войти"
    public By confirmButton = By.xpath("//div/form/button");

    // Поле ввода почты
    public By userEmailToEnter = By.xpath("//fieldset[1]/div/div/input");

    // Поле ввода пароля
    public By userPasswordToEnter = By.xpath( "//fieldset[2]/div/div/input");

    // Кнопка "Войти"
    //public By saveButton = By.xpath( "//div/form/button");

    // Кнопка "Восстановить пароль"
    public By enterForgotPassword = By.xpath("//div/p[2]/a");


    // Страница регистрации

    // Поле ввода пароля
    public By userPasswordInput = By.xpath("//fieldset[3]/div/div/input");

    // Поле ввода почты
    public By userEmailInput = By.xpath("//fieldset[2]/div/div/input");

    // Поле ввода имени
    public By userNameInput = By.xpath("//fieldset[1]/div/div/input");

    // Кнопка "Войти"
    public By getRegistrationButton = By.xpath( "//div/form/button");


    // Страница конструктора залогиненного пользователя

    // Кнопка "Оформить заказ"
    public By makeOrderButton = By.xpath("//section[2]/div/button");

    // Надпись "Соберите бургер"
    public By makeBurgerSign = By.xpath("//main/section[1]/h1");

    // Кнопки "Ингредиенты"
    public By ingredients = By.cssSelector("a[class^='BurgerIngredient']");

    // "Булочка"
    public By ban = By.xpath("//section[1]/div[1]/div[1]");

    // "Соус"
    public By sauce = By.xpath("//section[1]/div[1]/div[2]");

    // "Начинка"
    public By filling = By.xpath("//section[1]/div[1]/div[3]");


    public By enterButton = By.xpath("//div/p/a");



    // Личный кабинет юзера

    // Надпись "Профиль"
    public By profileSign = By.xpath("//ul/li[1]/a");

    // Кнопка "Выход"
    public By logOutButton = By.xpath("//ul/li[3]/button");

}
