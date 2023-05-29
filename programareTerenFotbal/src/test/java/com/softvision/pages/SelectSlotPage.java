package com.softvision.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelectSlotPage extends PageObject {

    private WebElement wholeResListWE = $("//div[@id='table-template-container_wrapper']");
    private WebElement bookBtnWE = $("//button[@type='submit']");
    private WebElement sportPopupWholeWE = $("//form[contains(@class, 'sc-submit-reservation')]");
    private WebElement selectSportDropDown = $("//select[contains(@class, 'form-select')]");
    private WebElement submitBtnWE = $("//button[contains(@class, 'btn-modal-confirmed-sport')]");
    public void selectSlot(String timeSlot){
        // check if timeslot is available
        String xpathSelector = String.format("//label[text()='%s']/ancestor::tr//child::label[@class='status']", timeSlot);
        WebElement timeSlotWE = wholeResListWE.findElement(By.xpath(xpathSelector));
        if(timeSlotWE.getText().equalsIgnoreCase("available")) {
            timeSlotWE.click();
            bookBtnWE.click();
        }
        else {
            Assert.fail(String.format("Timeslot %s is already ocupied by somebody else", timeSlot));
        }
    }

    public void selectSport(String sport) {
        Assert.assertTrue("The select sport popup should be displayed",sportPopupWholeWE.isDisplayed());
        selectSportDropDown.click();
        selectSportDropDown.findElement(By.xpath(String.format("//div[text()='%s'", sport))).click();
    }

    public void clickMakeReservation() {
        submitBtnWE.click();
    }
}
