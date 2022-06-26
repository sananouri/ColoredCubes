import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of cubes: ");
        int n = scanner.nextInt();
        double weight;
        int c1, c2, c3, c4, c5, c6;
        for (int i = 0; i < n; i++) {
            System.out.println("Cube" + (i + 1));
            System.out.print("Enter weight: ");
            weight = scanner.nextDouble();
            System.out.print("Enter colors: ");
            c1 = scanner.nextInt();
            c2 = scanner.nextInt();
            c3 = scanner.nextInt();
            c4 = scanner.nextInt();
            c5 = scanner.nextInt();
            c6 = scanner.nextInt();
            new Cube(weight, c1, c2, c3, c4, c5, c6);
        }
        /*new Cube(5, 1, 2, 3, 4, 5, 6);
        new Cube(2, 6, 15, 16, 17, 18, 19);
        new Cube(3, 5, 10, 11, 12, 13, 14);
        new Cube(4, 1, 5, 7, 8, 6, 9);
        new Cube(1, 18, 14, 20, 21, 22, 23);*/
        Tower.buildHighestTower();
        FxClass.launchApplication(args);
    }
}










/*new Cube(5, 1, 2, 3, 4, 5, 6);
        new Cube(2, 6, 15, 16, 17, 18, 19);
        new Cube(3, 5, 10, 11, 12, 13, 14);
        new Cube(4, 1, 5, 7, 8, 6, 9);
        new Cube(1, 18, 14, 20, 21, 22, 23);*/
