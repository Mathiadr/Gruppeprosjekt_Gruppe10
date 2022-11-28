# Gruppeprosjekt_Gruppe10
Gruppe 10 - Software &amp; Engineering prosjekt


Hvordan kjøre og teste prototypen

Finn frem zip. mappen til prosjektet, høyreklikk på zip. mappen og velg “pakk ut alle”.  Det er viktig se hvilken lokasjon filene blir pakket ut i. Det vil være standard å pakke ut filene der zip. mappa allerede ligger, hvis ikke ny lokasjon blir valgt.

Man åpner så IntelliJ,  hvis det ikke er noen prosjekter åpne, så velger man open, øverst til høyre i vinduet. Du finner så lokasjonen hvor zip. mappa ble pakket ut, og dobbeltklikker på hele mappa for å åpne det i IntelliJ. Hvis det allerede er et åpent prosjekt i IntelliJ, så må man velge  file øverst til venstre, for så å trykke open. Dette vil ta deg til filutforskeren, hvor du på samme måte som forklart tidligere, finner lokasjonen til mappen og klikker på selve mappa, og trykker open.


Kjøring av prototypen: Prosjektet vil nå åpne seg i IntelliJ, og for å kjøre prototypen må man dobbeltklikke på “src” mappa, deretter “main” mappa, før man høyreklikker på klassen Main (blå sirkel med C inni). Når man har høyreklikket får man opp en meny, hvor man da blar nedover til der det står “Run ‘Main.main()’ ”, og trykker denne. Hvis det ikke står “Run” der med en gang, så kan det være lurt å dobbeltklikke på selve klassen, og høyreklikken i vinduet der koden viser seg.  Det vil ta noen sekunder å bygge “Gradle” før den vil kjøre selve prototypen. 
Det ligger en feil som oppstår ved bruk av Gradle sammen med Java Swing, dette kan gi feilen:
 Execution failed for task ':Main.main()'.
> Process 'command 'C:/Users/marti/.jdks/openjdk-17.0.2/bin/java.exe'' finished with non-zero exit value 1

Dette er en kjent feil mellom Gradle og Java Swing, og er dessverre ikke noe vi får gjort noe med. Hvis denne feilen, dukker opp så må man bare høyreklikke på “Main” klassen og kjøre på nytt som forklart ovenfor. Prototypen skal da kjøre som normalt.

Når prototypen kjører er det bare å legge til biler, leie biler eller redigere de bilene som allerede er lagt inn. Om ikke alt vises i GUI vinduet, så kan det være nødvendig å skalere vinduet ved å dra det ut eller ta full skjerm.


Kjøring av tester: For å kjøre testene må man også gå inn i “src” mappa, men i stedet for å åpne “main”, så åpner man “test” mappen. hvis IntelliJ ikke åpne java mappen automatisk, så må man man dobbeltklikke på denne også. I denne mappen vil man se tre forskjellige “pakker” som vil inneholde tester ut ifra hva de tester. Man åpner så den mappen man har lyst til å se testene til, ved å dobbeltklikke. Hvis man for eksempel vil kjøre tester som ligger i “modules.test”, så gå man inn i mappen, høyreklikker på test klassen (blå sirkel med C inni). Og velger “Run ‘testnavn’ ”.  Dette vil da igangsette testene, og man vil få opp en slags meny som viser hvilken tester som kjøres, og om det blir det riktige utfallet. Dette er markert ved en grønn “hake” ved riktig eller “passed test.
Man kan også kjøre alle testene samtidig, ved å høyreklikke på selve java mappa under “test”, og trykke på “Run ‘Tests’ “.
    
Ved kjøring av GUI testene oppstår det en error som sier:
> Duplicate method name "$$$getFont$$$" with signature "(Ljava.lang.String;IILjava.awt.Font;)Ljava.awt.Font;" in class file forms/CarRental
Det er samme som med feilen i prototypen, og skjer på grunn av en feil mellom Gradle og java swing, som gjør at metodene for å kjøre en “frame” eller vindu, ikke kan foregå flere steder. Hvis denne feilen oppstår, så er det bare kjøre testene på nytt, så skal de kjøres riktig. 
