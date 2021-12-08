package components;

public class Factory {
	public VehicleFactory getFactory(int wheels) {
		if (wheels==2)
			return new twoWheelsVehicle();
		if (wheels==4)
			return new FourWheelsVehicle();
		if (wheels==10)
			return new TenWheelsVehicle();
		return null;
	}
	 
}
