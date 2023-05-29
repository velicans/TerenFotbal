package com.softvision.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationsPage extends PageObject {
    private String alertSuccessSelector = "//div[@class='alert alert-success']";
    private String reservationEntrySelector = "//h2[contains(@class, 'resevartion-row')]";
    public void validateReservation(LocalDateTime date, String timeslot, String sport) {
        boolean flagFound = false;
        WebDriverWait wait=new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOf($(alertSuccessSelector)));
        Assert.assertTrue("Something happened and the message Reservation was made successfully did not appear", $(alertSuccessSelector).getText().equalsIgnoreCase("Reservation was made successfully"));
        List<WebElementFacade> listOfReservationEntries = $$(reservationEntrySelector);
        for(WebElementFacade reservationEntryWE:listOfReservationEntries) {
            String currentDateString = reservationEntryWE.findElement(By.cssSelector("span[class=reservation-data]")).getText();
            String currSport = reservationEntryWE.findElement(By.cssSelector("span[class=sport-type]")).getText();
            String currTimeslot = reservationEntryWE.findElement(By.cssSelector("span[class=reservation-ora]")).getText();
            if(validateDate(date, currentDateString) && validateSport(sport, currSport) && validateTimeslot(timeslot, currTimeslot) ){
                flagFound = true;
                break;
            }
        }
        Assert.assertTrue(String.format("Iterated through all the entries in the reservation list and did not find any entry for date %s, sport %s and timeslot %s",date.toString(), sport, timeslot ), flagFound);
    }

    private boolean validateTimeslot(String timeslot, String currTimeslot) {
        //TODO  have to verify that the timeslot from reseration matches the expected timeslot
        return false;
    }

    private boolean validateSport(String sport, String currSport) {
        // TODO validate sport name is ok
        return false;
    }

    private boolean validateDate(LocalDateTime date, String currentDateString) {
        //TODO  have to verify that the timeslot  is "thursday DD MonthName
        return false;
    }
}
