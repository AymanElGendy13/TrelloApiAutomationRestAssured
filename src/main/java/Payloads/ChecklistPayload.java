package Payloads;

import java.util.List;
import java.util.Random;

public class ChecklistPayload {

    private static final Random random = new Random();

    public static final List<String> checklistNames = List.of(
            "ToDo",
            "Doing",
            "Progress",
            "Done",
            "Reported"
    );

    public static final List<String> updatedChecklistNames = List.of(
            "ToDo_Updated",
            "Doing_Updated",
            "Progress_Updated",
            "Done_Updated",
            "Reported_Updated"
    );

    public static String getRandom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }

}
