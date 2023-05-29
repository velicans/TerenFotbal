package com.softvision.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;

public class SelectDatePage extends PageObject {

    private WebElement tableHeaderWE = $("//h2[@class=fc-toolbar-title]");
    private WebElement nextMonthBtnWE = $("//button[contains(@class, 'fc-next-button')]");
    public void setMonth(LocalDateTime reqDate) {
        while(reqDate.getMonthValue() > LocalDateTime.now().getMonthValue()) {
            nextMonthBtnWE.click();
        }
    }

    public void selectDay(LocalDateTime date) {
        int reqMonthNumber = date.getMonthValue();
        int reqDayNumber = date.getDayOfMonth();
        int reqYear = date.getYear();
        String programaticXpathSelector = String.format("//td[@data-date='%d-%02d-%02d']",reqYear, reqMonthNumber, reqDayNumber);
        $(programaticXpathSelector).click();
    }
}
