#!/bin/bash

# This script displays various information to the screen.

# Display 'Hello'
echo 'Hello'

# Assign a value to a variable 
WORD='script'

# Display that value using the variable.
echo "$WORD"

# Demonstrate that single quotes cause variables to Not get expanded
echo '$WORD'

# combine the variable with hard-coded text.
echo "1.This is a shell $WORD"

# Display the contetns of the variable using an alternative syntax.
echo "2.This is a shell ${WORD}"

# Append text to the variable. 
echo "${WORD}ing is fun!"

# Show how NOT to append text to a variable.
# This doen't work;
echo "$WORDing is fun!"

# Create a new variable 
ENDING='ed'

# Combine the two variables.
echo "This is ${WORD}${ENDING}."

# Change the value stored in the ENDING variable (Reassignment.)
ENDING='ing'
echo "${WORD}${ENDING} is fun!"

# Reaasign value to ENDING.
ENDING='s'
echo "You are going to write many ${WORD}${ENDING} in this class~"
