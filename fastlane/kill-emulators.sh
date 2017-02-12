#!/bin/bash

ADB_CMD="$ANDROID_SDK/platform-tools/adb"

function checkParameters {
	if [ -z $ANDROID_SDK ]; then
	echo '$ANDROID_SDK is not set.'
	exit -1
	fi
}

checkParameters

echo "Killing all running emulators..."
eval "${ADB_CMD} emu kill"
