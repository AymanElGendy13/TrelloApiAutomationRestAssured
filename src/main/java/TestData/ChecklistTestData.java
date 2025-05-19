package TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChecklistTestData {

    private static final Random random = new Random();

    private static final List<String> CHECKLISTNAMES = List.of(
            "ToDo", "Doing", "Progress", "Done", "Reported"
    );


    public static String randomName() {
        return CHECKLISTNAMES.get(random.nextInt(CHECKLISTNAMES.size()));
    }


    public static Map<String, Object> randomChecklistPayload(String cardId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", randomName());
        payload.put("idCard", cardId);
        return payload;
    }

}
