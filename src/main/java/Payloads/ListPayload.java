package Payloads;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ListPayload {
    private static final Random random = new Random();
    public static final List<String> listNames = Arrays.asList("ListA", "ListB", "ListC", "ListD");

    public static final List<String> updatedListNames = Arrays.asList("ListA_UPDATED", "ListB_UPDATED", "ListC_UPDATED", "ListD_UPDATED");
    public static final List<String> listPositions = Arrays.asList("top","bottom");


    public static String getRandom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }
}
