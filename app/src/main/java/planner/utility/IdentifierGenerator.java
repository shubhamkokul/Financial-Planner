package planner.utility;

import java.sql.Timestamp;
import java.util.Random;

public class IdentifierGenerator {

    public static long timeStampGenerator(){
        return new Timestamp(System.currentTimeMillis()).getTime() + new Random().nextInt();
    }
}
