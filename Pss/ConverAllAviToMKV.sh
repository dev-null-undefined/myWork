#/bin/bash
searchForMovies() {
    for entry in $1/*; do
        if [[ -d $entry ]]; then
            searchForMovies $entry
        elif [[ -f $entry ]]; then
            if [[ $entry == *.avi ]]; then
                (echo -e "Subject: Movie Converting\n\nMovie name: \"$entry\"\n" | msmtp martinkos007@gmail.com)
                ffmpeg -i "$entry" -c:v libx264 -pix_fmt yuv420p -movflags faststart "${entry::-3}mp4" >/dev/null 2>&1 </dev/null
                (echo -e "Subject: Movie Converting\n\nFINISHED Movie name: ${entry::-3}mp4" | msmtp martinkos007@gmail.com)
            fi
        fi
    done
}
searchForMovies "/fileManager/Drive/MovieSaves"
