public class crc {

    // Method to compute the CRC checksum
    public static String calculateCRC(String data, String polynomial) {
        // Append zeros equal to the length of the polynomial minus one
        int polyLength = polynomial.length();
        String dividend = data + "0".repeat(polyLength - 1);

        // Perform the division (XOR operations)
        String remainder = divide(dividend, polynomial);

        // The remainder is the CRC checksum
        return remainder;
    }

    // Division method using XOR
    private static String divide(String dividend, String divisor) {
        int divisorLength = divisor.length();
        String temp = dividend.substring(0, divisorLength);

        for (int i = divisorLength; i <= dividend.length(); i++) {
            // Perform XOR if the leftmost bit is 1
            if (temp.charAt(0) == '1') {
                temp = xor(temp, divisor);
            } else {
                temp = xor(temp, "0".repeat(divisorLength));
            }

            // Append the next bit from the dividend
            if (i < dividend.length()) {
                temp += dividend.charAt(i);
            }

            // Remove the leftmost bit
            temp = temp.substring(1);
        }

        return temp;
    }

    // XOR operation
    private static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }

        return result.toString();
    }

    // Verify the CRC by appending it to the data and rechecking
    public static boolean verifyCRC(String data, String polynomial, String crc) {
        String check = data + crc;
        String remainder = divide(check, polynomial);
        return remainder.equals("0".repeat(polynomial.length() - 1));
    }

    public static void main(String[] args) {
        // Example data and polynomial
        String data = "1101101010011sss"; // Input data
        String polynomial = "1011";     // Polynomial

        // Calculate the CRC checksum
        String crc = calculateCRC(data, polynomial);
        System.out.println("Data: " + data);
        System.out.println("Polynomial: " + polynomial);
        System.out.println("CRC Checksum: " + crc);

        // Verify the CRC
        boolean isValid = verifyCRC(data, polynomial, crc);
        System.out.println("CRC Verification: " + (isValid ? "Valid" : "Invalid"));
    }
}

    

