import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    private int day, month, year;

    // this constructor is just to create a date object with the day, month, and year we enter.
    public Main(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //  method is for checking if the date we entered is valid.
    //  checks if month is between 1 and 12, and day is valid for that month.
    public boolean isValidDate() {
        if (month < 1 || month > 12 || day < 1) return false; // months from 1-12 only
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // days in each month
        if (month == 2 && isLeapYear()) daysInMonth[1] = 29; // fixing for leap years
        return day <= daysInMonth[month - 1]; // check if the day is valid for the month
    }

    // tha method checks if the year is a leap year.
    private boolean isLeapYear() {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); // leap year calculation
    }

    //that method updates the date if the new date is valid.
    public void updateDate(int d, int m, int y) {
        Main newDate = new Main(d, m, y);
        if (newDate.isValidDate()) {
            this.day = d; // day gets updated
            this.month = m; // month gets updated
            this.year = y; // year gets updated
        } else {
            System.out.println("Invalid date! Try again.");
        }
    }

    //this method is used to get the day of the week for the current date.
    public String getDayOfWeek() {
        Calendar calendar = new GregorianCalendar(year, month - 1, day); // calendar object to check weekday
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1]; // return the name of the day
    }

    // this method calculates the difference in days between this date and another date.
    public int calculateDifference(Main other) {
        Calendar c1 = new GregorianCalendar(year, month - 1, day); // current date
        Calendar c2 = new GregorianCalendar(other.year, other.month - 1, other.day); // other date
        long diff = Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis()); // absolute difference in milliseconds
        return (int) (diff / (1000 * 60 * 60 * 24)); // converting milliseconds to days
    }

    // this method prints the date in a readable format, like "January 1, 2020".
    public void printDate() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.println(months[month - 1] + " " + day + ", " + year); // print the full date
    }

    // this method compares two dates to sort them in ascending order.
    public static int compareDates(Main date1, Main date2) {
        if (date1.year != date2.year) return Integer.compare(date1.year, date2.year); // compare year
        if (date1.month != date2.month) return Integer.compare(date1.month, date2.month); // compare month
        return Integer.compare(date1.day, date2.day); // compare day
    }

    //  main method runs the program.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get the 1 date
        System.out.print("Enter day for the first date: ");
        int day1 = scanner.nextInt();
        System.out.print("Enter month for the first date: ");
        int month1 = scanner.nextInt();
        System.out.print("Enter year for the first date: ");
        int year1 = scanner.nextInt();

        // create 1st date object
        Main date1 = new Main(day1, month1, year1);
        System.out.println("Is the first date valid? " + date1.isValidDate()); // print if it's valid

        // get the 2 date
        System.out.print("Enter day for the second date: ");
        int day2 = scanner.nextInt();
        System.out.print("Enter month for the second date: ");
        int month2 = scanner.nextInt();
        System.out.print("Enter year for the second date: ");
        int year2 = scanner.nextInt();

        // create second date object
        Main date2 = new Main(day2, month2, year2);
        System.out.println("Is the second date valid? " + date2.isValidDate()); // print if it's valid

        //print the day of the week for the first date
        System.out.println("Day of the week for the first date: " + date1.getDayOfWeek());

        //calculate the difference in days between the two dates
        int diff = date1.calculateDifference(date2);
        System.out.println("Difference in days between the two dates: " + diff + " days");

        // create a list to store the dates
        ArrayList<Main> dateList = new ArrayList<>();
        dateList.add(date1);
        dateList.add(date2);

        // sort the dates in ascending order
        Collections.sort(dateList, Main::compareDates);

        // print sorted dates
        System.out.println("\nSorted dates:");
        for (Main date : dateList) {
            date.printDate(); // print each date in sorted order
        }

        // check for invalid date input (for exmpl, February 31st)
        System.out.print("Enter an invalid date (for exmpl, February 31) - Day: ");
        int invalidDay = scanner.nextInt();
        System.out.print("Enter Month: ");
        int invalidMonth = scanner.nextInt();
        System.out.print("Enter Year: ");
        int invalidYear = scanner.nextInt();

        Main invalidDate = new Main(invalidDay, invalidMonth, invalidYear);
        System.out.println("Is the invalid date valid? " + invalidDate.isValidDate()); // check if the invalid date is valid

        scanner.close();
    }
}
