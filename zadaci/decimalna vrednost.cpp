#include <iostream>
using namespace std;
int main()
{
float c = 9.0256, d = 2.54687;
float *pok;
pok = &c;
(*pok) += 2.0;
cout << (*pok) << endl; 
pok = &d;
(*pok)--;
cout << (*pok) << endl; 
return 0;
}
