package TestData;

import java.util.*;

public class BoardTestData {

    private static final Random random = new Random();

    // Test data collections
    private static final List<String> BOARD_NAMES = List.of(
            "Work Board", "Personal Board", "Team Projects", "Backlog"
    );

    private static final List<String> BOARD_DESCRIPTIONS = List.of(
            "Main project board", "Personal tasks", "Team collaboration", "Product backlog"
    );

    // Factory methods
    public static String randomName() {
        return BOARD_NAMES.get(random.nextInt(BOARD_NAMES.size()));
    }

    public static String randomDescription() {
        return BOARD_DESCRIPTIONS.get(random.nextInt(BOARD_DESCRIPTIONS.size()));
    }

    public static Map<String, Object> randomBoardPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", randomName());
        payload.put("desc", randomDescription());
        payload.put("defaultLists", random.nextBoolean());
        return payload;
    }

}