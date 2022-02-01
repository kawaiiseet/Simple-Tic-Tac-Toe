package TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String newCells = "_________";
        StringBuilder cells = new StringBuilder();
        cells.append(newCells);
        printCells(cells);

        gameProcess(cells);

    }

    static void gameProcess(StringBuilder cells) {
        int turns = 0;
        int index;
        char playerChar;
        boolean running = true;
        final int maxNumberOfTurns = 9;
        while (running) {
            index = userInput(cells);
            turns++;
            if (turns % 2 == 0) {
                playerChar = 'O';
            } else {
                playerChar = 'X';
            }
            cells.setCharAt(index, playerChar);
            printCells(cells);
            running = checkTheResult(cells, turns, maxNumberOfTurns);
        }
    }

    static boolean checkTheResult(StringBuilder cells, Integer turns, int maxNumberOfTurns) {
        for (int i = 0; i < 7; i++) {
            StringBuilder str = new StringBuilder();
            switch (i) {
                case 0:
                    str.append(cells.charAt(0)).append(cells.charAt(1)).append(cells.charAt(2));
                    break;
                case 1:
                    str.append(cells.charAt(0)).append(cells.charAt(3)).append(cells.charAt(6));
                    break;
                case 2:
                    str.append(cells.charAt(0)).append(cells.charAt(4)).append(cells.charAt(8));
                    break;
                case 3:
                    str.append(cells.charAt(1)).append(cells.charAt(4)).append(cells.charAt(7));
                    break;
                case 4:
                    str.append(cells.charAt(2)).append(cells.charAt(5)).append(cells.charAt(8));
                    break;
                case 5:
                    str.append(cells.charAt(2)).append(cells.charAt(4)).append(cells.charAt(6));
                    break;
                case 6:
                    str.append(cells.charAt(3)).append(cells.charAt(4)).append(cells.charAt(5));
                    break;
                case 7:
                    str.append(cells.charAt(6)).append(cells.charAt(7)).append(cells.charAt(8));
                    break;
            }
            if ("OOO".equals(str.toString())) {
                System.out.println("O wins");
                return false;
            } else if ("XXX".equals(str.toString())) {
                System.out.println("X wins");
                return false;
            }
        }
        if (turns >= maxNumberOfTurns) {
            System.out.println("Draw");
            return false;
        }
        return true;
    }

    static void printCells(StringBuilder cells) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", cells.charAt(0), cells.charAt(1), cells.charAt(2));
        System.out.printf("| %c %c %c |%n", cells.charAt(3), cells.charAt(4), cells.charAt(5));
        System.out.printf("| %c %c %c |%n", cells.charAt(6), cells.charAt(7), cells.charAt(8));
        System.out.println("---------");
    }

    static int userInput(StringBuilder cells) {
        Scanner scanner = new Scanner(System.in);
        int n, m, index;
        try {
            System.out.println("Enter the coordinates: ");
            n = scanner.nextInt();
            m = scanner.nextInt();
            n--;
            m--;
            index = n * 3 + m;
        }
        catch (InputMismatchException ex) {
            System.out.println("You should enter numbers!");
            index = userInput(cells);
        }

        if (index < 0 || index > 8) {
            System.out.println("Coordinates should be from 1 to 3!");
            index = userInput(cells);
        }

        if (cells.charAt(index) != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            index = userInput(cells);
        }
        return index;
    }
}
