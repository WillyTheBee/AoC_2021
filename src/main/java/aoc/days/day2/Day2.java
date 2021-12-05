package aoc.days.day2;

import aoc.days.AbstractDay;

import java.util.List;
import java.util.stream.Stream;

public class Day2 extends AbstractDay {


    @Override
    public long part1(Stream<String> stream) {

        List<String> inputList = stream
                .toList();

        int depths = 0;
        int horizontal = 0;

        for (String string : inputList) {
            String[] strings = string.split("\\s");
            if (strings[0].equals("forward")) {
                horizontal += Integer.parseInt(strings[1]);
            }

            if (strings[0].equals("down")) {
                depths += Integer.parseInt(strings[1]);
            }

            if (strings[0].equals("up")) {
                depths -= Integer.parseInt(strings[1]);
            }
        }

        return (long) depths * horizontal;

    }

    @Override
    public long part2(Stream<String> stream) {

        List<String> inputList = stream
                .toList();

        long aim = 0;
        long depth = 0;
        long horizontal = 0;

        for (String string : inputList) {

            String[] strings = string.split("\\s");
            String command = strings[0];
            int value = Integer.parseInt(strings[1]);

            switch (command) {
                case "forward" -> {
                    horizontal += value;
                    depth += (aim * value);
                }
                case "down" -> aim += value;
                case "up" -> aim -= value;
            }
        }

        return depth * horizontal;
    }
}
