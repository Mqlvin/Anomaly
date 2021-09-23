package com.anomaly.math;

import java.util.ArrayList;
import java.util.Collections;

public class Calculators {
    public static ArrayList<Double> getDifference(ArrayList<Integer> numbers) {
        ArrayList<Integer> nums = new ArrayList<>(numbers);
        ArrayList<Double> calculated = new ArrayList<>();
        Collections.sort(nums);
        Integer highest = nums.get(nums.size() - 1);
        for(int i = 0; i < nums.size() - 1; i++) {
            calculated.add(i, Double.parseDouble(nums.get(i).toString()) / highest);
        }
        return calculated;
    }
}