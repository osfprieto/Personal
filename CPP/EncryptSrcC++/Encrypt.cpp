//Encrypt.cpp
#include <iostream>

#include "Message.h"
using namespace std;

#define MAXLENGTH 256

void getMessage(char& amessage, char& akey);

int main(int argc, char* argv[])
{
	Message myMessage;
	
	if(argc != 2)
	{
		cout << "usage: " << argv[0] << ".exe -d or -e" << endl;
		system("pause");
		return 0;
	}
	
	if( !strcmp(argv[1], "-d"))
	{
		myMessage.getMessage();
		myMessage.getKey();
		myMessage.decryptMessage();
	}
	
	if( !strcmp(argv[1], "-e"))
	{
		myMessage.getMessage();
		myMessage.getKey();
		myMessage.encryptMessage();
	}
		
	return 0;
}
