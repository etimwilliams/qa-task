package com.deko.testing.robot.backoffice;

import com.deko.testing.robot.BaseRobot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class BackofficeRobot extends BaseRobot {

    public BackofficeRobot(WebDriver driver) { super(driver);}

    @FindBy(name = "LoginForm")
    private WebElement loginForm;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/form/div[2]/input")
    private WebElement usernameField;

    @FindBy(xpath = "/html/body/div/div/div/div/div/form/div[3]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class = \"btn btn-link forgotten-link\"]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//*[@id=\"ng-app\"]/div[1]/div/div/div/div/form/div[6]/button")
    private WebElement signInButton;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/form/div[1]/div/div/p")
    private WebElement signInError;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath ="//input[@placeholder='Username']")
    private WebElement resetPasswordField;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement resetButton;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath = "/html/body/div/div/div/div/form/div[2]/div/button")
    private WebElement resetSignInButton;

    //todo: locate this webelement which will need to be used in later methods and tests.
    @FindBy(xpath = "//p[@class='ng-binding ng-scope']")
    private WebElement resetSuccessText;

    //this element is created to toggle remember checkbox
    @FindBy(xpath = "//input[@id='remember']")
    private WebElement RememberMeCheckBox;

    @FindBy(xpath = "/html/body/div[3]/div/div[4]/div[1]/div/div[2]/div[1]/h4")
    private WebElement DashboardText;

    @FindBy(xpath = "/html/body/div[3]/div/div[3]/div[1]/div[1]/div/div/a")
    private WebElement userProfileElementLoggedIn;

    @FindBy(xpath = "/html/body/div[3]/div/div[3]/div[1]/div[1]/div/div/ul/li/a[4]")
    private WebElement logoutBtn;

    @FindBy(id = "top-bar")
    private WebElement backOfficeDashboardTopBar;



    private final String baseUrl = "https://release.dekopay.org/backoffice/v2/#/"; //insert provided test url here

    public BackofficeRobot openBackofficeLoginPage(){
        goTo(baseUrl);
        wait.until(ExpectedConditions.visibilityOf(this.loginForm));
        return this;
    }

    public BackofficeRobot fillLoginUsername(String username){
        type(usernameField, username);
        return this;
    }

    public BackofficeRobot fillResetPasswordUsername(String username){
        type(resetPasswordField, username);
        return this;
    }

    public BackofficeRobot fillLoginPassword(String password){

        type(passwordField, password);
        return this;
    }

    public BackofficeRobot CheckboxSelect(){

        click(RememberMeCheckBox);
        return this;

    }

    public BackofficeRobot submitLoginForm() {
        click(signInButton);

        return this;
    }

    public BackofficeRobot openUserProfileMenu(){

        click(userProfileElementLoggedIn);
        return this;
    }

    public BackofficeRobot ClickLogoutBtn(){

        click(logoutBtn);
        return this;
    }

    public BackofficeRobot clearUsernameField(){

        clear(usernameField);
        return this;
    }

    public BackofficeRobot clickForgottenPasswordLink(){
        click(forgotPasswordLink);
        waitUntilURLContains("reset");
        return this;
    }

    public BackofficeRobot clickResetSignInBtn(){
        click(resetSignInButton);
        return this;
    }

    public BackofficeRobot resetPassword(){
        click(resetButton);
        return this;
    }

    public boolean verifySignInError(String text){
        if(WebElementContains(signInError,text)){
            return true;
        }
        return false;
    }

    public boolean verifyBackofficeUrl(){
        if (verifyURLContains("backoffice")){
            return true;
        }
        return false;
    }

    public boolean verifySuccessfulLogin(String text){
        //todo: Complete this verify method, to be used by test class
        if(WebElementContains(DashboardText,text)){
            return true;
        }

        return false;
    }

    public boolean verifyResetPasswordSuccess(String text){
        //todo: Complete this verify method, to be used by test class

        if(WebElementContains(resetSuccessText,text)){
            return true;
        }

        return false;
    }
}
