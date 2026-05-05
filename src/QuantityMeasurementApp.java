// ===== VOLUME TESTS =====

Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

// Equality
demonstrateEquality(v1, v2); // true

// Conversion
demonstrateConversion(v1, VolumeUnit.MILLILITRE);
demonstrateConversion(v3, VolumeUnit.LITRE);

// Addition
demonstrateAddition(v1, v2, VolumeUnit.LITRE);
demonstrateAddition(v1, v3, VolumeUnit.MILLILITRE);

// Cross-category check
Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
demonstrateEquality(v1, l); // false