package com.lyca2206.model;

public class Exercise {
    private final String name;
    private final String measureUnit;
    private final float caloriesPerUnit;

    public Exercise(String name, String measureUnit, float caloriesPerUnit) {
        validateName(name);
        validateMeasureUnit(measureUnit);
        validateCaloriesPerUnit(caloriesPerUnit);

        this.name = name;
        this.measureUnit = measureUnit;
        this.caloriesPerUnit = caloriesPerUnit;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The exercise name needs to be non empty");
        }
    }

    private void validateMeasureUnit(String measureUnit) {
        if (measureUnit == null || measureUnit.isEmpty()) {
            throw new IllegalArgumentException("The measure unit needs to be non empty");
        }
    }

    private void validateCaloriesPerUnit(float caloriesPerUnit) {
        if ( caloriesPerUnit <= 0 ) {
            throw new IllegalArgumentException("The calories per unit needs to be a positive value");
        }
    }

    public String getName() {
        return name;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public float getCaloriesPerUnit() {
        return caloriesPerUnit;
    }
}
