public class QuantityMeasuementApp {

    /**
     * Quantity Class (now simplified)
     */
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
            }
            this.value = value;
            this.unit = unit;
        }

        /**
         * Convert to another unit
         */
        public Quantity convertTo(LengthUnit targetUnit) {
            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);
            return new Quantity(converted, targetUnit);
        }

        /**
         * Base conversion helper
         */
        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        /**
         * UC6: Add (result in first unit)
         */
        public Quantity add(Quantity other) {
            double sum = this.toBase() + other.toBase();
            double result = this.unit.convertFromBaseUnit(sum);
            return new Quantity(result, this.unit);
        }

        /**
         * UC7: Add with target unit
         */
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            double sum = this.toBase() + other.toBase();
            double result = targetUnit.convertFromBaseUnit(sum);
            return new Quantity(result, targetUnit);
        }

        /**
         * Equality
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBase());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    /**
     * Static API methods
     */
    public static double convert(double value, LengthUnit from, LengthUnit to) {
        double base = from.convertToBaseUnit(value);
        return to.convertFromBaseUnit(base);
    }

    public static Quantity add(double v1, LengthUnit u1,
                               double v2, LengthUnit u2,
                               LengthUnit target) {
        return new Quantity(v1, u1)
                .add(new Quantity(v2, u2), target);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        // Conversion
        System.out.println("Convert: " + q1.convertTo(LengthUnit.INCH));

        // Equality
        System.out.println("Equal: " + q1.equals(q2));

        // Addition UC6
        System.out.println("Add (default): " + q1.add(q2));

        // Addition UC7
        System.out.println("Add (target YARD): " +
                q1.add(q2, LengthUnit.YARD));

        // Static conversion
        System.out.println("Static convert: " +
                convert(1.0, LengthUnit.FEET, LengthUnit.INCH));

        // Static addition
        System.out.println("Static add: " +
                add(1.0, LengthUnit.FEET,
                        12.0, LengthUnit.INCH,
                        LengthUnit.FEET));
    }
}