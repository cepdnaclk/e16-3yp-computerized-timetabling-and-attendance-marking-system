
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>
#include <SPI.h>
#include <MFRC522.h>
#include <string.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);
const byte ROWS = 4; //four rows
const byte COLS = 4; //three columns
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','O','D'}
};
byte rowPins[ROWS] = {4,5,6,7}; //connect to the row pinouts of the keypad
byte colPins[COLS] = {8,9,10,11}; //connect to the column pinouts of the keypad
Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );
int upButton = 1;
int downButton = 2;
int selectButton = 3;
int menu = 0;
char lecRoom[3];
//no of chars
int count=0;

void updateMenu();
void executeAction();
void action1();
void action2();
void action3();
char getChar();
bool contains(String str);
bool waitForConfirmationFromAdmin(unsigned long initial_time);
void registerStudent(String userid);
void readDetails();
void stuRegister();
void failMessage();

#define RST_PIN         9           // Configurable, see typical pin layout above
#define SS_PIN          10          // Configurable, see typical pin layout above
int currentIndex = 0;
const int MAX_SIZE = 20; 
//string array to store enumbers of tags
String Enumbers[MAX_SIZE];
unsigned long initial_time;
unsigned long register_confirmation_period = 60000;
String AdminId = "6384c13e";

MFRC522::MIFARE_Key key;
  
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance


void setup() {
  Serial.begin(115200);
  lcd.begin();
  lcd.backlight();
  pinMode(upButton, INPUT_PULLUP);
  pinMode(downButton, INPUT_PULLUP);
  pinMode(selectButton, INPUT_PULLUP);
  updateMenu();

  SPI.begin();               // Init SPI bus
  mfrc522.PCD_Init();        // Init MFRC522 card
  Serial.print("Attendance marking System - UOP");
}


void updateMenu() {
  switch (menu) {
    case 0:
     lcd.clear();
     lcd.print("WELCOME !!!");
     break;
    case 1:
      lcd.clear();
      lcd.print(">>SET ROOM NO");
      lcd.setCursor(0, 1);
      lcd.print("REGISTER STUDENT");
      break;
    case 2:
      lcd.clear();
      lcd.print("SET ROOM NO");
      lcd.setCursor(0, 1);
      lcd.print(">>REGISTRATION");
      break;
    case 3:
      lcd.clear();
      lcd.print(">>EXIT");
      break;
    case 4:
      menu = 3;
      break;
  }
}

void executeAction() {
  switch (menu) {
    case 1:
      action1();
      break;
    case 2:
      action2();
      break;
    case 3:
      action3();
      break;
  }
}

void action1() {
  lcd.clear();
  lcd.print("ROOM NO :");
  lcd.setCursor(0, 1);
  while(1){
    char key = getChar();
    if(key){
      lecRoom[count]=key;
      count++;
      lcd.print(key);
      delay(5000);
      menu = 0;
      updateMenu();
      return;
    }
  }
}

//student registration action execute
void action2() {
  stuRegister();
}

void action3() {
  menu = 0;
  updateMenu();
}

char getChar(){
  while(true){
    if (Serial.available() > 0) {
      // read the incoming byte:
      int incomingByte = Serial.read();
      return (char)incomingByte;
    }
  }
  return ')';
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//check whether Enumber in the current tag is in previously recorded set of tags.
bool contains(String str){
//  Serial.println("Inside Loop");
  for(int i = 0;i < currentIndex;i = i + 1){
    if(Enumbers[i] == str){
      return true;
    }
  }
  return false;
}

bool waitForConfirmationFromAdmin(unsigned long initial_time){
  while(millis()-initial_time < register_confirmation_period){
      if ( ! mfrc522.PICC_IsNewCardPresent()) {
        continue;
      }
    
      // Select one of the cards
      if ( ! mfrc522.PICC_ReadCardSerial()) {
        continue;
      }
    
      Serial.print(F("Card UID:"));    //Dump UID
      String userid;
      for (byte i = 0; i < mfrc522.uid.size; i++) {
        Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
        Serial.print(mfrc522.uid.uidByte[i], HEX);
        userid += String(mfrc522.uid.uidByte[i], HEX);
      }
      Serial.println();
      bool isAdmin = (userid == AdminId);
      if(isAdmin){
        Serial.println(F("Registration Confirmed By Admin"));
        lcd.clear();
        lcd.print("Registration");
        lcd.setCursor(0, 1);
        lcd.print("Confirmed");
        delay(5000);
        return true;  
      }
      else{
        Serial.println(F("Please Enter a Admin RFID card for confirmation."));
        lcd.clear();
        lcd.print("Enter Valid");
        lcd.setCursor(0, 1);
        lcd.print("Admin RFID");
      }
      delay(5000); //change value if you want to read cards faster

      mfrc522.PICC_HaltA();
      mfrc522.PCD_StopCrypto1();  
  }
  lcd.clear();
  lcd.print("Timeout.");
  lcd.setCursor(0, 1);
  lcd.print("Try Again.");
  delay(5000);
  return false;
}

void stuRegister(){
  lcd.clear();
  lcd.print("Enter RFID...");

  while(true){
    // Prepare key - all keys are set to FFFFFFFFFFFFh at chip delivery from the factory.
    for (byte i = 0; i < 6; i++) key.keyByte[i] = 0xFF;

    // Reset the loop if no new card present on the sensor/reader. This saves the entire process when idle.
    if ( ! mfrc522.PICC_IsNewCardPresent()) {
      continue;
    }

    // Select one of the cards
    if ( ! mfrc522.PICC_ReadCardSerial()) {
      continue;
    }

    Serial.print(F("\nCard UID:"));    //Dump UID
    String userid;
    for (byte i = 0; i < mfrc522.uid.size; i++) {
      Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
      Serial.print(mfrc522.uid.uidByte[i], HEX);
      userid += String(mfrc522.uid.uidByte[i], HEX);
    }
    Serial.println();
    registerStudent(userid);
    break;
  }

}
void registerStudent(String userid){
  byte buffer[34];
  byte block;
  MFRC522::StatusCode status;
  byte len;

  Serial.setTimeout(60000L) ;     // wait until 60 seconds for input from serial
  // Ask personal data: E number
  Serial.println(F("Type E number , ending with #"));
  lcd.clear();
  lcd.print("Enter E number");
  len = Serial.readBytesUntil('#', (char *) buffer, 30) ; // read family name from serial
  for (byte i = len; i < 30; i++) buffer[i] = ' ';     // pad with spaces
  buffer[len] = '\0';
  block = 1;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("PCD_Authenticate() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }
  else Serial.println(F("PCD_Authenticate() success: "));

  // Write block
  status = mfrc522.MIFARE_Write(block, buffer, 16);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("MIFARE_Write() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }
  else Serial.println(F("MIFARE_Write() success: "));

  block = 2;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("PCD_Authenticate() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  // Write block
  status = mfrc522.MIFARE_Write(block, &buffer[16], 16);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("MIFARE_Write() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }
  else Serial.println(F("MIFARE_Write() success: "));

  // Ask personal data: First name
  Serial.println(F("Type First name, ending with #"));
  lcd.clear();
  lcd.print("Enter name");
  len = Serial.readBytesUntil('#', (char *) buffer, 20); // read first name from serial
  for (byte i = len; i < 20; i++) buffer[i] = ' ';     // pad with spaces
  buffer[len] = '\0';
  block = 4;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("PCD_Authenticate() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  // Write block
  status = mfrc522.MIFARE_Write(block, buffer, 16);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("MIFARE_Write() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }
  else Serial.println(F("MIFARE_Write() success: "));

  block = 5;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("PCD_Authenticate() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  // Write block
  status = mfrc522.MIFARE_Write(block, &buffer[16], 16);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("MIFARE_Write() failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }
  else Serial.println(F("MIFARE_Write() success: "));

  mfrc522.PICC_HaltA(); // Halt PICC
  mfrc522.PCD_StopCrypto1();  // Stop encryption on PCD  
  
  lcd.clear();
  lcd.print("Enter Admin RFID");
  lcd.setCursor(0, 1);
  lcd.print("To Confirm");

  Serial.println(F("Needs a confirmation from Admin"));
  //wait for confirmation by admin
  //if no confirmation ,then student registration cannot happen
  initial_time = millis();
  bool response = waitForConfirmationFromAdmin(initial_time);
  if(!response){
     Serial.println(F("Registration not successful.\nNo confirmation by admin was found.\nTry again."));
     lcd.clear();
     lcd.print("Timeout.");
     lcd.setCursor(0, 1);
     lcd.print("Try Again.");
     delay(5000);
     menu = 0;
     updateMenu();
     return;
  }

  //registration successful
  //then store id in Enumbers Array
  Enumbers[currentIndex] = userid;
  currentIndex = currentIndex + 1;
  lcd.clear();
  lcd.print("Registration");
  lcd.setCursor(0, 1);
  lcd.print("Successful");
  if(currentIndex == MAX_SIZE){
   Serial.println("Don't add again.currentIndex = MAX_SIZE."); 
  }
  else {
    Serial.println(F("Registration successful "));
  }
  delay(5000);
  menu = 0;
  updateMenu();
}

void readDetails(){
  //some variables we need
  byte block;
  byte len;
  MFRC522::StatusCode status;
  
  Serial.print("Name = ");

  byte buffer1[18];

  block = 4;
  len = 18;

  //------------------------------------------- GET E NUMBER
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, 4, &key, &(mfrc522.uid)); //line 834 of MFRC522.cpp file
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("Authentication failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  status = mfrc522.MIFARE_Read(block, buffer1, &len);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("Reading failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  //PRINT FIRST NAME
  for (uint8_t i = 0; i < 16; i++)
  {
    if (buffer1[i] != 32)
    {
      Serial.write(buffer1[i]);
    }
  }
  String myName = String((char *)buffer1);
  myName.trim();
  String welcome = "Hello ";
  String welcomeName = welcome + myName;
  lcd.clear();
  lcd.print(welcomeName);
  lcd.setCursor(0, 1);


  Serial.println();
  Serial.print("E no = ");
  //---------------------------------------- GET  NAME

  byte buffer2[18];
  block = 1;

  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, 1, &key, &(mfrc522.uid)); //line 834
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("Authentication failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  status = mfrc522.MIFARE_Read(block, buffer2, &len);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("Reading failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    failMessage();
    return;
  }

  //PRINT E number
  for (uint8_t i = 0; i < 16; i++) {
    Serial.write(buffer2[i] );
  }
  String myEnum = String((char *)buffer2);
  myEnum.trim();
  lcd.print(myEnum);

  //----------------------------------------

  Serial.println(F("\n**End Reading**\n"));

  delay(2000); //change value if you want to read cards faster

  updateMenu();
  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();  

}

void failMessage(){
  lcd.clear();
    lcd.print("Failed");
    lcd.setCursor(0, 1);
    lcd.print("Try Again");
    delay(5000);
    menu = 0;
    updateMenu();
}

void loop() {
  if (!digitalRead(downButton)){
    menu++;
    updateMenu();
    delay(100);
    while (!digitalRead(downButton));
  }
  if (!digitalRead(upButton)){
    menu--;
    updateMenu();
    delay(100);
    while(!digitalRead(upButton));
  }
  if (!digitalRead(selectButton)){
    executeAction();
    delay(100);
    while (!digitalRead(selectButton));
  }
  if(menu == 0){
    // Prepare key - all keys are set to FFFFFFFFFFFFh at chip delivery from the factory.
    for (byte i = 0; i < 6; i++) key.keyByte[i] = 0xFF;

    // Reset the loop if no new card present on the sensor/reader. This saves the entire process when idle.
    if ( ! mfrc522.PICC_IsNewCardPresent()) {
      return;
    }

    // Select one of the cards
    if ( ! mfrc522.PICC_ReadCardSerial()) {
      return;
    }

    Serial.print(F("\nCard UID:"));    //Dump UID
    String userid;
    for (byte i = 0; i < mfrc522.uid.size; i++) {
      Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? " 0" : " ");
      Serial.print(mfrc522.uid.uidByte[i], HEX);
      userid += String(mfrc522.uid.uidByte[i], HEX);
    }
    Serial.println();

    bool isAdmin = (userid == AdminId);
    if(isAdmin){
      lcd.clear();
      lcd.print("WELCOME ADMIN");
      Serial.println("You are an admin");
      delay(2000); //change value if you want to read cards faster

      mfrc522.PICC_HaltA();
      mfrc522.PCD_StopCrypto1();  
      updateMenu();
    }
    else {
      bool isPresent = contains(userid);

      if(isPresent){
        Serial.println("Already Registered");
        //Read the RFID 
        readDetails();
      }
      else{
         Serial.println("Not present");
         lcd.clear();
         lcd.print("Not Registered");
         delay(2000);
         updateMenu();

         //write e number and first name to rfid
         // registerStudent(userid);  
      }
    }    
  }
 
}
