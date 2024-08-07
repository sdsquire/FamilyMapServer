# FamilyMap Server

**Family Map** is an application that displays your family history across the globe. By inputting your family tree, you can view where each member of your family was born, married, died, and other major life events on an interactive map.

<img src="FamilyMapDemo.png" alt="Demo of the FamilyMap App" width=400>

This repository contains the backend server code, which uses Java and SQL to manage user information and family history data. The client-side application is available in the FamilyMapClient repository.

## Features

- User registration and login
- Family history data generation and storage
- RESTful API endpoints for data retrieval

## Getting Started

### Prerequisites

- Java Runtime Environment (JRE) 8 or higher

### Running the Server

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/family-map-server.git
   cd family-map-server
   ```

2. **Run the Server:**

   Execute the JAR file with the following command, specifying the desired port:

   ```bash
   java -jar target/FamilyMapServer-1.0-SNAPSHOT.jar [PORT]
   ```

   Replace `[PORT]` with the port number you want the server to run on (e.g., 8080).

### API Endpoints

#### User Registration

- **URL:** `/user/register`
- **Method:** `POST`
- **Description:** Creates a new user account and generates family history data.
- **Request Body:**
  ```json
  {
    "userName": "susan",
    "password": "mysecret",
    "email": "susan@gmail.com",
    "firstName": "Susan",
    "lastName": "Ellis",
    "gender": "f"
  }
  ```
- **Success Response:**
  ```json
  {
    "authToken": "cf7a368f",
    "userName": "susan",
    "personID": "39f9fe46"
  }
  ```

#### User Login

- **URL:** `/user/login`
- **Method:** `POST`
- **Description:** Logs in the user and returns an auth token.
- **Request Body:**
  ```json
  {
    "userName": "susan",
    "password": "mysecret"
  }
  ```
- **Success Response:**
  ```json
  {
    "authToken": "cf7a368f",
    "userName": "susan",
    "personID": "39f9fe46"
  }
  ```

#### Clear Data

- **URL:** `/clear`
- **Method:** `POST`
- **Description:** Deletes all data from the database.
- **Success Response:**
  ```json
  {
    "message": "Clear succeeded."
  }
  ```

#### Fill Data

- **URL:** `/fill/[username]/{generations}`
- **Method:** `POST`
- **Description:** Generates family history data for the specified user.
- **Success Response:**
  ```json
  {
    "message": "Successfully added X persons and Y events to the database."
  }
  ```

### Running Tests

To run the automated unit tests:

```bash
mvn test
```

### License

This project is licensed under the MIT License - see the LICENSE.md file for details.

---

Feel free to provide any further modifications or details you'd like to include!