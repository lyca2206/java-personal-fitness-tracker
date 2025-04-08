package com.lyca2206.model;

public record Exercise(String name, String measureUnit, float caloriesPerUnit) {
    public Exercise {
        validateName(name);
        validateMeasureUnit(measureUnit);
        validateCaloriesPerUnit(caloriesPerUnit);
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
        if (caloriesPerUnit <= 0) {
            throw new IllegalArgumentException("The calories per unit needs to be a positive value");
        }
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", measureUnit='" + measureUnit + '\'' +
                ", caloriesPerUnit=" + caloriesPerUnit +
                '}';
    }
}