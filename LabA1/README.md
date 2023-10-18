# ZADANIE A1 : Analiza cyklu życia Aktywności

## Zadanie

Podsumuj laboratorium w formie krótkiego tekstowego sprawozdania/opisu: czego się nauczyłeś podczas tego laboratorium.
W szczególności, wypisz sekwencje komunikatów Log/Toast jakie zaobserwowałeś gdy:
1) w trakcie działania aplikacji został naciśnięty przycisk "BACK"
2) w trakcie działania aplikacji został naciśnięty przycisk "Home"
3) gdy aplikacja została aktywowana kliknięciem na jej ikonie w "Home screen" lub "Recent apps" lub "Application launcher"
4) gdy w trakcie działania aplikacji zmieniono orientację urządzenia / ekranu (portrait vs landscape, z odblokowanym autorotate)
5) gdy w trakcie działania aplikacji otrzymano i odebrano połączenie głosowe
6) gdy w trakcie działania aplikacji otrzymano i odebrano wiadomość SMS

## Odpowiedź

1) w trakcie działania aplikacji został naciśnięty przycisk "BACK"
```
2023-10-16 09:48:13.531  4481-4481  MainActivity            com.example.laba1                    I  onPause
2023-10-16 09:48:13.961  4481-4481  MainActivity            com.example.laba1                    I  onStop
2023-10-16 09:48:13.962  4481-4481  MainActivity            com.example.laba1                    I  onDestroy
```

2) w trakcie działania aplikacji został naciśnięty przycisk "Home"
```
2023-10-16 09:49:40.216  4481-4481  MainActivity            com.example.laba1                    I  onPause
2023-10-16 09:49:40.243  4481-4481  MainActivity            com.example.laba1                    I  onSaveInstanceState
2023-10-16 09:49:40.254  4481-4481  MainActivity            com.example.laba1                    I  onStop
```

3) gdy aplikacja została aktywowana kliknięciem na jej ikonie w "Home screen" lub "Recent apps" lub "Application launcher"
W przypadku, gdy aplikacja była zminimalizowana:
```
2023-10-16 09:52:46.659  4781-4781  MainActivity            com.example.laba1                    I  onRestart
2023-10-16 09:52:46.662  4781-4781  MainActivity            com.example.laba1                    I  onStart
2023-10-16 09:52:46.662  4781-4781  MainActivity            com.example.laba1                    I  onResume
```

W przypadku, gdy aplikacja była zamknięta:
```
2023-10-16 09:53:14.831  4850-4850  MainActivity            com.example.laba1                    I  onCreate
2023-10-16 09:53:14.854  4850-4850  MainActivity            com.example.laba1                    I  onStart
2023-10-16 09:53:14.855  4850-4850  MainActivity            com.example.laba1                    I  onResume
```

4) gdy w trakcie działania aplikacji zmieniono orientację urządzenia / ekranu (portrait vs landscape, z odblokowanym autorotate)
```
2023-10-16 09:55:11.218  4850-4850  MainActivity            com.example.laba1                    I  onPause
2023-10-16 09:55:11.219  4850-4850  MainActivity            com.example.laba1                    I  onSaveInstanceState
2023-10-16 09:55:11.227  4850-4850  MainActivity            com.example.laba1                    I  onStop
2023-10-16 09:55:11.228  4850-4850  MainActivity            com.example.laba1                    I  onDestroy
2023-10-16 09:55:11.252  4850-4850  MainActivity            com.example.laba1                    I  onCreate
2023-10-16 09:55:11.273  4850-4850  MainActivity            com.example.laba1                    I  onStart
2023-10-16 09:55:11.273  4850-4850  MainActivity            com.example.laba1                    I  onRestoreInstanceState
2023-10-16 09:55:11.275  4850-4850  MainActivity            com.example.laba1                    I  onResume
```

5) gdy w trakcie działania aplikacji otrzymano i odebrano połączenie głosowe
Po odebraniu połączenia:
```
2023-10-16 09:57:03.296  4850-4850  MainActivity            com.example.laba1                    I  onPause
2023-10-16 09:57:03.946  4850-4850  MainActivity            com.example.laba1                    I  onSaveInstanceState
2023-10-16 09:57:03.947  4850-4850  MainActivity            com.example.laba1                    I  onStop
```

Po zakończeniu połączenia:
```
2023-10-16 09:57:15.850  4850-4850  MainActivity            com.example.laba1                    I  onRestart
2023-10-16 09:57:15.853  4850-4850  MainActivity            com.example.laba1                    I  onStart
2023-10-16 09:57:15.875  4850-4850  MainActivity            com.example.laba1                    I  onResume
```

6) gdy w trakcie działania aplikacji otrzymano i odebrano wiadomość SMS
Po kliknięciu na wiadomość:
```
2023-10-16 09:59:56.816  4850-4850  MainActivity            com.example.laba1                    I  onPause
2023-10-16 09:59:57.636  4850-4850  MainActivity            com.example.laba1                    I  onSaveInstanceState
2023-10-16 09:59:57.638  4850-4850  MainActivity            com.example.laba1                    I  onStop
```
