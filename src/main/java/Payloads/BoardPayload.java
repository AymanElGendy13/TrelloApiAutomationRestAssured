package Payloads;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoardPayload {

    public static final List<String> boardNames = Arrays.asList("BoardA", "BoardB", "BoardC", "BoardD");
    public static final List<String> boardDescriptions = Arrays.asList("DescriptionOne", "DescriptionTwo", "DescriptionThree");

    public static final List<String> updatedBoardNames = Arrays.asList("BoardA_UPDATED", "BoardB_UPDATED", "BoardC_UPDATED", "BoardD_UPDATED");
    public static final List<String> updatedBoardDescriptions = Arrays.asList("DescriptionOne_UPDATED", "DescriptionTwo_UPDATED", "DescriptionThree_UPDATED");
    private static final Random random = new Random();

    public static String getRandom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }
}