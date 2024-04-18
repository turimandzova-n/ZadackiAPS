#include <iostream>
using namespace std;
int main ()
{
int niza[5] = {10, 100, 200, 3000, 5000};
int i, *pok;
pok = niza;
for ( i = 0; i < 5; i++)
{
cout<<"Adresata na niza ["<<i<<"]="<< pok;
cout<<"Vrednosta na niza ["<<i<<"]="<< *pok;
pok++; 
}
return 0;
}
