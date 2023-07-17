import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String user = "player";
        boolean inGame = true;
        Scanner input = new Scanner(System.in);
        int stepCount = 0;
        int[] lockedPosition = new int[9];
        Random random = new Random();

        char[][] gameField = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};

        System.out.println("Game Start!");
        System.out.println("Playing field:");
        printGameField(gameField);

        while (inGame) {
            char symbol = 'X';
            for (int i = 0; i < 2; i++) {
                if(
                        gameField[0][0] == symbol && gameField[0][1] == symbol && gameField[0][2] == symbol ||
                        gameField[1][0] == symbol && gameField[1][1] == symbol && gameField[1][2] == symbol ||
                        gameField[2][0] == symbol && gameField[2][1] == symbol && gameField[2][2] == symbol ||

                        gameField[0][0] == symbol && gameField[1][0] == symbol && gameField[2][0] == symbol ||
                        gameField[0][1] == symbol && gameField[1][1] == symbol && gameField[2][1] == symbol ||
                        gameField[0][2] == symbol && gameField[1][2] == symbol && gameField[2][2] == symbol ||

                        gameField[0][0] == symbol && gameField[1][1] == symbol && gameField[2][2] == symbol ||
                        gameField[0][2] == symbol && gameField[1][1] == symbol && gameField[2][0] == symbol
                ){
                    gameOver(symbol);
                    inGame = false;
                    break;
                }
                symbol = 'O';
            }

            if(!inGame){
                break;
            }

            if(stepCount == 9) {
                inGame = false;
                gameOver('|');
            }

            int position;
            if (user.equals("player")) {
                System.out.println("Select position(1-9): ");
                position = input.nextInt();
            } else {
                position = random.nextInt(9) + 1;
            }
            if (Arrays.stream(lockedPosition).boxed().collect(Collectors.toSet()).contains(position)) {
                if (user.equals("player")) {
                    System.out.println("It`s position is lock\nTry again");
                }
                continue;
            }

            if(setPosition(gameField, position, user)){
                lockedPosition[stepCount] = position;
                stepCount++;
                if (user.equals("player")) user = "cpu";
                else if (user.equals("cpu")) user = "player";
            }
            printGameField(gameField);

        }

    }

    private static void printGameField(char[][] gameField) {
        System.out.println();
        for (char[] row : gameField) {
            for (char element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean setPosition(char[][] gameField, int position, String user) {
        char symbol = 'X';
        if (user.equals("player")) {
            symbol = 'X';
        } else if (user.equals("cpu")) {
            symbol = 'O';
        }

        switch (position) {
            case 1 -> {
                gameField[0][0] = symbol;
                return true;
            }
            case 2 -> {
                gameField[0][1] = symbol;
                return true;
            }
            case 3 -> {
                gameField[0][2] = symbol;
                return true;
            }
            case 4 -> {
                gameField[1][0] = symbol;
                return true;
            }
            case 5 -> {
                gameField[1][1] = symbol;
                return true;
            }
            case 6 -> {
                gameField[1][2] = symbol;
                return true;
            }
            case 7 -> {
                gameField[2][0] = symbol;
                return true;
            }
            case 8 -> {
                gameField[2][1] = symbol;
                return true;
            }
            case 9 -> {
                gameField[2][2] = symbol;
                return true;
            }
            default -> {
                System.out.println("It`s wrong position\nTry again");
                return false;
            }
        }
    }

    private static void gameOver(char winner){
        System.out.println("Game over!\nResults:");
        if(winner == '|') {
            System.out.println("Nobody won");
        }
        else {
            if(winner == 'X'){
                System.out.println("You win!");
            }
            else{
                System.out.println("Cpu won!");
            }
        }
    }
}