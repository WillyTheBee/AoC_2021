package aoc;

import aoc.days.Day;
import aoc.days.day1.Day1;
import aoc.days.day2.Day2;
import aoc.days.day3.Day3;
import aoc.days.day4.Day4;
import aoc.days.day5.Day5;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {

    public static void main(String[] args) {
        final ExecutorService service = Executors.newCachedThreadPool();

        final Collection<Day> days = List.of(new Day1(), new Day2(), new Day3(), new Day4(), new Day5());
        for (Day day : days) {
            service.submit(() -> executeDay(day));
        }
        service.shutdown();
    }

    private static void executeDay(Day day) {
        try {
            final Instant start = Instant.now();
            final long part1 = day.part1();
            final long part2 = day.part2();
            final Duration time = Duration.between(start, Instant.now());
            System.out.printf("%s - %d     -       %d      %s ms%n", day.getClass().getSimpleName(), part1, part2, time.getNano() / 1000_000);
        } catch (final Exception e) {
            System.err.println(day.getClass().getSimpleName() + e.getMessage());
            e.printStackTrace();
        }
    }

}
