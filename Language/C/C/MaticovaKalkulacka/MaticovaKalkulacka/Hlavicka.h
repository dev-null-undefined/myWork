#pragma once
#include <stdio.h>
struct matice {
	static const int max = 5;
	double hodnoty[max][max];
	int sirka;
	int vyska;
	double determinantdelic;
};
typedef enum {
	PLUS=1,//matici
	MINUS = 2,//matici
	KRAT = 3,//matici
	KRAT_CISLO = 4,//cislo
	DETERMINANT = 5,//nic
	HODNOST = 6,//nic
	INVERSNI_MATICE = 7,//nic
	TRANSPONOVANA_MATICE = 8,//nic
} znamenko;

void tiskMatice(struct matice* m1);
void maticeToFile(struct matice* m1,FILE* fout);
struct matice* soucet(struct matice* m1,struct matice* m2);// +
struct matice* odecet(struct matice* m1, struct matice* m2);// -
struct matice* nasobCislem(struct matice* m1, double cislo);// *
struct matice* nasob(struct matice* m1, struct matice* m2);// x
struct matice* trojuhelnikovyTvar(struct matice* m1); 
double determinant(struct matice* m1);// d
int hodnostMatice(struct matice* m1);// h
struct matice* inversniMatice(struct matice* m1);// i
struct matice* transponovana(struct matice* m1);// t