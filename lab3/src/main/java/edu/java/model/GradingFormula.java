package edu.java.model;

import java.util.Map;

public record GradingFormula(
        double labTotalWeight,
        double examTotalWeight,
        Map<String, Double> labWeights) {

    public GradingFormula(double labTotalWeight, double examTotalWeight,
                          Map<String, Double> labWeights) {
        this.labTotalWeight = labTotalWeight;
        this.examTotalWeight = examTotalWeight;
        this.labWeights = Map.copyOf(labWeights);
    }
}
