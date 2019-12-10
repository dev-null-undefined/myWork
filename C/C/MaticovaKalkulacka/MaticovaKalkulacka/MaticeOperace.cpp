#include "Hlavicka.h"
#include <cstdlib>
#include <stdio.h>
struct matice* soucet(struct matice* m1, struct matice* m2) {
	struct matice* vysledek = (struct matice*)malloc(sizeof(struct matice));
	for (int x = 0; x < 5; x++) {
		for (int i = 0; i < 5; i++) {
			vysledek->hodnoty[x][i] = m1->hodnoty[x][i]+ m2->hodnoty[x][i];
		}
	}
	return vysledek;
}
struct matice* odecet(struct matice* m1, struct matice* m2) {
	struct matice* vysledek = (struct matice*)malloc(sizeof(struct matice));
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			vysledek->hodnoty[x][i] = m1->hodnoty[x][i] - m2->hodnoty[x][i];
		}
	}
	return vysledek;
}
struct matice* nasobCislem(struct matice* m1, double cislo) {
	struct matice* vysledek = (struct matice*)malloc(sizeof(struct matice));
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			vysledek->hodnoty[x][i] = m1->hodnoty[x][i] * cislo;
		}
	}
	return vysledek;
}
struct matice* nasob(struct matice* m1, struct matice* m2) {
	struct matice* vysledek = (struct matice*)malloc(sizeof(struct matice));
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			vysledek->hodnoty[x][i] = 0;
			for (int o = 0; o < m1->max; o++) {
				vysledek->hodnoty[x][i] = vysledek->hodnoty[x][i] + (m1->hodnoty[x][o] * m2->hodnoty[o][i]);
			}
		}
	}
	return vysledek;
}
struct matice* trojuhelnikovyTvar(struct matice* m1) {
	m1->determinantdelic = 1;
	for (int p = 0; p < m1->max; p++) {
		if (m1->hodnoty[p][p] == 0) {
			for (int x = p + 1; x < m1->max; x++) {
				if (m1->hodnoty[x][p]!=0) {
					for (int k = 0; k < m1->max; k++) {
						double temp;
						temp = m1->hodnoty[p][k];
						m1->hodnoty[p][k] = m1->hodnoty[x][k];
						m1->hodnoty[x][k] = temp;
					}
					break;
				}
			}
		}
		//printf("Matice mensi o %d\n",p);
		//tiskMatice(m1);
		for (int x = p+1; x < m1->max; x++) {
			if (m1->hodnoty[x][p] != 0) {
				double a = m1->hodnoty[p][p];
				//printf("radek:%d,", x);
				//printf("a:%lf,", a);
				m1->determinantdelic *= a;
				double b = m1->hodnoty[x][p];
				//printf("b:%lf.\n", b);
				for (int i = 0; i < m1->max; i++) {
					m1->hodnoty[x][i] = m1->hodnoty[x][i] * a - b * m1->hodnoty[p][i];
				}
			}
		}
		//printf("\n\n");
	}
	return m1;
}
int hodnostMatice(struct matice* m1) {
	int o = 0;
	for (int p = 0; p < m1->max; p++) {
		bool readekNenulovy = false;
		for (int x = 0; x < m1->max; x++) {
			if (m1->hodnoty[p][x] != 0)
			{
				readekNenulovy = true;
				continue;
			}
		}
		if (readekNenulovy == true) {
			o++;
		}
	}
	return o;
}
void tiskMatice(struct matice* m1) {
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			printf("%6.2lf:", m1->hodnoty[x][i]);
		}
		printf("\n");
	}
}
double determinant(struct matice* m1) {
	double number = 1;
	for (int x = 0; x < m1->sirka && x < m1->vyska; x++) {
		number *= m1->hodnoty[x][x];
	}
	return number/m1->determinantdelic;
}
struct matice* inversniMatice(struct matice* m1) {
	struct matice* vysledek = (struct matice*)malloc(sizeof(struct matice));
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			if (i == x) {
				if (i >= m1->sirka|| x >= m1->vyska) {
					vysledek->hodnoty[x][i] = 0;
				}
				else {
					vysledek->hodnoty[x][i] = 1;
				}
			}
			else {
				vysledek->hodnoty[x][i] = 0;
			}
		}
	}
	tiskMatice(vysledek);
	printf("\n\nVysledekPo:\n");
	for (int p = 0; p < m1->max; p++) {
		if (m1->hodnoty[p][p] == 0) {
			for (int x = p + 1; x < m1->max; x++) {
				if (m1->hodnoty[x][p] != 0) {
					for (int k = 0; k < m1->max; k++) {
						double temp;
						temp = m1->hodnoty[p][k];
						m1->hodnoty[p][k] = m1->hodnoty[x][k];
						m1->hodnoty[x][k] = temp;
					}
					break;
				}
			}
		}
		for (int x = p + 1; x < m1->max; x++) {
			if (m1->hodnoty[x][p] != 0) {
				double a = m1->hodnoty[p][p];
				double b = m1->hodnoty[x][p];
				for (int i = 0; i < m1->max; i++) {
					vysledek->hodnoty[x][i] = vysledek->hodnoty[x][i] * a - b * vysledek->hodnoty[p][i];
					m1->hodnoty[x][i] = m1->hodnoty[x][i] * a - b * m1->hodnoty[p][i];
				}
			}
		}
	}
	for (int p = m1->max-1; p>=0; p--) {
		for (int x = p - 1; x >= 0; x--) {
			if (m1->hodnoty[x][p] != 0) {
				double a = m1->hodnoty[x][p];
				double b = m1->hodnoty[p][p];
				for (int i = 0; i < m1->max; i++) {
					vysledek->hodnoty[x][i] = vysledek->hodnoty[x][i] * b - a * vysledek->hodnoty[p][i];
					m1->hodnoty[x][i] = m1->hodnoty[x][i] * b - a * m1->hodnoty[p][i];
				}
			}
		}
	}
	for (int x = 0; x < m1->sirka; x++) {
			double delic = m1->hodnoty[x][x];
			for (int i = 0; i < m1->max; i++) {
				vysledek->hodnoty[x][i] /= delic;
			}
	}
	return vysledek;
}
struct matice* transponovana(struct matice* m1) {
	double temp;
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			if (x == i) {
				break;
			}
			temp = m1->hodnoty[x][i];
			m1->hodnoty[x][i] = m1->hodnoty[i][x];
			m1->hodnoty[i][x] = temp;
		}
	}
	return m1;
}

void maticeToFile(struct matice* m1,FILE* fout) {
	char buffer[500];
	for (int x = 0; x < m1->max; x++) {
		for (int i = 0; i < m1->max; i++) {
			fprintf(fout, "%6.2lf:", m1->hodnoty[x][i]);
		}
		fprintf(fout, "\n");
	}
}