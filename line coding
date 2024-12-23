public enum LineCodingTechnique {
    UNIPOLAR,
    POLAR_NRZ,
    POLAR_RZ,
    MANCHESTER,
    DIFFERENTIAL_MANCHESTER
}



public class LineCoding {

    public static String encode(String data, LineCodingTechnique technique) {
        switch (technique) {
            case UNIPOLAR:
                return unipolarEncoding(data);
            case POLAR_NRZ:
                return polarNRZEncoding(data);
            case POLAR_RZ:
                return polarRZEncoding(data);
            case MANCHESTER:
                return manchesterEncoding(data);
            case DIFFERENTIAL_MANCHESTER:
                return differentialManchesterEncoding(data);
            default:
                throw new IllegalArgumentException("Invalid line coding technique");
        }
    }

    private static String unipolarEncoding(String data) {
        // Unipolar Encoding: 1 -> High (1), 0 -> Low (0)
        StringBuilder encoded = new StringBuilder();
        for (char bit : data.toCharArray()) {
            if (bit == '1') {
                encoded.append("1");
            } else {
                encoded.append("0");
            }
        }
        return encoded.toString();
    }

    private static String polarNRZEncoding(String data) {
        // Polar NRZ: 1 -> High (+1), 0 -> Low (-1)
        StringBuilder encoded = new StringBuilder();
        for (char bit : data.toCharArray()) {
            if (bit == '1') {
                encoded.append("+1 ");
            } else {
                encoded.append("-1 ");
            }
        }
        return encoded.toString();
    }

    private static String polarRZEncoding(String data) {
        // Polar RZ: 1 -> High (+1), then 0, 0 -> Low (-1), then 0
        StringBuilder encoded = new StringBuilder();
        for (char bit : data.toCharArray()) {
            if (bit == '1') {
                encoded.append("+1 0 ");
            } else {
                encoded.append("-1 0 ");
            }
        }
        return encoded.toString();
    }

    private static String manchesterEncoding(String data) {
        // Manchester Encoding: 1 -> Low-High (0 1), 0 -> High-Low (1 0)
        StringBuilder encoded = new StringBuilder();
        for (char bit : data.toCharArray()) {
            if (bit == '1') {
                encoded.append("01 ");
            } else {
                encoded.append("10 ");
            }
        }
        return encoded.toString();
    }

    private static String differentialManchesterEncoding(String data) {
        // Differential Manchester Encoding: 1 -> No transition at the beginning, transition at mid, 
        // 0 -> Transition at the beginning, transition at mid
        StringBuilder encoded = new StringBuilder();
        boolean lastLevel = true; // Assume the signal starts with a high level
        for (char bit : data.toCharArray()) {
            if (bit == '1') {
                // No transition at the beginning
                encoded.append(lastLevel ? "1" : "0");
                lastLevel = !lastLevel;
                encoded.append(lastLevel ? "1 " : "0 ");
            } else {
                // Transition at the beginning
                lastLevel = !lastLevel;
                encoded.append(lastLevel ? "1" : "0");
                lastLevel = !lastLevel;
                encoded.append(lastLevel ? "1 " : "0 ");
            }
        }
        return encoded.toString();
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input binary data
        System.out.print("Enter binary data (e.g., 101010): ");
        String data = scanner.nextLine();

        // Display the menu for line coding techniques
        System.out.println("Select Line Coding Technique:");
        System.out.println("1. Unipolar");
        System.out.println("2. Polar NRZ");
        System.out.println("3. Polar RZ");
        System.out.println("4. Manchester");
        System.out.println("5. Differential Manchester");

        int choice = scanner.nextInt();
        LineCodingTechnique technique;

        switch (choice) {
            case 1:
                technique = LineCodingTechnique.UNIPOLAR;
                break;
            case 2:
                technique = LineCodingTechnique.POLAR_NRZ;
                break;
            case 3:
                technique = LineCodingTechnique.POLAR_RZ;
                break;
            case 4:
                technique = LineCodingTechnique.MANCHESTER;
                break;
            case 5:
                technique = LineCodingTechnique.DIFFERENTIAL_MANCHESTER;
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

