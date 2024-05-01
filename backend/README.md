# Instructions - How to create a PostgreSQL database instance in Amazon RDS and setup the env.properties file:

1) Log into the AWS console, navigate to the "RDS" => Databases section and click on the "Create Database" button

<img src='./../github-images/Amazon RDS Create Database Location.png' alt='Frontend Example' width=100%>

2) While creating the database instance, write down the "Master Username" and "Master Password" as they will be needed to access the database in the User Manager. These fields will eventually be added to the env.properties file:

<img src='./../github-images/Amazon RDS Username Password Location.png' alt='Frontend Example' width=85%>

3) While creating the database instance, define the initial database name to be "users" as it will become the default User database table:

<img src='./../github-images/Amazon RDS Initial Database Name.png' alt='Frontend Example' width=80%>

4) After creating the database instance, write down the "Endpoint" as it will be needed to access the database in the User Manager. This field will eventually be added to the env.properties file:

<img src='./../github-images/Amazon RDS Endpoint Location.png' alt='Frontend Example' width=75%>

5) Create an env.properties file on the "/backend" folder with this format:

```
RDS_DB_ENDPOINT=<endpoint>
RDS_DB_USER=<master username>
RDS_DB_PASSWORD=<master password>
```

6) The "/backend" folder should now have the env.properties file:

<img src='./../github-images/env properties file location.png' alt='Frontend Example' width=65%>

7) The application.properties (/backend/src/main/resources/application.properties) file will automatically include the information from env.properties:

<img src='./../github-images/application properties.png' alt='Frontend Example' width=60%> 
