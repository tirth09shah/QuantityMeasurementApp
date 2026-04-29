public class QuantityMeasuementApp {

    /**
     * Enum for Length Units
     * Base unit: FEET
     */
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.0328084);     // 1 cm = 0.0328084 feet

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toFeet(double value) {
            return value * conversionFactor;
        }
    }

    /**
     * Generic Quantity Class (same as UC3)
     */
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }

    /**
     * Static comparison method
     */
    public static boolean compare(double v1, LengthUnit u1,
                                  double v2, LengthUnit u2) {

        Quantity q1 = new Quantity(v1, u1);
        Quantity q2 = new Quantity(v2, u2);

        return q1.equals(q2);
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {

        // Yards tests
        System.out.println("Yard vs Yard: " +
                compare(2.0, LengthUnit.YARD, 2.0, LengthUnit.YARD)); // true

        System.out.println("Yard vs Feet: " +
                compare(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET)); // true

        System.out.println("Yard vs Inches: " +
                compare(1.0, LengthUnit.YARD, 36.0, LengthUnit.INCH)); // true

        // Centimeter tests
        System.out.println("CM vs CM: " +
                compare(2.0, LengthUnit.CENTIMETER, 2.0, LengthUnit.CENTIMETER)); // true

        System.out.println("CM vs Inches: " +
                compare(1.0, LengthUnit.CENTIMETER, 0.393701, LengthUnit.INCH)); // true

        System.out.println("CM vs Feet: " +
                compare(1.0, LengthUnit.CENTIMETER, 1.0, LengthUnit.FEET)); // false

        // Mixed unit (transitive)
        boolean step1 = compare(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET);
        boolean step2 = compare(3.0, LengthUnit.FEET, 36.0, LengthUnit.INCH);

        System.out.println("Transitive Check (Yard->Feet->Inch): " + (step1 && step2)); // true

        // Example Output
        System.out.println("\nExample:");
        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)");
        System.out.println("Output: Equal (" +
                compare(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET) + ")");
    }
}