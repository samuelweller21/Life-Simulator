package Game.Cars;

public class Car {
	
	protected String name;
	protected int speedLimit;
	protected int hp;
	protected int cost;
	protected int seats;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + ", ");
		sb.append("Cost: £" + cost + ", ");
		sb.append("Top Speed: " + speedLimit + ", ");
		sb.append("HorsePower: " + hp + ", ");
		sb.append("Seats: " + seats);
		return sb.toString();
	}
	

}
