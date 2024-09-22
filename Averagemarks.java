
import java.util.Scanner;

public class Averagemarks{

    // Method to calculate grade based on average percentage
    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B+";
        } else if (averagePercentage >= 60) {
            return "B";
        } else if (averagePercentage >= 50) {
            return "C";
        } else {
            return "F"; // Fail
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking input for number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();
        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // Taking input for marks in each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = sc.nextDouble();
            totalMarks += marks[i];
        }

        // Calculate average percentage
        double averagePercentage = totalMarks / numSubjects;

        // Calculate grade
        String grade = calculateGrade(averagePercentage);

        // Display the results
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        sc.close();
    }
}

