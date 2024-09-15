package Main;

public class Javaexercises {

    public static void main(String[] args) {

        toMilesPerHour(10);
        System.out.println(toMilesPerHour(15));


    }
    public static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) return -1;
        return Math.round(kilometersPerHour / 1.609);
    }

}
