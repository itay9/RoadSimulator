package components;

import utilities.Utilities;
import utilities.VehicleType;

public class twoWheelsVehicle implements VehicleFactory,Utilities{
	public Vehicle getVehicle(String type) {
		if (type.equalsIgnoreCase(null))
			return null;
		if (type.equalsIgnoreCase("fast"))
			return new Vehicle(VehicleType.motorcycle);
		if (type.equalsIgnoreCase("slow"))
			return new Vehicle(VehicleType.bicycle);
		return null;
	}
}
