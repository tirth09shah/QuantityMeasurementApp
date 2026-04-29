public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.0328084);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    /**
     * Convert given value to base unit (FEET)
     */
    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    /**
     * Convert from base unit (FEET) to this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}