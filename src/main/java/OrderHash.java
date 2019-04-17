
import java.util.Arrays;

public class OrderHash <T> {


    public static <T extends Comparable<T>> T[] getOrder (T[] ts) {

        Arrays.sort(ts);
        return ts;
    }

}
