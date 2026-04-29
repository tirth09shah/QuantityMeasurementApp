public class QuantityMeasuementApp {

    /**
     * Enum for Length Units
     * Conversion factor relative to FEET (base unit)
     */
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double toFeet(double value) {
            return value * conversionFactor;
        }
    }

    /**
     * Generic Quantity Class
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

        /**
         * Convert to base unit (feet)
         */
        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        /**
         * Override equals() for cross-unit comparison
         */
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or type check
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting both to feet
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
     * Main method
     */
    public static void main(String[] args) {

        // Same-unit equality
        System.out.println("Feet vs Feet: " +
                compare(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET)); // true

        System.out.println("Inch vs Inch: " +
                compare(1.0, LengthUnit.INCH, 1.0, LengthUnit.INCH)); // true

        // Cross-unit equality
        System.out.println("Feet vs Inches: " +
                compare(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH)); // true

        System.out.println("Inches vs Feet: " +
                compare(12.0, LengthUnit.INCH, 1.0, LengthUnit.FEET)); // true

        // Different values
        System.out.println("Different Values: " +
                compare(1.0, LengthUnit.FEET, 2.0, LengthUnit.FEET)); // false

        // Edge cases
        Quantity q = new Quantity(1.0, LengthUnit.FEET);
        System.out.println("Null Comparison: " + q.equals(null)); // false
        System.out.println("Same Reference: " + q.equals(q)); // true

        // Example Output
        System.out.println("\nExample:");
        System.out.println("Input: Quantity(1.0, feet) and Quantity(12.0, inches)");
        System.out.println("Output: Equal (" +
                compare(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH) + ")");
    }
}