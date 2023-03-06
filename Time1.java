 public class Time1 {
        private int _hour;
        private int _minute;
        private final int MAX_MINUTE = 60;
        private final int MAX_HOUR = 24;
        private final int MIN_MINUTE_HOUR = 0;
        private final int MAX_UNIT_MINUTE_HOUR = 9;
        private final int MAX_MINUTE_DAY = 1440;
        //constructors:
        /**
         * Constructs a Time1 object.
         * Construct a new time instance with the specified hour and minute .
         * hour should be between 0-23, otherwise it should be set to 0.
         * minute should be between 0-59, otherwise it should be set to 0.
         *
         * @param h the hour of the time
         * @param m the minute of the time
         */
        public Time1(int h, int m) {
            _hour = h;
            if (h >= MAX_HOUR || h < MIN_MINUTE_HOUR) {
                _hour = MIN_MINUTE_HOUR;
            }
            _minute = m;
            if (m > MAX_MINUTE || m < MIN_MINUTE_HOUR) {
                _minute = MIN_MINUTE_HOUR;
            }
        }
        /**
         * Copy constructor for Time1.
         * Construct a time with the same instance variables as another time.
         *
         * @param other The time object from which to construct the new time
         */
        public Time1(Time1 other) {
            this._hour = other._hour;
            this._minute = other._minute;
        }
        //Method Detail:
        /**
         * Returns the hour of the time.
         */
        public int getHour() {
            return _hour;
        }
        /**
         * Returns the minute of the time.
         */
        public int getMinute() {
            return _minute;
        }
        /**
         * Changes the hour of the time.
         * If an illegal number is received hour will be unchanged.
         *
         * @param num The new hour
         */
        public void setHour(int num) {
            if (num < MAX_HOUR && num >= MIN_MINUTE_HOUR) {
                _hour = num;
            } else {
                _hour += MIN_MINUTE_HOUR;
            }
        }
        /**
         * Changes the minute of the time.
         * If an illegal number is received minute will be unchanged.
         *
         * @param num The new minute
         */
        public void setMinute(int num) {
            if (num < MAX_MINUTE && num >= MIN_MINUTE_HOUR) {
                _minute = num;
            } else {
                _minute = _minute;
            }
        }
        /**
         * Return a string representation of this time (hh:mm).
         * Overrides:
         * toString in class java.lang.Object
         * Returns:
         * String representation of this time (hh:mm).
         */
        public String toString() {
            if (_hour <= MAX_UNIT_MINUTE_HOUR && _minute <= MAX_UNIT_MINUTE_HOUR) {
                return ("0" + _hour + ":" + "0" + _minute);
            }

            if (_hour > MAX_UNIT_MINUTE_HOUR && _minute <= MAX_UNIT_MINUTE_HOUR) {
                return (_hour + ":" + "0" + _minute);
            }

            if (_hour <= MAX_UNIT_MINUTE_HOUR && _minute > MAX_UNIT_MINUTE_HOUR) {
                return ("0" + _hour + ":" + _minute);
            } else {
                return (_hour + ":" + _minute);
            }
        }
        /**
         * Return the amount of minutes since midnight.
         */
        public int minFromMidnight() {
            return (_hour * MAX_MINUTE + _minute);
        }
        /**
         * Check if the received time is equal to this time.
         * Returns:
         * True if the received time is equal to this time
         *
         * @param other The time to be compared with this time
         */
        public boolean equals(Time1 other) {
            if (_hour == other._hour && _minute == other._minute) {
                return true;
            } else return false;
        }
        /**
         * Check if this time is before a received time.
         * Returns:
         * True if this time is before other time
         *
         * @param other The time to check if this point is before
         */
        public boolean before(Time1 other) {
            if (_hour < other._hour) {
                return true;
            }

            if (_hour == other._hour && _minute < other._minute) {
                return true;
            } else return false;
        }
        /**
         * Check if this time is after a received time.
         * Returns:
         * True if this time is after other time
         *
         * @param other The time to check if this point is after
         */
        public boolean after(Time1 other) {
            return other.before(this);
        }
        /**
         * Calculates the difference (in minutes) between two times.
         * Assumption: this time is after other time.
         * Returns:
         * int difference in minutes
         *
         * @param other The time to check the difference to
         */
        public int difference(Time1 other) {
            return (minFromMidnight() - other.minFromMidnight());
        }
        /**
         * Copy current object and add requested minutes to new object.
         * Returns:
         * new update Time1 object.
         *
         * @param num The minutes need to add.
         */
        public Time1 addMinutes(int num) {
            int newTime = this.minFromMidnight() + num % MAX_MINUTE_DAY;

            if (newTime >= MAX_MINUTE_DAY) {
                newTime = newTime % MAX_MINUTE_DAY;
            }
            if (newTime < MIN_MINUTE_HOUR) {
                newTime = MAX_MINUTE_DAY - Math.abs(newTime % MAX_MINUTE_DAY);
            }
            return (new Time1(newTime / MAX_MINUTE, newTime % MAX_MINUTE));
        }
    }

