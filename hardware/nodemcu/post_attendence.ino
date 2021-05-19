

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>

const char* ssid = "SLT-ADSL-54E53";
const char* password = "1996may08";

//Your Domain name with URL path or IP address with path
String serverName = "http://192.168.1.21:8080";

// the following variables are unsigned longs because the time, measured in
// milliseconds, will quickly become a bigger number than can be stored in an int.
unsigned long lastTime = 0;
// Timer set to 10 minutes (600000)
//unsigned long timerDelay = 600000;
// Set timer to 5 seconds (5000)
unsigned long timerDelay = 5000;

void setup() {
  Serial.begin(115200); 

  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());

  if(WiFi.status()== WL_CONNECTED){
      HTTPClient http;

      String serverPath = serverName + "/attendance/addrecord";
      
      // Your Domain name with URL path or IP address with path
      http.begin(serverPath);

      String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJub2RlbWN1IiwiZXhwIjoxNjIxNDIyOTc3LCJpYXQiOjE2MjE0MDQ5Nzd9.wECDMp9tyxW0oXUEkPwrPQqlyv_0SoAGS-Bait_nPmUXGWx34BvgVQq2jw4zQvXTaSH0RIHlf0oqtIa28r2Wgg";
      
      http.addHeader("Content-Type", "application/json");
      http.addHeader("Authorization", "Bearer " + token );
      String httpRequestData = "{\"courseNumber\" : \"CO324\" ,\"studentRegNoList\" : [ \"E/16/286\" , \"E/16/242\" ] }";
      
      // Send HTTP GET request
      int httpResponseCode = http.POST(httpRequestData);
      
      if (httpResponseCode>0) {
        Serial.print("HTTP Response code: ");
        Serial.println(httpResponseCode);
        String payload = http.getString();
        Serial.println(payload);
      }
      else {
        Serial.print("Error code: ");
        Serial.println(httpResponseCode);
      }
      // Free resources
      http.end();
    }
    else {
      Serial.println("WiFi Disconnected");
    }
}

void loop() {
  
  /*if ((millis() - lastTime) > timerDelay) {
   
    lastTime = millis();
  }*/
}
