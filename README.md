# Student Management System (CRUD Application)

A modern, full-stack Student Management System built with **Spring Boot** and **Vanilla JavaScript**. This application provides a seamless interface to Create, Read, Update, and Delete (CRUD) student records efficiently.

## 🚀 Features
- **Add New Students**: Easily add students with their Name, Age, and Department.
- **View All Students**: Displays a real-time list of all registered students in a dynamic table.
- **Update Records**: Edit existing student details instantly.
- **Delete Records**: Remove student records safely from the database.
- **Responsive UI**: A beautiful, glassmorphism-styled frontend built with modern HTML/CSS.

## 🛠️ Technical Stack
- **Backend Framework**: Spring Boot 3.2.6
- **Language**: Java 23
- **Database**: H2 Database (File-based local storage)
- **Data Access**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Frontend**: HTML5, CSS3, Vanilla JavaScript (Fetch API)
- **Validation**: Spring Boot Starter Validation

## 📁 Project Structure
- `src/main/java/.../Controller/` - Contains REST API endpoints (`StudentController`).
- `src/main/java/.../Service/` - Business logic and mapping (`StudentService`).
- `src/main/java/.../Repository/` - JPA Interfaces for database access (`StudentRepository`).
- `src/main/java/.../Entity/` - Database models (`Student`).
- `src/main/java/.../DTO/` - Data Transfer Objects for API requests and responses.
- `src/main/resources/static/` - Contains the Frontend files (`index.html`, `style.css`, `app.js`).
- `data/` - Contains the physical H2 database storage files.

## 🔗 API Endpoints
| HTTP Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/students/create` | Create a new student |
| `GET` | `/api/students/getAll` | Fetch all students |
| `GET` | `/api/students/getStudent/{id}` | Fetch a student by ID |
| `PUT` | `/api/students/updateStudent/{id}` | Update an existing student |
| `DELETE` | `/api/students/deleteStudent/{id}` | Delete a student by ID |

## 💻 How to Run the Project

### Prerequisites
1. **Java Development Kit (JDK) 23** must be installed and properly configured in your system `PATH`.
2. **Maven** must be installed (Alternatively, use the provided `mvnw` wrapper).
3. **VS Code** with the Extension Pack for Java installed.

### Running Backend (Spring Boot)
1. Open the project folder in **VS Code**.
2. Open a new terminal (`Ctrl + ~`).
3. Run the application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Alternatively, you can open `src/main/java/.../DemoApplication.java` and click the **Run** button provided by the VS Code Java extension.
5. The server will start on `http://localhost:8080`.

### Accessing the Frontend
Once the Spring Boot server is running, the frontend is automatically served as static content!
1. Open your web browser.
2. Go to: **[http://localhost:8080](http://localhost:8080)**
3. You will see the Student Management System UI and you can start interacting with the API immediately.

## 🛠️ Troubleshooting & Technical Notes
- **VS Code Errors**: Ensure your Global VS Code User Settings (`settings.json`) points `java.jdt.ls.java.home` to your JDK 23 installation path. 
- **Database**: We are using H2 Database file-storage. If you want to reset the database, simply delete the files inside the `data/` folder and restart the application; a fresh database will be created.
- **Port Conflicts**: If port 8080 is already in use, you can change the server port by adding `server.port=8081` (or another port) in `src/main/resources/application.properties`.
