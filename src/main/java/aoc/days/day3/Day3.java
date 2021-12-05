package aoc.days.day3;

import aoc.days.AbstractDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3 extends AbstractDay {

    @Override
    public long part1(Stream<String> stream) {

        List<String> input = stream.toList();
        List<Row> summary = getSummary(input);

        String gammaRate = getMostCommonBits(summary);
        int decimalGamma = Integer.parseInt(gammaRate,2);

        String epsilonRate = getLeastCommonBits(summary);
        int deciamalEpsilon = Integer.parseInt(epsilonRate,2);

        return (long) deciamalEpsilon * decimalGamma;
    }

    @Override
    public long part2(Stream<String> stream) {

        List<String> input = stream.toList();

        String oxygenRating = filterInput(input, "oxygen");
        String co2Rating = filterInput(input,"co2");

        int decimalOxygen = Integer.parseInt(oxygenRating,2);
        int decimalCO2 = Integer.parseInt(co2Rating,2);

        return (long) decimalOxygen * decimalCO2;
    }

    // Man könnte hier auch rekursion einbauen, bin mir nur nicht sicher was lesbarer ist :D
    private String filterInput(List<String> input, String rating) {
        List<String> filtered = input;

        int i = 0;
        while (filtered.size() > 1) {

            if (i > filtered.get(0).length()) {
                throw new IllegalStateException();
            }

            String filterCriteria = null;
            if (rating.equals("oxygen")) {
                List<Row> summary = getSummary(filtered);
                filterCriteria = getMostCommonBits(summary);
            }
            if (rating.equals("co2")) {
                List<Row> summary = getSummary(filtered);
                filterCriteria = getLeastCommonBits(summary);
            }

            int finalI = i;
            String finalFilterCriteria = filterCriteria;
            filtered = filtered.stream()
                    .filter(num -> num.charAt(finalI) == finalFilterCriteria.charAt(finalI))
                    .toList();

            i++;
        }
        return filtered.get(0);
    }

    private List<Row> getSummary(List<String> input) {
        List<Row> summary = new ArrayList<>();
        int bytesize = input.get(0).length();
        for (int i = 0; i < bytesize; i++) {
            summary.add(new Row());
        }

        for (String string : input) {
            for (int i = 0; i < bytesize; i++) {
                char c = string.charAt(i);
                if (c == '1') {
                    summary.get(i).addOne();
                }
                if (c == '0') {
                    summary.get(i).addZero();
                }
            }
        }
        return summary;
    }

    public String getMostCommonBits(List<Row> summary) {

        StringBuilder str = new StringBuilder();

        for (Row row : summary) {
            if (row.getOnes() >= row.getZeros()) {
                str.append('1');
            }
            if (row.getZeros() > row.getOnes()){
                str.append('0');
            }
        }

        return str.toString();
    }

    public String getLeastCommonBits(List<Row> summary) {

        StringBuilder str = new StringBuilder();

        for (Row row : summary) {
            if (row.getOnes() < row.getZeros()) {
                str.append('1');
            }
            if (row.getZeros() <= row.getOnes()){
                str.append('0');
            }
        }

        return str.toString();
    }

    public class Row {

        private int zeros = 0;
        private int ones = 0;

        public void addZero () {
            this.zeros++;
        }

        public void addOne () {
            this.ones++;
        }

        public int getZeros() {
            return zeros;
        }

        public int getOnes() {
            return ones;
        }
    }
}
