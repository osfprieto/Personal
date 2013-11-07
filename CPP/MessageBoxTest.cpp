#include<windows.h>
int main(){
    
    HWND hwnd;
    
    hwnd = CreateWindowEx(
      WS_EX_CLIENTEDGE,
      "Hola",
      "A Bitmap Program",
      WS_OVERLAPPEDWINDOW,
      CW_USEDEFAULT, CW_USEDEFAULT, 320, 240,
      NULL, NULL, NULL, NULL);
    
    ShowWindow(hwnd, 0);
   UpdateWindow(hwnd);
    
    system("pause");
    
    MessageBox(0, "Load of resources failed.", "Error",
               MB_OK | MB_ICONEXCLAMATION);
               return 0;
}
