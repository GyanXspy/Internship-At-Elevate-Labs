# Internship-At-Elevate-Labs

# Day1
What I Did
Developed a Java-based console calculator for basic arithmetic operations.

Implemented:

    Addition

    Subtraction

    Multiplication

    Division (with zero-division check)

Used methods for each operation to promote clean code and reusability.

Created a menu-driven interface using Scanner for user input.

Ensured error handling for invalid inputs (e.g., division by zero).

# Day2
What I Did
    Created a Student Management System in Java using a menu-driven console application.

    Used OOP concepts: encapsulation, constructors, getters/setters, and toString().

    Implemented CRUD operations:

        Add: Input student details and add to ArrayList.

        Display: Show all student records.

        Remove: Delete student by ID.

        Update: Modify name and marks by ID.

    Used Java Scanner for user input and ArrayList for data storage (in-memory).

# Day3
What I Did
    Created a Library Management System in Java using a menu-driven console-style structure.
    Applied OOP concepts: abstraction, inheritance, polymorphism, constructors, and method overriding.
    Implemented core features:

        Add: Add new books to the library.

        Issue: Issue a book to a user by ISBN.

        Return: Return a previously issued book.

        Display: Show all book details (title, author, ISBN, issued status).

    Used Java ArrayList to store books.

# Day 4
    The program uses Java to demonstrate basic file handling with reading and writing.

    It starts by creating a Scanner object to take user input from the console.

    A FileWriter object is created in append mode, so new data is added without deleting existing content in the file D:\FileHandlingInJavaEX\WriteFile.txt.

    Inside a try-catch-finally block:

        It prompts the user to enter some text.

        The entered text is written to the file using FileWriter.write().

        If any error occurs, it's caught and printed.

        The FileWriter is closed in the finally block to save changes and release resources.

        After writing, a FileReader is used to open and read the file.

    Inside another try-catch-finally block:

        It reads each character from the file one by one using a loop.

        The characters are printed to the console.

        Any reading error is caught and printed.

        The FileReader is closed in the finally block.

    Finally, the Scanner object is closed to free system resources.

    The program ensures proper exception handling and resource management for file operations.

# Day5

This Java program simulates a simple bank account system.

    What it does:

        It creates an Account class that maintains a balance and a list of transaction history.

        The class has methods to deposit money, withdraw money, and check the current balance.

        All transactions (deposit and withdraw) are recorded in the transaction history list.

        The program starts by depositing ₹6000, withdrawing ₹200, and then depositing ₹150.

        It prints the current balance and the entire transaction history.

    Key features:

        Uses ArrayList to store transaction history.

        Applies basic validation (e.g., no withdrawal if balance is insufficient).

        Demonstrates object-oriented programming concepts like encapsulation.

        All data members are private and accessed through public methods.
    
    Output:
        Current Balance: 5950.0
        Transaction History:
        Deposited: 6000.0
        Withdrew: 200.0
        Deposited: 150.0

# Day 6
Created a GUI-based To-Do List application using Java Swing.

    Used JFrame, JList, JTextField, JButton, and layout managers for the interface.

    Implemented:

        Add task functionality using an input field and button.

        Delete selected task functionality from the list.

    Used DefaultListModel for dynamic task management.

    Organized layout using BorderLayout and JPanel.

# Day 7
Employee Management System (Java + JDBC + MySQL)
    Built a menu-driven CRUD application for managing employee records.

    Connected Java to MySQL database using JDBC.

    Created and managed employees table with fields like name, email, department, salary, etc.

    Implemented:

        Add, View (All / By ID / By Department), Update, and Delete employee records.

    Used PreparedStatement, ResultSet, and try-with-resources for secure and efficient DB access.

    Replaced rs.beforeFirst() with a boolean found flag to avoid cursor issues.

    Handled SQL exceptions, duplicate entries, and invalid inputs gracefully.

# Day8
QuizApp - Java Console Quiz Application
    This is a simple Java console-based quiz application. It presents a set of multiple-choice questions to the user, accepts their input, and calculates the final score.

    Features:
        Displays questions with 4 answer options.

        Accepts user input and checks answers.

        Calculates and displays the final score.

    Built using Java Collections, OOP concepts and Scanner for input.