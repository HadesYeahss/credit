package creditos.com.creditohugo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by rigoberto.torres on 06/02/2018.
 */

public class Utils {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
