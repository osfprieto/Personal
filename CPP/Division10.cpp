#include<iostream>
using namespace std;

int nums[10];
bool valid;

int main() {
	int n;
	
	cin>>n;
	while(n!=0){
		
		iniciarNums();
		valid=true;
		
		while(!division(n) && valid){
			nextNum();
		}
		imprimirNum(n);
		
		cin>>n;
	}

    return 0;
}

void iniciarNums(){
	nums[0]=0;
	nums[1]=1;
	nums[2]=2;
	nums[3]=3;
	nums[4]=4;
	nums[5]=5;
	nums[6]=6;
	nums[7]=7;
	nums[8]=8;
	nums[9]=9;
}

void nextNum(){
	
	
	
	if(nums[0]=0 && nums[1]==1 && nums[2]==2 && nums[3]==3 && nums[4]==4 && nums[5]==5 && nums[6]==6 && nums[7]==7 && nums[8]==8 && nums[9]==9)
	   valid=false;
}

public static boolean division(int n){
	int a=0, b=0, i;
    for(i=0;i<5;i++){
        a*=10;
        b*=10;
        a+=nums[i];
        b+=nums[i+5];
    }
    if(a/b==n)
        return true;
    return false;
}
public static void imprimirNum(int n){
	if(valid){
        int i;
        for(i=0;i<5;i++)
            cout<<nums[i];
        cout<<" / ";
        for(i=5;i<10;i++)
            cout<<nums[i];
        cout<<" = "<<n<<endl;
    }
    else{
        cout<<"There are no solutions for "<<n<<"."<<endl;
    }
}
	
