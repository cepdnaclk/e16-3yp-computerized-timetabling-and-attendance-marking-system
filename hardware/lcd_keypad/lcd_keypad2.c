#include <avr/io.h>
#include <util/delay.h>
#include <ctype.h>

#define LCDsData   				PORTB
#define LCDsControl 			PORTC
#define ENABLE   				4
#define ReadWrite  			   	3
#define RS   					2
#define LcdDataDir				DDRB
#define DataDirLCDsControl 	DDRC

void wait(void){
  	//enable the lcd
	LCDsControl |= (1 << ENABLE);
  	//very short delays
	asm volatile ("nop");
	asm volatile ("nop");
  	//disable the lcd
	LCDsControl &= ~(1 << ENABLE);
}

void isBusy(){
  	//read data from the lcd
	 LcdDataDir = 0;
	 LCDsControl |= (1 << ReadWrite); //set controles for read lcd
	 LCDsControl &= ~(1 << RS);
		
  	 //check 7th bit of lcd, PB3
	 while(LCDsData >= 0x08){
	 	wait();
	 }
	
  	//make lcd data PORTB as output
	 LcdDataDir = 0x0F;
}

void Lcd_CmdWrite(char cmd)
{
  	isBusy();
    LCDsData |= (cmd >>4 & 0x0F);     //Send higher bits
  	LCDsControl &= ~ (1 << ReadWrite | 1 << RS  ); //set lcd controles
   	wait();
  	LCDsData = 0; //clear the data
	
  	isBusy();
   	LCDsData |= ( cmd & 0x0F); //Send Lower bits
    LCDsControl &= ~ (1 << ReadWrite | 1 << RS  ); //set lcd controles
   	wait();
  	LCDsData = 0; //clear the data
}

void Lcd_DataWrite(char dat)
{
  	isBusy();
    LCDsData |= ((dat >>4) & 0x0F);      //Send higher bits
    LCDsControl &= ~ (1 << ReadWrite);
	LCDsControl |= (1 << RS  ); //set lcd controles
	wait();
	LCDsData = 0; //clear the data

  	isBusy();
    LCDsData |= (dat & 0x0F);  //Send Lower bits
   	LCDsControl &= ~ (1 << ReadWrite);
	LCDsControl |= (1 << RS  ); //set lcd controles
	wait();
	LCDsData = 0; //clear the data

}
void clearScreen(){
	Lcd_CmdWrite(0x01);  //clear screen 0x01 = 00000001
	_delay_ms(50);
} 

void init(){
  
  	//set Enable ReadWrite and RS as output
	DataDirLCDsControl |= (1 << ENABLE   ) | (1 << ReadWrite) | (1 << RS  );
	_delay_ms(15);
	clearScreen();
	Lcd_CmdWrite(0b00001110);
	_delay_ms(50);
  	Lcd_CmdWrite(0x02);        // cursor to the upper left-hand corner
 	 _delay_ms(50);
    Lcd_CmdWrite(0x28);        //	Using two lines, 4-bit mode
  	_delay_ms(50);
   	Lcd_CmdWrite(0x0E);        // Display ON, cursor blinking
  	_delay_ms(50);
   	Lcd_CmdWrite(0x80);        // Move the cursor to beginning of first line
  	_delay_ms(50);
}

 
void Send_A_String(char *StringOfCharacters){
	while(*StringOfCharacters > 0) {
		Lcd_DataWrite(*StringOfCharacters++);
	}
}

/*******KeyPad******/
char keyPadScan(){

  //get the column
  uint8_t keyPressCode = PIND;
  //reverse the data direction,and input/output s by xoring
  DDRD ^= 0b11111111;
  PORTD ^= 0b11111111;
  
  //time delay
  //this will need after creating some kind of change to a register  
  asm volatile("nop");
  asm volatile("nop");
  //get the row
  //combine row and column
  keyPressCode |= PIND;
  
  //select the button pressed and store in result
  char result = '/';
  if(keyPressCode ==  0b11101110)result =  '1';
  if(keyPressCode ==  0b11011110)result =  '2';
  if(keyPressCode ==  0b10111110)result =  '3';
  if(keyPressCode ==  0b01111110)result =  'A';
  if(keyPressCode ==  0b11101101)result =  '4';
  if(keyPressCode ==  0b11011101)result =  '5';
  if(keyPressCode ==  0b10111101)result =  '6';
  if(keyPressCode ==  0b01111101)result =  'B';
  if(keyPressCode ==  0b11101011)result =  '7';
  if(keyPressCode ==  0b11011011)result =  '8';
  if(keyPressCode ==  0b10111011)result =  '9';
  if(keyPressCode ==  0b01111011)result =  'C';
  if(keyPressCode ==  0b11100111)result =  '*';
  if(keyPressCode ==  0b11010111)result =  '0';
  if(keyPressCode ==  0b10110111)result =  '#';
  if(keyPressCode ==  0b01110111)result =  'D';
  return result;
}

void initKeyPad(){
  //assign as outputs pin 0-3 ,inputs pin 4-7
  DDRD = 0b00001111;
  //clear outputs pins
  PORTD = 0b11110000;
  //very short delay
  asm volatile("nop");
  asm volatile("nop");
}

char nextButtonPress(){
  char value;
  while(1){
    if(PIND != 0b11110000){//check whether button is clicked or not
      //if button is clicked scan it
      value = keyPadScan();
      break;
    }
  }
  return value;
}

char caesarcipher(char c,int key){
    char ans = c;
    if(isalpha(c) != 0){//check whether c is an alphabet
      ans = (char)(((int)c - (int)'A' +key )%26 + (int)'A');
    }
  return ans;
}
/*******EEFROM******/
void EEFROMwrite(unsigned int address , char data){
  
  //wait for completion of previous write
  while(EECR & (1 << EEPE));
  
  //set up address and data regs
  EEAR = address;
  EEDR = data;
  
  //write logical 1 to EEMPE
  EECR |= (1 << EEMPE);
  
  //start eefrom write
  EECR |= (1 << EEPE);
  
}  
char EEFROMread(unsigned int address){
  
  //wait for completion of writes
  while(EECR & (1 << EEPE));
  
  //set up address
  EEAR = address;
  
  //start eefrom read
  EECR |= (1 << EERE);
  
  //return data
  return EEDR;
}  

int main(void){
  init();//for LCD
  
  Send_A_String("select encrypt=1");
  Lcd_CmdWrite(0x80 + 0x40 +0);//go to second line
  Send_A_String("change the key=2");
  
  initKeyPad();//initialize the keypad 

  char select = nextButtonPress();
  if(select != '1' && select != '2'){
    Send_A_String("Error");
    _delay_ms(2000);
    return -1;
  }
  char c;
  if(select == '2'){//changing key selected
    //ask to input the key
    clearScreen();
    Send_A_String("Enter key :");
    Lcd_CmdWrite(0x80 + 0x40 +0);//go to second line
    Send_A_String("Press * to OK");
    
    //writing key to EEFROM
    unsigned int address = 0;
    while(1){
      c = nextButtonPress();
      if(c == '*'){
        EEFROMwrite(address,'\0');
        break;
      }
      EEFROMwrite(address++,c);
    }
    //success message
    clearScreen();
    Send_A_String("key Stored");
    Lcd_CmdWrite(0x80 + 0x40 +0);//go to second line
    Send_A_String("successfully");
    _delay_ms(1000);
  }
  
  //convert key to a integer
  int key = 0;
  unsigned int address = 0;
  while(1){
    //get key which is stored in eefrom 
      c = EEFROMread(address++);
      if(c == '\0'){
        break;
      }
      key = key*10 + (c - '0');
  }
  
  //encryption
  clearScreen();
  Send_A_String("Enter text :");
  Lcd_CmdWrite(0x80 + 0x40 +0);//go to second line
    Send_A_String("Press * to OK");
  
  _delay_ms(1000);
  clearScreen();//clear the screen

  Send_A_String("Encrypted text:");
  Lcd_CmdWrite(0x80 + 0x40 +0);//go to second line
  
  int count = 0;//input count
  //input encrypted text using keypad
  while((c = nextButtonPress()) != '*'){
    Lcd_DataWrite(caesarcipher(c,key));//display ciphered character in LCD
    count++;
    if(count == 10)break;//if no of characters=10 exit
  }
  
  //successfully encrypted message
  _delay_ms(5000);
  clearScreen();
    Send_A_String("text encrypted");
    Lcd_CmdWrite(0x80 + 0x40 +0);//go to second line
    Send_A_String("successfully");
    _delay_ms(1000);

  return 0;
}