# Card Service

### Tech Stack
 * Spring boot - 2.5.8
 * H2 in memory database
 * Junit/Mockito/Postman for testing.           



### Running the application

Please follow below steps to run the service:
- Clone git repo https://github.com/suresh1234/CardService

- Once cloned, go inside folder CardService and run ./gradlew clean build


   ![alt text](https://github.com/suresh1234/CardService/blob/main/img/building.png?raw=true)

- Go to build/libs folder and run java -jar cardservice-1.0.0.jar, this will  start the application at 8080 port.

   ![alt text](https://github.com/suresh1234/CardService/blob/main/img/runningTheservice.png?raw=true)
   
- Once application is up,import postman collection from location  CardService/cardService.postman_collection.json.

- Postman collection contains one GET and one POST request for testing the application:
 
  

![alt text](https://github.com/suresh1234/CardService/blob/main/img/postManCollection.png?raw=true)






