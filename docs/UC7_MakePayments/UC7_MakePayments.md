# UC7 - Make Payments

## 1. Requirements engineering

### Brief Format


### SSD
![UC7_SSD.svg](UC7_SSD.svg)


### Complete Format 

#### Main Actor 

System

#### Main interested parts and their interests 

#### Pre-requirements 
Previously Scheduled Date 
#### Post-conditions
* Each Frelancer Receives an Email wit Invoice details
#### Main success scenario 

1. step 
2. step

#### Special Requiremebs 

####  List of Variations, Technology, and data 

#### Occurrence Frequency

#### Open Questions

* Email Service choosen  in the start of the application?


## 2.OO Analysis 

### Domain submodel for the UC 

![UC7_MD.svg](UC7_MD.svg)


## 3. Design - Use Case execution

### Rational

| Main Flcu | Question : Which Class... | Answer  | Justification  |
|:--------------  |:---------------------- |:----------|:---------------------------- |
|1. Scheduled Date arrives |... interacts with ?| System timer ||
| || ||
| |||
|2. System starts payment process||||
|3. Payment is generated and Email withInvoice is sent to Freelancer |... interacts with ?| Platform ||

             

### Systematization ##

 From the rational results he following conceptual classes

 * Platform 
 * Emailystem
 * Organization
 * Transaction
 * Task
 * Payment 
 * Invoice
 * Freelancer
 * Address
 * Country
 * EmailSystem 
 * CurrencyConverter


###	Sequence Diagram
![UC7_SD_Scheduller.png](UC7_SD_Scheduller.png)
![UC7_SD_PaymentProcess.png](UC7_SD_PaymentProcess.png)



###	Class Diagram

![UC7_CD_MakePayments.svg](UC7_CD_MakePayments.svg)

Currency Convereter

![CD_CurrencyConverter.svg](CD_CurrencyConverter.svg)
Email System:
![CD_EmailSystem.svg](CD_EmailSystem.svg)


