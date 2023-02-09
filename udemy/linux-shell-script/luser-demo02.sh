#!/bin/bash 

# Display the UID and username of the user executing this script 
# Display if the user is the root user or not.

# Display the UID 
echo "Your UID is ${UID}"

# Display the username 

#same result by tick mark
#USER_NAME=`id -un`
USER_NAME=$(id -un)
echo "Your username is ${USER_NAME}"

# Display if the user is the root user or not.
if [[ "${UID}" -eq 0 ]]
then
  echo 'u r root.'
else
  echo 'u r not root'
fi






