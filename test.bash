#!/bin/bash

cd output/c

for FILE in *
do
    IFS='.'
    read -ra dotarr <<< $FILE

    if [[ "$dotarr[0]" == "$1" ]] 
    then
        if [[ "$dotarr[1]" == "c" ]]
        then
            gcc $FILE -o "$dotarr[0]"
            c_out = ./"$dotarr[0]"
        fi
    fi
done

cd ..
cd java

for FILE in *
do
    IFS='.'
    read -ra dotarr <<< $FILE

    if [[ "$dotarr[0]" == "$1" ]] 
    then
        if [[ "$dotarr[1]" == "java" ]]
        then
            javac $FILE
            j_out = java "$dotarr[0]"
        fi
    fi
done

cd ..
cd python

for FILE in *
do
    IFS='.'
    read -ra dotarr <<< $FILE

    if [[ "$dotarr[0]" == "$1" ]] 
    then
        if [[ "$dotarr[1]" == "py" ]]
        then
            p_out = python "$FILE"
        fi
    fi
done

if [[ "$c_out" == "$j_out" ]]
then
    if [[ "$j_out" == "$p_out" ]]
    then
        echo "Test Passed!"
    else
        echo "java and python outputs do not match"
        echo "$j_out"
        echo "$p_out"
    fi
else
    echo "c and java outputs do not match"
    echo "$c_out"
    echo "$j_out"
fi
