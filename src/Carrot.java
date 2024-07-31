import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Carrot {

    private static final int MAX_WEIGHT = 5;
    private static final int MAX_TRIPS = 10;
    public static List<Integer> carrotQuantity = Arrays.asList(1, 1, 3, 1, 1);
    public static int countOfTrips = 0;
    private static int totalCarrots = 0;

    public static void main(String[] args) {
        for (; ; ) {
            if (countOfTrips == MAX_TRIPS) break;
            countOfTrips++;

            List<Integer> indexes = findIndexes(); //Подбор индексов грядок дающих взятку приближенную к максимальной
            totalCarrots = totalCarrots + sumElements(indexes) + indexes.size(); //Прибавление взятки
            for (int index : indexes) {// Уменьшение моркови на грядках
                carrotQuantity.set(index, carrotQuantity.get(index) - 1);
            }
            if (sumElements(carrotQuantity) <= 0) {
                break;
            }
        }
        System.out.println("Количество собранной моркови: " + totalCarrots + " кг. Количество ходок - " + countOfTrips + ".");
        System.out.println("Остатки моркови по грядкам: " + carrotQuantity.toString());
    }

    public static List<Integer> findIndexes() {
        List<Integer> trialIndexes = new ArrayList<>();
        List<Integer> finalIndexes = new ArrayList<>();
        List<Integer> trialQuantities = new ArrayList<>();
        trialQuantities.addAll(carrotQuantity);
        int indexToTry = maxNotZeroIndex();//нахождение максимального индекса грядки с ненулевым кол-вом моркови
        int trialSum = 0;
        for (; ; ) {
            if (indexToTry < 0) break;
            ;
            if (indexToTry == 0 && trialQuantities.get(0) == 0) break;
            if (sumElements(trialQuantities) <= 0) {
                break;
            }
            if (trialQuantities.get(indexToTry) > 0) {
                trialIndexes.add(indexToTry);
                trialQuantities.set(indexToTry, trialQuantities.get(indexToTry) - 1);
            } else if (indexToTry != 0) {
                indexToTry--;
                continue;
            }
            trialSum = sumElements(trialIndexes) + trialIndexes.size();
            if (trialSum == MAX_WEIGHT) {
                finalIndexes = trialIndexes.stream().collect(Collectors.toList());
                break;
            }
            if (trialSum < MAX_WEIGHT) {
                finalIndexes = trialIndexes.stream().collect(Collectors.toList());
                continue;
            }
            if (trialSum > MAX_WEIGHT) {
                indexToTry--;
                trialIndexes = finalIndexes.stream().collect(Collectors.toList());
            }
        }
        return finalIndexes;
    }

    public static int maxNotZeroIndex() {
        int i = carrotQuantity.size() - 1;
        for (; ; ) {
            if (carrotQuantity.get(i) != 0) break;
            else i--;
        }
        return i;
    }

    public static int sumElements(List<Integer> toSum) {
        int sum = 0;
        for (Integer d : toSum)
            sum += d;
        return sum;
    }
}