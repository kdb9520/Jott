#!/bin/bash

cd jott_translator/src/classes

for FILE in ../../../phase4Tests/*
do 
    IFS='/'
    read -ra splitarr <<< "$FILE"
    IFS='.'
    read -ra dotarr <<< "${splitarr[-1]}"
    echo ${dotarr[0]}

    java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/${dotarr[0]}.c C
    java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/${dotarr[0]}.java Java
    java phase4Main ../../../phase4Tests/${dotarr[0]}.jott ../../../output/${dotarr[0]}.py Python

done