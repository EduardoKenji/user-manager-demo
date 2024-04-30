The User Manager was made in Java 17 (Spring)/Maven and it supports CRUD operations such as create(POST), read(GET), update(PUT) and delete(DELETE). The User Manager operates with a cloud PostgreSQL DB in Amazon's RDS.

The User Manager writes database changes (from requests) into .txt files inside the /database-changelogs folder. There are currently 3 examples files.

