public class QuantityMeasuementApp {

    /**
     * Inner class representing Feet measurement
     * Immutable and encapsulated
     */
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Getter (optional)
        public double getValue() {
            return value;
        }

        /**
         * Override equals() to compare values
         */
        @Override
        public boolean equals(Object obj) {

            // Reflexive check (same object)
            if (this == obj) {
                return true;
            }

            // Null and type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Cast to Feet
            Feet other = (Feet) obj;

            // Compare double safely
            return Double.compare(this.value, other.value) == 0;
        }

        /**
         * Override hashCode (required when equals is overridden)
         */
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    /**
     * Static method to compare two feet values
     */
    public static boolean compareFeet(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    /**
     * Main method (execution starts here)
     */
    public static void main(String[] args) {

        // Test cases
        System.out.println("Same Value (1.0, 1.0): " + compareFeet(1.0, 1.0)); // true
        System.out.println("Different Value (1.0, 2.0): " + compareFeet(1.0, 2.0)); // false

        // Edge cases
        Feet f = new Feet(1.0);
        System.out.println("Null Comparison: " + f.equals(null)); // false
        System.out.println("Non-Numeric Input: " + f.equals("abc")); // false
        System.out.println("Same Reference: " + f.equals(f)); // true

        // Example Output
        System.out.println("\nExample:");
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + compareFeet(1.0, 1.0) + ")");
    }
}