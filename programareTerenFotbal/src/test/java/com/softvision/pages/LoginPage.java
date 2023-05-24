package com.softvision.pages;

import com.google.j2objc.annotations.Weak;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    public void setUsername(String username) {
       // WebElement usernameWE = getDriver().findElement(By.xpath("//input[@id='email']"));
//        usernameWE.sendKeys(username);
        $("//input[@id='email']").type(username);
    }

    public void setPassword(String password) {
    }

    public void checkAgree() {
    }

    public void submit() {

    }
}
