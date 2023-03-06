/**
 * MMN12 q.3 - class that present Rent
 * @author elay levi
 */
public class Rent {

    String _name; // customer name
    Car _car; //car that was rented
    Date _pickDate;// date of the car that was given
    Date _returnDate; // date when the car returned

    private final int PriceA = 100;
    private final int PriceB = 150;
    private final int PriceC = 180;
    private final int PriceD = 240;

    /**
     * constructor 1 - input _name & _car & _pickDate & _pickDate and check if
     *
     * @param name - customer name
     * @param car  - car that was rented
     * @param pick - date of the car that was given
     * @param ret  - date of the return car
     */
    public Rent(String name, Car car, Date pick, Date ret) {
        _name = name;
        _car = new Car(car);
        _pickDate = new Date(pick);
        if (pick.before(ret))//check if the date is least one day before the return date
            _returnDate = new Date(ret);
        else
            _returnDate = new Date((pick.tomorrow()));
    }

    /**
     * Copy constructor
     * Parameters:
     * other - the rent to be copied
     */

    public Rent(Rent other) {
        if (other != null) { // check that the given object is initialized
            _name = other._name;
            _car = new Car(other._car);
            _pickDate = new Date(other._pickDate);
            _returnDate = new Date(other._returnDate);
        }
    }

    /**
     * Check if 2 rents are the same
     * Parameters:
     * other - the rent to compare this rent to
     * Returns:
     * true if the rents are the same
     */
    public boolean equals(Rent other) {// Check if 2 rents are the same and return true or false
        return other.getCar().equals(_car) && other.getName().equals(_name) && other.getPickDate().equals(_pickDate) && other.getReturnDate().equals(_returnDate);
    }

    /**
     * Gets the car
     * Returns:
     * the car
     */
    public Car getCar() {
        return new Car(_car);
    }

    /**
     * Gets the name
     * Returns:
     * the name
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets the pick up date
     * Returns:
     * the pick up date
     */
    public Date getPickDate() {
        return new Date(_pickDate);
    }

    /**
     * Gets the return date
     * Returns:
     * the return date
     */
    public Date getReturnDate() {
        return new Date(_returnDate);
    }

    /**
     * Sets the rented car
     * Parameters:
     * car - the rented car (You can assume that car is not null)
     */

    public void setCar(Car car) {
        if (car != null)
            _car = new Car(car);
    }

    /**
     * Sets the client name
     * Parameters:
     * name - the client name (You can assume that the name is a valid name)
     */
    public void setName(String name) {
        if (name != null)
            _name = name;
    }

    /**
     * Sets the pick up date
     * The pick up date must be at least one day before the return date, otherwise - don't change the pick up date
     * Parameters:
     * pickDate - the pick up date (You can assume that pick up date is not null)
     */
    public void setPickDate(Date pickDate) {
        if (pickDate != null)
            _pickDate = new Date(pickDate);
    }

    /**
     * Sets the return date
     * The return date must be at least one day after the pick up date, otherwise - don't change the return date
     * Parameters:
     * returnDate - the return date (You can assume that return date is not null)
     */
    public void setReturnDate(Date returnDate) {
        if (returnDate != null)
            _returnDate = new Date(returnDate);
    }

    /**
     * Returns the number of rent days
     * Returns:
     * the number of rent days
     */
    public int howManyDays() {
        return _pickDate.difference(_returnDate);//Returns the number of rent days

    }

    /**
     * Returns the rent total price
     * Returns:
     * the rent total price
     */

    public int getPrice() {//Calculates the total price of the rent
        int weeks = howManyDays() / 7;
        int regularDays = howManyDays() % 7;
        if (_car.getType() == 'A') {
            return (int) (PriceA * (0.9 * weeks * 7 + regularDays));
        } else if (_car.getType() == 'B') {
            return (int) (PriceB * (0.9 * weeks * 7 + regularDays));
        } else if (_car.getType() == 'C') {
            return (int) (PriceC * (0.9 * weeks * 7 + regularDays));
        } else {
            return (int) (PriceD * (0.9 * weeks * 7 + regularDays));
        }
    }

    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * Parameters:
     * car - the car to upgrade to
     * Returns:
     * the upgrade cost
     */
    public int upgrade(Car newCar) {
        if (newCar.better(this._car))//Try to upgrade the car to a better car If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
        {
            int priceNow = this.getPrice();
            _car = new Car(newCar);
            int priceAfter = this.getPrice();
            return (priceAfter - priceNow);
        } else {
            return 0;
        }
    }

    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise - return null.
     * Parameters:
     * other - the other rent
     * Returns:
     * the unified rent or null
     */

    public Rent overlap(Rent other) {
        Date first;
        Date end;
        Date pic1 = other.getPickDate();
        Date pic2 = getPickDate();
        Date ret1 = other.getReturnDate();
        Date ret2 = getReturnDate();
        if (other.getName() != this._name || !other.getCar().equals(this.getCar()))
            // Check if there is a double listing of a rent for the same person and car with an overlap in the rental daysIf there is - return a new rent object with the unified dates, otherwise - return null.
            return null;
        else if (pic2.after(ret1) || pic1.after(ret2)) {
            return null;
        } else {
            if (pic1.before(pic2))
                first = new Date(pic1);
            else
                first = new Date(pic2);

            if (ret1.after(ret2))
                end = new Date(ret1);
            else
                end = new Date(ret2);

            return new Rent(_name, _car, first, end);
        }
    }

    /**
     * Returns a String that represents this rent
     * Overrides:
     * toString in class java.lang.Object
     * Returns:
     * String that represents this rent in the following format:
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString() {//Returns a String that represents this rent
        return "Name:" + _name + " From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() + " Days:" + howManyDays() + " Price:" + getPrice();

        }

    }


