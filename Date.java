
/**
 * MMN12 q.1 - class that present date
 * @author elay levi
 */

public class Date {
    private int _day, _month, _year;// the Parameters

    private final int TWO_DIGITS = 10, MAX_YEAR = 9999, DAY31 = 31, DAY30 = 30, DAY29 = 29, DAY1 = 1;
    private final int JAN = 1, FEB = 2, MARCH = 3, APRIL = 4, MAY = 5, JUN = 6, JUL = 7, AUG = 8, SEP = 9, OCT = 10, NOV = 11, DEC = 12;
    private final int MIN_YEAR = 1000;


    private boolean isDateRight(int day, int month, int year) {
        if (day < DAY1 || day > DAY31 || month < JAN || month > DEC || year < MIN_YEAR || year > MAX_YEAR)
            return false;
        else if (month == FEB && day == DAY29) {
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0)
                        return true; // divide in 4 and in 100 and in 400
                    return false; // divide in 4 and in 100 but not in 400
                }
                return true; // divide in 4 and not in 100
            }
            return false; // not divide in 4
        } else if (day == DAY30) {
            if (month == FEB)
                return false;
            else
                return true;
        } else if (day == DAY31) {
            if (month == JAN || month == MARCH || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC)
                return true;
            return false;
        }
        return true;

    }

    /**
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * Parameters:
     * day - the day in the month (1-31)
     * month - the month in the year (1-12)
     * year - the year (4 digits)
     *
     * @param day
     * @param month
     * @param year
     */
    public Date(int day, int month, int year) {
        boolean valid = isDateRight(day, month, year);//If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000

        if (valid) {
            _day = day;
            _month = month;
            _year = year;
        } else { // default values
            _day = 1;
            _month = 1;
            _year = 2000;
        }
    }

    /**
     * Copy constructor
     * Parameters:
     * other - the date to be copied
     */
    public Date(Date other) {
        if (other != null) { // check that the given object is initialized
            _day = other.getDay();
            _month = other.getMonth();
            _year = other.getYear();
        }
    }

    /**
     * Gets the day
     * Returns:
     * the day
     */
    public int getDay() {
        return _day;//return the day
    }

    /**
     * Gets the month
     * Returns:
     * the month
     */
    public int getMonth() {
        return _month;//return the month
    }

    /**
     * Gets the year
     * Returns:
     * the year
     */
    public int getYear() {
        return _year;//return the year
    }

    /**
     * set the day
     *
     * @param dayToSet the value to be set
     */
    public void setDay(int dayToSet) {
        if (isDateRight(dayToSet, _month, _year))
            _day = dayToSet;//Set the day (only if date remains valid)

    }

    /**
     * set the month
     *
     * @param monthToSet the value to be set
     */
    public void setMonth(int monthToSet) {
        if (isDateRight(_day, monthToSet, _year))
            _month = monthToSet;//Set the month (only if date remains valid)
    }

    /**
     * set the year
     *
     * @param yearToSet the value to be set
     */
    public void setYear(int yearToSet) {
        if (isDateRight(_day, _month, yearToSet))
            _year = yearToSet;//Sets the year (only if date remains valid)
    }

    /**
     * Check if 2 dates are the same
     * Parameters:
     * other - the date to compare this date to
     * Returns:
     * true if the dates are the same, otherwise false
     */
    public boolean equals(Date other) {
        if (other.getDay() == _day && other.getMonth() == _month && other.getYear() == _year)
            return true;//Check if 2 dates are the same and return true or false

        return false;
    }

    /**
     * Check if this date is before other date
     * Parameters:
     * other - date to compare this date to
     * Returns:
     * true if this date is before other date, otherwise false
     */
    public boolean before(Date other) {
        int a = calculateDate(_day, _month, _year);
        int b = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        if (a < b)// check if this date is before other date and return true or false
            return true;
        else
            return false;
    }

    /**
     * Check if this date is after other date
     * Parameters:
     * other - date to compare this date to
     * Returns:
     * true if this date is after other date, otherwise false
     */
    public boolean after(Date other) {
        return other.before(this);// check if this date is after other date and return true or false
    }

    /**
     * Calculates the difference in days between two dates
     * Parameters:
     * other - the date to calculate the difference between
     * Returns:
     * the number of days between the dates (non negative value)
     */
    public int difference(Date other) {
        int a = calculateDate(_day, _month, _year);
        int b = calculateDate(other.getDay(), other.getMonth(), other.getYear());

        return Math.abs(a - b);//Calculates the difference in days between two dates

    }

    /**
     * Returns a String that represents this date
     * Overrides:
     * toString in class java.lang.Object
     * Returns:
     * String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString() {//Returns a String that represents this date

        if (_day < TWO_DIGITS || _month < TWO_DIGITS) {
            if (_month >= TWO_DIGITS)
                return "0" + _day + "/" + _month + "/" + _year; // all 0 to the day if missing
            else if (_day >= TWO_DIGITS)
                return _day + "/0" + _month + "/" + _year; // all 0 to the month if missing
            return "0" + _day + "/0" + _month + "/" + _year; // all 0 to the year if missing
        }
        return _day + "/" + _month + "/" + _year; // else
    }

    /**
     * Calculate the date of tomorrow
     * Returns:
     * the date of tomorrow
     */
    public Date tomorrow() {//Calculate the date of tomorrow

        if (_day == DAY31 && _month == DEC && _year == MAX_YEAR)
            return new Date(01, 01, 2000);
        else if (isDateRight(_day + 1, _month, _year))
            return new Date(_day + 1, _month, _year); // if the date+1 is right return the date+1
        else {
            if (_month == 12)
                return new Date(01, 01, _year + 1); // if the date is the last one in the year return the first in the new year
            else
                return new Date(1, _month + 1, _year); // else return the first in the next month
        }
    }

    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62); // formula for calculation
    }
}



