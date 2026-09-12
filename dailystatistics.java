public class DailyStatistics {

    public static void main(String[] args) {

    
        int[] serviceTimes = {12, 5, 8, 4, 15, 7, 20, 9, 3, 11};

        int totalStudentsServed = serviceTimes.length;

        int totalServiceTime = 0;
        int highestServiceTime = serviceTimes[0]; 
        int lowestServiceTime = serviceTimes[0];  
        int countLongerThan10 = 0;

        for (int i = 0; i < serviceTimes.length; i++) {

            totalServiceTime = totalServiceTime + serviceTimes[i];

            if (serviceTimes[i] > highestServiceTime) {
                highestServiceTime = serviceTimes[i];
            }

            if (serviceTimes[i] < lowestServiceTime) {
                lowestServiceTime = serviceTimes[i];
            }

            if (serviceTimes[i] > 10) {
                countLongerThan10 = countLongerThan10 + 1;
            }
        }

        double averageServiceTime = (double) totalServiceTime / totalStudentsServed;

        System.out.println("========================================");
        System.out.println(" DAILY STATISTICS - SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("Total students served: " + totalStudentsServed);
        System.out.println("Total service time: " + totalServiceTime + " minutes");
        System.out.println("Average service time: " + averageServiceTime + " minutes");
        System.out.println("Highest service time: " + highestServiceTime + " minutes");
        System.out.println("Lowest service time: " + lowestServiceTime + " minutes");
        System.out.println("Number of services longer than 10 minutes: " + countLongerThan10);
    }
}