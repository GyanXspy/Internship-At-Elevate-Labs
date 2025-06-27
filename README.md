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