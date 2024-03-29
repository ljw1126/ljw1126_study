#!/bin/bash 

# Display the UID and username of the suer executing this script.
# Display if the user is the leejinwoo user or not.

# Display the UID.
echo "Your UID is ${UID}"

# Only display if the UID does NOT match 1000.  ( sudo ./filename )
UID_TO_TEST_FOR='1000'
if [[ "${UID}" -ne "${UID_TO_TEST_FOR}" ]]
then 
  echo "Your UID does not match ${UID_TO_TEST_FOR}."
  exit 1
fi

# Display the username.
USER_NAME=$(id -un)

# Test if the command succeeded.
if [[ "${?}" -ne 0 ]]
then 
  echo 'The id command did not execute succesfully.'
  exit 1
fi 
echo "Your username is ${USER_NAME}"

# You can use a string test conditional.
USER_NAME_TO_TEST_FOR='leejinwoo'
if [[ "${USER_NAME}" = "${USER_NAME_TO_TEST_FOR}" ]]
then 
  echo "Your username matches ${USER_NAME_TO_TEST_FOR}."
fi

# Test for != (not equal) fro the string.
if [[ "${USER_NAME}" != "${USER_NAME_TO_TEST_FOR}" ]]
then
  echo "Your username does not match ${USER_NAME_TO_TEST_FOR}."
  exit 1
fi

# end :: successful 
exit 0

