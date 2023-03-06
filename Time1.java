public class Time1 {
    private int _hour;
    private int _minute;
    private final int MIN_HOUR = 0;
    private final int MIN_MINUTE = 0;
    private final int MAX_HOUR = 23;
    private final int MAX_MINUTE = 59;
    private final int DEFAULT = 0;
    private final int MINS_PER_HOUR = 60;
    private final int MINS_PER_DAY = 1440;

    public Time1(int h, int m) {
        if (h >= 0 && h <= 23) {
            this._hour = h;
        } else {
            this._hour = 0;
        }

        if (m >= 0 && m <= 59) {
            this._minute = m;
        } else {
            this._minute = 0;
        }

    }

    public Time1(Time1 other) {
        this._hour = other._hour;
        this._minute = other._minute;
    }

    public int getHour() {
        return this._hour;
    }

    public int getMinute() {
        return this._minute;
    }

    public void setHour(int h) {
        if (h >= 0 && h <= 23) {
            this._hour = h;
        }

    }

    public void setMinute(int m) {
        if (m >= 0 && m <= 59) {
            this._minute = m;
        }

    }

    public String toString() {
        String s = "";
        if (this._hour < 10) {
            s = s + "0";
        }

        s = s + this._hour + ":";
        if (this._minute < 10) {
            s = s + "0";
        }

        s = s + this._minute;
        return s;
    }

    public int minFromMidnight() {
        return this._hour * 60 + this._minute;
    }

    public boolean equals(Time1 other) {
        return this._hour == other._hour && this._minute == other._minute;
    }

    public boolean before(Time1 other) {
        if (this._hour < other._hour) {
            return true;
        } else if (this._hour > other._hour) {
            return false;
        } else {
            return this._minute < other._minute;
        }
    }

    public boolean after(Time1 other) {
        return other.before(this);
    }

    public int difference(Time1 other) {
        return this.minFromMidnight() - other.minFromMidnight();
    }

    public Time1 addMinutes(int num) {
        int m = this.minFromMidnight();
        m += num;
        m %= 1440;
        if (m < 0) {
            m += 1440;
        }

        return new Time1(m / 60, m % 60);
    }
}
