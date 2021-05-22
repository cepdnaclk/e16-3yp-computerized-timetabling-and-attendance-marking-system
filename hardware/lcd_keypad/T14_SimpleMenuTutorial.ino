
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Keypad.h>

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

void setup() {
  Serial.begin(9600);
  lcd.begin();
  lcd.backlight();
  pinMode(upButton, INPUT_PULLUP);
  pinMode(downButton, INPUT_PULLUP);
  pinMode(selectButton, INPUT_PULLUP);
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
    char key = keypad.getKey();
    if(key){
      lecRoom[count]=key;
      count++;
      lcd.print(key);
    }
  }
}

//student registration action execute
void action2() {
  lcd.clear();
  lcd.print("  > 2nd Page <");
   lcd.setCursor(0, 1);
  lcd.print("  ------------");
  delay(1500);
}
void action3() {
  lcd.clear();
  lcd.print("WELCOME !!!");
}
