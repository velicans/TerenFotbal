package com.softvision.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SelectSlotPage extends PageObject {

    private String reservationRowSelector = "//tr[@attr-slot='undefined']";
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
    public void selectSlot(String expectedTimeslotStart){
        boolean flagFound = false;
        List<WebElementFacade> reservationRowListWE = $$(reservationRowSelector);
        for(WebElementFacade reservationRow:reservationRowListWE) {
            List<WebElement> elementsInRow = reservationRow.findElements(By.cssSelector("td"));
            if(elementsInRow.get(0).getText().equalsIgnoreCase(expectedTimeslotStart)) {
                flagFound = true;
                // validate that the reservation is not taken by somebody else
                if(elementsInRow.get(2).getText().equalsIgnoreCase("available")) {
                    elementsInRow.get(2).click();
                    $(bookBtnSelector).click();
                }
                else {
                    Assert.fail(String.format("The timeslot that starts ar time %s is taken by somebody else", expectedTimeslotStart));
                }
            }
        }
        Assert.assertTrue(String.format("Iterated through all timeslots and did not find any slot that starts with time %s", expectedTimeslotStart), flagFound);
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
