package aoc.days.day1;

import aoc.days.AbstractDay;

import java.util.List;
import java.util.stream.Stream;

public class Day1 extends AbstractDay {

    @Override
    public long part1(Stream<String> stream) {
        return 0;
    }

    @Override
    public long part2(Stream<String> stream) {

        List<Integer> inputList = stream
                .map(Integer::parseInt)
                .toList();

        return compareAndCount(inputList);
    }

    private static int compareAndCount(List<Integer> inputList) {
        int counter = 0;
        for (int i = 0; i < inputList.size() - 3; i++) {
            int left = inputList.get(i) + inputList.get(i + 1) + inputList.get(i + 2);
            int right = inputList.get(i + 1) + inputList.get(i + 2) + inputList.get(i + 3);

            if (right > left) {
                counter++;
            }
        }
        return counter;
    }
}
