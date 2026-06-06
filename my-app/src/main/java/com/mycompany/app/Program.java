package com.mycompany.app;

import java.util.Scanner;

public class Program {
    
    private static final char PLAYER = 'X';
    private static final char AI = 'O';
    private static final char EMPTY = ' ';
    
    private char[][] board;
    private Scanner scanner;
    
    public Program() {
        board = new char[3][3];
        scanner = new Scanner(System.in);
        initBoard();
    }

    public Program(String simulatedInput) {
        board = new char[3][3];
        if (simulatedInput != null) {
            scanner = new Scanner(simulatedInput);
        } else {
            scanner = new Scanner(System.in);
        }
        initBoard();
    }

    public void startForTest() {
        while (true) {
            playerMove();
            if (checkGameOver()) break;
            aiMove();
            if (checkGameOver()) break;
        }
    }
    
    private void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
    
    private void printBoard() {
        System.out.println("\n   1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  ---+---+---");
        }
        System.out.println();
    }
    
    private boolean isMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int evaluate() {
        // Проверка строк
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == AI) return +10;
                if (board[row][0] == PLAYER) return -10;
            }
        }
        
        // Проверка колонок
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == AI) return +10;
                if (board[0][col] == PLAYER) return -10;
            }
        }
        
        // Проверка диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == AI) return +10;
            if (board[0][0] == PLAYER) return -10;
        }
        
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == AI) return +10;
            if (board[0][2] == PLAYER) return -10;
        }
        
        return 0;
    }
    
    private int minimax(int depth, boolean isMax) {
        int score = evaluate();
        
        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        
        if (!isMovesLeft()) return 0;
        
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = AI;
                        best = Math.max(best, minimax(depth + 1, false));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER;
                        best = Math.min(best, minimax(depth + 1, true));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
    }
    
    private void aiMove() {
        int bestVal = -1000;
        int bestRow = -1;
        int bestCol = -1;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = AI;
                    int moveVal = minimax(0, false);
                    board[i][j] = EMPTY;
                    
                    if (moveVal > bestVal) {
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        
        if (bestRow != -1 && bestCol != -1) {
            board[bestRow][bestCol] = AI;
            System.out.println("AI поставил O в клетку " + (bestRow + 1) + "," + (bestCol + 1));
        }
    }
    
    private boolean checkGameOver() {
        int score = evaluate();
        
        if (score == 10) {
            printBoard();
            System.out.println("AI выиграл!");
            return true;
        } else if (score == -10) {
            printBoard();
            System.out.println("Вы выиграли!");
            return true;
        } else if (!isMovesLeft()) {
            printBoard();
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
    
    private void playerMove() {
        int row, col;
        while (true) {
            System.out.print("Введите координаты (строка колонка): ");
            try {
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
                
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
                    board[row][col] = PLAYER;
                    break;
                } else {
                    System.out.println("Некорректный ход! Попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Введите два числа от 1 до 3.");
                scanner.nextLine(); // очистка буфера
            }
        }
    }
    
    public void start() {
        System.out.println("Добро пожаловать в игру Крестики-Нолики!");
        System.out.println("Вы играете за X, AI играет за O");
        System.out.println("Клетки нумеруются от 1 до 3 по строкам и колонкам\n");
        
        while (true) {
            printBoard();
            
            // Ход игрока
            playerMove();
            
            if (checkGameOver()) {
                break;
            }
            
            // Ход AI
            aiMove();
            
            if (checkGameOver()) {
                break;
            }
        }
        
        scanner.close();
    }
}