# com.xy.tools.hotelreservation (Spring boot rest microservice)

## Launching via STS/Eclipse

1. Simply import project into STS/Eclipse as maven project
2. Launch application as springboot 
3: Access swagger-ui via browser http://localhost:8082/swagger-ui.html
4: In order to observe in-memory database, check it on http://localhost:8082/h2-console , type database name as hotelreservation

## Launching via command line

1. Navigate to the root directoy of your project
2. Execute command mvn spring-boot:run	
3: Access swagger-ui via browser http://localhost:8082/swagger-ui.html
4: In order to observe in-memory database, check it on http://localhost:8082/h2-console , type database name as hotelreservation

## Abstract View:

1. A database persistent layer is demonstrated in a code via implementing persistence layer using in-memory database
2. Spring application file is used for property configuration	
3. Separate validator is used for validating input values
4. Room reservation process is not directly binded to persistence layer in order to avoid SQL queries, as in real world this is not optimal, rather a datastructure is designed in sync with database to do reservation fast without querying database.
	
### Workflow:
1. A data structure is designed using 2d matrix to keep record of reservations and to do new reservations (please see, ReservationHandler.java & BasicReservationHandler.java)
2. 2d matrix is Rooms*Days, each slot is represented as true/false representing true for already reserved and false as not reserved
3. The main algorithm is in the method(BasicReservationHandler.performReservation) that checks existing reservation and either accept or decline request, If request is accepted then its also saved in database
4. Database is always in-sync with reservation data structure and reservation finding algorithm however its never used directly to find reservation rather its only used to keep the reservation track and to simulate data persistence for applicaiton restart scenario.
	
### Tests:
1. TestReservationProcess.java represents all the examples test mentioned in the document	
2. TestReservationProcessDatabaseSimulation.java provides the simulation scneario where data is read first from database and then reservation request was processed.

### Tests via swagger-ui
Access the swagger-ui and do the manual tests via directly calling end points
		
