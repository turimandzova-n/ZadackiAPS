#include <iostream>
using namespace std;
int main ()
{
int a[100], i, *pok, n;
pok=a;
cout<<"Kolku elementi ima tvojata niza? ";
cin>>n;
cout<<"Vnesi gi elementite na nizata: "<<endl;
for(i=0; i<n; i++)
{
	cout<<"Vnesi go "<<i<<"-ot elemet: ";
	cin>>a[i];
}
for(i=0; i<n; i++)
{
	cout<<"Adresata na nizata ["<<i<<"]="<<pok<<endl;
	cout<<"Vrednosta na nizta ["<<i<<"]="<<*pok<<endl;
	pok++;
}
return 0;
}

