package com.softvision.features.rezervari;

import com.softvision.steps.serenity.ReservationSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@RunWith(SerenityRunner.class)
public class ReservationTest {

    @Managed
    WebDriver driver;

    @Steps
    public ReservationSteps reservationSteps;

    @Test
    public void createReservation(){

        driver.get("https://rezervari.tnsquare.ro/calendar");

        String username = "administrativbucuresti@softvision.com";
        String password = "";
        reservationSteps.login(username, password);

        LocalDateTime date = getLastThursday(LocalDateTime.now());
        Assert.assertNotNull("Something wrong when trying to compute the last thursday", date);
        reservationSteps.selectDate(date);

        String slot = "20:00";
        String sport = "Football";
        reservationSteps.selectSlot(slot);


        reservationSteps.selectSportAndMakeReservation(sport);

        reservationSteps.validateReservation(date, slot, sport);
    }

    private LocalDateTime getLastThursday(LocalDateTime initDate) {
        //transform init date to calendar instance
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(initDate.atZone(ZoneId.systemDefault()).toInstant()));
        //increase month with 1
        cal.add(Calendar.MONTH, 1);
        LocalDateTime workDate = LocalDateTime.ofInstant(cal.getTime().toInstant(), ZoneId.systemDefault());
        while(initDate.isBefore(workDate)) {
            workDate = LocalDateTime.ofInstant(cal.getTime().toInstant(), ZoneId.systemDefault());
            if (workDate.getDayOfWeek().toString().equals("THURSDAY")) {
                return workDate;
            }
            cal.add(Calendar.DAY_OF_YEAR, -1);
        }
        return null;
    }
}
