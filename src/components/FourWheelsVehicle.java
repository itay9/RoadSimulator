package components;

import utilities.Utilities;
import utilities.VehicleType;

public class FourWheelsVehicle implements VehicleFactory,Utilities{
	public Vehicle getVehicle(String type) {
		if (type.equalsIgnoreCase(null))
			return null;
		if (type.equalsIgnoreCase("private"))
			return new Vehicle(VehicleType.car);
		if (type.equalsIgnoreCase("work"))
			return new Vehicle(VehicleType.truck);
		if (type.equalsIgnoreCase("public"))
			return new Vehicle(VehicleType.bus);
		return null;
	}

}

