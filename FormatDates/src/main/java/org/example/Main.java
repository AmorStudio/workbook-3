package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formattingDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String fullDate = currentTime.format(formattingDate);

        DateTimeFormatter updatedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String newFormattedDate = currentTime.format(updatedFormat);

        DateTimeFormatter monthInWords = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String monInword = currentTime.format(monthInWords);

        DateTimeFormatter DayInWeek = DateTimeFormatter.ofPattern ("EEEE, MMM dd, yyyy hh:mm");
        String dayInWeekWithMoninWords = currentTime.format(DayInWeek);




        System.out.println(fullDate);
        System.out.println(newFormattedDate);
        System.out.println(monInword);
        System.out.println(dayInWeekWithMoninWords);


    }
}