//You need to link gdi32.lib for SetPixel and GetPixel...
//You can also add a thread with while(1) BlockInput(true); but that's up to you...
#include <windows.h>
 
int main()
{
 HDC dcDesktop = GetWindowDC(NULL);
 int scrX=GetSystemMetrics(SM_CXSCREEN);
 int scrY=GetSystemMetrics(SM_CYSCREEN);
  
 srand(GetTickCount());
  
 for(;;)
 {
  int x = rand() % scrX;
  for(int y=scrY;y>0;y--)
   SetPixel(dcDesktop,x,y,GetPixel(dcDesktop,x,y-3));
 } 
 return 0;
}