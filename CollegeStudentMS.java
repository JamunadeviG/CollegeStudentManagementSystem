package CSMS;

import java.sql.*;
import java.util.*;

public class CollegeStudentMS {

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/db1";
    private static final String username = "root";
    private static final String password = "Jamuna@2006";

    private final Scanner scanner = new Scanner(System.in);

    public CollegeStudentMS() {
        System.out.println("College Student Management System Initialized.");
    }

    private void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            String query = "INSERT INTO Students (id, name, department) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, department);
                stmt.executeUpdate();
                System.out.println("Student added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student to database: " + e.getMessage());
        }
    }

    private void addCourse() {
        try {
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine();
            System.out.print("Enter Course Name: ");
            String name = scanner.nextLine();

            String query = "INSERT INTO Courses (code, name) VALUES (?, ?)";
            try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, code);
                stmt.setString(2, name);
                stmt.executeUpdate();
                System.out.println("Course added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding course to database: " + e.getMessage());
        }
    }

    private void addFaculty() {
        try {
            System.out.print("Enter Faculty ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Faculty Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Specialization: ");
            String specialization = scanner.nextLine();

            String query = "INSERT INTO Faculties (id, name, specialization) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, specialization);
                stmt.executeUpdate();
                System.out.println("Faculty added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding faculty to database: " + e.getMessage());
        }
    }

    private void displayStudents() {
        String query = "SELECT * FROM Students";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Students List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                                   ", Department: " + rs.getString("department"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }

    private void displayCourses() {
        String query = "SELECT * FROM Courses";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Courses List:");
            while (rs.next()) {
                System.out.println("Code: " + rs.getString("code") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }
    }

    private void displayFaculties() {
        String query = "SELECT * FROM Faculties";
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Faculties List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                                   ", Specialization: " + rs.getString("specialization"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving faculties: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String query = "DELETE FROM Students WHERE id = ?";
            try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("No student found with the given ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student from database: " + e.getMessage());
        }
    }
    
    private void menu() {
        while (true) {
            System.out.println("\n=== College Student Management System Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Add Faculty");
            System.out.println("4. Display Students");
            System.out.println("5. Display Courses");
            System.out.println("6. Display Faculties");
            System.out.println("7. Delete Student");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> addCourse();
                    case 3 -> addFaculty();
                    case 4 -> displayStudents();
                    case 5 -> displayCourses();
                    case 6 -> displayFaculties();
                    case 7 -> deleteStudent();
                    case 8 -> {
                        System.out.println("Exiting College Student Management System. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        CollegeStudentMS system = new CollegeStudentMS();
        system.menu();
    }
}