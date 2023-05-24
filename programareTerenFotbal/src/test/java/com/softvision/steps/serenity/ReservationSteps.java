package com.softvision.steps.serenity;

import com.softvision.pages.*;
import net.thucydides.core.annotations.Step;

import java.time.LocalDateTime;

public class ReservationSteps {
    LoginPage loginPage;
    @Step
    public void login(String username, String password) {

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.checkAgree();
        loginPage.submit();
    }

    @Step
    public void selectDate(LocalDateTime date) {

        SelectDatePage selectDatePage = new SelectDatePage();
        selectDatePage.setMonth(date);
        selectDatePage.selectDay(date);

    }

    @Step
    public void selectSlot(String slot) {

        SelectSlotPage selectSlotPage = new SelectSlotPage();
        selectSlotPage.selectSlot(slot);
    }

    @Step
    public void selectSportAndMakeReservation(String sport) {

        SelectSportPage selectSportPage = new SelectSportPage();
        selectSportPage.selectSport(sport);
        selectSportPage.clickMakeReservation();
    }

    @Step
    public void validateReservation(LocalDateTime date, String slot, String sport) {

        ReservationsPage reservationsPage = new ReservationsPage();
        reservationsPage.validateReservation();
    }
}
