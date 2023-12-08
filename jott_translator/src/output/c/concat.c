/* concat.c */
#include "concat.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* define the concat function */
char * concat(char * s0, char * s1) {

    int strSize = strlen(s0) + strlen(s1) + 1;
    char * resultStr = (char *)malloc(strSize);
    strcpy(resultStr, s0);
    strcat(resultStr, s1);

    return resultStr;

}