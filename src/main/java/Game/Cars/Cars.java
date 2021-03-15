package main.java.Game.Cars;
import java.util.ArrayList;

public class Cars {
	
	private ArrayList<Car> cars;
	
	public Cars() {
		cars = new ArrayList<Car>();
	}
	
	public String toString() {
		return cars.toString();
	}
	
	public void buy(Car car) {
		cars.add(car);
	}
	
}
