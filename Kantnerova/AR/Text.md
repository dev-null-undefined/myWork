# 😄 HTML & CSS & JS & PhP😄
+ HTML......urcuje strukturu a obsah
+ CSS.......urcuje vzhled (Design)
  - tags
  - index
  - class
+ Java Script..na uzivatelskem pocitaci,rychlejsi
+ Php..........na serveru,bezpecny


## CSS index id 
- index je vzdy jedinecny
- pouziva se na hledani na strance
- napr.: "go to top","go to bottom"....

## Formular
- #### umoznuje uzivateli vlozit:
1) Jedno radkovy inout ```<input type="text">```
2) Vice radkovy input ```<textarea>```
3) volba jedne moznosti ```<input type="radio">```
4) volba vice moznosti ```<input type="checkbox">```
5) potrvzeni (ulozeni dat) ```<input type="submit">```
6) Atd..
   
#### - vzdy musi mit zakladni tag ```<form></form>``` Muze byt rozdelen na:
1) ```<fieldset>```
2) ```<legend>```
  
## OverFlow a Display
- OverFlow
  - vlastnosti OverFlow kontroluji co se stane s obsahem ktery je prilis velky aby se vesel do nejake nami pouzicane plochy 
  - obsah muzeme oriznout, skrolovat, skryt,nebo skrolovat pokudje treba
  - Syntaxe:
    - overflow: scroll;
    - overflow: visible;
    - overflow: hidden;
    - overflow: auto;
  - overflow umoznuje zobrazit bloky se specifickou vyskou(padding) pri float
- Float 
  - vlastnost float urcuje zda (jak) se bude prvek vznaset
  - pouziva se pro pozicovani a formatovani obsahu (text vkontejnerech)
  - Syntaxe:
    - float: letf;
    - float: right;
    - float: none;
    - float: inherit;
- Clear  
  - vlastnost clear urcuje ktere prvky se mohou vznaset vedlesmazaneho prvku a na ktere strane 
  - pouziva se nejcasteji po float
  - Syntaxe:
    - clear: none;
    - clear: left;
    - clear: right;
    - clear: inherit;
- Display
  - zpusob zobrazeni
  - Syntaxe:
    - display: block;(Kazdy prvvek na novem radku)
    - display: inline;(V radku za sebou)
    - display: inline-block;(V radku za sebou s moznostinastavit vysku a sirku)