#include "Hlavicka.h"
#include <stdio.h>
#include <cstdlib>
#include <string.h>


/*typedef enum {
	KRAT,
	MINUS,
	PLUS,
	KRAT_CISLO
} znamenko;*/

int main() {
	bool m1Nactena = false;
	struct matice* m1 = (struct matice*)malloc(sizeof(struct matice));
	struct matice* m2 = (struct matice*)malloc(sizeof(struct matice));
	for (int x = 0; x < 5; x++) {
		for (int i = 0; i < 5; i++) {
			m1->hodnoty[x][i] = 0;
			m2->hodnoty[x][i] = 0;
		}
	}
	double kratCislem = 0;
	znamenko operace;
	FILE* fp;
	FILE* fout;
	char cislo[100] = { 0 };
	int takesToLoadm1 = 0;
	char lines[11][100];
	int lineNumber = 0;
	fopen_s(&fp, "C:\\Users\\marti\\OneDrive\\Programing\\C\\C\\MaticovaKalkulacka\\txt.txt", "r");
	if (fp != NULL) {
		while (fgets(lines[lineNumber], sizeof(lines[lineNumber]), fp) != NULL) {
			printf("got line: %s", lines[lineNumber]);
			lineNumber++;
		}
		for (int x = 0; x < 11; x++) {
			int position = 0;
			int numbers = 0;
			if (lines[x][0] != '|') {
				if (m1Nactena == false) 
				{
					for (int i = 0; i < 100; i++) {
						if (lines[x][i] < 0) {
							break;
						}
						if (lines[x][i] != ';') {
							cislo[position] = lines[x][i];
							position++;
						}
						else {
							position = 0;
							double a = atof(cislo);
							m1->hodnoty[x][numbers] = a;
							numbers++;
							m1->sirka = numbers;
							m1->vyska = x + 1;
							memset(cislo, 0, 100);
						}
					}
				}
				else 
				{
					if (operace == KRAT_CISLO) {
						for (int i = 0; i < 100; i++) {
							if (lines[x][i] < 0) {
								break;
							}
							if (lines[x][i] != ';') {
								cislo[position] = lines[x][i];
								position++;
							}
							else {
								position = 0;
								double a = atof(cislo);
								kratCislem = a;
								memset(cislo, 0, 100);
								break;
							}
						}
					}
					else {
						if (operace < KRAT_CISLO) {
							for (int i = 0; i < 100; i++) {
								if (lines[x][i] < 0) {
									break;
								}
								if (lines[x][i] != ';') {
									cislo[position] = lines[x][i];
									position++;
								}
								else {
									position = 0;
									double a = atof(cislo);
									m2->hodnoty[x - takesToLoadm1][numbers] = a;
									numbers++;
									m1->sirka = numbers;
									m1->vyska = x - takesToLoadm1 + 1;
									memset(cislo, 0, 100);
								}
							}
						}
					}
				}
			}
			else {
				switch (lines[x][1]) {
				case '+':
					operace = PLUS;
					break;
				case '-':
					operace = MINUS;
					break;
				case '*':
					operace = KRAT_CISLO;
					break;
				case 'x':
					operace = KRAT;
					break;
				case 'd':
					operace = DETERMINANT;
					break;
				case 'h':
					operace = HODNOST;
					break;
				case 'i':
					operace = INVERSNI_MATICE;
					break;
				case 't':
					operace = TRANSPONOVANA_MATICE;
					break;
				}
				m1Nactena = true;
				takesToLoadm1 = x + 1;
			}
		}

		fopen_s(&fout, "c:\\temp\\1.txt", "w");
		switch (operace) {
		case 1:
			maticeToFile(soucet(m1, m2), fout);
			break;
		case 2:
			maticeToFile(odecet(m1, m2), fout);
			break;
		case 3:
			maticeToFile(nasob(m1, m2), fout);
			break;
		case 4:
			maticeToFile(nasobCislem(m1, kratCislem), fout);
			break;
		case 5:
			fprintf(fout, "%lf:", determinant(trojuhelnikovyTvar(m1)));
			break;
		case 6:
			trojuhelnikovyTvar(m1)
			fprintf(fout, "%d:", hodnostMatice(m1));
			break;
		case 7:
			maticeToFile(inversniMatice(m1), fout);
			break;
		case 8:
			maticeToFile(transponovana(m1), fout);
			break;
		}
		_fcloseall();
	}
}
