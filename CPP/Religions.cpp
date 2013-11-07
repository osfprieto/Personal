#include<iostream>
using namespace std;

int main() {
    
	int n, m, bel1, bel2, last, i, j, temp, relCount, caso=1;
	
	cin>>n>>m;
	
	while(n!=0 && m!=0){
		int religiones[n];
		
		for(i=0;i<n;i++)
			religiones[i]=0;
		
		last = 1;
		
		for(i=0;i<m;i++){
            cin>>bel1>>bel2;
            bel1--;
            bel2--;
            
			if(religiones[bel1]==0 && religiones[bel2]==0){
				religiones[bel1]=last;
				religiones[bel2]=last;
				last++;
			}
			else if(religiones[bel1]>0 && religiones[bel2]>0){
				temp = religiones[bel2];
				for(j=0;j<n;j++){
					if(religiones[j]==temp){
						religiones[j]=religiones[bel1];
					}
				}
			}
			else if(religiones[bel1]>0){
				religiones[bel2]=religiones[bel1];
			}
			else if(religiones[bel2]>0){
				religiones[bel1]=religiones[bel2];
			}
		}
		
		relCount=0;
		
		for(i=0;i<n;i++){
			if(religiones[i]==0){
				relCount++;
			}
			else if(religiones[i]>=0){
				relCount++;
				temp = religiones[i];
				for(j=i;j<n;j++){
					if(religiones[j]==temp){
						religiones[j]=-1;
					}
				}
			}
		}
		
		cout<<"Case "<<caso++<<": "<<relCount<<endl;
		
		cin>>n>>m;
	}
}
