
#include <iostream>
#include "Header.h"
#pragma warning( disable : 4996)
int main() {
	while (true) {
		char jmeno[20];
		printf("Zadej jmeno toho kokota:");
		scanf("%s", &jmeno);
		char album[20];
		printf("Zadej album toho kokota:");
		scanf("%s", &album);
		printf("Zadej Rok toho kokota:");
		int rok;
		scanf("%d", &rok);
		add(jmeno, "ss",11);
		show();
	}
	return 1;
}
