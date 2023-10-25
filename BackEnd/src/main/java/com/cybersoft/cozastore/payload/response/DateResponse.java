package com.cybersoft.cozastore.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateResponse {
    private String year;
    private String month;
    private String date;

    public DateResponse() {
    }
    public DateResponse(Date date) {
        String getDate = date.toString();
        getDate = getDate.substring(0,10);
        String[] arrDate = getDate.split("-");
        Map<String, String> allMonth = new HashMap<>();
        allMonth.put("01","Jan");
        allMonth.put("02","Feb");
        allMonth.put("03","Mar");
        allMonth.put("04","Apr");
        allMonth.put("05","May");
        allMonth.put("06","Jun");
        allMonth.put("07","Jul");
        allMonth.put("08","Aug");
        allMonth.put("09","Sep");
        allMonth.put("10","Oct");
        allMonth.put("11","Nov");
        allMonth.put("12","Dec");

        this.year = arrDate[0];
        this.month = allMonth.get(arrDate[1]);
        this.date = arrDate[2];
    }

    public DateResponse(String year, String month, String date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
