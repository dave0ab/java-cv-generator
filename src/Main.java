import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CvGenerator cvGenerator = new CvGenerator();
        cvGenerator.generateResume(scanner);
        scanner.close();
    }
}
