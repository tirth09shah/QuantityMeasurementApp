public class QuantityMeasuementApp {

    // ================= DEMONSTRATION METHODS =================

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        System.out.println("Subtraction: " + q1.subtract(q2, targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1, Quantity<U> q2) {

        System.out.println("Division: " + q1.divide(q2));
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {

        System.out.println("Addition: " + q1.add(q2, targetUnit));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> q, U targetUnit) {

        System.out.println("Conversion: " + q.convertTo(targetUnit));
    }

    public static void demonstrateEquality(Quantity<?> q1, Quantity<?> q2) {
        System.out.println("Equality: " + q1.equals(q2));
    }


    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        System.out.println("===== UC12 DEMO =====");

        // ---------- LENGTH ----------
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("\n--- LENGTH ---");
        demonstrateSubtraction(l1, l2, LengthUnit.FEET);
        demonstrateDivision(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(2.0, LengthUnit.FEET));

        // ---------- WEIGHT ----------
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("\n--- WEIGHT ---");
        demonstrateSubtraction(w1, w2, WeightUnit.KILOGRAM);
        demonstrateDivision(
                new Quantity<>(10.0, WeightUnit.KILOGRAM),
                new Quantity<>(5.0, WeightUnit.KILOGRAM));

        // ---------- VOLUME ----------
        Quantity<VolumeUnit> v1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("\n--- VOLUME ---");
        demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
        demonstrateDivision(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE));

        // ---------- EXTRA TESTS ----------
        System.out.println("\n--- EXTRA CHECKS ---");

        // Equality
        demonstrateEquality(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCH));

        // Conversion
        demonstrateConversion(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                VolumeUnit.MILLILITRE);

        // Addition
        demonstrateAddition(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM),
                WeightUnit.KILOGRAM);
    }
}