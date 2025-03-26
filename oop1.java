
class Vehicle {
  String brand;
  int speed;

  public Vehicle(String brand, int speed) {
    this.brand = brand;
    this.speed = speed;
  }

  public void displayInfo() {
    System.out.println("Brand: " + brand);
    System.out.println("Speed: " + speed + " km/h");
  }
}

class Car extends Vehicle {
  int numberOfDoors;

  public Car(String brand, int speed, int numberOfDoors) {
    super(brand, speed);
    this.numberOfDoors = numberOfDoors;
  }

  public void showCarDetails() {
    displayInfo();
    System.out.println("Number of Doors: " + numberOfDoors);
  }
}

class Bike extends Vehicle {
  boolean hasGear;

  public Bike(String brand, int speed, boolean hasGear) {
    super(brand, speed);
    this.hasGear = hasGear;
  }

  public void showBikeDetails() {
    displayInfo();
    System.out.println("Has Gear: " + (hasGear ? "Yes" : "No"));
  }
}

public class oop1 {
  public static void main(String[] args) {
    Car myCar = new Car("Toyota", 180, 4);
    System.out.println("Car Details:");
    myCar.showCarDetails();

    System.out.println("\n----------------\n");

    Bike myBike = new Bike("Yamaha", 120, true);
    System.out.println("Bike Details:");
    myBike.showBikeDetails();
  }
}
