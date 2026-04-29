public class QuantityMeasuementApp {

    public static void main(String[] args) {

        // Equality
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Equality: " + w1.equals(w2)); // true

        // Conversion
        System.out.println("Convert kg → g: " +
                w1.convertTo(WeightUnit.GRAM));

        System.out.println("Convert pound → kg: " +
                new QuantityWeight(2.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM));

        // Addition (default)
        System.out.println("Add kg + g: " +
                w1.add(w2)); // 2 kg

        // Addition (target unit)
        System.out.println("Add in grams: " +
                w1.add(w2, WeightUnit.GRAM));

        // Pound example
        QuantityWeight p1 = new QuantityWeight(1.0, WeightUnit.POUND);
        QuantityWeight g1 = new QuantityWeight(453.592, WeightUnit.GRAM);

        System.out.println("Pound equality: " + p1.equals(g1));

        // Edge cases
        System.out.println("Zero: " +
                new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM));

        System.out.println("Negative: " +
                new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM));
    }
}