# Orange-praktyki-letnie
## Zadanie programistyczne „SpotkajmySię”

Budowanie projektu: `mvn clean compile assembly:single`

Uruchomienie testów `mvn clean test`

Program wymaga plików `.json` z kalendarzami, domyślnie szuka ich w katalogu, w którym znajduje się program.


### Krótki opis działania
1. Wczytanie plików .json i stworzenie obiektów Calendar.
2. Znalezienie wspólnych godzin pracy na podstawie godzin z kalendarzy.
3. Dodanie wszystkich spotkań do uporządkowanej kolekcji (TreeSet).
4. Sprawdzanie, czy można zaplanować spotkanie między początkiem pracy, a pierwszym spotkaniem, między każdymi kolejnymi dwoma spotkaniami oraz między ostanim spotkaniem, a końcem pracy.

    4a. Jeżeli przedział czasu wykracza poza godziny pracy, wyrównanie go do godzin pracy.
   
    4b. Jeżeli przedział jest dłuższy niż zakładany czas spotkania, dodanie przedziału do listy wolnych terminów.
5. Wyświetlenie znalezionych przedziałów.

### Założenia
W algorytmie założono, że wszystkie przedziały nie są dłuższe niż 24h oraz dla każdego przedziału godzina `start` nie jest późniejsza niż godzina `end`.