public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    // Convert
    public Quantity<U> convertTo(U targetUnit) {
        double base = this.toBase();
        double result = targetUnit.convertFromBaseUnit(base);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // Add (UC6)
    public Quantity<U> add(Quantity<U> other) {
        double sum = this.toBase() + other.toBase();
        double result = unit.convertFromBaseUnit(sum);
        return new Quantity<>(result, unit);
    }

    // Add (UC7)
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double sum = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sum);
        return new Quantity<>(result, targetUnit);
    }

    // Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison
        if (this.unit.getClass() != other.unit.getClass()) {
            return false;
        }

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}