# Book Gallery - React Frontend & Spring Boot Backend

This project is a web application built with a **React frontend**, a **Spring Boot backend**, and a **PostgreSQL** database. Users can find books, create book collections, and track their reading progress. Users can assign books statuses such as `Want to Read`, `Reading`, and `Finished Reading` to help organize their reading journey.

## About
BiblioBrowse allows users to:
- **Search for books and collection** by book title or author and using filters.
- **Create and manage book collections**.
- **Assign reading statuses** to books such as `Want to Read`, `Reading`, and `Finished Reading`.
- **View detailed information** about books, authors and their collections.

## Get Started
First, clone this repository.
### Backend Setup
To run this application, you need:
- Java Development Kit (JDK 17 or higher)
- PostgreSQL
- any Java IDE

Navigate to the backend directory called "bibliobrowse-api" and create a database using the provided commands from the file `bibliobrowse-api/src/main/resources/createDB.sql`.
After creating the database, you can use a Java IDE to run the Spring boot application.
### Frontend Setup
Navigate to the frontend directory called "bibliobrowse-app" and install the dependencies listed in the package.json file using the command below.
```bash
npm install
```
Then, run the frontend:
```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```
## Ongoing Project
This project is still under active development, and several features are being worked on to improve functionality and user experience.
