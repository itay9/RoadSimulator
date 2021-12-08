package components;

import utilities.Utilities;

public class JFactory implements Utilities{
	public Junction getJunction(String juncType) {
		if (juncType==null)
			return null;
		if(juncType.equalsIgnoreCase("city")) {
			return new LightedJunction();
		}
		if(juncType.equalsIgnoreCase("country")) {
			if (getRandomBoolean()) {
				return new LightedJunction();
			}
			else
				return new Junction();
		}
	return null;
	}
}
