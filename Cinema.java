package Main;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);
    private static char[][] cinema;
    private static int purchasedTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }

        totalIncome = calculateTotalIncome(rows, seats);

        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket(rows, seats);
                    break;
                case 3:
                    showStatistics(rows, seats);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to display the current seating arrangement
    public static void showSeats() {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to buy a ticket
    public static void buyTicket(int rows, int seats) {
        int row, seat;
        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt() - 1;
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt() - 1;

            // Check if input is valid
            if (row < 0 || row >= rows || seat < 0 || seat >= seats) {
                System.out.println("Wrong input!");
                continue;
            }

            // Check if seat is already taken
            if (cinema[row][seat] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        }

        // Calculate ticket price
        int ticketPrice = calculateTicketPrice(row, rows);
        System.out.println("Ticket price: $" + ticketPrice);

        // Mark the seat as taken and update income
        cinema[row][seat] = 'B';
        purchasedTickets++;
        currentIncome += ticketPrice;
    }

    // Method to calculate the price of a ticket
    public static int calculateTicketPrice(int row, int rows) {
        if (rows * cinema[0].length <= 60) {
            return 10;
        } else {
            int frontHalfRows = rows / 2;
            return (row < frontHalfRows) ? 10 : 8;
        }
    }

    // Method to show statistics
    public static void showStatistics(int rows, int seats) {
        int totalSeats = rows * seats;
        double percentage = (double) purchasedTickets / totalSeats * 100;
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    // Method to calculate total income
    public static int calculateTotalIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            int frontHalfRows = rows / 2;
            int backHalfRows = rows - frontHalfRows;
            return (frontHalfRows * seats * 10) + (backHalfRows * seats * 8);
        }

    }


}




