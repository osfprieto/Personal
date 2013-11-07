#include<iostream>
using namespace std;
int buscarCuadro(int *size, int from, int max);
void counting_sort(int *A, int size, int range);
int main(){
    //while(true)
    {
    int sticks, i, j, temp, cont=0, found, *size;
    cin>>sticks;
    size = new int[sticks];
    for(i=0;i<sticks;i++){
        cin>>size[i];
    }
    
    //organizar ascendentemente
    
    //counting sort
    counting_sort(size, sticks, 101);
    
    //bubble sort
    /*for(i=0;i<sticks;i++)
        for(j=0;j<sticks;j++)
            if(size[j]>size[i]){
                temp = size[i];
                size[i] = size[j];
                size[j] = temp;
            }*/
    
    /*for(i=0;i<sticks;i++)
        cout<<size[i]<<'\t';
    
    cout<<endl;*/
    
    found=0;
    
    while(found<sticks){
        found = buscarCuadro(size, found, sticks);
        if(found<=sticks)
            cont++;
        //cout<<found<<" ";
    }
    
    cout<<cont<<endl;
    
    delete[] size;
    }
    return 0;
}

int buscarCuadro(int *size, int from, int max){
    
    while(from<max && from+1<max && size[from]!=size[from+1])
        from++;
    
    if(from==max || from+1==max)
        return max+1;//llegó al final y no cuenta
    //sino, es que encontró los dos primeros cuadros
    
    from+=2;
    
    while(from<max && from+1<max && size[from]!=size[from+1])
        from++;
        
    if(from==max || from+1==max)
        return max+1;//llegó al final y no cuenta
    
    from+=2;
    
    return from;
}

void counting_sort(int *A, int size, int range) {
  // tmp array
  int *B = new int[size];
  // array for counting indexes
  int *C = new int[range + 1];
  for (int i = 0; i <= range; ++i)
    C[i] = 0;
  // count appearances of values
  for (int i = 0; i < size; ++i)
    ++C[A[i]];
  // count the places where values
  // should be after sorting -
  // just add previous indexes counts
  for (int i = 1; i <= range; ++i)
    C[i] += C[i - 1];
  // copy values from A to the right
  // place in B, and decrement C[A[i]] by one -
  // because you already place value on this index
  for (int i = size - 1; i >= 0; --i) {
    B[C[A[i]] - 1] = A[i];
    --C[A[i]];
  }
  // copying back to A
  for (int i = 0; i < size; ++i)
    A[i] = B[i];
  delete[] B;
  delete[] C;
}
