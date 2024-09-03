# Booky - Book Social Network Project



Booky is a social network application designed for book lovers to manage their book collections, borrow and return books, share feedback, and interact with others. The application is built with secure authentication and follows REST API best practices.

Built with Java,SpringBoot,PostgreSQL.

## Features

- **User Registration and Secure Email Validation**
  - Users can register and validate their email securely.
  - Email validation ensures only verified users access the platform.

- **Book Management**
  - **Creation, Updating, Sharing, and Archiving:**
    - Users can create and update book entries in their collection.
    - Books can be shared with other users and archived when no longer needed.

- **Book Borrowing**
  - **Availability Checks:**
    - Users can borrow books from other users.
    - The system checks the availability of the book before borrowing.

- **Book Return Functionality**
  - **Approval of Returns:**
    - Users can return borrowed books.
    - The return process includes an approval step to confirm the book's condition.

- **User Feedback and Reviews**
  - Users can provide feedback and reviews on books they have read.
  - Reviews help others discover great books and share their opinions.

- **Secure Authentication and Authorization**
  - The application uses JWT tokens for secure authentication and authorization.
  - Ensures that all user actions are authenticated and authorized.

- **REST API Best Practices**
  - The application adheres to REST API best practices.
  - Clear and consistent API endpoints for smooth integration and usage.
 





**Class Diagram**

![Class Diagram](https://github.com/youssefgamal123/Booky/blob/main/classdigram.jpg?raw=true)


## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.8.1 or later
- PostgreSQL 12 or later

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/youssefgamal123/Booky.git
   cd Booky
2. **Set up the Database:**

   Create a new database and update the application.properties file with your database credentials:

             spring.datasource.url=jdbc:postgresql://localhost:yourport/databaseName
             spring.datasource.username=your-username
             spring.datasource.password=your-password

3.**Build the project:**
``mvn clean install  ``

4.**Run the application:**
    ``mvn spring-boot:run`` 
         
       



**API Documentation**


    The application uses Springdoc OpenAPI for API documentation.
    Once the application is running, you can access the API documentation at http://localhost:8080/api/v1/swagger-ui.html.



Contributing

We welcome contributions! Please follow these steps:

    Fork the repository.
    Create a new branch (git checkout -b feature/your-feature).
    Commit your changes (git commit -m 'Add some feature').
    Push to the branch (git push origin feature/your-feature).
    Open a pull request.

License

This project is licensed under the MIT License - see the LICENSE file for details.

          
