# Integration and API Test Documentation

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date: 23/05/2020

Version: 1

# Contents

- [Dependency graph](#dependency-graph)

- [Integration and API Test Documentation](#integration-and-api-test-documentation)
- [Contents](#contents)
- [Dependency graph](#dependency-graph)
- [Integration approach](#integration-approach)
- [Tests](#tests)
  - [Step 1](#step-1)
  - [Step 2](#step-2)
  - [Step 3 API Tests UserService](#step-3-api-tests-userservice)
  - [Step 4 API Tests GasStationService](#step-4-api-tests-gasstationservice)
- [Scenarios](#scenarios)
  - [Scenario UC1.1](#scenario-uc11)
  - [Scenario UC1.2](#scenario-uc12)
  - [Scenario UC2.1](#scenario-uc21)
  - [Scenario UC3.1](#scenario-uc31)
  - [Scenario UC7.1](#scenario-uc71)
  - [Scenario UC7.2](#scenario-uc72)
  - [Scenario UC8.1](#scenario-uc81)
  - [Scenario UC8.2](#scenario-uc82)
  - [Scenario UC8.3](#scenario-uc83)
  - [Scenario UC8.4](#scenario-uc84)
- [Coverage of Scenarios and FR](#coverage-of-scenarios-and-fr)
- [Coverage of Non Functional Requirements](#coverage-of-non-functional-requirements)
- [Code coverage report](#code-coverage-report)



# Dependency graph 

     <report the here the dependency graph of the classes in it/polito/Ezgas, using plantuml>
```plantuml
top to bottom direction
class User
class UserDto
class UserRepository
class UserConverter
class UserServiceimpl
class UserService
class UserController

class IdPw

class LoginDto
class LoginConverter

class GasStation
class GasStationDto
class GasStationRepository
class GasStationServiceimpl
class GasStationService

UserRepository         ---> User
   
UserConverter          ---> User
UserConverter          ---> UserDto
   
UserServiceimpl        ---> UserConverter
UserServiceimpl        ---> UserRepository
UserServiceimpl        ---> IdPw
UserServiceimpl        ---> User
UserServiceimpl        ---> UserDto
UserServiceimpl        ---> LoginDto
UserServiceimpl        ---> LoginConverter

UserService            ---> UserServiceimpl

UserController         ---> UserService

LoginConverter         ---> LoginDto
LoginConverter         ---> User

GasStation             ---> User

GasStationDto          ---> UserDto

GasStationRepository   ---> GasStation

GasStationConverter    ---> GasStation
GasStationConverter    ---> GasStationDto

GasStationServiceimpl  ---> GasStationRepository
GasStationServiceimpl  ---> GasStationConverter
GasStationServiceimpl  ---> GasStation
GasStationServiceimpl  ---> GasStationDto
GasStationServiceimpl  ---> UserService

GasStationService      ---> GasStationServiceimpl 

GasStationController   ---> GasStationService

```
     
# Integration approach

    <Write here the integration sequence you adopted, in general terms (top down, bottom up, mixed) and as sequence
    (ex: step1: class A, step 2: class A+B, step 3: class A+B+C, etc)> 
    <The last integration step corresponds to API testing at level of Service package>
    <Tests at level of Controller package will be done later>
We adopted a bottom up approach divided in the following steps:

|Step#             |Classes                 |
|:-----:           |:-----                 |
|1                 |User                    |
|                  |UserDto                 |
|                  |IdPw                    |
|                  |LoginDto                |
|2                 |User+GasStation         |
|                  |UserDto+GasStationDto   |
|                  |User+UserDto+UserConverter|
|                  |User+LoginDto+LoginConverter|
|                  |User+UserRepository   |
|3                 |UserDto+IdPw+LoginDto+UserConverter+LoginConverter+UserRepository+User+UserServiceimpl|
|                  |UserDto+GasStationDto+User+GasStation+GasStationConverter                    |
|                  |User+GasStation+GasStationRepository        |
|4                 |UserDto+IdPw+LoginDto+UserConverter+LoginConverter+UserRepository+User+UserServiceimpl+GasStationConverter+GasStationRepository+GasStation+GasStationDto+GasStationServiceimpl|
|                  |                    |

    
    

#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them>

## Step 1
**Step 1 is extensively documented [here](./UnitTestReport.md#white-box-unit-tests).**


## Step 2
| Classes  | JUnit test cases |
|--|--|
| GasStationDto.computeReportDependability | GasStationDtoTest.testComputeReportDependabilityMoreThan7Days |
|| testComputeReportDependabilityLessThan7Days |
|| testComputeReportDependabilityWithNullReportTimestamp |
|| testComputeReportDependabilityWithInvalidReportTimestamp |
| GasStationDto.toString | GasStationDtoTest.testToStringWithUninitializedAttributes|
|| GasStationDtoTest.testToStringWithInitializedAttributes|
| UserConverter.toDto | UserConverterTest.testToDto |
| UserConverter.toEntity| UserConverterTest.testToEntity |
| UserConverter.toDto | UserConverterTest.testToDtoList |
| LoginConverter.toDto | LoginConverterTest.testLoginConverter |
| UserRepository.findByEmail | UserRepositoryTest.testFindByEmail |
| UserRepository.findByUserId | UserRepositoryTest.testFindByUserId |

## Step 3 API Tests UserService
| Classes  | JUnit test cases |
|--|--|
|UserServiceimpl.getUserById|UserServiceimplTest.testGetUserByIdNegative|
||UserServiceimplTest.testGetUserByIdDoesNotExist|
||UserServiceimplTest.testGetUserByIdPositiveAndExists|
|UserServiceimpl.saveUser|UserServiceimplTest.testSaveUserValid|
||UserServiceimplTest.testSaveUserForUpdate|
||UserServiceimplTest.testSaveUserForInvalidUpdate|
||UserServiceimplTest.testSaveUserFails|
|UserServiceimpl.getAllUsers|UserServiceimplTest.testGetAllUsersNotEmpty|
||UserServiceimplTest.testGetAllUsersEmpty|
|UserServiceimpl.deleteUser|UserServiceimplTest.testDeleteUserThrowInvalidUserException|
||UserServiceimplTest.testDeleteUserSuccessful|
||UserServiceimplTest.testDeleteUserNotExists|
||UserServiceimplTest.testDeleteUserFails|
|UserServiceimpl.login|UserServiceimplTest.testLoginThrowInvalidLoginDataExceptionForWrongPw|
||UserServiceimplTest.testLoginThrowInvalidLoginDataExceptionForWrongEmail|
||UserServiceimplTest.testLoginSuccessful|
|UserServiceimpl.increaseUserReputation|UserServiceimplTest.testIncreaseUserReputationThrowsInvalidUserException|
||UserServiceimplTest.testIncreaseUserReputation|
|UserServiceimpl.decreaseUserReputation|UserServiceimplTest.testDecreaseUserReputationThrowsInvalidUserException|
||UserServiceimplTest.testDecreaseUserReputation|
|GasStationConverter.toDto|GasStationConverterTest.testToDto|
||GasStationConverterTest.testToDtoWithUser|
|GasStationConverter.toDto|GasStationConverterTest.testToDtoList|
|GasStationConverter.toEntity|GasStationConverterTest.testToEntity|
||GasStationConverterTest.testToEntityWitUser|
|GasStationRepository.findByProximity|GasStationRepositoryTest.testFindByProximity|
|GasStationRepository.findByCarSharingOrderByGasStationName|GasStationRepositoryTest.testFindByCarSharingOrderByGasStationName|
|GasStationRepository.findByHasMethaneOrderByMethanePriceAsc|GasStationRepositoryTest.testFindByHasMethaneOrderByMethanePriceAscTrue|
||GasStationRepositoryTest.testFindByHasMethaneOrderByMethanePriceAscFalse|
||GasStationRepositoryTest.testFindByHasMethaneOrderByMethanePriceAscTotal|
|GasStationRepository.findByHasGasOrderByGasPriceAsc|GasStationRepositoryTest.testFindByHasDieselOrderByDieselPriceAscTrue|
||GasStationRepositoryTest.testFindByHasDieselOrderByDieselPriceAscFalse|
||GasStationRepositoryTest.testFindByHasDieselOrderByDieselPriceAscTotal|
|GasStationRepository.findByHasSuperOrderBySuperPriceAsc|GasStationRepositoryTest.testFindByHasSuperOrderBySuperPriceAscTrue|
||GasStationRepositoryTest.testFindByHasSuperOrderBySuperPriceAscFalse|
||GasStationRepositoryTest.testFindByHasSuperOrderBySuperPriceAscTotal|
|GasStationRepository.findByHasSuperPlusOrderBySuperPlusPriceAsc|GasStationRepositoryTest.testFindByHasSuperPlusOrderBySuperPlusPriceAscTrue|
||GasStationRepositoryTest.testFindByHasSuperPlusOrderBySuperPlusPriceAscFalse|
||GasStationRepositoryTest.testFindByHasSuperPlusOrderBySuperPlusPriceAscTotal|
|GasStationRepository.findByHasDieselOrderByDieselPriceAsc|GasStationRepositoryTest.testFindByHasDieselOrderByDieselPriceAscTrue|
||GasStationRepositoryTest.testFindByHasDieselOrderByDieselPriceAscFalse|
||GasStationRepositoryTest.testFindByHasDieselOrderByDieselPriceAscTotal|
|GasStationRepository.save|GasStationRepositoryTest.testSaveNewGasStation|
||GasStationRepositoryTest.testUpdateOldGasStation|

## Step 4 API Tests GasStationService  
<The last integration step  should correspond to API testing, or tests applied to all classes implementing the APIs defined in the Service package>

| Classes  | JUnit test cases |
|--|--|
|GasStationServiceimpl.getGasStationById|GasStationServiceimplTest.testGetGasStationByIdNegative|
||GasStationServiceimplTest.testGetGasStationByIdDoesNotExist|
||GasStationServiceimplTest.testGetGasStationByIdPositiveAndExists|
|GasStationServiceimpl.saveGasStation|GasStationServiceimplTest.testSaveGasStationInvalidNegativePrice|
||GasStationServiceimplTest.testSaveGasStationInvalidCoordinates|
||GasStationServiceimplTest.testSaveGasStationValid|
||GasStationServiceimplTest.testSaveGasStationValidAlreadyExists|
||GasStationServiceimplTest.testSaveGasStationValidNotAlreadyExists|
|GasStationServiceimpl.getAllGasStations|GasStationServiceimplTest.testGetAllGasStationsEmpty|
||GasStationServiceimplTest.testGetAllGasStationsNotEmpty|
|GasStationServiceimpl.deleteGasStation|GasStationServiceimplTest.testDeleteGasStationValid|
||GasStationServiceimplTest.testDeleteGasStationIdNegative|
||GasStationServiceimplTest.testDeleteGasStationIdDoesNotExist|
||GasStationServiceimplTest.testDeleteGasStationDeleteFails|
|GasStationServiceimpl.getGasStationsByGasolineType|GasStationServiceimplTest.testGetGasStationsByGasolineTypeDiesel|
||GasStationServiceimplTest.testGetGasStationsByGasolineTypeGas|
||GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuper|
||GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuperPlus|
||GasStationServiceimplTest.testGetGasStationsByGasolineTypeMethane|
||GasStationServiceimplTest.testGetGasStationsByGasolineTypeNull|
||GasStationServiceimplTest.testGetGasStationsByGasolineTypeInvalid|
|GasStationServiceimpl.getGasStationsByProximity|GasStationServiceimplTest.testGetGasStationsByProximityInvalidCoordinates|
||GasStationServiceimplTest.testGetGasStationsByProximityEmptyList|
||GasStationServiceimplTest.testGetGasStationsByProximityNonEmptyList|
||GasStationServiceimplTest.testGetGasStationsByProximityWithValidRadius|
||GasStationServiceimplTest.testGetGasStationsByProximityWithInvalidRadius|
||GasStationServiceimplTest.testGetGasStationsByProximityInvalidCoordinatesAndValidRadius|
|GasStationServiceimpl.getGasStationsWithCoordinates|GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType|
||GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude|
||GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates|
||GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid|
|GasStationServiceimpl.getGasStationsWithoutCoordinates|GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineType|
||GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineTypeNullCarSharing|
||GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullCarSharing|
||GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesValid|
||GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesInvalidGasType|
|GasStationServiceimpl.setReport|GasStationServiceimplTest.testSetReportValid|
||GasStationServiceimplTest.testSetReportInvalidPrice|
||GasStationServiceimplTest.testSetReportInvalidUser|
||GasStationServiceimplTest.testSetReportInvalidGasStation|
||GasStationServiceimplTest.testSetReportInvalidTimestamp|
||GasStationServiceimplTest.testSetReportNullTimestamp|
||GasStationServiceimplTest.testSetReportEmptyTimestamp|
||GasStationServiceimplTest.testSetReportLowerUserReputation|
||GasStationServiceimplTest.testSetReportLessThan4Days|
|GasStationServiceimpl.getGasStationByCarSharing|GasStationServiceimplTest.testGetGasStationByCarSharingValid|
||GasStationServiceimplTest.testGetGasStationByCarSharingNull|


# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC they detail>

## Scenario UC1.1

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Account U exists inside the system and account B does not exist|
|  Post condition     |  -|
| Step#        | Description  |
|  1     |  A user tries to create a new account B and populates its fields using the same email of account U |  
|  2     |  System searches for email to see if it exists inside the system |
|  3     |  System finds the email inside the system |
|  4     |  System checks to see the type of account (user or admin)|
|  5     |  System finds out that type of account is user|
|  6     |  Failure message is prompted to user stating that an account already exists for this email |
## Scenario UC1.2

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Admin account A exists inside the system and user account U does not exist|
|  Post condition     |  Account A and account U exist inside the system|
| Step#        | Description  |
|  1     |  Admin tries to create a new user account U and populates its fields |
|  2     |  System searches for email to see if it exists inside the system |
|  3     |  System doesn't find the email inside the system |
|  4     |  System successfully creates account U inside the database|

## Scenario UC2.1

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Admin account B exists|
|  Post condition     |  -|
| Step#        | Description  |
|  1     |  Admin gets a list of all accounts |
|  2     |  Admin modifies one or more fields of any account |

## Scenario UC3.1

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Account U exists|
|  Post condition     |  Account U deleted from the system|
| Step#        | Description  |
|  1     |  Admin gets a list of all accounts|
|  2     |  Admin selects an account to delete |


## Scenario UC7.1

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Gas station G exists |
|                   |User U1 is registered in the system|
|                   |G already has an attached price list P submitted by user U2|
|                   | User U1 reputation >= User U2 reputation at the time of the submission |
|  Post condition     |  Price list P2 is created |
||P2.time_tag is set to the current timestamp of the system|
||P is overwritten by P2|
||U1 is attached to P2 |
| Step#        | Description  |
|  1     |  The user U1 selects a gas station G for which he/she wants to insert a price report |
|2       |The system prompts the user with the list of possible fuels provided by the gas station with their prices (if available)|
|3       |The user inerts the prices for the fuels|
|4       |System overwrites the previous price list attached to G by the new one|

## Scenario UC7.2

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | Gas station G exists |
|                   |User U1 is registered in the system|
|                   |G already has an attached price list P submitted by user U2|
|                   | User U1 reputation < User U2 reputation at the time of the submission |
|                   | today - P.timestamp > 4 days |
|  Post condition     |  Price list P2 is created |
||P2.time_tag is set to the current timestamp of the system|
||P is overwritten by P2|
||U1 is attached to P2 |
| Step#        | Description  |
|  1     |  The user U1 selects a gas station G for which he/she wants to insert a price report |
|2       |The system prompts the user with the list of possible fuels provided by the gas station with their prices (if available)|
|3       |The user inerts the prices for the fuels|
|4       |System overwrites the previous price list attached to G by the new one|


## Scenario UC8.1

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | - |
|  Post condition     |  - |
| Step#        | Description  |
|  1     |  The anonymous user AU selects a geo point GP and a radius r (default radius is 5km) and restricts to a certain fuel type|
|2       | The system prompts all gas stations within r from GP, with their prices for certain available fuel type|
|3       | If a price for a fuel is missing "NA" is reported|
|4       |The system shows also, for each gas station, the trust level of the prices, computed as in UC 9|

## Scenario UC8.2

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | - |
|  Post condition     |  - |
| Step#        | Description  |
|  1     |  The anonymous user AU selects a geo point GP and a radius r (default radius is 5km) and restricts to car sharing|
|2       | The system prompts only gas stations having a deal with a certain car sharing company, and that are located within r from GP, with their prices for all available fuels|
|3       | If a price for a fuel is missing "NA" is reported|
|4       |The system shows also, for each gas station, the trust level of the prices, computed as in UC 9|


## Scenario UC8.3

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | - |
|  Post condition     |  - |
| Step#        | Description  |
|  1     |  The anonymous user AU selects a geo point GP and a radius r (default radius is 5km) and sorts by price for a fuel type|
|2       | The system prompts all gas stations within r from GP, with their prices for certain available fuel type, sorted by price, in ascending order|
|3       | If a price for a fuel is missing "NA" is reported|
|4       |The system shows also, for each gas station, the trust level of the prices, computed as in UC 9|


## Scenario UC8.4

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     | - |
|  Post condition     |  - |
| Step#        | Description  |
|  1     |  The anonymous user AU selects a geo point GP and a radius r (default radius is 5km) and sorts by distance for a fuel type|
|2       | The system prompts all gas stations within r from GP, with their prices for certain available fuel type, sorted by distance, in ascending order|
|3       | If a price for a fuel is missing "NA" is reported|
|4       |The system shows also, for each gas station, the trust level of the prices, computed as in UC 9|


# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) | 
| ----------- | ------------------------------- | ----------- | 
| UC1| FR1.1                          | UserServiceimplTest.testSaveUserValid            |      
||| UserServiceimplTest.testSaveUserForUpdate|  
||| UserServiceimplTest.testSaveUserForInvalidUpdate|
|||UserServiceimplTest.testSaveUserFails |  
|||UserConverterTest.testToEntity |
|||UserRepositoryTest.testFindByEmail|
|||UserConverterTest.testToDto|
| UC1.1| FR1.1                          | UserServiceimplTest.testSaveUserValid            |      
||| UserServiceimplTest.testSaveUserForUpdate|  
||| UserServiceimplTest.testSaveUserForInvalidUpdate|
|||UserServiceimplTest.testSaveUserFails |  
|||UserConverterTest.testToEntity |
|||UserRepositoryTest.testFindByEmail|
|||UserConverterTest.testToDto|
| UC1.2| FR1.1|UserServiceimplTest.testSaveUserValid | 
||| UserServiceimplTest.testSaveUserForUpdate|  
||| UserServiceimplTest.testSaveUserForInvalidUpdate|
|||UserServiceimplTest.testSaveUserFails |  
|||UserConverterTest.testToEntity |
|||UserRepositoryTest.testFindByEmail|
|||UserConverterTest.testToDto|
|          UC2 | FR1.1                          |UserServiceimplTest.testSaveUserValid|
||| UserServiceimplTest.testSaveUserForUpdate|  
||| UserServiceimplTest.testSaveUserForInvalidUpdate|
|||UserServiceimplTest.testSaveUserFails |  
|||UserConverterTest.testToEntity |
|||UserRepositoryTest.testFindByEmail|
|||UserConverterTest.testToDto|
| UC2.1  | FR1.1                          |UserServiceimplTest.testSaveUserValid|
||| UserServiceimplTest.testSaveUserForUpdate|  
||| UserServiceimplTest.testSaveUserForInvalidUpdate|
|||UserServiceimplTest.testSaveUserFails |  
|||UserConverterTest.testToEntity |
|||UserRepositoryTest.testFindByEmail|
|||UserConverterTest.testToDto|
||FR1.3|UserServiceimplTest.testGetAllUsersNotEmpty|
|||UserServiceimplTest.testGetAllUsersEmpty|
|||UserConverterTest.testToDtoList|
|||UserConverterTest.testToDto|
|          UC3 | FR1.2                          |UserServiceimplTest.testDeleteUserThrowInvalidUserException|   
|||UserServiceimplTest.testDeleteUserSuccessful|
|||UserServiceimplTest.testDeleteUserNotExists|
|||UserServiceimplTest.testDeleteUserFails|
| UC3.1  | FR1.2                          |UserServiceimplTest.testDeleteUserThrowInvalidUserException|   
|||UserServiceimplTest.testDeleteUserSuccessful|
|||UserServiceimplTest.testDeleteUserNotExists|
|||UserServiceimplTest.testDeleteUserFails|
||FR1.3|UserServiceimplTest.testGetAllUsersNotEmpty|
|||UserServiceimplTest.testGetAllUsersEmpty|
|||UserConverterTest.testToDtoList|
|||UserConverterTest.testToDto|             
| UC4 | FR3.1 | GasStationServiceimplTest.testSaveGasStationInvalidNegativePrice   |             
||| GasStationServiceimplTest.testSaveGasStationInvalidCoordinates   |             
|||  GasStationServiceimplTest.testSaveGasStationValid |
|||  GasStationServiceimplTest.testSaveGasStationValidAlreadyExists |
|||  GasStationServiceimplTest.testSaveGasStationValidNotAlreadyExists |
| UC5 | FR3.1 | GasStationServiceimplTest.testSaveGasStationInvalidNegativePrice   |             
||| GasStationServiceimplTest.testSaveGasStationInvalidCoordinates |             
||| GasStationServiceimplTest.testSaveGasStationValid |                
| UC6 | FR3.2 | GasStationServiceimplTest.testDeleteGasStationValid |             
||| GasStationServiceimplTest.testDeleteGasStationIdNegative |     
||| GasStationServiceimplTest.testDeleteGasStationIdDoesNotExist |  
||| GasStationServiceimplTest.testDeleteGasStationDeleteFails |  
| UC7 | FR5.1 | GasStationServiceimplTest.testSetReportInvalidPrice |  
||| GasStationServiceimplTest.testSetReportInvalidUser |  
||| GasStationServiceimplTest.testSetReportInvalidGasStation | 
||| GasStationServiceimplTest.testSetReportValid |  
||| GasStationServiceimplTest.testSetReportInvalidTimestamp |  
||| GasStationServiceimplTest.testSetReportNullTimestamp |  
||| GasStationServiceimplTest.testSetReportEmptyTimestamp |  
||| GasStationServiceimplTest.testSetReportLowerUserReputation |  
||| GasStationServiceimplTest.testSetReportLessThan4Days |   
| UC7.1 | FR5.1 | GasStationServiceimplTest.testSetReportInvalidPrice |  
||| GasStationServiceimplTest.testSetReportInvalidUser |  
||| GasStationServiceimplTest.testSetReportInvalidGasStation | 
||| GasStationServiceimplTest.testSetReportValid |  
||| GasStationServiceimplTest.testSetReportInvalidTimestamp |  
||| GasStationServiceimplTest.testSetReportNullTimestamp |  
||| GasStationServiceimplTest.testSetReportEmptyTimestamp |  
||| GasStationServiceimplTest.testSetReportLowerUserReputation |  
||| GasStationServiceimplTest.testSetReportLessThan4Days |  
| UC7.2 | FR5.1 | GasStationServiceimplTest.testSetReportInvalidPrice |  
||| GasStationServiceimplTest.testSetReportInvalidUser |  
||| GasStationServiceimplTest.testSetReportInvalidGasStation | 
||| GasStationServiceimplTest.testSetReportValid |  
||| GasStationServiceimplTest.testSetReportInvalidTimestamp |  
||| GasStationServiceimplTest.testSetReportNullTimestamp |  
||| GasStationServiceimplTest.testSetReportEmptyTimestamp |  
||| GasStationServiceimplTest.testSetReportLowerUserReputation |  
||| GasStationServiceimplTest.testSetReportLessThan4Days |  
| UC8 | FR4.1 | GasStationServiceimplTest.testGetGasStationsByProximityInvalidCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsByProximityEmptyList |  
||| GasStationServiceimplTest.testGetGasStationsByProximityNonEmptyList |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid |  
|| FR4.2 | GasStationServiceimplTest.testGetGasStationsByProximityInvalidCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsByProximityEmptyList |  
||| GasStationServiceimplTest.testGetGasStationsByProximityNonEmptyList |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid | 
| UC8.1 | FR4.5 | GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineType |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineTypeNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeDiesel |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeGas |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuper |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuperPlus |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeMethane |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeNull |  
| UC8.2 | FR4.5 | GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineType |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineTypeNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationByCarSharingValid |  
||| GasStationServiceimplTest.testGetGasStationByCarSharingNull |  
| UC8.3 | FR4.5 | GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineType |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineTypeNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeDiesel |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeGas |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuper |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuperPlus |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeMethane |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeNull |  
| UC8.4 | FR4.5 | GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineType |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullGasolineTypeNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesNullCarSharing |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsWithoutCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidGasType |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesInvalidLatitudeAndLongitude |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesNullGasStationsWithoutCoordinates |  
||| GasStationServiceimplTest.testGetGasStationsWithCoordinatesValid |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeDiesel |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeGas |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuper |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeSuperPlus |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeMethane |  
||| GasStationServiceimplTest.testGetGasStationsByGasolineTypeNull |  
| UC9 | FR5.2 | GasStationServiceimplTest.testGetGasStationByIdPositiveAndExists |  
||| GasStationServiceimplTest.testGetAllGasStationsNotEmpty |    
| UC10.1 | FR5.3 | UserServiceimpl.testIncreaseUserReputationThrowsInvalidUserException |  
||| UserServiceimpl.testIncreaseUserReputation |  
||| GasStationServiceimplTest.testGetGasStationByIdPositiveAndExists |  
| UC10.2 | FR5.3 | UserServiceimpl.testDecreaseUserReputationThrowsInvalidUserException |          
||| UserServiceimpl.testDecreaseUserReputation |  
||| GasStationServiceimplTest.testGetGasStationByIdPositiveAndExists |  



# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


### 

**At this point in the testing process, none of the non functional requirements can be properly assessed with an automated testing framework. All of them require a test at the level of the GUI.**

# Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >
We achieved 100% instructions and branches coverage if we do not count the classes left to test in the next phase (Controllers and, possibly, the Boot Class).
![Code coverage report](./images/ezgasIntegrationCoverage.png)<br/>