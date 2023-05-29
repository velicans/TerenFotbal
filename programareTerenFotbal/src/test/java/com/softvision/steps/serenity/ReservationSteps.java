package com.softvision.steps.serenity;

import com.softvision.pages.*;
import net.thucydides.core.annotations.Step;

import java.time.LocalDateTime;

public class ReservationSteps {
    LoginPage loginPage;
    SelectDatePage selectDatePage;
    SelectSlotPage selectSlotPage;
    ReservationsPage reservationsPage;
    @Step
    public void login(String username, String password) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.checkAgree();
        loginPage.submit();
    }

    @Step
    public void selectDate(LocalDateTime date) {
        selectDatePage.setMonth(date);
        selectDatePage.selectDay(date);
    }

    @Step
    public void selectSlot(String slot) {
        selectSlotPage.selectSlot(slot);
    }

    @Step
    public void selectSportAndMakeReservation(String sport) {
        selectSlotPage.selectSport(sport);
        selectSlotPage.clickMakeReservation();
    }

    @Step
    public void validateReservation(LocalDateTime date, String slot, String sport) {

        reservationsPage.validateReservation(date, slot, sport);
    }
}
