#include<iostream>
using namespace std;

void sort(int *A, int size, int range);

int main(){
    int n, money, i;
	cin>>n;
	while(n!=0){
		money = 0;
		
		int *count = new int[n];
		int *cost = new int[n];
		
		for(i=0;i<n;i++)
		  cin>>count[i];
		
		for(i=0;i<n;i++)
		  cin>>cost[i];
		
		sort(count, n, 100000);
		sort(cost, n, 100000);
		
		for(i=0;i<n;i++)
			money+=cost[i]*count[n-1-i];
		
		cout<<money<<endl;
		cin>>n;
	}
    return 0;
}

void sort(int *A, int size, int range){
    int *B = new int[size];
    int *C = new int[range+1];
    int i;
    for(i=0;i<=range;i++)
        C[i] = 0;
    for(i=0;i<size;i++)
        C[A[i]]++;
    for(i=1;i<=range;i++)
        C[i]+=C[i-1];
    for(i=size-1;i>=0;i--)
        B[ C[A[i]]-- - 1 ] = A[i];
    for(i=0;i<size;i++)
        A[i] = B[i];
    delete[] B;
    delete[] C;
}
