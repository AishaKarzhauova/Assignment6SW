package assignment6;

interface Car {
    double getPrice();
}

class BasicCar implements Car {
    private final double basePrice = 20000.0;

    @Override
    public double getPrice() {
        return basePrice;
    }
}

abstract class CarDecorator implements Car {
    protected Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public double getPrice() {
        return car.getPrice();
    }
}

class GPSNavigation extends CarDecorator {
    private final double gpsPrice = 1000.0;

    public GPSNavigation(Car car) {
        super(car);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + gpsPrice;
    }
}

class LeatherSeats extends CarDecorator {
    private final double leatherSeatsPrice = 1500.0;

    public LeatherSeats(Car car) {
        super(car);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + leatherSeatsPrice;
    }
}

public class Assignment6Ex2 {
    public static void main(String[] args) {
        Car basicCar = new BasicCar();

        Car carWithGPS = new GPSNavigation(basicCar);
        Car luxuryCar = new LeatherSeats(carWithGPS);

        System.out.println("Basic Car Price: $" + basicCar.getPrice());
        System.out.println("Car with GPS Navigation Price: $" + carWithGPS.getPrice());
        System.out.println("Luxury Car Price: $" + luxuryCar.getPrice());
    }
}


