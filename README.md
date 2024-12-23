# CoderHack Application
**CoderHack** is a Spring Boot application that gamifies user engagement by assigning scores and badges based on their performance. It leverages a MongoDB database to manage users and their achievements effectively.

## Table of Contents
1. [Features](#features)
2. [Tech Stack](#tech-stack)
3. [Setup Instructions](#setup-instructions)
4. [Project Structure](#project-structure)
5. [Example Usage](#example-usage)
6. [Testing](#testing)

## Features
- **CRUD Operations**: Create, Read, Update, and Delete user data.
- **Score Tracking**: Dynamically update user scores with validation.
- **Badge System**: Automatically assign badges based on user performance:
    - `Code Ninja`: Score between 1–30.
    - `Code Champ`: Score between 30–60.
    - `Code Master`: Score above 60.
- **Sorted Leaderboard**: Retrieve all users sorted by scores in descending order.

## Tech Stack
- **Backend**: Spring Boot
- **Database**: MongoDB
- **Testing**: JUnit, Mockito

## Setup Instructions
1. **Clone the Repository**:
```bash
git clone https://github.com/geetanjaligit/CoderHack.git
cd CoderHack
```
2. **Setup MongoDB**:
- Ensure MongoDB is installed and running locally on `localhost:27017`.
- Default database: `Coderhackdb`.
3. **Configure Application Properties**:
- Edit `src/main/resources/application.properties` if needed:
```bash
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=Coderhackdb
spring.data.mongodb.auto-index-creation=true
```
4. **Run the Application**:
```bash
./mvnw spring-boot:run
```
5. **API Endpoints**:
- **Get All Users**: `GET /users`
- **Get User by ID**: `GET /users/id/{userId}`
- **Create User**: `POST /users`
- **Update User Score**: `PUT /users/id/{userId}`
- **Delete User**: `DELETE /users/{userId}`

## Project Structure
- **Controller**: Manages API endpoints.
    - `UserController.java`
- **Service**: Handles business logic.
    - `UserService.java`
    - `UserScoreComparator.java`
- **Repository**: Interfaces with MongoDB.
    - `UserRepository.java`
- **Entity**: Defines the data model.
    - `User.java`
- **Testing**: Ensures reliability with JUnit and Mockito.
    - `UserServiceTest.java`

## Example Usage
### Add a User
```bash
POST /users
{
  "id": "101",
  "username": "John",
  "score": 0,
  "badges": []
}
```
### Update User Score
```bash
PUT /users/id/101
Body: 40
```
**Response**:
```bash
{
  "id": "101",
  "username": "John",
  "score": 40,
  "badges": ["Code Champ"]
}
```

## Testing
Run unit tests with:
```bash
./mvnw test
```
Sample Test Cases:
- Verify badge assignment logic.
- Validate CRUD operations.
- Test for invalid score inputs.




