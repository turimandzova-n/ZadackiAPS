#include <iostream>
using namespace std;
int main()
{
int b;
int *p;
b=7;
p=&b;
cout<<" Adresata na b e" << &b << "\n Vrednosta na p e " << p;
cout<<"Vrednosta na b e "<< b << "\n Vrednosta na *p e "<< *p;
return 0;
}
