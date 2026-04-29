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
         * Convert to another unit
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
         * ADD method (instance)
         * Result is in unit of FIRST operand
         */
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double sumInFeet = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = this.unit.fromFeet(sumInFeet);

            return new Quantity(resultValue, this.unit);
        }

        /**
         * Equality
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
     * STATIC ADD METHOD (overloaded style)
     */
    public static Quantity add(double v1, LengthUnit u1,
                               double v2, LengthUnit u2) {

        Quantity q1 = new Quantity(v1, u1);
        Quantity q2 = new Quantity(v2, u2);

        return q1.add(q2);
    }

    /**
     * Demonstration methods
     */
    public static void demonstrateAddition(Quantity q1, Quantity q2) {
        Quantity result = q1.add(q2);
        System.out.println("add(" + q1 + ", " + q2 + ") = " + result);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        // Same unit
        demonstrateAddition(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(2.0, LengthUnit.FEET)); // 3 FEET

        // Feet + Inches
        demonstrateAddition(
                new Quantity(1.0, LengthUnit.FEET),
                new Quantity(12.0, LengthUnit.INCH)); // 2 FEET

        // Inches + Feet
        demonstrateAddition(
                new Quantity(12.0, LengthUnit.INCH),
                new Quantity(1.0, LengthUnit.FEET)); // 24 INCH

        // Yard + Feet
        demonstrateAddition(
                new Quantity(1.0, LengthUnit.YARD),
                new Quantity(3.0, LengthUnit.FEET)); // 2 YARD

        // Inches + Yard
        demonstrateAddition(
                new Quantity(36.0, LengthUnit.INCH),
                new Quantity(1.0, LengthUnit.YARD)); // 72 INCH

        // CM + Inch
        demonstrateAddition(
                new Quantity(2.54, LengthUnit.CENTIMETER),
                new Quantity(1.0, LengthUnit.INCH));

        // Zero case
        demonstrateAddition(
                new Quantity(5.0, LengthUnit.FEET),
                new Quantity(0.0, LengthUnit.INCH));

        // Negative case
        demonstrateAddition(
                new Quantity(5.0, LengthUnit.FEET),
                new Quantity(-2.0, LengthUnit.FEET));

        // Example Output
        System.out.println("\nExample:");
        Quantity result = add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH);
        System.out.println("Output: " + result);
    }
}