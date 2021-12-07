package aoc.days.day5;

import aoc.days.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Day5 extends AbstractDay {


    @Override
    public long part1(Stream<String> stream) {

        List<String> data = stream.toList();

        List<Integer[]> verticalLines = new ArrayList<>();
        List<Integer[]> horizontalLines = new ArrayList<>();

        int maxNumber = 0;

       for (String s : data) {
           String numberOnly = s.replaceAll("[^0-9]", " ");
           String test = numberOnly.replaceAll(" +", " ");
           String[] s1 = test.split(" ");

           for (String str : s1) {
               int i = Integer.parseInt(str);
               if (i > maxNumber) {
                   maxNumber = i;
               }
           }

           if (Objects.equals(s1[1], s1[3])) {
               horizontalLines.add(Arrays.stream(s1)
                       .map(Integer::parseInt)
                       .toArray(Integer[]::new));
           }
           if (Objects.equals(s1[0], s1[2])) {
               verticalLines.add(Arrays.stream(s1)
                       .map(Integer::parseInt)
                       .toArray(Integer[]::new));
           }
       }

       int[][] playgrid = new int[maxNumber+1][maxNumber+1];

       for (Integer[] line : horizontalLines) {
           if (line[0] < line[2]) {
               for (int i = line[0]; i <= line[2]; i++) {
                   playgrid[line[1]][i] += 1;
               }
           } else {
               for (int i = line[2]; i <= line[0]; i++) {
                   playgrid[line[1]][i] += 1;
               }
           }

       }

        for (Integer[] line : verticalLines) {
            if (line[1] < line[3]) {
                for (int i = line[1]; i <= line[3]; i++) {
                    playgrid[i][line[0]] += 1;
                }
            } else {
                for (int i = line[3]; i <= line[1]; i++) {
                    playgrid[i][line[0]] += 1;
                }
            }

        }

        int counter = 0;
        for (int i = 0; i < playgrid.length; i++) {
            for (int j = 0; j < playgrid[i].length; j++) {
                if (playgrid[i][j] >= 2) {
                    counter += 1;
                }
            }
        }

        return counter;
    }

    @Override
    public long part2(Stream<String> stream) {
        List<String> data = stream.toList();

        List<Integer[]> verticalLines = new ArrayList<>();
        List<Integer[]> horizontalLines = new ArrayList<>();
        List<Integer[]> diagonalLines = new ArrayList<>();

        int maxNumber = 0;

        for (String s : data) {
            String numberOnly = s.replaceAll("[^0-9]", " ");
            String test = numberOnly.replaceAll(" +", " ");
            String[] s1 = test.split(" ");

            Integer[] i1 = (Arrays.stream(s1)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new));

            for (String str : s1) {
                int i = Integer.parseInt(str);
                if (i > maxNumber) {
                    maxNumber = i;
                }
            }

            if (Objects.equals(s1[1], s1[3])) {
                horizontalLines.add(i1);
            } else if (Objects.equals(s1[0], s1[2])) {
                verticalLines.add(i1);
            } else {
                diagonalLines.add(i1);
            }
        }

        int[][] playgrid = new int[maxNumber+1][maxNumber+1];

        for (Integer[] line : horizontalLines) {
            if (line[0] < line[2]) {
                for (int i = line[0]; i <= line[2]; i++) {
                    playgrid[line[1]][i] += 1;
                }
            } else {
                for (int i = line[2]; i <= line[0]; i++) {
                    playgrid[line[1]][i] += 1;
                }
            }

        }

        for (Integer[] line : verticalLines) {
            if (line[1] < line[3]) {
                for (int i = line[1]; i <= line[3]; i++) {
                    playgrid[i][line[0]] += 1;
                }
            } else {
                for (int i = line[3]; i <= line[1]; i++) {
                    playgrid[i][line[0]] += 1;
                }
            }

        }

        for (Integer[] line : diagonalLines) {
            int x1 = line[0];
            int y1 = line[1];
            int x2 = line[2];
            int y2 = line[3];
            // nach unten rechts
            if (x2 > x1 && y2 > y1) {
                int j = 0;
                for (int i = x1; i <= x2 ; i++) {
                    playgrid[y1+j][i] += 1;
                    j++;
                }
            }
            // nach oben links x1 = 5, x2 = 3, y1 = 7, y2 = 5
            if (x1 > x2 && y1 > y2) {
                int j = 0;
                for (int i = x2; i <= x1; i++) {
                    playgrid[y1 - j][x1-j] += 1;
                    j++;
                }
            }
            // nach oben rechts x1 = 0, x2 = 3, y1 = 5, y2 = 2
            if (y1 > y2 && x2 > x1) {
                int j = 0;
                for (int i = x1; i <= x2; i++) {
                    playgrid[y1-j][i] += 1;
                    j++;
                }
            }
            // nach unten links x1 = 8, x2 = 0, y1 = 0, y2 = 8
            if (y2 > y1 && x1 > x2){
                int j = 0;
                for (int i = y1; i <= y2; i++) {
                    playgrid[y1+j][x1-j] += 1;
                    j++;
                }
            }
        }

        int counter = 0;
        for (int i = 0; i < playgrid.length; i++) {
            for (int j = 0; j < playgrid[i].length; j++) {
                if (playgrid[i][j] >= 2) {
                    counter += 1;
                }
            }
        }

        return counter;
    }
}
