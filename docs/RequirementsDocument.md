# Requirements Document 

Authors: Mehdi Khrichfa, Alessandro Ricciuto, Toni Saliba, Mostafa Tavassoli

Date:10/04/2020

Version:2

### List of changes
| Version | Changes |
|--|---|
|2 | Transformed NFR10 and NFR11 in FR7, FR7.1, FR7.2 and FR7.3| 


# Contents

- [Abstract](#abstract)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
	
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	+ [Functional Requirements](#functional-requirements)
	+ [Non functional requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	+ [Use case diagram](#use-case-diagram)
	+ [Use cases and relevant scenarios](#use-cases-and-relevant-scenarios)
- [Glossary](#glossary)
- [Deployment diagram](#deployment-diagram)

# Abstract

Currently there is no way to compare prices of fuel in a reliable, easy and interoperable way between mobile and desktop devices.
The solutions that are already there, more often than not, have poor reviews due to their out of date fuel prices or only work in certain countries or regions of the world.
This can seem unavoidable, due to the sheer scale of the problem at hand.

EZGas proposes to change this, thanks to a crowdsourcing model, where both drivers and gas station managers update prices of the fuel at gas stations themselves.

# Stakeholders


| Stakeholder name  | Description | 
| ----------------- |:-----------:|
| Developer | Develops, deploys and maintains the web application. | 
| Administrator | Manages user data and can perform all the tasks of a data analyst. | 
| Data Analyst | Manages the data related to gas stations and prices, verifies the validity of user reports and can perform all the tasks of a user. |
| User |Uses the web application in order to find the cheapest fuel prices in his proximity and update the prices when they differ from the actual ones.|
| Google Maps |Service used by the application to locate and display gas stations close to the users. The service is also used to find the gas stations to save in the database. |

# Context Diagram and interfaces

## Context Diagram
```plantuml
left to right direction
actor "Administrator" as admin
actor "Driver" as d
actor "Gas Station Manager" as gsm
actor "Google Maps" as gm
actor "Data Analyst" as da

rectangle System{
	(EZGas) -- admin
	gsm -- (EZGas)
	d -- (EZGas)
	(EZGas) -- gm
	(EZGas) -- da
}
```

## Interfaces
| Actor | Logical Interface | Physical Interface  |
| ------------- |:-------------:| -----:|
|User| Web application GUI |Screen, mouse and keyboard,<br/>smartphone touchscreen|
|Administrator| Administrative panel GUI |Screen, mouse and keyboard|
|Data Analyst|Administrative panel GUI|Screen, mouse and keyboard|
|Google Maps|Web Services (APIs)|Internet connection|

# Stories and personas
Michael is a student worker who does not earn much money in his part-time job.
He has a really tight budget due to his daily expenses and can barely afford a car as is.
He would really love to shop for cheaper gasoline for his car, but cannot find a service with up to date prices. That is, until he learns about EZGas. Now he can save some of the money he used to spend on gas and allocate those funds for a different purpose.

Vivian is the manager of a small independent gas station with some of the best prices in her part of town. However, many drivers are not even aware of its existence as they are used to buying fuel at flashier and well established chains. She doesn't know how to attract customers without spending a fortune in an advertising campaign. On EZGas brands don't matter. By being one of the cheapest, Vivian's gas station is guaranteed to be near the top of every user's search results.

Marco is a businessman living in Rome. His job forces him to drive around the city all day with his car and he has often to refuel. Thanks to EZGas, he is always able to find a close and convenient gas station, without having to remember the price of the various gas stations all the time. While refueling, he checks if the price on the application is updated and if it isn't, he updates it.

Julia is on vacation in Rome. During these days she decides to take a rental car to visit the city. Since she doesn't know the city and is afraid of wasting too much time and money looking for gas stations, she searches on EZGas for those that are most comfortable to reach, comparing the prices they practice. Fortunately, she immediately finds a very convenient station, thanks to Marco who just updated the price.

George is gas station manager in London. He was really worried for his job because his gas station wasn't finding the success he was hoping for. After using EZGas he finally realized that he should lower the price to be more competitive in his area. Now drivers line up at George's gas station to refuel and he is always up to date on the prices of competitors.

# Functional and non functional requirements

## Functional Requirements

| ID        | Description  |
| ------------- |-------------| 
|  FR1     | Record fuel prices for a gas station|  
|  FR2     | Check prices of fuel in various gas stations |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.1|Load prices|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.2|Load map|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.3|Display prices on the map|
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR2.4|Display route to a gas station|
|  FR3     | Authorize and authenticate |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR3.1 | Log in |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR3.2 | Log out |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR3.3 | Create new a account |
|  FR4     | Report inaccurate fuel prices |
|  FR5 | Manage users and data |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR5.1 | Ban fraudulent users |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR5.2 | Hide suspicious prices |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR5.3 | Review user reports |
| FR6 | Manage personal account|
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR6.1 | Edit account information |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR6.2 | Delete personal account |
|  FR7 | Sort gas stations |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR7.1 | Sort gas stations by distance |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR7.2 | Sort gas stations by price |
|  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FR7.3 | Sort gas stations by cost efficiency |

## Non Functional Requirements
| ID        | Type (efficiency, reliability, .. see iso 9126)           | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1     | Usability | The web application should be used with no training | All FR except FR5|
|  NFR2     | Performance | Functions should complete in < 0.5 sec  | All FR except FR2.2 and FR2.4 |
|  NFR3     | Performance | Functions should complete in < 2 sec | FR2.2 and FR2.4 |
|  NFR4     | Portability | The web application should run on Firefox (74+), Chrome (62+), Safari (12+), Chrome for Android, Safari iPhone and Safari iPad | All FR |
|  NFR5     | Localization | Decimal numbers use . (dot) as decimal separator |FR1,FR2,FR4|
|  NFR6     | Localization | Prices are to be displayed as "per liter" or "per gallon" depending on the region of the user |FR1,FR2,FR4|
|  NFR7     | Localization | Distances are to be displayed in meters or miles depending on the region of the user |FR1,FR2,FR4|
|  NFR8     | Localization | Prices are to be displayed in the local currency |FR1,FR2,FR4|
|  NFR9     | Localization | Dates (and their format) and times should depend on the region of the user |FR1,FR2,FR3.3,FR4,FR5|

# Use case diagram and use cases

## Use case diagram
```plantuml
left to right direction
actor User as u
actor "Data Analyst" as da
actor Administrator as admin
actor "Google Maps" as gm
rectangle System{
u ---> (FR1 Record fuel prices for a given gas station)
u ---> (FR2 Check prices of fuel in various gas stations)
u ---> (FR4 Report inaccurate fuel prices)
u ---> (FR6 Manage personal account)
u ---> (FR7 Sort gas stations)
admin ---> (FR5 Manage users and data)
admin -|> da
da ---> (FR5.2 Hide suspicious prices)
da ---> (FR5.3 Review user reports)
da -|> u
(FR2.2 Load map) ---> gm

(FR1 Record fuel prices for a given gas station) ...> (FR3 Authorize and authenticate):<<include>>
(FR2 Check prices of fuel in various gas stations) ...> (FR2.1 Load prices):<<include>>
(FR2 Check prices of fuel in various gas stations) ...> (FR2.2 Load map):<<include>>
(FR2 Check prices of fuel in various gas stations) ...> (FR2.3 Display prices on the map):<<include>>
(FR2 Check prices of fuel in various gas stations) ...> (FR2.4 Display route to a gas station):<<include>>
(FR2 Check prices of fuel in various gas stations) ...> (FR3 Authorize and authenticate):<<include>>

(FR3 Authorize and authenticate) ...> (FR3.1 Log in):<<include>>
(FR3 Authorize and authenticate) ...> (FR3.2 Log out):<<include>>
(FR3 Authorize and authenticate) ...> (FR3.3 Create new a account):<<include>>
(FR4 Report inaccurate fuel prices) ...> (FR1 Record fuel prices for a given gas station):<<include>>
(FR4 Report inaccurate fuel prices) ...> (FR3 Authorize and authenticate):<<include>>
(FR5 Manage users and data) ...> (FR3 Authorize and authenticate):<<include>>
(FR5 Manage users and data) ...> (FR5.1 Ban fraudulent users):<<include>>
(FR5 Manage users and data) ...> (FR5.2 Hide suspicious prices):<<include>>
(FR5 Manage users and data) ...> (FR5.3 Review user reports):<<include>>
(FR6 Manage personal account) ...> (FR3 Authorize and authenticate):<<include>>
(FR6 Manage personal account) ...> (FR6.1 Edit account information):<<include>>
(FR6 Manage personal account) ...> (FR6.2 Delete personal account):<<include>>
(FR7 Sort gas stations) ...> (FR7.1 Sort gas stations by distance):<<include>>
(FR7 Sort gas stations) ...> (FR7.2 Sort gas stations by price):<<include>>
(FR7 Sort gas stations) ...> (FR7.3 Sort gas stations by cost efficiency):<<include>>
```
## Use cases and relevant scenarios
### Use case 1, UC1 - FR1  Record fuel prices for a given gas station

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | User is registered, logged in |  
|  Postcondition     | For all updated fuel types at gas station GS: GS.\<fuel_name\>.post_price != GS.\<fuel_name\>.pre_price|
|  Nominal Scenario     | User selects a fuel type, then they select, from the provided map or list, the gas station at which they want to update the price and provides the new price, if it is different from the previously recorded one|
|  Variants     | User provides price that doesn't match the real price. The web application will rely on another user reporting this incorrect price. |
| |User is banned from contributing. In this case, they are presented with a warning message that states that they are not allowed to complete this operation.|

##### Scenario 1.1 
| Scenario ID: SC1.1        | Corresponds to UC1  |
| ------------- |:-------------| 
| Description | User wants to register a fuel F price P at a gas station GS|
| Precondition |  User is registered and logged in|
| Postcondition |  Price P of fuel F at gas station GS is updated |
| Step#        |  Step description   |
|  1     | User taps desired fuel F |  
|  2     | User taps the pencil icon next to a gas station GS |
|  3     | User enters a new price P |
|  4     | User confirms the price P update |
[Go to UI prototype](./UI_prototype.md#Scenario-1.1-and-Scenario-1.2)

##### Scenario 1.2

| Scenario ID: SC1.2        | Corresponds to UC1  |
| ------------- |:-------------| 
| Description | User wants to register a fuel F price P at a gas station GS, but their account is banned|
|Precondition |  User is registered and logged in but banned from contributing|
|Postcondition |  Price P of fuel F at gas station GS is not updated|
| Step#        | Step description  |
|  1     | User taps desired fuel F |
|  2     | User taps the pencil icon next to a gas station GS |
|  3     | User receives a message stating that their account is not allowed to perform that operation|
[Go to UI prototype](./UI_prototype.md#Scenario-1.1-and-Scenario-1.2)

### Use case 2, UC2 - FR2 Check prices of fuel in various gas stations

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition         | User is registered and logged in and the GPS is enabled|  
|  Postcondition       |  |
|  Nominal Scenario     | User selects the type of fuel they need and they are presented with a map and a list of gas stations near them |
|  Variants             |   |

##### Scenario 2.1 
| Scenario ID: SC2.1        | Corresponds to UC2  |
| ------------- |:-------------| 
| Description | Driver wants to find a gas station to fill their vehicle|
| Precondition |  User is registered and logged in and the GPS is enabled |
| Postcondition |   |
| Step#        |  Step description   |
|  1     | User taps the desired fuel|  
|  2     | User scrolls the list of gas stations or pans around the map|
|  3     | User taps the desired gas station and is presented with the shortest route to reach it by car|
[Go to UI prototype](./UI_prototype.md#Scenario-2.1-and-Scenario-2.2)

##### Scenario 2.2
| Scenario ID: SC2.2        | Corresponds to UC2  |
| ------------- |:-------------| 
| Description | Gas Station Manager wants to check competitors prices |
| Precondition |  User is registered and logged in and the GPS is enabled |
| Postcondition |   |
| Step#        |  Step description   |
|  1     | User taps the fuel they are interested in|  
|  2     | User scrolls the list of gas stations or pans around the map|
[Go to UI prototype](./UI_prototype.md#Scenario-2.1-and-Scenario-2.2)

### Use case 3, UC3 - FR3 Authorize and authenticate

| Actors Involved        | User, Administrator, Data Analyst |
| ------------- |:-------------:| 
|  Precondition     |  |  
|  Postcondition     | User/Administrator/Data Analyst logged in, logged out or new User personal account created |
|  Nominal Scenario     | User/Administrator/Data Analyst wants to use one of the functions of the web application, so they are requested to register or login.|
||User/Administrator/Data Analyst wants to log out of the web application.|
|  Variants     | User/Administrator/Data Analyst fails to log in because of missing/incorrect data in the form.| 
||User fails to register because of missing/incorrect/incoherent data in the form. In this case they are presented again with the same form, with their data filled as it was, and with an error message explaining what is the issue. |

##### Scenario 3.1 
| Scenario ID: SC3.1        | Corresponds to UC3  |
| ------------- |:-------------| 
| Description | In order to access any function the user needs to be logged in|
| Precondition | |
| Postcondition |  User logged in |
| Step#        |  Step description   |
|  1     | User opens the web application |  
|  2     | User fills the email and password fields with their information |  
|  1     | User taps the login button |  
[Go to UI prototype](./UI_prototype.md#Scenario-3.1-and-Scenario-3.2)

##### Scenario 3.2
| Scenario ID: SC3.2        | Corresponds to UC3  |
| ------------- |:-------------| 
| Description | Before being able to log in the user needs to register|
| Precondition |  |
| Postcondition |  User registered and logged in |
| Step#        |  Step description |
|  1     | User taps the registration button|  
|  2     | User fills the form |  
|  3     | User taps the Submit button |  
|  4     | User confirms their email |  
[Go to UI prototype](./UI_prototype.md#Scenario-3.1-and-Scenario-3.2)

### Use case 4, UC4 - FR4 Report inaccurate fuel prices

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | There is a price P related to a fuel type F in a gas station GS that is perceived by the user as suspiciously out of line with the prices at that gas station|  
|  Postcondition     | Report sent to the Administrator/Data Analyst for further consideration |
|| User thanked for their contribution and asked to continue as specified in UC1 |
|  Nominal Scenario     | User notices a suspicious price on the web application, that they think does not make sense or is clearly fraudulent. They press the report button next to the price and provide the correct one following the procedure specified in UC1|
|  Variants     | User does not want to (or cannot) enter a new price. In this case the previous price is neither hidden nor replaced, but it is publicly marked as suspicious on the web application, until the administrator takes further steps. |

##### Scenario 4.1 
| Scenario ID: SC4.1        | Corresponds to UC4  |
| ------------- |:-------------| 
| Description | The user wants to report a suspicious price and substitute it with the correct one. |
| Precondition |  There is a price SP considered suspicious by the user and the user knows the current price CP. |
| Postcondition |  SP replaced by CP |
| Step#        |  Step description   |
|  1     | User taps the octagonal icon with an exclamation mark to report the price |  
|  2     | User taps the check mark button to confirm the report |
|  3     | User confirms that they want to update the price|
|  4     | User enters the new price |
|  5     | User confirms the price update |
[Go to UI prototype](./UI_prototype.md#Scenario-4.1-and-Scenario-4.2)

##### Scenario 4.2
| Scenario ID: SC4.2        | Corresponds to UC4  |
| ------------- |:-------------| 
| Description | The user wants to report a suspicious price, but doesn't know or doesn't want to insert the current one. |
| Precondition |  There is a price SP considered suspicious by the user. |
| Postcondition |  SP marked as suspicious |
| Step#        |  Step description   |
|  1     | User taps the octagonal icon with an exclamation mark to report the price |  
|  2     | User taps the check mark button to confirm the report |
|  3     | User taps the close button when asked if they want to update the price|
[Go to UI prototype](./UI_prototype.md#Scenario-4.1-and-Scenario-4.2)

### Use case 5, UC5 - FR5 Manage users and data

| Actors Involved        | Administrator, Data Analyst  |
| ------------- |:-------------:| 
|  Precondition     | Administrator/Data Analyst logged in|  
|  Postcondition     |  |
|  Nominal Scenario     | Administrator can add, remove or ban users and add administrators or data analysts.|
||Administrator/Data Analyst can view gas stations with their fuels and relative prices, hide prices, and inspect reports and decide whether to accept or reject them. |
|  Variants     | |

##### Scenario 5.1 
| Scenario ID: SC5.1        | Corresponds to UC5 |
| ------------- |:-------------| 
| Description | Administrator wants to add a new Data Analyst|
| Precondition |  Administrator registered and logged in  |
| Postcondition |  New Data Analyst account created |
| Step#  |  Step description   |
|  1     | Administrator clicks on "Manage accounts" in the navigation bar |
|  2     | Administrator clicks on the "Add account" button |
|  3     | Administrator fills the form with the necessary information |
|  4     | Administrator selects Data Analyst from the "Privileges" drop down menu |
| 5 | Administrator clicks the "Submit" button|
[Go to UI prototype](./UI_prototype.md#Scenario-5.1)

##### Scenario 5.2 
| Scenario ID: SC5.2        | Corresponds to UC5  |
| ------------- |:-------------| 
| Description | Data Analyst wants to hide a fuel price|
| Precondition |  Data Analyst is logged in and fuel price FP to be hidden exists and is visible |
| Postcondition |  Fuel price FP exists, but it's hidden |
| Step#        |  Step description   |
|  1     | Data Analyst clicks "User activity" in the navigation bar|  
|  2     | Data Analyst clicks on the "white x on a red circle" icon next to a User submitted price change |
|  3     | Data Analyst confirms that he wishes to hide the current price and revert the displayed price to its previous value |
[Go to UI prototype](./UI_prototype.md#Scenario-5.2)

##### Scenario 5.3
| Scenario ID: SC5.3        | Corresponds to UC5  |
| ------------- |:-------------| 
| Description | Administrator wants to confirm a report as accurate|
| Precondition |  Administrator is logged in and a non reviewed report exits |
| Postcondition |  The report is marked as accepted. User automatically banned if reported more than 5 times (and reports marked as accepted) |
| Step#        |  Step description   |
|  1     | Administrator clicks "Reports" in the navigation bar|  
|  2     | Administrator clicks on the "Accept" icon next to a report |
[Go to UI prototype](./UI_prototype.md#Scenario-5.3)

### Use case 6, UC6 - FR6 Manage personal account

| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | User registered and logged in |  
|  Postcondition     | User data possibly changed |
|  Nominal Scenario     | User can change their personal account information (first name, last name, username, email and password) or delete their account |
|  Variants     | |

##### Scenario 6.1 
| Scenario ID: SC6.1        | Corresponds to UC6 |
| ------------- |:-------------| 
| Description | User U wants to change username|
| Precondition |  User U logged in|
| Postcondition |  User U username changed |
| Step#        |  Step description   |
|  1     | User taps the portrait icon in the top right|  
|  2     | User taps the pencil icon next to their username |
|  3     | User types a new username |
|  4     | User taps the confirm button at the bottom of the screen |
[Go to UI prototype](./UI_prototype.md#Scenario-6.1-and-Scenario-6.2)

##### Scenario 6.2
| Scenario ID: SC6.2        | Corresponds to UC6 |
| ------------- |:-------------| 
| Description | User wants to delete account|
| Precondition |  Account A exists and user U logged in|
| Postcondition |  Account A doesn't exist anymore and user U logged out|
| Step#        |  Step description   |
|  1     | User taps the portrait icon in the top right|  
|  2     | User taps the delete account button on the bottom of the page |
|  3     | User confirms the deletion |
[Go to UI prototype](./UI_prototype.md#Scenario-6.1-and-Scenario-6.2)

### Use case 7, UC7 - FR7 Sort gas stations
| Actors Involved        | User |
| ------------- |:-------------:| 
|  Precondition     | User registered, logged in and gas stations list is displayed on their screen |  
|  Postcondition     | Gas stations list sorted based on distance, price or cost efficiency |
|  Nominal Scenario     | User can sort the gas station list based on 3 criteria: distance from their location, price of the fuel or cost efficiency. When sorting by cost efficiency, given the fuel consumption of the motor vehicle to be filled and the amount of fuel desired (or the amount of money the user is going to spend to fill the motor vehicle), the web application should sort the gas stations list while taking into consideration fuel cost at the gas station, fuel consumption of the motor vehicle and distance between the user and the gas station |
|  Variants     | |

##### Scenario 7.1
| Scenario ID: SC7.1        | Corresponds to UC7 |
| ------------- |:-------------| 
| Description | User wants to sort the list of gas stations based on distance|
| Precondition |  Account A exists, user U logged in and list of gas stations L displayed|
| Postcondition |  List L gas stations sorted based on distance from the user|
| Step#        |  Step description   |
|  1     | User taps the sort icon in the top right of the list|  
|  2     | User taps the "distance" option |

##### Scenario 7.2
| Scenario ID: SC7.2        | Corresponds to UC7 |
| ------------- |:-------------| 
| Description | User wants to sort the list of gas stations based on price|
| Precondition |  Account A exists, user U logged in and list of gas stations L displayed|
| Postcondition |  List L gas stations sorted based on price of the fuel|
| Step#        |  Step description   |
|  1     | User taps the sort icon in the top right of the list|  
|  2     | User taps the "price" option |

##### Scenario 7.3
| Scenario ID: SC7.3        | Corresponds to UC7 |
| ------------- |:-------------| 
| Description | User wants to sort the list of gas stations based on cost efficiency|
| Precondition |  Account A exists, user U logged in and list of gas stations L displayed|
| Postcondition |  List L gas stations sorted based on cost efficiency|
| Step#        |  Step description   |
|  1     | User taps the sort icon in the top right of the list|  
|  2     | User taps the "cost efficient" option |


# Glossary

```plantuml

class GasStation{
	+ id
	+ name
	+ brand
}
class User {
	+ username
	+ name
	+ surname
	+ banned
	+ sendReport()
	+ recordPrice()
}
class Administrator {
	+ banUser()
	+ addAdministrator()
	+ addDataAnalyst()
}
class FuelType {
	+ name
	+ description
}
class FuelPrice {
	+ id
	+ value
	+ suspicious
	+ hidden
}
class Report {
	+ id
	+ date
	+ reviewed
	+ confirmed
}

class Location {
	+ address
	+ longitude
	+ latitude
}
class DataAnalyst {
	+ editData()
	+ reviewReport()
	+ hideFuelPrice()
}

User  -- "*" FuelPrice:Recorded by
User -- "*" Report:Sent by
DataAnalyst <|-- Administrator
User <|-- DataAnalyst
DataAnalyst -- "*" Report:Reviewed by
FuelPrice "*" -- GasStation:Offered by
FuelPrice "*" -- FuelType:Described by
FuelPrice -- "*" Report:Regarding
Location "1" -- "1" GasStation:Located at

note left of User : Includes both \nGas Station Managers \nand Drivers
note left of FuelPrice : Relates fuel type and price \nat a specific gas station
note right of FuelType : Contains the\n description\nof a specific \nfuel type
note left of Report: Generated when \na User reports \na suspicious price

```

# Deployment Diagram 

```plantuml
left to right direction
node "Web \nServer" as ws
database "Database \nServer" as dbs
node "PC \nClient" as pc
node "Smartphone \nClient" as smph

node "Application \nServer" as as{
	artifact "<<artifact>>\n<b>EZGas</b>"{
		artifact "<<artifact>>\n<b>Web application</b>"
		artifact "<<artifact>>\n<b>Administrative panel</b>"
	}
}

pc --> ws:internet
smph --> ws:internet
ws --> as
as --> dbs

```
