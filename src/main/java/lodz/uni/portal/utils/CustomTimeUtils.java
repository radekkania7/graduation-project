package lodz.uni.portal.utils;

import java.sql.Time;

public class CustomTimeUtils {
    public static boolean isFirstBeforeSecond(Time first, Time second) {
        return first.before(second);
    }
}
