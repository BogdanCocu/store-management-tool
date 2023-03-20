package ro.ing.storemanagementtool.util;

import java.util.UUID;

public class Utils {

    public static Long generateRandomUniqueId() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
