#!/bin/bash

minorFactor=1000
majorFactor=1000000

if [ -z $1 ]
  then
    echo "no version provided"
    exit -1
fi

IFS='.' read -ra parts <<< "$1"

if [ ${#parts[@]} -ne 3 ]; then
    echo "wrong version type: $1"
    exit -1
fi

echo $((${parts[0]} * majorFactor + ${parts[1]} * minorFactor + ${parts[2]}))
