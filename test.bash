#!/bin/bash

cd output/c

for FILE in *
do
        IFS='.'
        read -ra dotarr <<< $FILE

        if [[ ${dotarr[0]} == $1 ]]
        then
                if [[ ${dotarr[1]} == "c" ]]
                then
                        gcc ${dotarr[0]}.c concat.c -o ${dotarr[0]}
                        cout=$(./${dotarr[0]})
                        echo "C output:"
                        echo $cout
                        echo ""
                fi
        fi
done

cd ..
cd java

for FILE in *
do
        IFS='.'
        read -ra dotarr <<< $FILE

        if [[ ${dotarr[0]} == $1 ]]
        then
                if [[ ${dotarr[1]} == "java" ]]
                then
                        javac ${dotarr[0]}.java
                        jout=$(java ${dotarr[0]})
                        echo "Java output:"
                        echo $jout
                        echo ""
                fi
        fi
done

cd ..
cd python

for FILE in *
do
        IFS='.'
        read -ra dotarr <<< $FILE

        if [[ ${dotarr[0]} == $1 ]]
        then
                if [[ ${dotarr[1]} == "py" ]]
                then
                        pout=$(python ${dotarr[0]}.py)
                        echo "Python output:"
                        echo $pout
                        echo ""
                fi
        fi
done

if [[ $cout == $jout ]]
then
        if [[ $jout == $pout ]]
        then
                echo "Test Passed!"
                echo ""
        else
                echo "java and python outputs do not match"
                echo $jout
                echo $pout
        fi
else
        echo "c and java outputs do not match"
        echo $cout
        echo $jout
fi
