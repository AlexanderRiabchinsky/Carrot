import java.util.Arrays;

public class Carrot {

    private static final int MAX_WEIGHT = 5;
    private static final int MAX_TRIPS = 10;
    private static final int[] CARROT_WEIGHTS = {1, 2, 3, 4, 5};
    public static int countOfTrips = 0;

    public static void main(String[] args) {

        int totalCarrots = harvestCarrots();
        System.out.println("Количество собранной моркови: " + totalCarrots + " кг. Количество ходок - " + countOfTrips + ".");
    }

    public static int harvestCarrots() {

        int totalCarrots = 0;
        int[] remainingCarrots = Arrays.copyOf(CARROT_WEIGHTS, CARROT_WEIGHTS.length);

        for (int i = 0; i < MAX_TRIPS; i++) {
            int currentWeight = 0;
            for (int j = remainingCarrots.length - 1; j >= 0; j--) {
                if (remainingCarrots[j] > 0 && currentWeight + remainingCarrots[j] <= MAX_WEIGHT) {
                    currentWeight += remainingCarrots[j];
                    totalCarrots += remainingCarrots[j];
                    remainingCarrots[j] = 0;
                }
            }

            countOfTrips++;
            if (Arrays.stream(remainingCarrots).sum() == 0) {
                break;
            }
        }
        return totalCarrots;
    }
}