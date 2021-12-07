package aoc.days.day4;

import aoc.days.AbstractDay;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// gefällt mir alles nicht am liebsten den ganzen Tag 4 nochmal neu bauen.... T.T

public class Day4 extends AbstractDay {

    @Override
    public long part1(Stream<String> stream) {

        List<String> data = stream
                .toList();

        int[] numbers = loadInput(data);
        List<Number[][]> boards = loadBoards(data);


        long score = getFirstWinningBoard(numbers, boards);

        return score;
    }

    @Override
    public long part2(Stream<String> stream) {

        List<String> data = stream
                .toList();

        int[] numbers = loadInput(data);
        List<Number[][]> boards = loadBoards(data);

        long score = getLastWinningBoard(numbers, boards);

        return score;
    }


    private long getScore(int lastInput, Number[][] board) {

        int counter = 0;

        for (Number[] line : board) {
            for (Number number : line) {
                if (!number.isMarked()) {
                    counter += number.getNumber();
                }
            }
        }

        return (long) counter * lastInput;
    }

    private int[] loadInput(List<String> data) {
        String str = data.get(0);
        return Arrays.stream(str.substring(0, str.length()-1).split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private List<Number[][]> loadBoards(List<String> data) {
        // indexes of empty lines
        int[] indexes = Stream.of(
                            IntStream.range(0, data.size()).filter(i -> data.get(i).isEmpty()),
                            IntStream.of(data.size()))
                        .flatMapToInt(ix -> ix)
                        .toArray();

        List<List<String>> subSets =
                IntStream.range(0, indexes.length - 1)
                        .mapToObj(i -> data.subList(indexes[i] + 1, indexes[i + 1]))
                        .toList();


        List<Number[][]> boards = new ArrayList<>();
        for (List<String> list : subSets) {
            Number[][] board = list.stream()
                    .map(s -> Arrays.stream(s.split("\\s"))
                                .filter(Predicate.not(String::isBlank))
                                .map(String::trim)
                                .mapToInt(Integer::parseInt)
                                .mapToObj(Number::new)
                                .toArray(Number[]::new)
                    ).toArray(Number[][]::new);
            boards.add(board);
        }
        return boards;
    }

    private long getFirstWinningBoard(int[] numbers, List<Number[][]> boards) {

        for (int input : numbers) {
            for (int i = 0; i < boards.size(); i++) {
                Number[][] board = boards.get(i);
                for (Number[] line : board) {
                    for (Number boardNumber : line) {
                        if (boardNumber.getNumber() == input){
                            boardNumber.setMarked(true);
                        }
                        if (checkWinCondition(board)) {
                            return getScore(input, board);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private long getLastWinningBoard(int[] numbers, List<Number[][]> boards) {

        int lastWinInput = 0;
        Number[][] lastWinBoard = new Number[0][];


        for (int input : numbers) {
            List<Number[][]> winningBoards = new ArrayList<>();
            for (int i = 0; i < boards.size(); i++) {
                Number[][] board = boards.get(i);
                for (Number[] line : board) {
                    for (Number boardNumber : line) {
                        if (boardNumber.getNumber() == input) {
                            boardNumber.setMarked(true);
                        }
                    }
                }
                if (checkWinCondition(board)) {
                    lastWinInput = input; 
                    lastWinBoard = board; 
                    winningBoards.add(board);
                }
            }
            if (!winningBoards.isEmpty() ){
                    boards.removeAll(winningBoards);
            }
        }
        return getScore(lastWinInput, lastWinBoard);
    }

    private boolean checkWinCondition(Number[][] board) {

        boolean lineComplete = true;
        boolean columnComplete = true;

        for (int i = 0; i < board.length; i++) {
            lineComplete = true;
            columnComplete = true;
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isMarked()) {
                    lineComplete = false;
                }
                if (!board[j][i].isMarked()) {
                    columnComplete = false;
                }
            }
            if (lineComplete || columnComplete) {
                return true;
            }
        }
        return lineComplete || columnComplete;
    }

    public class Number extends Object{

        private int number = 0;
        private boolean marked = false;

        public Number(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked(boolean marked) {
            this.marked = marked;
        }
    }
}
