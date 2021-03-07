#include <avr/io.h>

#define BUAT_RATE 9600
#define FOSC 16000000
#define UBRR (FOSC/16/BUAT_RATE)-1

void usart_init(){
  
  //set the buad rate to the buad rate register
  UBRR0H = (UBRR>>8); //high register
  UBRR0L = UBRR;    //low register

  //select asynchronous mode
  UCSR0C &= ~((1<<UMSEL00) | (1<<UMSEL01));

  //select no parity bit
  UCSR0C &= ~((1<<UPM00) | (1<<UPM01));
  
  //set the data frame as 8bit frame
  UCSR0C |= ( (1<<UCSZ01) | (1<<UCSZ00)); 
  
  //select one stop bit
  UCSR0C &= ~(1<<USBS0);
}

//send a character
void usart_send(unsigned char c){
  
  //Wait for empty transmit buffer
  while ( !( UCSR0A & (0b00100000)) );
  
  //Put data into buffer, sends the data
  UDR0 = c;

}

//recieve a character
unsigned char usart_receive(){
  
  //Wait for data to be received
  while ( !(UCSR0A & (0b10000000)) );
  
  //Get and return received data from buffer
  return UDR0;

}

void EEPROMwrite(unsigned int address,char data){
  //wait  for completion of previous writing
  while(EECR & (1<<EEPE));
  
  //give address and data
  EEAR = address;
  EEDR = data;

  //write logical 1 to EEMPE
  EECR|=(1<<EEMPE);
  
  //start eeprom write
  EECR|= (1<<EEPE);
}

char EEPROMread(unsigned int address){
  
  //wait  for completion of previous writing
  while(EECR & (1<<EEPE));
  
  //give address 
  EEAR = address;
  
  //start EERE read
  EECR |= (1<<EERE);

  //return data
  return EEDR;

}

//read the sentence and write to the memory
void recieveSentence(unsigned char data,unsigned int address){
  
  usart_init();//set the buad rate and data frame size
  UCSR0B |= (1<<TXEN0);//enable data transmitting
  UCSR0B |= (1<<RXEN0);//enable dara recieving

  while( ((data = usart_receive()) != '\r') && (address < 0x400)){ //recieve a sentence from another device

    //write data into EEPROM
    EEPROMwrite(address,data);
    address++;
  }
}

//read the eeprom memory and send data
void sendSentence(unsigned char data,unsigned int address){
  
  address = 0x00;
  while(address < 0x400){//check the memory addresses is valid
    data=EEPROMread(address);//read the memory
    usart_send(data); //send data out of the device
    address++;
  }

}

int main(void){
  
  unsigned char data;
  unsigned int address = 0x00;
  
  recieveSentence(data,address);
  sendSentence(data,address);
  
  return 0;
}