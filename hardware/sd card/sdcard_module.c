#include <avr/io.h>
#include <ctype.h>
#include <SPI.h>
#include <SD.h>

#define F_OSC  16000000
#define BAUD   9600
//baud rate calculation
#define X    ((F_OSC /16 / BAUD) - 1)
#define DELAY_MS 100

void usart_init(){
   UBRR0H = (X >> 8);//get first 8 bits
   UBRR0L = X; //get last 8 bits
  
   
   //used asynchronous mode,no parity ,one stop bit
   
   //8 bit data frame
   UCSR0C |= (1 << UCSZ01) | (1 << UCSZ00);
   
}

void usart_send(char c){
  while(!(UCSR0A & (1 << UDRE0))){
    //wait previous byte to be sent
  }
  //character byte to be transmitted serially is written into the UDR0 register
  UDR0 = c;
}

char usart_receive(){
  //RXC0 flag bit of the UCSR0A register is monitored for a HIGH to see if an entire character has been received yet
  while(!(UCSR0A & ( 1 << RXC0))){
    //wait
  }
  return UDR0;//return received byte
}

void println(char arr[]){
  for(int i = 0 ; i < strlen(arr) ; i++){
    usart_send(arr[i]);
  }
  usart_send('\n');
}

void print(char arr[]){
  for(int i = 0 ; i < strlen(arr) ; i++){
    usart_send(arr[i]);
  }
}

File myFile;

int main(){
   //configure pin 5 of PORTB for output.
   DDRD = DDRD | (1<<4);

   //Enable the USART transmitter
   UCSR0B |= (1 << TXEN0);

   //Enable the USART receiver
   UCSR0B |= (1 << RXEN0);

   //initiallize serial communication
   usart_init();

   //print line in serial monitor
   println("Initializing SD card. . . ");

   if(!SD.begin(4)){
     println("Initialization Failed");
     while(1);
   }

   println("Initialization done . ");

   if(SD.exists("example.txt")){
     println("example.txt exists.");
   }
   else{
     println("example.txt doesn't exist.");
   }

   //open a new file and immediately close it.
   println("Creating example.txt.");
   myFile = SD.open("example.txt", FILE_WRITE);

   //if the file opened successfully ,write to it.
   if(myFile){
     myFile.write("CO321,08:00,09:00,2021-04-21,lecture room 2");
     myFile.write("Nuwan Harsha,E/16/286");
     myFile.write("Saubhagya Munasinghe,E/16/242");
     myFile.write("Erandana Wijerathna,E/16/399");
     println("Writing to file successful");
     myFile.close();
   }
   else{
    //if file didn't open,then there is an error
    println("Error opening file.");
   }

   //re open the file for reading
   myFile = SD.open("example.txt");

   if(myFile){
    println("example.txt: ");
    //read from the file until there's nothing else in it.
    while(myFile.available()){
      println(myFile.read());
    }
    //close the file
    myFile.close();
   }
   else{
     //if file didn't open,then there is an error
     println("Error  opening file.");
   }

}