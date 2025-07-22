/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pratham
 */
@Named(value = "datetime")
@SessionScoped
public class Datetime implements Serializable {

    private String fromdate;
    private String todate;
    private String currentDate;
    private String tomorrowDate;

    public Datetime() {

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Format dates for JSF
        this.currentDate = today.format(formatter);
        this.tomorrowDate = tomorrow.format(formatter);
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getTomorrowDate() {
        return tomorrowDate;
    }

    public void setTomorrowDate(String tomorrowDate) {
        this.tomorrowDate = tomorrowDate;
    }
}
