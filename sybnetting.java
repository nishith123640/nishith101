import java.util.Arrays;

public class SubnettingUtils {

    // Convert IP Address from String to Integer Array
    public static int[] convertIpToIntArray(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        int[] ip = new int[4];
        for (int i = 0; i < 4; i++) {
            ip[i] = Integer.parseInt(octets[i]);
        }
        return ip;
    }

    // Convert Integer Array to IP Address String
    public static String convertIntArrayToIp(int[] ipArray) {
        return ipArray[0] + "." + ipArray[1] + "." + ipArray[2] + "." + ipArray[3];
    }

    // Calculate Network Address
    public static int[] calculateNetworkAddress(int[] ipAddress, int[] subnetMask) {
        int[] networkAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            networkAddress[i] = ipAddress[i] & subnetMask[i];
        }
        return networkAddress;
    }

    // Calculate Broadcast Address
    public static int[] calculateBroadcastAddress(int[] networkAddress, int[] subnetMask) {
        int[] broadcastAddress = new int[4];
        for (int i = 0; i < 4; i++) {
            broadcastAddress[i] = networkAddress[i] | (~subnetMask[i] & 0xFF);
        }
        return broadcastAddress;
    }

    // Calculate the Number of Usable Hosts
    public static int calculateUsableHosts(int[] subnetMask) {
        int onesCount = 0;
        for (int i : subnetMask) {
            onesCount += Integer.bitCount(i);
        }
        int totalHosts = (int) Math.pow(2, 32 - onesCount);
        return totalHosts - 2; // Subtract network and broadcast addresses
    }

    // Convert CIDR notation to Subnet Mask
    public static int[] convertCidrToSubnetMask(int cidr) {
        int[] subnetMask = new int[4];
        int fullOnes = cidr / 8;
        int remainder = cidr % 8;

        Arrays.fill(subnetMask, 0);
        for (int i = 0; i < fullOnes; i++) {
            subnetMask[i] = 255;
        }

        if (remainder > 0) {
            subnetMask[fullOnes] = (int) (256 - Math.pow(2, 8 - remainder));
        }

        return subnetMask;
    }
}

import java.util.Scanner;

public class SubnettingMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input IP address and CIDR notation
        System.out.print("Enter IP address (e.g., 192.168.1.10): ");
        String ipAddress = scanner.nextLine();

        System.out.print("Enter CIDR notation (e.g., 24): ");
        int cidr = scanner.nextInt();

        // Convert IP address to integer array
        int[] ipArray = SubnettingUtils.convertIpToIntArray(ipAddress);

        // Convert CIDR to subnet mask
        int[] subnetMask = SubnettingUtils.convertCidrToSubnetMask(cidr);

        // Calculate network address
        int[] networkAddress = SubnettingUtils.calculateNetworkAddress(ipArray, subnetMask);

        // Calculate broadcast address
        int[] broadcastAddress = SubnettingUtils.calculateBroadcastAddress(networkAddress, subnetMask);

        // Calculate usable hosts
        int usableHosts = SubnettingUtils.calculateUsableHosts(subnetMask);

        // Calculate first and last usable IP addresses
        int[] firstUsableIp = Arrays.copyOf(networkAddress, networkAddress.length);
        firstUsableIp[3] += 1; // The first usable IP is network address + 1

        int[] lastUsableIp = Arrays.copyOf(broadcastAddress, broadcastAddress.length);
        lastUsableIp[3] -= 1; // The last usable IP is broadcast address - 1

        // Output results
        System.out.println("Subnet Mask: " + SubnettingUtils.convertIntArrayToIp(subnetMask));
        System.out.println("Network Address: " + SubnettingUtils.convertIntArrayToIp(networkAddress));
        System.out.println("Broadcast Address: " + SubnettingUtils.convertIntArrayToIp(broadcastAddress));
        System.out.println("Usable Hosts: " + usableHosts);
        System.out.println("First Usable IP: " + SubnettingUtils.convertIntArrayToIp(firstUsableIp));
        System.out.println("Last Usable IP: " + SubnettingUtils.convertIntArrayToIp(lastUsableIp));

        scanner.close();
    }
}

