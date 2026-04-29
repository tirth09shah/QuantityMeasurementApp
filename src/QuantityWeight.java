public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
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
    public QuantityWeight convertTo(WeightUnit target) {
        double base = this.toBase();
        double converted = target.convertFromBaseUnit(base);
        return new QuantityWeight(converted, target);
    }

    // Add (UC6 style)
    public QuantityWeight add(QuantityWeight other) {
        double sum = this.toBase() + other.toBase();
        double result = this.unit.convertFromBaseUnit(sum);
        return new QuantityWeight(result, this.unit);
    }

    // Add (UC7 style)
    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        double sum = this.toBase() + other.toBase();
        double result = target.convertFromBaseUnit(sum);
        return new QuantityWeight(result, target);
    }

    // Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;
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