#!/bin/bash

/usr/java/default/bin/java -jar HwxCbdAzureVaultApp.jar  -f ambarildapsync.sh -sk ambariadminpass,ldapbindpass

exitValue=$?

if [ $exitValue != 0 ]
then
exit $exitValue
fi