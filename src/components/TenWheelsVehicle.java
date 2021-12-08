package components;

import utilities.Utilities;
import utilities.VehicleType;

public class TenWheelsVehicle implements VehicleFactory,Utilities{
	public Vehicle getVehicle(String type) {
		if (type.equalsIgnoreCase(null))
			return null;
		if (type.equalsIgnoreCase("public"))
			return new Vehicle(VehicleType.tram);
		if (type.equalsIgnoreCase("work"))
			return new Vehicle(VehicleType.semitrailer);

		return null;
	}
}
