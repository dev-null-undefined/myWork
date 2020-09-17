#!/bin/bash
if [ -z "$1" ]; then
    echo "No argument supplied"
else
    if curl --request POST -F "verify_card_number=$1" https://www.isic.org/verify/ -s | grep "You entered a valid card number" >/dev/null; then
        echo "Valid"
    else
        echo "Invalid"
    fi
fi
