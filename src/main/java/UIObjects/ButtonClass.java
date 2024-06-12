package UIObjects;

import org.openqa.selenium.By;

public class ButtonClass {

    private static final String REGISTRATION_FORM_XPATH = "//*[@id=\"root\"]/div/main/div/form/fieldset";

    public By personalAccountButton = By.xpath("//nav/a/p");
    public By loader = By.cssSelector("div.Modal_modal_overlay__x2ZCr");
    public By registrationButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");
    public By confirmButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");

    public By userPasswordInput = By.xpath(REGISTRATION_FORM_XPATH + "[3]/div/div/input");
    public By userEmailInput = By.xpath(REGISTRATION_FORM_XPATH + "[2]/div/div/input");
    public By userNameInput = By.xpath(REGISTRATION_FORM_XPATH + "[1]/div/div/input");
    public By userEmailToEnter = By.xpath(REGISTRATION_FORM_XPATH + "[1]/div/div/input");
    public By userPasswordToEnter = By.xpath(REGISTRATION_FORM_XPATH + "[2]/div/div/input");
    public By makeOrderButton = By.xpath("//section[2]/div/button");
    public By makeBurger = By.xpath("//*[@id=\"root\"]/div/main/section[1]/h1");
    public By enterToAccountButton = By.xpath("//section[2]/div/button");
    public By enterButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    public By enterForgotPassword = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
    public By saveButton = By.xpath( "//div/form/button");
    public By profileSign = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[1]/a");
    public By logo = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");
    public By ban = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    public By sauce = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    public By filling = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");
    public By logOutButton = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button");

}
