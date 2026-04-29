public class QuantityMeasuementApp {

    /**
     * Enum for Length Units (Base Unit: FEET)
     */
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double toFeet(double value) {
            return value * factor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / factor;
        }
    }

    /**
     * Quantity Class (Immutable)
     */
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            this.value = value;
            this.unit = unit;
        }

        /**
         * Convert this quantity to another unit (instance method)
         */
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double valueInFeet = unit.toFeet(this.value);
            double convertedValue = targetUnit.fromFeet(valueInFeet);

            return new Quantity(convertedValue, targetUnit);
        }

        /**
         * Convert to base unit (feet)
         */
        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        /**
         * Equality (cross-unit)
         */
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

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    /**
     * STATIC API → convert(value, source, target)
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    /**
     * Overloaded method (method overloading)
     */
    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {

        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(Quantity q,
                                                   LengthUnit to) {

        Quantity result = q.convertTo(to);
        System.out.println(q + " → " + result);
    }

    /**
     * Equality demo
     */
    public static void demonstrateLengthEquality(Quantity q1, Quantity q2) {
        System.out.println(q1 + " == " + q2 + " → " + q1.equals(q2));
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // Basic conversions
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH); // 12
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET); // 9
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD); // 1

        // Centimeter conversion
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        // Instance method conversion
        Quantity q = new Quantity(2.0, LengthUnit.YARD);
        demonstrateLengthConversion(q, LengthUnit.FEET);

        // Equality checks
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        demonstrateLengthEquality(q1, q2); // true

        // Edge cases
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCH); // 0
        demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCH); // -12

        // Example Output
        System.out.println("\nExample:");
        System.out.println("convert(1.0, FEET, INCH) = " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
    }
}