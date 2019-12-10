#include <string.h>
#include <xutility>
#include <stdio.h>
#include "Header.h"

struct hudba* prvni = NULL;
void show() // tisk aut
{
	struct hudba* aktHudba = prvni; // ukazatel na aktualni auto
	while (aktHudba) // prochazeni seznamu
	{
		printf("%d: %s-%s\n", aktHudba->rok, aktHudba->Interpret, aktHudba->Album); // tisk radku
		aktHudba = aktHudba->dalsi; // posun na dalsi auto
	}
}
void add(const char* Interpret, const char* Album, int rok)
{
	struct hudba* novaHudba;
	struct hudba* aktHudba = prvni;
	novaHudba = (struct hudba*)malloc(sizeof(struct hudba));
	strcpy_s(novaHudba->Interpret, 20, Interpret);
	strcpy_s(novaHudba->Album, 20, Album);
	novaHudba->rok = rok;
	novaHudba->dalsi = NULL;
	if (prvni == NULL) // linearni seznam je prazdny
	{
		prvni = novaHudba;
		return;
	}
	else
	{
		aktHudba = prvni;
		if (strcmp(aktHudba->Interpret, novaHudba->Interpret) < 0) {
			novaHudba->dalsi = prvni;
			prvni = novaHudba;
			return;
		}
		while (aktHudba->dalsi != NULL &&
			strcmp(aktHudba->dalsi->Interpret, novaHudba->Interpret)<0)
		{
			aktHudba = aktHudba->dalsi;
		}
		novaHudba->dalsi = aktHudba->dalsi;
		aktHudba->dalsi = novaHudba;
	}
}