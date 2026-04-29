public enum WeightUnit {

    KILOGRAM(1.0),       // Base unit
    GRAM(0.001),         // 1 g = 0.001 kg
    POUND(0.453592);     // 1 lb ≈ 0.453592 kg

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    // Convert to base (kg)
    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    // Convert from base (kg)
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}