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
        public class QuantityMeasuementApp {

            public static void demonstrateEquality(Quantity<?> q1, Quantity<?> q2) {
                System.out.println(q1 + " == " + q2 + " → " + q1.equals(q2));
            }

            public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> q, U target) {
                System.out.println(q + " → " + q.convertTo(target));
            }

            public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U target) {
                System.out.println("Add: " + q1.add(q2, target));
            }

            public static void main(String[] args) {

                // Length
                Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
                Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCH);

                demonstrateEquality(l1, l2);
                demonstrateConversion(l1, LengthUnit.INCH);
                demonstrateAddition(l1, l2, LengthUnit.FEET);

                // Weight
                Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
                Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

                demonstrateEquality(w1, w2);
                demonstrateConversion(w1, WeightUnit.GRAM);
                demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);

                // Cross-category (should be false)
                demonstrateEquality(l1, w1);
            }
        }