#!/bin/bash

cd jott_translator/src/classes

for FILE in ../../../phase4Tests/*
do 
    IFS='/'
    read -ra splitarr <<< "$FILE"
    IFS='.'
    read -ra dotarr <<< "${splitarr[-1]}"

    if [[ $1 == "c" ]]
    then
        java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/c/${dotarr[0]}.c C
    fi

    if [[ $1 == "j" ]]
    then
        java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/java/${dotarr[0]}.java Java
    fi

    if [[ $1 == "p" ]]
    then
        java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/python/${dotarr[0]}.py Python
    fi

    if [[ -z $1 ]]
    then
        java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/c/${dotarr[0]}.c C
        java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/java/${dotarr[0]}.java Java
        java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/python/${dotarr[0]}.py Python
    fi

done
