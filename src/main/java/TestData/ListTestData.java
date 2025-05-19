package TestData;

import java.util.*;

public class ListTestData {
    private static final Random random = new Random();

    // Test data collections
    private static final List<String> NAMES = List.of(
            "ListA", "ListB", "ListC", "ListD"
    );

    private static final List<String> UPDATED_NAMES = List.of(
            "ListA_UPDATED", "ListB_UPDATED", "ListC_UPDATED", "ListD_UPDATED"
    );

    private static final List<String> POSITIONS = List.of("top", "bottom");

    // Factory methods
    public static String randomName() {
        return NAMES.get(random.nextInt(NAMES.size()));
    }

    public static String randomUpdatedName() {
        return UPDATED_NAMES.get(random.nextInt(UPDATED_NAMES.size()));
    }

    public static String randomPosition() {
        return POSITIONS.get(random.nextInt(POSITIONS.size()));
    }

    public static Map<String, Object> randomListPayload(String boardId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", NAMES.get(random.nextInt(NAMES.size())));
        payload.put("pos", POSITIONS.get(random.nextInt(POSITIONS.size())));
        return payload;
    }

}
