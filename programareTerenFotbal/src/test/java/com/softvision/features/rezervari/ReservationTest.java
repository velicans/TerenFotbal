package com.softvision.features.rezervari;

import com.softvision.steps.serenity.ReservationSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;

@RunWith(SerenityRunner.class)
public class ReservationTest {

    @Managed
    WebDriver driver;

    @Steps
    public ReservationSteps reservationSteps;

    @Test
    public void createReservation(){

        driver.get("https://rezervari.tnsquare.ro/calendar");

        String username = "";
        String password = "";
        reservationSteps.login(username, password);

        LocalDateTime date = getLastThursday();
        reservationSteps.selectDate(date);

        String slot = null;
        reservationSteps.selectSlot(slot);

        String sport = "Fotbal";
        reservationSteps.selectSportAndMakeReservation(sport);

        reservationSteps.validateReservation(date, slot, sport);

    }

    private LocalDateTime getLastThursday() {

        LocalDateTime date = LocalDateTime.now();

        return date;
    }
}
