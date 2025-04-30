# Lietotāja rokasgrāmata
# Automašīnu vadības sistēma

## Ievads

Automašīnu vadības sistēma ir konsoles lietojumprogramma, kas izstrādāta, lai efektīvi uzskaitītu un pārvaldītu informāciju par automašīnām. Programma ļauj uzglabāt, skatīt un organizēt datus par automašīnām ērtā tabulas formātā.

### Programmas mērķi:
- Automašīnu uzskaites automatizācija
- Automašīnu meklēšanas un filtrēšanas procesa vienkāršošana pēc dažādiem parametriem
- Ērtas saskarnes nodrošināšana datu manipulācijai
- Uzticama informācijas uzglabāšana failu sistēmā

### Galvenās funkcijas:
- Jaunu automašīnu pievienošana datubāzei
- Pilna automašīnu saraksta skatīšana
- Automašīnu kārtošana pēc dažādiem parametriem
- Automašīnu filtrēšana pēc noteiktiem kritērijiem
- Esošo ierakstu rediģēšana
- Nevajadzīgo datu dzēšana
- Automātiska datu saglabāšana un ielāde no faila

## Lietotāja saskarnes apraksts

Automašīnu vadības sistēmai ir konsoles saskarne ar krāsu formatējumu labākai lasāmībai un elementu vizuālai nodalīšanai.

### Galvenais logs un izvēlne

Palaižot programmu, jūs redzēsiet virsrakstu "AUTOMAŠĪNU VADĪBAS SISTĒMA" un galveno izvēlni ar pieejamajām darbībām:

```
                                              
          AUTOMAŠĪNU VADĪBAS SISTĒMA          
                                              

PIEEJAMĀS DARBĪBAS:
1. Pievienot mašīnu
2. Parādīt visas mašīnas
3. Kārtot mašīnas
4. Filtrēt mašīnas
5. Rediģēt mašīnu
6. Dzēst mašīnu
7. Saglabāt un iziet
```

Lai izvēlētos darbību, ievadiet atbilstošo ciparu kodu un nospiediet Enter.

### Tabulas datu attēlojums

Informācija par automašīnām tiek attēlota formatētas tabulas veidā:

```
┌───────┬──────────────┬──────────────┬──────────────┬────────┬────────────┐
│ ID    │ Marka        │ Modelis      │ Krāsa        │ Gads   │ Cena       │
├───────┼──────────────┼──────────────┼──────────────┼────────┼────────────┤
│ 1     │ BMW          │ X5           │ Melns        │ 2021   │ 45000.00   │
│ 2     │ Audi         │ A6           │ Balts        │ 2019   │ 32000.00   │
└───────┴──────────────┴──────────────┴──────────────┴────────┴────────────┘
```

Tiek izmantota krāsu kodēšana dažādu informācijas veidu attēlošanai:
- **Zils** - automašīnas marka
- **Violets** - automašīnas krāsa
- **Dzeltens** - izlaides gads
- **Zaļš** - cena
- **Treknraksts** - ID un tabulas virsraksti

### Papildu izvēlnes

Izmantojot kārtošanas un filtrēšanas funkcijas, parādās papildu apakšizvēlnes ar opcijām:

#### Kārtošanas izvēlne:
```
KĀRTOŠANAS OPCIJAS:
1. Pēc ID (augošā secībā)
2. Pēc ID (dilstošā secībā)
3. Pēc cenas (augošā secībā)
4. Pēc cenas (dilstošā secībā)
5. Pēc gada (augošā secībā)
6. Pēc gada (dilstošā secībā)
7. Pēc markas (alfabētiski)
8. Pēc modeļa (alfabētiski)
```

#### Filtrēšanas izvēlne:
```
FILTRĒŠANAS OPCIJAS:
1. Pēc cenas diapazona
2. Pēc markas
3. Pēc krāsas
4. Pēc gada diapazona
5. Pēc ID diapazona
```

## Funkciju apraksts

### 1. Automašīnas pievienošana

Lai pievienotu jaunu automašīnu datubāzei:

1. Galvenajā izvēlnē izvēlieties opciju "1" (Pievienot mašīnu)
2. Sekojiet norādījumiem, lai aizpildītu informāciju:
   - **Marka** - ievadiet markas nosaukumu (tikai burti)
   - **Modelis** - ievadiet modeļa nosaukumu (burti, cipari, atstarpes)
   - **Krāsa** - ievadiet automašīnas krāsu (tikai burti)
   - **Gads** - ievadiet izlaides gadu (skaitlis no 1850 līdz 2025)
   - **Cena** - ievadiet cenu (pozitīvs skaitlis)
3. Pēc visu datu ievadīšanas automašīna tiks pievienota sistēmai ar automātiski piešķirto ID

**Piemērs:**
```
JAUNAS MAŠĪNAS PIEVIENOŠANA
Marka: Audi
Modelis: A4
Krāsa: Melns
Gads: 2020
Cena: 30000

Mašīna pievienota!
```

### 2. Automašīnu saraksta skatīšana

Lai skatītu pilnu automašīnu sarakstu:

1. Galvenajā izvēlnē izvēlieties opciju "2" (Parādīt visas mašīnas)
2. Sistēma parādīs tabulu ar visām automašīnām datubāzē
3. Automašīnas pēc noklusējuma tiek kārtotas pēc ID augošā secībā

Ja datubāze ir tukša, sistēma paziņos: "Nav pievienota neviena mašīna!"

### 3. Automašīnu kārtošana

Lai sakārtotu automašīnu sarakstu pēc dažādiem parametriem:

1. Galvenajā izvēlnē izvēlieties opciju "3" (Kārtot mašīnas)
2. Izvēlieties kārtošanas kritēriju no piedāvātās izvēlnes (1-8)
3. Sistēma parādīs sakārtotu automašīnu sarakstu

**Pieejamie kārtošanas kritēriji:**
- Pēc ID (augošā/dilstošā secībā)
- Pēc cenas (augošā/dilstošā secībā)
- Pēc izlaides gada (augošā/dilstošā secībā)
- Pēc markas (alfabētiskā secībā)
- Pēc modeļa (alfabētiskā secībā)

### 4. Automašīnu filtrēšana

Lai filtrētu automašīnas pēc noteiktiem kritērijiem:

1. Galvenajā izvēlnē izvēlieties opciju "4" (Filtrēt mašīnas)
2. Izvēlieties filtra veidu no piedāvātās izvēlnes (1-5)
3. Sekojiet norādījumiem, lai norādītu filtrēšanas parametrus:

**Filtru veidi:**

#### 4.1. Filtrēšana pēc cenas diapazona:
1. Ievadiet minimālo cenu
2. Ievadiet maksimālo cenu
3. Sistēma parādīs visas automašīnas ar cenu norādītajā diapazonā

#### 4.2. Filtrēšana pēc markas:
1. Ievadiet automašīnas marku
2. Sistēma parādīs visas norādītās markas automašīnas

#### 4.3. Filtrēšana pēc krāsas:
1. Ievadiet automašīnas krāsu
2. Sistēma parādīs visas automašīnas ar norādīto krāsu

#### 4.4. Filtrēšana pēc gadu diapazona:
1. Ievadiet minimālo gadu
2. Ievadiet maksimālo gadu
3. Sistēma parādīs visas automašīnas, kas ražotas norādītajā gadu diapazonā

#### 4.5. Filtrēšana pēc ID diapazona:
1. Ievadiet minimālo ID
2. Ievadiet maksimālo ID
3. Sistēma parādīs visas automašīnas ar ID norādītajā diapazonā

### 5. Automašīnas rediģēšana

Lai rediģētu informāciju par automašīnu:

1. Galvenajā izvēlnē izvēlieties opciju "5" (Rediģēt mašīnu)
2. Sistēma parādīs visu automašīnu sarakstu
3. Ievadiet tās automašīnas ID, kuru vēlaties rediģēt
4. Sistēma parādīs pašreizējo informāciju par automašīnu
5. Katram laukam jums tiks piedāvāts ievadīt jaunu vērtību vai saglabāt pašreizējo:
   - Lai saglabātu pašreizējo vērtību, vienkārši nospiediet Enter
   - Lai mainītu, ievadiet jaunu vērtību un nospiediet Enter
6. Kad visi lauki aizpildīti, izmaiņas tiks saglabātas

**Piemērs:**
```
Ievadi mašīnas ID, ko vēlies rediģēt: 2
Esošā informācija: │ 2     │ Audi         │ A6           │ Balts        │ 2019   │ 32000.00   │
Jaunā marka (vai Enter, lai saglabātu esošo 'Audi'): 
Jaunais modelis (vai Enter, lai saglabātu esošo 'A6'): A8
Jaunā krāsa (vai Enter, lai saglabātu esošo 'Balts'): 
Jaunais gads (vai 0, lai saglabātu esošo 2019): 2020
Jaunā cena (vai 0, lai saglabātu esošo 32000.0): 38000

Mašīna atjaunināta!
```

### 6. Automašīnas dzēšana

Lai dzēstu automašīnu no datubāzes:

1. Galvenajā izvēlnē izvēlieties opciju "6" (Dzēst mašīnu)
2. Sistēma parādīs visu automašīnu sarakstu
3. Ievadiet tās automašīnas ID, kuru vēlaties dzēst
4. Sistēma parādīs informāciju par automašīnu un pieprasīs apstiprinājumu dzēšanai
5. Ievadiet "j", lai apstiprinātu, vai "n", lai atceltu
6. Ja apstiprināsiet, automašīna tiks dzēsta no datubāzes

**Piemērs:**
```
Ievadi mašīnas ID, ko vēlies dzēst: 3
Dzēšamā mašīna: │ 3     │ Toyota       │ Corolla      │ Sarkans      │ 2018   │ 15000.00   │
Vai tiešām vēlaties dzēst šo mašīnu? (j/n): j

Mašīna dzēsta!
```

### 7. Saglabāšana un iziešana

Lai pabeigtu darbu ar programmu un saglabātu visas izmaiņas:

1. Galvenajā izvēlnē izvēlieties opciju "7" (Saglabāt un iziet)
2. Sistēma saglabās visus datus failā "masinas.csv"
3. Programma beigs darbu

Nākamajā programmas palaišanas reizē visi dati tiks automātiski ielādēti no faila.

## Papildu informācija

### Ievades validācija

Sistēma veic stingru ievadīto datu pārbaudi:
- **Marka un krāsa** - tikai burti
- **Modelis** - burti, cipari un atstarpes
- **Gads** - skaitlis diapazonā no 1850 līdz 2025
- **Cena** - pozitīvs skaitlis

Ievadot nepareizus datus, sistēma parādīs attiecīgu ziņojumu un piedāvās atkārtot ievadi.

### Automātiskā saglabāšana

Visas izmaiņas tiek saglabātas failā "masinas.csv" tikai tad, kad programma tiek korekti izbeigta, izmantojot opciju "Saglabāt un iziet". Ja programma tiek aizvērta avārijas kārtā, izmaiņas var tikt zaudētas.

### Datu faila formāts

Dati tiek glabāti CSV formātā šādi:
```
id,marka,modelis,krasa,gads,cena
1,BMW,X5,Melns,2021,45000.0
2,Audi,A8,Balts,2020,38000.0
```

## Biežāk uzdotie jautājumi

**J: Ko darīt, ja esmu ievadījis nepareizus datus?**  
A: Ja vēl neesat apstiprinājis ievadi, vienkārši ievadiet pareizo vērtību. Ja ieraksts jau ir izveidots, izmantojiet rediģēšanas funkciju.

**J: Kāpēc es nevaru ievadīt gadu, kas zemāks par 1850 vai augstāks par 2025?**  
A: Šie ierobežojumi ir ieviesti, lai nodrošinātu datu ticamību, jo pirmās automašīnas parādījās 19. gadsimta vidū.

**J: Kā atcelt filtrēšanu un atkal redzēt visas automašīnas?**  
A: Vienkārši izvēlieties opciju "2" galvenajā izvēlnē, lai skatītu visas automašīnas.

**J: Vai es varu atjaunot dzēstu automašīnu?**  
A: Diemžēl pēc dzēšanas apstiprināšanas datus nevar atjaunot.
