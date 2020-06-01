#/bin/bash
searchForMovies() {
    for entry in $1/*; do
        if [[ -d $entry ]]; then
            searchForMovies $entry
        elif [[ -f $entry ]]; then
            if [[ $entry == *.avi ]]; then
                if [[ -f ${entry::-4}.mkv ]]; then
                    rm "${entry::-4}.mkv"
                fi
            fi
        fi
    done
}
searchForMovies "/fileManager/Drive/MovieSaves"
