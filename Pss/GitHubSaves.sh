#!/bin/bash
(cd /root/GitHub/myWork/ && git pull)
for n in {1..20}; do
    FILE=/root/GitHub/Archives/myWork_save_$n
    if [ -f "$FILE" ]; then
        echo "$FILE exist";
    else
        echo "Creating archive: $FILE";
        tar -vzcf $FILE /root/GitHub/myWork
        echo "Created archive: $FILE";
        exit 1
    fi
done
tar -vzcf /root/GitHub/Archives/myWork_save /root/GitHub/myWork
echo "Created archive all slots are full: $FILE"
exit 1
