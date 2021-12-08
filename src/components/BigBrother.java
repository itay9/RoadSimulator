package components;

import utilities.Utilities;

import java.io.IOException;

public class BigBrother implements Utilities {
    static private volatile BigBrother instance = null;

    private Moked moked;
    private BigBrother() {
            //print("1");
            moked = new Moked();
        //print("2");
            successMessage("BigBrother");
            //print("3");
    }
    public static BigBrother getBigBrotherInst(){
        if (instance == null)
            synchronized(BigBrother.class){
                if (instance == null)
                    instance = new BigBrother();
            }
        return instance;

    }
    public void checkSpeeding(Vehicle vehicle) {
        RouteParts routePart = vehicle.getCurrentRoutePart();
        //System.out.println(" speedeing 1");
        if (routePart instanceof Road) {
            //System.out.println(" speedeing 2");
            int maxSpeed = ((Road) routePart).getMaxSpeed();
            //System.out.println(" speedeing 3");
            if (vehicle.getSpeed() > maxSpeed) {
                System.out.println(" speedeing 4");
                getMoked().giveReport(vehicle);
                System.out.println(" speedeing 5 OK ");
                //  }
            }
        }
    }

    public Moked getMoked() {
        return moked;
    }
    /*
    public static void main(String[] args) {
        Road r= new Road(new Junction(), new Junction());
        Vehicle v1 = new Vehicle(r);
        Vehicle v2 = new Vehicle(r);
        r.setMaxSpeed(40);
        v1.setSpeed(30); //driving safe
        v2.setSpeed(50); // speeding , need to get report

        BigBrother brother = getBigBrotherInst();
        brother.checkSpeeding(v1);
        brother.checkSpeeding(v2);

    }

 */


}
