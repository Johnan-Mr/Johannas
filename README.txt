Hej!
Server:
Det behövs 
* Maven 3.0+, 
* IDE (Jag använder community-IntelliJ på min hemma-burk),
* JDK 1.8+
* MYSQL databas server

Client (Angular):
Det behövs
* Node.js, 
* npm (Node Package Manager)
* Installation av Angular CLI (`npm install -g @angular/cli`)

Skapa en MYSQL databas och editera config i
/src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:(PORT)/[DATABAS_NAMN]?serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username=[SQL_DATABAS_ANVÄNDARNAMN]
spring.datasource.password=[SQL_DATABAS_LÖSENORD]

Databas-schema finns definierad i schema.sql i backend. Har man betal-version
av IntelliJ ska den köra på bygg enligt utsago, men man kan också copy pasta
sql:en och köra den direkt istället.

För att dra igång server: mvn spring-boot:run
För att dra igång client: ng serve
Surfa in på localhost:4200
Klicka på!