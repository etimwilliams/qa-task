package com.deko.tests.RetailFinance;

import com.deko.testing.robot.backoffice.BackofficeRobot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BackofficeLoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void testTearDown(){
        driver.close();
    }

    @AfterSuite
    public void suiteTearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void backOfficeLoginPageLoadSuccess() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openBackofficeLoginPage()
                .verifyBackofficeUrl();

        Thread.sleep(2000);


        Assert.assertTrue(robot.verifyBackofficeUrl());
    }

    //todo: Write the rest of the tests for the backoffice login page here.
    //todo: Chrome driver should spin up, pass all tests identified (unless you find a bug?) and quit.

    @Test(priority = 2)
    public void backOfficeLoginPageCorrectLogin() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .fillLoginUsername("Etim.Williams")
                .fillLoginPassword("DekoQA2020")
                .CheckboxSelect()
                .submitLoginForm();
        Thread.sleep(2000);

        Assert.assertTrue(robot.verifySuccessfulLogin("Etim's Dashboard"));

    }


    @Test(priority = 3)
    public void backOfficeLoginPageLogout() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .openUserProfileMenu()
                .ClickLogoutBtn();
        Thread.sleep(4000);

        Assert.assertTrue(robot.verifyBackofficeUrl());

    }


    @Test(priority = 4)
    public void backOfficeLoginPageInCorrectUsernameCorrectPass() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .clearUsernameField()
                .fillLoginUsername("TestUser")
                .fillLoginPassword("DekoQA2020")
                .CheckboxSelect()
                .submitLoginForm();
        Thread.sleep(2000);

        Assert.assertTrue(robot.verifySignInError("Sorry, the details you provided were incorrect."));

    }
    @Test(priority = 5)
    public void backOfficeLoginPageCorrectUsernameInCorrectPass() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .clearUsernameField()
                .fillLoginUsername("Etim.Williams")
                .fillLoginPassword("DekoQA2019")
                .CheckboxSelect()
                .submitLoginForm();
        Thread.sleep(2000);

        Assert.assertTrue(robot.verifySignInError("Sorry, the details you provided were incorrect."));

    }

    @Test(priority = 6)
    public void backOfficeLoginPageInCorrectUsernameInCorrectPass() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .clearUsernameField()
                .fillLoginUsername("Testuser")
                .fillLoginPassword("DekoQA2019")
                .CheckboxSelect()
                .submitLoginForm();
        Thread.sleep(2000);

        Assert.assertTrue(robot.verifySignInError("Sorry, the details you provided were incorrect."));

    }

    @Test(priority = 7)
    public void backOfficeLoginPageResetPassword() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .clickForgottenPasswordLink()
                .fillResetPasswordUsername("Etim.Williams")
                .resetPassword();
        Thread.sleep(2000);


        Assert.assertTrue(robot.verifyResetPasswordSuccess("If the username exists, a password reset email has been sent to the connected email address. Please contact your account manager if you need further assistance."));

    }

    @Test(priority = 8)
    public void backOfficeLoginPageagain() throws InterruptedException {
        BackofficeRobot robot = new BackofficeRobot(driver);
        robot
                .clickResetSignInBtn();
        Thread.sleep(2000);

        Assert.assertTrue(robot.verifyBackofficeUrl());


    }

}
