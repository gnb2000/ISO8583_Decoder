# ISO8583_Decoder


#Requirements
  - Maven
  - MySql
  - Spring Boot
 
#Commands

  - Run (POST) "http://localhost:8080/fields" to insert ISO 8583 Fields
  - Run (POST) "http://localhost:8080/mtis" to insert ISO 8583 MTIs
  - Run (GET) "http://localhost:8080/decode/(Insert Message)" to decode a message.
  
#Example

  http://localhost:8080/decode/00%2057%2060%2000%2005%2000%2000%2004%2000%2072%203C%2005%2080%2000%20C0%2080%2004%2015%2037%2042%2045%2000%2027%2041%2000%206F%2000%2040%2000%2000%2000%2000%2004%2040%2000%2008%2024%2006%2047%2050%2000%2002%2036%2006%2037%2043%2008%2023%2021%2003%2005%2053%2000%2005%2000%2032%2033%2039%2039%2039%2039%2034%2038%2030%2037%2037%2033%2031%2033%2032%2030%2030%2036%2020%2020%2020%2020%2020%2030%2033%2032%2000%2006%2030%2030%2030%2030%2034%2037
  
 
 ![image](https://cdn.discordapp.com/attachments/674412377818529831/932650449176248350/unknown.png)
 
 *Also, you can test using Postman and you will get a JSON Response.

  
