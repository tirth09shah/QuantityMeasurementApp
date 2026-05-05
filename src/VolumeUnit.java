public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),          // Base unit
    MILLILITRE(0.001),   // 1 mL = 0.001 L
    GALLON(3.78541);     // 1 gallon ≈ 3.78541 L

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double getConversionFactor() {
        return factor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}