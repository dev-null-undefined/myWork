#pragma once
#include <string.h>
#include <xutility>
#include <stdio.h>
#include "Header.h"

struct hudba
{
	char Interpret[20];
	char Album[20];
	int rok;
	struct hudba* dalsi;
};
void show();
void add(const char* Interpret, const char* Album, int rok);