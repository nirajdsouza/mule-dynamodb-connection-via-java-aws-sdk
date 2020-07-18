# mule-dynamodb-connection-via-java-aws-sdk

## Description
This is a POC created to check Mulesoft connection to DynamoDB without the use of connectors, by using Java AWS SDK.
This contains basic CRUD operations to connect to an instance of DynamoDB running on the local system and can be modified to connect to an instance of DynamoDB on AWS Cloud.

## Purpose
To check whether Mule can connect to DynamoDB without the use of an in-built connector. It is useful in the scenarios where the connector may not be downloadable like a closed/private network or application is running on Mule 3, which is not supported by the DynamoDB connector.

## Application details

+ Application name: mule-dynamodb-connection-via-java-aws-sdk
+ Version: 1.0.0-SNAPSHOT
+ Mule version: 4.3.0
+ AWS SDK version: 1.11.327
+ Java version: 1.8.0_251 

### Getting Started

+ Clone the application onto your local system
+ Run the downloadable version of DynamoDB locally by following the instructions in this [link](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html)
+ Import the mule project into your Anypoint Studio.
+ First we need to create a table in DynamoDB. Run the java class [DynamoDBTable](./src/main/java/aws/dynamodb/util/DynamoDBTable) by right clicking  on the class and selecting Run as a Java program. This will create a Table called "Records" in your local instance of DynamoDB.
+ Run the Mule application by right-clicking on the application and selecting Run as a Mule application.
+ The application will be running at [localhost:8081](localhost:8081). You can use the postman collection present in folder [documents](./documents) to test the application.