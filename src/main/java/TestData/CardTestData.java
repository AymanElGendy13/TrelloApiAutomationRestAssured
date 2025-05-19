package TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CardTestData {
    private static final Random random = new Random();

    // Test data collections
    private static final List<String> CARDNAMES = List.of(
            "Visa", "MasterCard", "American Express", "Discover"
    );

    private static final List<String> CARDDESCRIPTIONS = List.of(
            "CardDescriptionOne", "CardDescriptionTwo",
            "American CardDescriptionThree", "CardDescriptionFour"
    );

    private static final List<String> COLORS = List.of(
            "red", "blue", "green", "yellow", "purple"
    );

    // Factory methods
    public static String randomCardName() {
        return CARDNAMES.get(random.nextInt(CARDNAMES.size()));
    }

    public static String randomCardDescription() {
        return CARDDESCRIPTIONS.get(random.nextInt(CARDDESCRIPTIONS.size()));
    }


    public static Map<String, Object> randomCardPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", randomCardName());
        payload.put("desc", randomCardDescription());
        // Add more fields if needed
        return payload;
    }

}