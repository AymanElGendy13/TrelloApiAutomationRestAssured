package Payloads;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardPayload {
    private static final Random random = new Random();

    public static final List<String> cardNames = Arrays.asList("Visa", "MasterCard", "American Express", "Discover");
    public static final List<String> updatedCardDescriptions = Arrays.asList("CardDescriptionOne", "CardDescriptionTwo", "American CardDescriptionThree", "CardDescriptionFour");

    public static String getRandom(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }

}