# ISO8583_Decoder


#Requirements
  - Maven
  - MySql
  - Spring Boot
 
#Commands

  - Run (POST) "http://localhost:8080/mtis" to insert ISO 8583 MTIs
  - Run (POST) "http://localhost:8080/acquirers" to insert ISO 8583 Acquirers
  - Run (POST) "http://localhost:8080/fields" to insert ISO 8583 Fields
  - Run (GET) "http://localhost:8080/decode/{acquirer_id}/(Insert Message)" to decode a message.

* Acquirer_id = 1 (Wikipedia table), 2 (EMV Prisma), 3 (AMEX)
  
#Example

  http://localhost:8080/decode/3/60 00 03 00 00 02 00 30 20 05 80 20 C0 82 04 00
40 00 00 00 00 00 10 12 00 00 18 05 91 00 03 00
37 37 42 45 00 17 71 00 4D 24 12 70 21 71 01 23
45 00 00 00 37 31 32 35 36 33 36 31 36 38 31 36
37 38 33 31 36 31 20 20 20 20 20 30 33 32 00 57
C1 C7 D5 E2 00 01 F9 E2 B0 9C FB CA D1 E7 07 06
02 01 03 A0 10 02 81 BE 75 40 00 01 00 00 80 00
00 20 02 19 00 00 00 00 00 10 12 00 32 00 32 0D
80 00 00 00 00 00 00 00 80 00 06 30 30 30 30 33
36
  
 
 ![image](https://cdn.discordapp.com/attachments/674412377818529831/944241269004173332/unknown.png)
 
 *Also, you can test using Postman and you will get a JSON Response.

  
