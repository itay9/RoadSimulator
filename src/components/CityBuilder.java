package components;

import java.util.ArrayList;

import utilities.Utilities;

public class CityBuilder implements Utilities{
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Junction> junctions;
	private ArrayList<Road> roads;
	private ArrayList<TrafficLights> lights;
	private JFactory jFactory=new JFactory();
	public Factory factory=new Factory();
	public static final int NUMOFJUNC=12;
	public static final String CITY="city";
	public static final int[] WHEELS= {2,4,10};
	public static final String[][] TYPE= {{"fast","slow"},{"private","public"},{"public"}};;
	public static final int NUMOFVEHICLES=6;
	
	public CityBuilder() {
		vehicles=new ArrayList<Vehicle>();
		junctions=new ArrayList<Junction>();
		roads=new ArrayList<Road>();
		lights=new ArrayList<TrafficLights>();
		System.out.println("\n================= CREATING JUNCTIONS=================");
		for (int i=0; i<NUMOFJUNC; i++) {
			junctions.add(jFactory.getJunction(CITY));
		}
		setAllRoads();
		turnLightsOn();
		System.out.println("\n================= CREATING VEHICLES =================");
		for(int i=0;i<NUMOFVEHICLES;i++) {
			Road temp=getRoads().get(getRandomInt(0,getRoads().size()));//random road from the map
			if( temp.getEnabled()) {
				int ind=getRandomInt(0,WHEELS.length);
				int numOfWheels=WHEELS[ind];
				String type=TYPE[ind][getRandomInt(0,TYPE[ind].length)];
				Vehicle vehicle=factory.getFactory(numOfWheels).getVehicle(type);
				vehicle.setCurrentRoute(new Route(temp, vehicle));
				vehicle.setCurrentRoutePart(temp);
				getVehicles().add(vehicle);
			}

		}
		System.out.println("\n================= GAME MAP HAS BEEN CREATED =================\n");
	
	}
	public void turnLightsOn() {
		System.out.println("\n================= TRAFFIC LIGHTS TURN ON =================");

		for (Junction junction: junctions) {
			LightedJunction junc=(LightedJunction)junction;
			junc.getLights().setTrafficLightsOn(getRandomBoolean());
			if (junc.getLights().getTrafficLightsOn()) {
				lights.add(junc.getLights());
			}
		}
	}
	public void setAllRoads() {
		System.out.println("\n================= CREATING ROADS=================");

		for (int i=0; i<junctions.size();i++) {
			for (int j=0; j<junctions.size();j++) {
				if(i==j) {
					
					continue;
				}
				roads.add(new Road(junctions.get(i), junctions.get(j)));
				
					
			}
		}
	}
	public ArrayList<Junction>getJunctions(){
		return junctions;
	}
	
	public ArrayList<Road>getRoads(){
		return roads;
	}
	
	public ArrayList<TrafficLights> getLights(){
		return lights;
	}
	
	@Override
	public String toString() {
		return new String(CITY+" Map: " +this.getJunctions().size()+" junctions, "+this.getRoads().size()+" roads." );
	}
	/**
	 * @param junctions the junctions to set
	 */
	public void setJunctions(ArrayList<Junction> junctions) {
		this.junctions = junctions;
	}
	/**
	 * @param roads the roads to set
	 */
	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}
	/**
	 * @param lights the lights to set
	 */
	public void setLights(ArrayList<TrafficLights> lights) {
		this.lights = lights;
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
