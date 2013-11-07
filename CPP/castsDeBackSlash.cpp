#include<iostream>
using namespace std;
//sequence (valid ones are  \b  \t  \n  \f  \r  \"  \'  \\ )
int main(){
    
    cout<<"1. bla\nbla"<<endl;
    cout<<"2. bla\bbla"<<endl;
    cout<<"3. bla\tbla"<<endl;
    cout<<"4. bla\fbla"<<endl;
    cout<<"5. bla\rbla"<<endl;
    cout<<"6. bla\\bla"<<endl;
    cout<<"7. bla\'bla"<<endl;
    cout<<"8. bla\"bla"<<endl;
    
    
    system("pause");
    return 0;
}
