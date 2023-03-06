public class Flight {
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;
    private final int MAX_CAPACITY = 250;

    public Flight(String origin, String dest, int depHour, int depMinute, int durTimeMinutes, int noOfPass, int price) {
        this._origin = origin;
        this._destination = dest;
        this._departure = new Time1(depHour, depMinute);
        this._noOfPassengers = noOfPass;
        if (noOfPass > 250) {
            this._noOfPassengers = 250;
        }

        if (noOfPass < 0) {
            this._noOfPassengers = 0;
        }

        if (this._noOfPassengers == 250) {
            this._isFull = true;
        } else {
            this._isFull = false;
        }

        if (durTimeMinutes < 0) {
            this._flightDuration = 0;
        } else {
            this._flightDuration = durTimeMinutes;
        }

        if (price < 0) {
            this._price = 0;
        } else {
            this._price = price;
        }

    }

    public Flight(Flight other) {
        this._origin = other._origin;
        this._destination = other._destination;
        this._departure = new Time1(other._departure);
        this._noOfPassengers = other._noOfPassengers;
        this._isFull = other._isFull;
        this._flightDuration = other._flightDuration;
        this._price = other._price;
    }

    public String getOrigin() {
        return this._origin;
    }

    public String getDestination() {
        return this._destination;
    }

    public Time1 getDeparture() {
        return new Time1(this._departure);
    }

    public int getFlightDuration() {
        return this._flightDuration;
    }

    public int getNoOfPassengers() {
        return this._noOfPassengers;
    }

    public boolean getIsFull() {
        return this._isFull;
    }

    public int getPrice() {
        return this._price;
    }

    public void setDestination(String dest) {
        this._destination = dest;
    }

    public void setOrigin(String origin) {
        this._origin = origin;
    }

    public void setDeparture(Time1 departureTime) {
        this._departure = new Time1(departureTime);
    }

    public void setFlightDuration(int durTimeMinutes) {
        if (durTimeMinutes >= 0) {
            this._flightDuration = durTimeMinutes;
        }

    }

    public void setNoOfPassengers(int noOfPass) {
        if (noOfPass <= 250 && noOfPass >= 0) {
            this._noOfPassengers = noOfPass;
            if (this._noOfPassengers == 250) {
                this._isFull = true;
            } else {
                this._isFull = false;
            }
        }

    }

    public void setPrice(int price) {
        if (price >= 0) {
            this._price = price;
        }

    }

    public String toString() {
        return "Flight from " + this._origin + " to " + this._destination + " departs at " + this._departure + ". Flight is " + (this._isFull ? "full." : "not full.");
    }

    public boolean equals(Flight other) {
        return this._departure.equals(other._departure) && this._destination.equals(other._destination) && this._origin.equals(other._origin);
    }

    public Time1 getArrivalTime() {
        int arrivalMin = (this._departure.getMinute() + this._flightDuration) % 60;
        int arrivalHour = ((this._departure.getMinute() + this._flightDuration) / 60 + this._departure.getHour()) % 24;
        return new Time1(arrivalHour, arrivalMin);
    }

    public boolean addPassengers(int num) {
        if (this._noOfPassengers + num <= 250) {
            this._noOfPassengers += num;
            if (this._noOfPassengers == 250) {
                this._isFull = true;
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean isCheaper(Flight other) {
        return this._price < other._price;
    }

    public int totalPrice() {
        return this._price * this._noOfPassengers;
    }

    public boolean landsEarlier(Flight other) {
        return this.getArrivalTime().before(other.getArrivalTime());
    }
}