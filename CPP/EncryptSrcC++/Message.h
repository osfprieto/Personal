//Message.h
#include <iostream>
#include <string>
#include <stdio.h>
#include "table.h"

#ifndef MESSAGE_H
#define MESSAGE_H
using namespace std;

class Message
{
	public:
		Message(const char* pStr, const char* key);
		Message(const char* pStr);
		Message();
		
		void encryptMessage();
		void decryptMessage();
		
		const char* getUnMessage() const;
		const char* getEnMessage() const;
		
		void getMessage();
		void getKey();
		
		~Message();
		
	private:
		char* pUnMessage;
		char* pEnMessage;
		char* pKey;
};

//Default constructor
Message::Message()
{}

//Constructor
Message::Message(const char* pStr, const char* key)
{	
	int i, j;
	
	pUnMessage = new char[ strlen(pStr) + 1 ];
	strcpy(pUnMessage, pStr);
	 
	pEnMessage = new char[ strlen(pStr) + 1 ];
	strcpy(pEnMessage, pStr);
	
	pKey = new char[ strlen(pStr) + 1 ];
	strcpy(pKey, pStr);

	for(i = 0, j = 0; i < strlen(pStr), j < strlen(pStr); i++, j++)
	{	
		if(key[j] && pKey[i])
		{
			pKey[i] = key[j];
		}
		else if(key[j] == '\0')
		{
			j = -1;
			i--;
		}
		else
			break;
	}
}

//Constructor for only the message, not the key
Message::Message(const char* pStr)
{
	pUnMessage = new char[ strlen(pStr) + 1 ];
	strcpy(pUnMessage, pStr);
	
	pEnMessage = new char[ strlen(pStr) + 1 ];
	strcpy(pEnMessage, pStr);
}

//Encrypts message and returns encrypted message
void Message::encryptMessage()
{
	int across, down;
	int i, j;
	
	
	cout << "Encrypting message..." << endl;
	cout << endl << pKey << endl << pUnMessage << endl << endl;
	for(j = 0; j < strlen(pEnMessage); j++)
	{
		for(i = 0; i < 26; i++)
		{
			if( !(isalpha(pUnMessage[j])))
			{
				across = 30;
				break;
			}
			
			if( ( toupper(pUnMessage[j]) == table[0][i]))
			{
				across = i;
				break;
			}
		}
		
		for(i = 0; i < 26; i++)
		{	
			if( !(isalpha(pUnMessage[j])))
			{
				down = 30;
				break;
			}
				
			if( ( toupper(pKey[j]) == table[i][0]))
			{
				down = i;
				break;
			}
		}
		
		if(across != 30 && down != 30)
			pEnMessage[j] = table[down][across];
	}	
	cout << pEnMessage << endl << endl;
}

void Message::decryptMessage()
{
	int down, across, i, j;
	
	cout << "Decrypting message..." << endl;
	cout << endl << pKey << endl << pEnMessage << endl << endl;
		
	for(j = 0; j < strlen(pEnMessage); j++)
	{
		for(i = 0; i < 26; i++)
		{
			if( !(isalpha(pKey[j])))
			{
				across = 30;
				break;
			}
			
			if( ( toupper(pKey[j]) == table[0][i]))
			{
				across = i;
				break;
			}
		}
		
		for(i = 0; i < 26; i++)
		{
			if( !(isalpha(pEnMessage[j])))
			{
				down = 30;
				break;
			}
			
			if( ( toupper(pEnMessage[j]) == table[i][across]))
			{
				down = i;
				break;
			}
		}
		
		if(down != 30)
			pUnMessage[j] = table[down][0];
	}

	cout << pUnMessage << endl;
}

//Returns unencrypted message
const char* Message::getUnMessage() const
{
	if(pUnMessage)
		return pUnMessage;
	else
		return "null pointer (should never happen, default constructor is private";
}

//Returns encrypted message
const char* Message::getEnMessage() const
{
	if( strcmp(pEnMessage, pUnMessage))
		return pEnMessage;
	else
		return "Message not yet encrypted";
}


//Gets message to encrypt
void Message::getMessage()
{	
	char* pTemp = new char[256];
	
	cout << "Enter your message (less than 256 characters, end with \"ENTER\": " << endl;
	fflush(stdin);
	cin.getline(pTemp, 256, '\n');
	cout << endl;
	
	pUnMessage = new char[ strlen(pTemp) + 1 ];
	strcpy(pUnMessage, pTemp);
	
	pEnMessage = new char[ strlen(pTemp) + 1 ];
	strcpy(pEnMessage, pTemp);
	
	delete[] pTemp;
}

void Message::getKey()
{	
	if(!pUnMessage)
	{
		cout << "You have to enter the message before you enter the key." << endl;
		return;
	}
	
	char* pTemp = new char[32];
	
	cout << "Enter your key (less than 32 characters, one word) that ends with \"ENTER\": " << endl;
	fflush(stdin);
	cin.getline(pTemp, 32, '\n');
	cout << endl;
	
	pKey = new char[ strlen(pUnMessage) + 1 ];
	strcpy(pKey, pUnMessage);
	
	int i, j;
	
	for(i = 0, j = 0; i < strlen(pUnMessage), j < strlen(pUnMessage); i++, j++)
	{	
		if(pTemp[j] && pKey[i])
		{
			pKey[i] = pTemp[j];
		}
		else if(pTemp[j] == '\0')
		{
			j = -1;
			i--;
		}
		else
			break;
	}
	
	delete[] pTemp;
}


//Destructor
Message::~Message()
{
	delete[] pUnMessage;
	delete[] pEnMessage;
	delete[] pKey;
}

#endif
