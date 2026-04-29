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

            double feetValue = unit.toFeet(this.value);
            double converted = targetUnit.fromFeet(feetValue);

            return new Quantity(converted, targetUnit);
        }

        /**
         * Convert to base (feet)
         */
        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        /**
         * UC6: Add (result in unit of first operand)
         */
        public Quantity add(Quantity other) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double sumFeet = this.toBaseUnit() + other.toBaseUnit();
            double result = this.unit.fromFeet(sumFeet);

            return new Quantity(result, this.unit);
        }

        /**
         * UC7: Add with explicit target unit
         */
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumFeet = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.fromFeet(sumFeet);

            return new Quantity(result, targetUnit);
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

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    /**
     * STATIC ADD (UC6)
     */
    public static Quantity add(double v1, LengthUnit u1,
                               double v2, LengthUnit u2) {
        return new Quantity(v1, u1).add(new Quantity(v2, u2));
    }

    /**
     * STATIC ADD (UC7 with target unit)
     */
    public static Quantity add(double v1, LengthUnit u1,
                               double v2, LengthUnit u2,
                               LengthUnit target) {
        return new Quantity(v1, u1)
                .add(new Quantity(v2, u2), target);
    }

    /**
     * Demo method
     */
    public static void demonstrate(String label, Quantity result) {
        System.out.println(label + " → " + result);
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);

        // UC6 (default)
        demonstrate("Feet + Inches (default)",
                q1.add(q2)); // 2 FEET

        // UC7 explicit target
        demonstrate("Result in FEET",
                q1.add(q2, LengthUnit.FEET));

        demonstrate("Result in INCH",
                q1.add(q2, LengthUnit.INCH));

        demonstrate("Result in YARD",
                q1.add(q2, LengthUnit.YARD));

        // Other cases
        demonstrate("Yard + Feet → YARD",
                new Quantity(1.0, LengthUnit.YARD)
                        .add(new Quantity(3.0, LengthUnit.FEET), LengthUnit.YARD));

        demonstrate("Inch + Yard → FEET",
                new Quantity(36.0, LengthUnit.INCH)
                        .add(new Quantity(1.0, LengthUnit.YARD), LengthUnit.FEET));

        demonstrate("CM + Inch → CM",
                new Quantity(2.54, LengthUnit.CENTIMETER)
                        .add(new Quantity(1.0, LengthUnit.INCH), LengthUnit.CENTIMETER));

        // Edge cases
        demonstrate("Zero case",
                new Quantity(5.0, LengthUnit.FEET)
                        .add(new Quantity(0.0, LengthUnit.INCH), LengthUnit.YARD));

        demonstrate("Negative case",
                new Quantity(5.0, LengthUnit.FEET)
                        .add(new Quantity(-2.0, LengthUnit.FEET), LengthUnit.INCH));

        // Example Output
        System.out.println("\nExample:");
        System.out.println("add(1 ft, 12 inch, yard) = " +
                add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH, LengthUnit.YARD));
    }
}