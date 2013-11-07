#include<iostream>
using namespace std;

void quicksort(int *a, int s, int k);

int main(){
    int n, i, j, temp;
    cin>>n;
    int a[n];
    for(i=0;i<n;i++)
        cin>>a[i];
        
    quicksort(a, 0, n-1);
    
    /*for(i=0;i<n;i++)
        for(j=i;j<n;j++)
            if(a[i]>a[j]){
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
    
    for(i=0;i<n;i++)
        cout<<a[i]<<endl;*/
    
    i=0;
    while(i<n-1 && a[i+1]-a[i]<2)
        i++;
    
    cout<<a[i]+1;
    //system("pause");
}

void quicksort(int *a, int s, int k){
    int left = s, right = k;
    int med = a[s+(s+k)/2], temp;
    do{
        while(a[left]<med)
            ++left;
        while(a[right]>med)
            --right;
        if(left<=right){
            temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            ++left;
            --right;
        }
    }while(left<right);
    if(left<right)
        quicksort(a, left, k);
    if(s<right)
        quicksort(a, s, right);
}
