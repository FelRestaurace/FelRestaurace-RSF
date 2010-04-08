Verze modulu pokladna z 22.11.2009 16:53
----------------------------------------------------------------
Opraveny všechny dosud zjištěné chyby
Funkční filtrování účtu podle jména a stavu
Funční přiřazování stavu účtu při jeho vytváření
Přidána možnost změnit stav účtu při jeho prohlížení z přehledu účtu

V současnosti není známa žádná funční chyba. Při případném nálezu prosím neprodleně hlaste.

Problém se zobrazováním tlačítek účtů mimo vymezenou oblast při velkém množství založených účtů
se vztahuje k bodu "optimalizace pro dotykový diplej", vyžaduje pouze "povrchovou" úpravu gui,
a není proto brán jako funční chyba.

Stále je třeba doimplementovat:
Výběr slevy při objednání položky
Nabídka slev podle zvolené osoby (to bude asi vyžadovat celou novou tabulku v databázi - UserDiscountType)
Možnost tisknout zobrazené účtenky
Optimalizace pro dotykový displej





Verze modulu pokladna s upraveným serverem z 16.11.2009 14:32
----------------------------------------------------------------

Hlavní změny:
Filtrování účtů podle stolů, osob ...
Přehled každého účtu se všemi položkami a nezaplacenými položkami
Nový formulář pro vkládání zákazníků
Přidány a upraveny komentáře v javadoc ve všech třídách klientské části
Vytvořen export z testovací databáze

Známé chyby:
Nefungující metody
 - getAccountByName(String name)
 - getAccountStatusTypeByName(String name)
 - getAccountStatusTypeById(int Id)
přes důkladné debuggování klienta i serveru stále nevím proč
Z toho důvodu nefunguje
 - filtrování účtu podle jména
 - filtrování účtu podle stavu
 - přiřazování stavu účtu při jeho vytváření (stav je tam natvrdo v kódu)
Při neexistujících instancích v databázi (např. tříd Account, Table) vyhazuje NullPointerException (to by mělo být v pohodě, jenom jsem zatím líný to ošetřit)

Zatím chybí:
Výběr slevy při objednání položky
Nabídka slev podle zvolené osoby (to bude asi vyžadovat celou novou tabulku v databázi - UserDiscountType)
Možnost tisknout zobrazené účtenky
Optimalizace pro dotykový displej