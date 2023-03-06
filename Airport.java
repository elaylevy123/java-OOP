/**
 *
 * @author elay levi
 */

public class Airport {
    private Flight[] _flightsSchedule;
    private int _noOfFlight;
    private String _city;
    private final int MAX_FLIGHTS = 200;

    /**
     * @param city
     */
    public Airport(String city) {
        _flightsSchedule = new Flight[MAX_FLIGHTS];
        this._city = new String(city);
        _noOfFlight = 0;
    }

    /**
     * add a flight to the Airport
     *
     * @param f - the flight we want to add
     * @return - the flight we will join
     */
    public boolean addFlight(Flight f) {
        Flight other_Flight = new Flight(f);
        if (_noOfFlight >= MAX_FLIGHTS) {
            return false;
        }
        if (!other_Flight.getOrigin().equals(this._city) && !other_Flight.getDestination().equals(this._city)) {
            return false;
        }
        for (int i = 0; i < _noOfFlight; i++) {
            if (_flightsSchedule[i].equals(f)) {
                return false;
            }
        }
        _flightsSchedule[_noOfFlight] = other_Flight;
        _noOfFlight++;
        return true;
    }

    /**
     * removes the given flight from the Airport
     *
     * @param f - the flight we want to remove
     * @return - the flight we will remove
     */
    public boolean removeFlight(Flight f) {
        for (int i = 0; i < _noOfFlight; i++) {
            int j = i;
            if (_flightsSchedule[i].equals(f)) {
                while (j + 1 < _noOfFlight) {
                    _flightsSchedule[j] = _flightsSchedule[j + 1];
                    j++;
                }
                _flightsSchedule[j] = null;
                _noOfFlight--;
                return true;
            }
        }
        return false;
    }

    /**
     * @param place - checking what is the first flight and the time it takes off The first flight
     * @return - the time it takes off The first flight
     */
    public Time1 firstFlightFromOrigin(String place) {
        if (_noOfFlight == 0) {
            return null;
        }
        Time1 first_flight = null;
        for (int i = 0; i < _noOfFlight; i++) {
            if (_flightsSchedule[i].getOrigin().equals(place)) //checking for flights from place
                if (first_flight == null || _flightsSchedule[i].getDeparture().before(first_flight)) { //checking what the first flight
                    first_flight = _flightsSchedule[i].getDeparture();// the time of the flight takes off
                }
        }
        return new Time1(first_flight);
    }

    /**
     * //return the information about the flights
     *
     * @return - return all the flights
     */
    public String toString() {
        if (_noOfFlight == 0)
            return null;
        String str = " The flights for airport " + this._city + " today are:";
        for (int i = 0; i < _noOfFlight; i++)
            str += "\n" + _flightsSchedule[i].toString();

        return str;
    }

    /**
     * checking how many full flights
     *
     * @return - return how many full flights
     */
    public int howManyFullFlights() {
        int count = 0;
        for (int i = 0; i < _noOfFlight; i++) {
            if (_flightsSchedule[i].getIsFull()) { // checking if its a full flight
                count++; // count how many full flights
            }
        }
        return count;
    }

    /**
     * Checking for several flights taking off and landing in _city and place
     *
     * @param city
     * @return - return how many flights taking off and landing in _city and place
     */
    public int howManyFlightsBetween(String city) {
        int count = 0;
        for (int i = 0; i < _noOfFlight; i++) {
            if (_flightsSchedule[i].getOrigin().equals(city) || _flightsSchedule[i].getDestination().equals(city))
                //Checking how many flights taking off and landing in _city and place
                count++;
        }
        return count;
    }

    /**
     * The city with the most flights
     *
     * @return
     */
    public String mostPopularDestination() {
        if (_noOfFlight == 0) {
            return null;
        }
        int max = 1;
        String city_flight = _flightsSchedule[0].getDestination();

        int count = 1;
        for (int i = 0; i < _noOfFlight; i++) {
            String city_flight2 = _flightsSchedule[i].getDestination();

            for (int j = i; j < _noOfFlight; j++) {
                if (_flightsSchedule[i].getOrigin().equals(this._city) && _flightsSchedule[i].getDestination().equals(_flightsSchedule[j].getDestination())) { // checking what is the city with the most landings
                    count++; //count what is the city with the most landings
                }
            }
            if (count > max) {
                max = count;
                city_flight = city_flight2;
            }
        }
        return new String(city_flight);
    }

    /**
     * Checking which flight has the most expensive ticket
     *
     * @return - return the expensive flight
     */
    public Flight mostExpensivenTicket() {
        if (_noOfFlight == 0) {
            return null;
        }
        Flight Flight_ticket_most_expensive = _flightsSchedule[0];
        for (int i = 1; i < _noOfFlight; i++) {
            if (_flightsSchedule[i].getPrice() > Flight_ticket_most_expensive.getPrice()) {
                Flight_ticket_most_expensive = _flightsSchedule[i];
            }
        }
        return new Flight(Flight_ticket_most_expensive);
    }

    /**
     * Checking who is the longest flight
     *
     * @return - return the longest flight
     */
    public Flight longestFlight() {
        if (_noOfFlight == 0) {//If there are no flights
            return null;
        }
        Flight longest = _flightsSchedule[0];
        for (int i = 0; i < _noOfFlight; i++) {
            if (_flightsSchedule[i].getFlightDuration() > longest.getFlightDuration()) {//checking what is the longest flight
                longest = _flightsSchedule[i];
            }
        }
        return new Flight(longest);
    }
}