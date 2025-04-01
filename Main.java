import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

class Main {
    int day, month, year;

    Main(int day, int month, int year) {
        //constructor;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    //the date is valid?; checking
    boolean isValidDate() {
        if (month < 1 || month > 12 || day < 1) return false;
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeapYear()) daysInMonth[1] = 29;
        return day <= daysInMonth[month - 1];
    }
    //is it a leap year or not(29.02);
    private boolean isLeapYear() {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
    //updating date if its valid;
    void updateDate(int d, int m, int y) {
        Main newDate = new Main(d, m, y);
        if (newDate.isValidDate()) {
            this.day = d;
            this.month = m;
            this.year = y;
        } else {
            System.out.println("Invalid date!");
        }
    }
    //finding the day of the week;
    String getDayOfWeek() {
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }
    //find the difference between 2 given dates :)
    int calculateDifference(Main other) {
        Calendar c1 = new GregorianCalendar(year, month - 1, day);
        Calendar c2 = new GregorianCalendar(other.year, other.month - 1, other.day);
        long diff = Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis());
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
    //orint the date ti read it easier
    void printDate() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month - 1] + " " + day + ", " + year);
    }
    //comparing 2 dates we have ;
    static int compareDates(Main date1, Main date2) {
        if (date1.year != date2.year) return Integer.compare(date1.year, date2.year);
        if (date1.month != date2.month) return Integer.compare(date1.month, date2.month);
        return Integer.compare(date1.day, date2.day);
    }
    //using user input;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1st date:
        System.out.print("Enter day for the first date: ");
        int day1 = scanner.nextInt();
        System.out.print("Enter month for the first date: ");
        int month1 = scanner.nextInt();
        System.out.print("Enter year for the first date: ");
        int year1 = scanner.nextInt();

        Main date1 = new Main(day1, month1, year1);
        System.out.println("Is the first date valid? " + date1.isValidDate());
        // 2nd date:
        System.out.print("Enter day for the second date: ");
        int day2 = scanner.nextInt();
        System.out.print("Enter month for the second date: ");
        int month2 = scanner.nextInt();
        System.out.print("Enter year for the second date: ");
        int year2 = scanner.nextInt();

        Main date2 = new Main(day2, month2, year2);
        System.out.println("Is the second date valid? " + date2.isValidDate());

        System.out.println("Day of the week for the first date: " + date1.getDayOfWeek());
        //output difference in days between them;
        int diff = date1.calculateDifference(date2);
        System.out.println("Difference in days between the two dates: " + diff + " days");

        ArrayList<Main> dateList = new ArrayList<>();
        dateList.add(date1);
        dateList.add(date2);

        Collections.sort(dateList, Main::compareDates);

        System.out.println("\nSorted dates:");
        for (Main date : dateList) {
            date.printDate();
        }
        // invalid dates like 31.02 , 32.07 ... to check how program will find the validation;
        System.out.print("Enter an invalid date (for expml, February 31) - Day: ");
        int invalidDay = scanner.nextInt();
        System.out.print("Enter Month: ");
        int invalidMonth = scanner.nextInt();
        System.out.print("Enter Year: ");
        int invalidYear = scanner.nextInt();
        // is invalid date - valid?
        Main invalidDate = new Main(invalidDay, invalidMonth, invalidYear);
        System.out.println("Is the invalid date valid? " + invalidDate.isValidDate());

        scanner.close();
    }
}
