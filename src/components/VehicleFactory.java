package components;

import utilities.VehicleType;

public interface VehicleFactory {
	public Vehicle getVehicle(String type);
}
