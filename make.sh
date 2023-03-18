#$/bin/bash

#Compile and load the programm
javac Main.java 2>/dev/null
java Main

#Then, when the program is loaded in memory, delete all .class extension
rm */*/*/*.class 2>/dev/null
rm */*/*.class 2>/dev/null
rm */*.class 2>/dev/null
rm *.class 2>/dev/null
exit 0;