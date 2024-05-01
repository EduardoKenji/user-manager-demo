The User Manager supports CRUD operations such as create(POST), read(GET), update(PUT) and delete(DELETE). The User Manager operates with a cloud PostgreSQL DB in Amazon's RDS.

The User Manager writes database changes (from requests) into .txt files inside the /backend/database-changelogs folder. There are currently 3 examples files.

Technologies utilized:

- Java 17
- Maven
- Spring Boot 3.2.5
- Angular 17.3.6
- PostgreSQL
- AWS RDS

The backend and the frontend are separated and they need to be running simultaneously.
To run the backend:
```
cd backend
mvn spring-boot:run
```

To run the frontend:
```
cd frontend
npm install
ng serve
```
