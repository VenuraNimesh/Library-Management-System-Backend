package models;

public class DateTime  {
    private String fullDate;
    private int year;
    private int month;
    private int day;
    private int time;

    int jan = 1, feb = 2, mar = 3, apr = 4, may = 5, jun = 6, jul = 7, aug = 8, sep = 9, oct = 10, nov = 11, dec = 12;;
    int daysInFebruary = 28;

   /* if(isLeapYear(year)) {
        daysInFebruary = 29;
    }*/

    public DateTime() {
    }

    public DateTime( int year, int month, int day, int time) {
        this.fullDate =  year+ "/" + month +"/"+ day + ":" + time + ":00"+":00";
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    public String getFullDate() {
        return fullDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private int daysToHours (int days) {
        return days * 24;
    }


    public int convertToHours(DateTime dateTime){
        return (dateTime.year *12*30*24) + (dateTime.month*30) + (dateTime.day*24)+ (dateTime.time);
    }

    public DateTime checkDays(int count){

        if ((day + count > 31) && (month == jan || month == mar || month == may || month == jul || month == aug || month == oct)) {
            month++;
            day = day + count - 31;
        } else if ((day + count > 30) && (month == apr || month == jun || month == sep || month == nov)) {
            month++;
            day = day + count - 30;
        } else if ((day + count > 31) && month == dec) {
            year++;
            month = 1;
            day = day + count - 31;
        } else if ((day + count > daysInFebruary) && month == feb) {
            month++;
            day = day + count - daysInFebruary;
        } else {
            day = day + count;
        }

        return new DateTime(year,month,day,time);
    }

    public boolean isLeapYear(int Year) {
        boolean leap;

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            } else {
                leap = true;
            }
        } else {
            leap = false;
        }

        return leap;
    }
}

