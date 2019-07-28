package planner.utility;

import java.sql.Timestamp;
import java.util.Random;

public class IdentifierGenerator {

    public static long timeStampGenerator(){
        return new Timestamp(System.currentTimeMillis()).getTime() + new Random().nextInt();
    }
    public static int generateColorNumber() {
        Random r = new Random();
        int low = 10;
        int high = 100;
        return r.nextInt(high-low) + low;
    }
}
