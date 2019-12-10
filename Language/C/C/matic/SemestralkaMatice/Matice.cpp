// Matice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <ctype.h>
#include <stdio.h>

#define max 10 //konstanta, maximalna matica je 10x10

char operacia; //operator +,-,*,/
int type;//urcuje 
double m1[max][max];//stlpce, riadky matice 1
double m2[max][max];//stlpce, riadky matice 2
double vysl[max][max];//stlpce, riadky -vysledna matica

int x1,y1;//premenne do ktorej sa zapise skutocny pocet stlpcov a riadkov matice 1
int x2,y2;//premenne do ktorej sa zapise skutocny pocet stlpcov a riadkov matice 2
double cislo=0;//cislo sluzi pri operacii [matica,cislo] a taktiez sluzi ako pomocna premenna
int error=0; //premenna do ktorej sa zapisuje chyba v pripade ze sa operacie medzi maticami nemoze urobit 0-ok, 1-chyba

//deklaracia funkcii
int checkCondition();
double matematickeOperacie(double cislo,char operacia);

int _tmain(int argc, _TCHAR* argv[])
{

	printf(">>-----------------------------KALKULATOR-MATICOWY----------------------------<<\n\n");
	while (cislo==0){
		printf("Zadaj operaciu: +,-,*,/\n");
		scanf_s("%c",&operacia,20);//zadava sa znak operacie +,-,*,/...treti parameter je velkost buffera

		printf("\nVyber: \n");
		printf("1) matica %c cislo\n",operacia);
		printf("2) matica %c matica\n",operacia);
		scanf_s("%d",&type);//---------------------------

		if(operacia=='/' && type==2){ //ak sa vyberie delenie a zaroven sa vybere operacia 2(matica,matica) tak vypise toto
			printf("\n>>PRE DELENIE MATICE MATICOU PROSIM VLOZTE NAMIESTO MATICE B JEJ INVERZNU MATICU(B^-1)<<\n");
		}


		printf("\nZadaj rozmery matice(pocet riadkov stlpcov):\n");
		scanf_s("%d%d",&x1,&y1);//scanf tuna bere 2 parametre za sebou....zadava sa: parameter, enter , parameter


		printf("Zadaj hodnoty matice:\n");

		for(int i=0;i<x1;i++){ //cyklus ktory naplna prvu maticu prvkami, ktore zada uzivatel
			printf("zadaj hodnoty pre riadok %d\n",i+1);
	
			for(int j=0;j<y1;j++){
				scanf_s("%lf",&m1[j][i]);
			}
		}
	
		if(type==2){
			printf("\nZadaj rozmery matice(pocet riadkov stlpcov):\n");//ak sa vybere operacia 2(matica,matica) tak bude pokracovat na plnenie druhej matice
			scanf_s("%d%d",&x2,&y2);//---------------------------

			error=checkCondition();//funkcia ktora kontroluje logicke podmienky ci sa moze z maticami ratat, vrati 0=ak nie je chyba, 1=ak je chyba
			if (error!=1){
				printf("Zadaj hodnoty matice:\n");

				for(int i=0;i<x2;i++){
					printf("zadaj hodnoty pre riadok %d\n",i+1);
			
					for(int j=0;j<y2;j++){
						scanf_s("%lf",&m2[j][i]);//zadavaju sa hodnoty v riadku, zvysenie i-cka posuva na dalsi riadok
					}
				}
			}
		}
		else{
			printf("\nZadaj cislo(pre vypocet cislo %c matica):\n",operacia);
			scanf_s("%lf",&cislo);//uzivatel zada cislo,pre vypocet [cislo operacia matica]
		}


		
		if (error!=1){
			matematickeOperacie(cislo,operacia);

			printf("\n\n");

			printf("\nZelate si novy vypocet ? 1-ano, 2-nie\n");
			scanf_s("%d",&cislo);//ak chce uzivatel vyratat dalsiu maticu tak stlac9 ano....pokial nie tak sa cyklus WHILE ukonci

			if(cislo!=1){
				break;
			}
		}
	
	}
	
	printf("\nDovidenia !\n\n");

	return 0;
}


int checkCondition(){//ci splnuje podmienky na vykonanie operacii

	if ((operacia=='+' || operacia=='-')&&(x1!=x2||y1!=y2)){//ak je operacie + alebo - a zaroven nie su stlpce matice1 rovne so stlpcami matice2 alebo nie su riadky matice1 rovne s riadkami matice2
		printf("\nPri scitani matic musia byt dane matice rovnakeho typu.\nProsim zvolte nove hodnoty.\n\n");
		return 1;
	}
	else if((operacia=='*' || operacia=='/')&&(x1!=y2))//ak je operacie * alebo / a zaroven sa pocet riadkov v matici1 nerovna poctu stlpcov v matici2
	{
		printf("\nPri nasobeni sa musi pocet stlpcov matice A rovnat poctu riadkov matice B.\nProsim zvolte nove hodnoty.\n\n");
		return 1;
	}

	return 0;
}

double matematickeOperacie(double cislo,char operacia){

			if(cislo!=0){//toto sluzi na rozlisenie ci je vypocet [matica,cislo]

				for(int i=0;i<x1;i++){
					for(int j=0;j<y1;j++){
						switch (operacia)
						{
							case '+':
								vysl[j][i]=m1[j][i]+cislo; //scitavanie [matica][cislo]
								break;
							case '-':
								vysl[j][i]=m1[j][i]-cislo; //odcitavanie [matica][cislo]
								break;
							case '*':
								vysl[j][i]=m1[j][i]*cislo; //nasobenie [matica][cislo]
								break;
							case '/':
								vysl[j][i]=m1[j][i]/cislo; //delenie [matica][cislo]
								break;

							default:
								break;
						}
					}
				}
			}
			else if(operacia=='+' || operacia=='-'){//scitanie a odcitanie vo forme [matica,matica]
				for(int i=0;i<x1;i++){
					for(int j=0;j<y1;j++){
						if(operacia=='+'){
							vysl[j][i]=m2[j][i]+m1[j][i];//scitavanie jedlotlivych prvkov v matici
						}
						else{
							vysl[j][i]=m2[j][i]-m1[j][i];//odcitavanie jedlotlivych prvkov v matici
						}
					}
				}	
			}
			else if(operacia=='*' || operacia=='/'){//nasobenie a delenie vo forme [matica,matica]
				for(int i=0;i<x1;i++){//posun na novy riadok
					
					for(int j=0;j<y1;j++){//posun na prvok v riadku
						cislo=0;
						for(int k=0;k<x1;k++){//cyklus na ratanie konkretnej hodnoty [riadok z matice1 * riadok z matice2]
							cislo=cislo+(m1[k][i]*m2[j][k]);
						}
						vysl[j][i]= cislo;
					}
				}
			}

			printf("\nVysledna matica vyzera takto:\n");
			for(int i=0;i<x1;i++){
				for(int j=0;j<y1;j++){
					printf("%0.2lf ",vysl[j][i]);//cyklus vypisuje prvky matice vo formate zaokruhlenom na 2 des. miesta
				}
				printf("\n");
			}

			return 1;
}
