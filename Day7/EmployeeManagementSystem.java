package Day7;

import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sql24";
    private static Connection connection = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            initializeDatabase();

            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addEmployee(scanner);
                    case 2 -> viewAllEmployees();
                    case 3 -> viewEmployeeById(scanner);
                    case 4 -> updateEmployee(scanner);
                    case 5 -> deleteEmployee(scanner);
                    case 6 -> viewEmployeesByDepartment(scanner);
                    case 7 -> {
                        System.out.println("Exiting application...");
                        closeConnection();
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void initializeDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");
            createTable();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
            throw new SQLException("Driver not found");
        }
    }

    private static void createTable() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS employees (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL,
                phone VARCHAR(15),
                department VARCHAR(50) NOT NULL,
                position VARCHAR(50) NOT NULL,
                salary DECIMAL(10,2) NOT NULL,
                hire_date DATE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        try (PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
            System.out.println("Employees table created/verified successfully!");
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Employee Management System ===");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. View Employee by ID");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. View Employees by Department");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee(Scanner scanner) {
        System.out.println("\n=== Add New Employee ===");

        try {
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();

            System.out.print("Enter employee email: ");
            String email = scanner.nextLine();

            System.out.print("Enter employee phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            System.out.print("Enter position: ");
            String position = scanner.nextLine();

            System.out.print("Enter salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter hire date (YYYY-MM-DD): ");
            String hireDate = scanner.nextLine();

            String insertSQL = "INSERT INTO employees (name, email, phone, department, position, salary, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(insertSQL)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setString(4, department);
                stmt.setString(5, position);
                stmt.setDouble(6, salary);
                stmt.setDate(7, Date.valueOf(hireDate));

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Employee added successfully!");
                } else {
                    System.out.println("Failed to add employee.");
                }
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.err.println("Error: Email already exists!");
            } else {
                System.err.println("Database error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    // ✅ UPDATED: View All Employees
    private static void viewAllEmployees() {
        System.out.println("\n=== All Employees ===");

        String selectSQL = "SELECT * FROM employees ORDER BY id";

        try (PreparedStatement stmt = connection.prepareStatement(selectSQL);
             ResultSet rs = stmt.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                if (!found) {
                    System.out.printf("%-5s %-20s %-25s %-15s %-15s %-20s %-12s %-12s%n",
                            "ID", "Name", "Email", "Phone", "Department", "Position", "Salary", "Hire Date");
                    System.out.println("-".repeat(120));
                    found = true;
                }

                System.out.printf("%-5d %-20s %-25s %-15s %-15s %-20s %-12.2f %-12s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("department"),
                        rs.getString("position"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date"));
            }

            if (!found) {
                System.out.println("No employees found.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void viewEmployeeById(Scanner scanner) {
        System.out.println("\n=== View Employee by ID ===");

        try {
            System.out.print("Enter employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String selectSQL = "SELECT * FROM employees WHERE id = ?";

            try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("\nEmployee Details:");
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Phone: " + rs.getString("phone"));
                        System.out.println("Department: " + rs.getString("department"));
                        System.out.println("Position: " + rs.getString("position"));
                        System.out.println("Salary: $" + rs.getDouble("salary"));
                        System.out.println("Hire Date: " + rs.getDate("hire_date"));
                        System.out.println("Created At: " + rs.getTimestamp("created_at"));
                    } else {
                        System.out.println("Employee with ID " + id + " not found.");
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.println("\n=== Update Employee ===");

        try {
            System.out.print("Enter employee ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (!employeeExists(id)) {
                System.out.println("Employee with ID " + id + " not found.");
                return;
            }

            System.out.print("Enter new name (or press Enter to keep current): ");
            String name = scanner.nextLine();

            System.out.print("Enter new email (or press Enter to keep current): ");
            String email = scanner.nextLine();

            System.out.print("Enter new phone (or press Enter to keep current): ");
            String phone = scanner.nextLine();

            System.out.print("Enter new department (or press Enter to keep current): ");
            String department = scanner.nextLine();

            System.out.print("Enter new position (or press Enter to keep current): ");
            String position = scanner.nextLine();

            System.out.print("Enter new salary (or -1 to keep current): ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter new hire date (YYYY-MM-DD) (or press Enter to keep current): ");
            String hireDate = scanner.nextLine();

            StringBuilder updateSQL = new StringBuilder("UPDATE employees SET ");
            boolean hasUpdates = false;

            if (!name.trim().isEmpty()) {
                updateSQL.append("name = ?, ");
                hasUpdates = true;
            }
            if (!email.trim().isEmpty()) {
                updateSQL.append("email = ?, ");
                hasUpdates = true;
            }
            if (!phone.trim().isEmpty()) {
                updateSQL.append("phone = ?, ");
                hasUpdates = true;
            }
            if (!department.trim().isEmpty()) {
                updateSQL.append("department = ?, ");
                hasUpdates = true;
            }
            if (!position.trim().isEmpty()) {
                updateSQL.append("position = ?, ");
                hasUpdates = true;
            }
            if (salary != -1) {
                updateSQL.append("salary = ?, ");
                hasUpdates = true;
            }
            if (!hireDate.trim().isEmpty()) {
                updateSQL.append("hire_date = ?, ");
                hasUpdates = true;
            }

            if (!hasUpdates) {
                System.out.println("No updates provided.");
                return;
            }

            updateSQL.setLength(updateSQL.length() - 2);
            updateSQL.append(" WHERE id = ?");

            try (PreparedStatement stmt = connection.prepareStatement(updateSQL.toString())) {
                int paramIndex = 1;

                if (!name.trim().isEmpty()) stmt.setString(paramIndex++, name);
                if (!email.trim().isEmpty()) stmt.setString(paramIndex++, email);
                if (!phone.trim().isEmpty()) stmt.setString(paramIndex++, phone);
                if (!department.trim().isEmpty()) stmt.setString(paramIndex++, department);
                if (!position.trim().isEmpty()) stmt.setString(paramIndex++, position);
                if (salary != -1) stmt.setDouble(paramIndex++, salary);
                if (!hireDate.trim().isEmpty()) stmt.setDate(paramIndex++, Date.valueOf(hireDate));

                stmt.setInt(paramIndex, id);

                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected > 0 ? "Employee updated successfully!" : "Failed to update employee.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.err.println("Error: Email already exists!");
            } else {
                System.err.println("Database error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.println("\n=== Delete Employee ===");

        try {
            System.out.print("Enter employee ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (!employeeExists(id)) {
                System.out.println("Employee with ID " + id + " not found.");
                return;
            }

            viewEmployeeById(new Scanner(String.valueOf(id)));

            System.out.print("Are you sure you want to delete this employee? (y/n): ");
            String confirm = scanner.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Deletion cancelled.");
                return;
            }

            String deleteSQL = "DELETE FROM employees WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(deleteSQL)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected > 0 ? "Employee deleted successfully!" : "Failed to delete employee.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    // ✅ UPDATED: View Employees by Department
    private static void viewEmployeesByDepartment(Scanner scanner) {
        System.out.println("\n=== View Employees by Department ===");

        try {
            System.out.print("Enter department name: ");
            String department = scanner.nextLine();

            String selectSQL = "SELECT * FROM employees WHERE department = ? ORDER BY name";

            try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
                stmt.setString(1, department);

                try (ResultSet rs = stmt.executeQuery()) {
                    boolean found = false;

                    while (rs.next()) {
                        if (!found) {
                            System.out.printf("%-5s %-20s %-25s %-15s %-20s %-12s %-12s%n",
                                    "ID", "Name", "Email", "Phone", "Position", "Salary", "Hire Date");
                            System.out.println("-".repeat(105));
                            found = true;
                        }

                        System.out.printf("%-5d %-20s %-25s %-15s %-20s %-12.2f %-12s%n",
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("phone"),
                                rs.getString("position"),
                                rs.getDouble("salary"),
                                rs.getDate("hire_date"));
                    }

                    if (!found) {
                        System.out.println("No employees found in " + department + " department.");
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

    private static boolean employeeExists(int id) throws SQLException {
        String selectSQL = "SELECT COUNT(*) FROM employees WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
