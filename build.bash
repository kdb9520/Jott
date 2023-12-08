#!/bin/bash

cd jott_translator/src
javac nodes/*.java provided/*.java validate/*.java phase4Main.java -d classes
cd ..
cd ..
