#/bin/bash
searchForMovies() {
    for entry in $1/*; do
        if [[ -d $entry ]]; then
            searchForMovies $entry
        elif [[ -f $entry ]]; then
            if [[ $entry == *.avi ]]; then
                (echo -e "Subject: Movie Starting Converting\n\nMovie name: \"$entry\"\nCommand:ffmpeg -fflags +genpts -i \"$entry\" -c:av copy \"${entry::-4}.mkv\"" | msmtp martinkos007@gmail.com)
                ffmpeg -fflags +genpts -i "$entry" -c:av copy "${entry::-4}.mkv" >/dev/null 2>&1 </dev/null
                (echo -e "Subject: Movie Finished Converting\n\n Movie name: $entry" | msmtp martinkos007@gmail.com)
            fi
        fi
    done
}
searchForMovies "/fileManager/Drive/Movie"
