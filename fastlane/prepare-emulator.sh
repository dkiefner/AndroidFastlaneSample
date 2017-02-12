#!/bin/bash

EMULATOR_CMD="$ANDROID_SDK/tools/emulator"
ADB_CMD="$ANDROID_SDK/platform-tools/adb"

MAX_TIME_TO_WAIT_FOR_EMULATOR_IN_SECONDS=60

EMULATOR_FOR_TESTS=$1

function printInstalledEmulators {
	echo "Installed emulators:"
	eval "${EMULATOR_CMD} -list-avds"
}

function checkParameters {
	if [ -z $ANDROID_SDK ]; then
	echo '$ANDROID_SDK is not set.'
	exit -1
	fi

	if [ -z $EMULATOR_FOR_TESTS ]; then
		echo 'No emulator was set.'
		echo "Usage: $(basename $0) <emulator-name>"
		printInstalledEmulators
		exit -1
	fi
}

function killAllEmulators {
	sh ./kill-emulators.sh
}

function waitForEmulatorToComeOnline {
	echo "Waiting for emulator $EMULATOR_FOR_TESTS to come online..."
	WAIT_COUNTER=0
	WAIT_FOR_DEVICE_CMD="$ADB_CMD wait-for-device shell getprop init.svc.bootanim"
	until $WAIT_FOR_DEVICE_CMD | grep -m 1 stopped; do
	  echo "ping..."
	  sleep 1
	  ((WAIT_COUNTER++))

	  if [ "$WAIT_COUNTER" -eq "$MAX_TIME_TO_WAIT_FOR_EMULATOR_IN_SECONDS" ]; then
	  	echo "Emulator is not coming up after $MAX_TIME_TO_WAIT_FOR_EMULATOR_IN_SECONDS seconds."
	  	killAllEmulators
	  	break
	  fi

	done
}

function startEmulator {
	echo "starting emulator $EMULATOR_FOR_TESTS and wipe content..."
	START_EMULATOR_CMD="$EMULATOR_CMD -avd $EMULATOR_FOR_TESTS -wipe-data"
	eval "${START_EMULATOR_CMD}" &>/dev/null &disown

	waitForEmulatorToComeOnline
}

checkParameters
printInstalledEmulators

echo "Running devices:"
RUNNING_DEVICES_CMD="$($ADB_CMD devices)"
RUNNING_DEVICES="${RUNNING_DEVICES_CMD}"
echo "$RUNNING_DEVICES"

if [[ $RUNNING_DEVICES == *"emulator"* ]]; then
	echo "Found running emulator(s)."
	killAllEmulators
fi

startEmulator
