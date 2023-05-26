package com.softvision.pages;

import com.google.j2objc.annotations.Weak;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    public void setUsername(String username) {
        $("input[id='email']").type(username);
    }

    public void setPassword(String password) {
        $("input[id='password']").type(password);
    }

    public void checkAgree() {
        $("input[id=terms_and_conditions]").click();
    }

    public void submit() {
        $("//button[text()='Login']").click();
    }
}
