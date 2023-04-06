#!/bin/bash
echo

# man expr

a=0
while [ "$a" -lt 5 ]
do 
	a=$(expr $a + 1)
	echo $a
done
