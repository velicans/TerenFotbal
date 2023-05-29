package com.softvision.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectSlotPage extends PageObject {

    private String wholeResListSelector = "//div[@id='table-template-container_wrapper']";
    private String bookBtnSelector = "//button[@type='submit']";
    private String sportPopupWholeSelector = "//form[contains(@class, 'sc-submit-reservation')]";
    private String selectSportDropDownSelector = "//div[@class='select-selected']";
    private String submitBtnSelector = "//button[contains(@class, 'btn-modal-confirmed-sport')]";
    private String footballSelector = "//div[text()='Football']";
    private String tennisSelector = "//div[text()='Tennis']";
    private String footTennisSelector = "//div[text()='Foot Tennis']";
    private String basketballSelector = "//div[text()='Basketball']";
    private String handballSelector = "//div[text()='Handball']";
    private String volleyballSelector = "//div[text()='Volleyball']";
    private String makeReservationSelector = "//button[text()='Make a reservation']";;
    public void selectSlot(String timeSlot){
        // check if timeslot is available
        String timeSlotXpathSelector = String.format("//label[text()='%s']/ancestor::tr//child::label[@class='status']", timeSlot);
        WebElement timeSlotWE = $(wholeResListSelector).findElement(By.xpath(timeSlotXpathSelector));
        if(timeSlotWE.getText().equalsIgnoreCase("available")) {
            timeSlotWE.click();
            $(bookBtnSelector).click();
        }
        else {
            Assert.fail(String.format("Timeslot %s is already occupied by somebody else", timeSlot));
        }
    }

    public void selectSport(String sport) {
        WebDriverWait wait=new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sportPopupWholeSelector)));
        Assert.assertTrue("The select sport popup should be displayed",$(sportPopupWholeSelector).isDisplayed());
        $(selectSportDropDownSelector).click();
        switch(sport){
            case "Football":
                $(footballSelector).click();
                break;
            case "Tennis":
                $(tennisSelector).click();
                break;
            case "Foot Tennis":
                $(footTennisSelector).click();
                break;
            case "Basketball":
                $(basketballSelector).click();
            case "Handball":
                $(handballSelector).click();
            case "Volleyball":
                $(volleyballSelector).click();
            default:
                Assert.fail("Only valid arguments are: Football, Tennis, Foot Tennis, Basketball, Handball, Volleyball");
        }

    }

    public void clickMakeReservation() {
        WebDriverWait wait=new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(makeReservationSelector)));
        $(makeReservationSelector).click();
    }
}
