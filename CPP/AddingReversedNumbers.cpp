#include<iostream>
using namespace std;
int main(){
    long n, in0, in1, i, rin0, rin1, sum;
     cin>>n;
     for(i=0;i<n;i++){
            cin>>in0>>in1;
            rin0 = rin1 = 0;
            while(in0>0){
                rin0 *= 10;
                rin0 += in0%10;
                in0 /= 10;
            }
            while(in1>0){
                rin1 *= 10;
                rin1 += in1%10;
                in1 /= 10;
            }
            sum = rin0 + rin1;
            while(sum%10 == 0)
                sum/=10;
            while(sum>0){
                cout<<(sum%10);
                sum/=10;
            }
            cout<<endl;
    }
    return 0;
}

/*


#include<iostream>
using namespace std;

int reverse(int x);

int main(){
	int T, k, a, b, sum;
	cin>>T;
	for(k=0;k<T;k++){
		cin>>a>>b;
		a = reverse(a);
		b = reverse(b);
		sum = a+b;
		cout<<reverse(sum)<<endl;
	}
	return 0;
}

int reverse(int x){
	int retorno = 0;
	while(x>0){
		retorno += x%10;
		retorno *= 10;
		x /= 10;
	}
	x/=10;
	return retorno;
}
*/
