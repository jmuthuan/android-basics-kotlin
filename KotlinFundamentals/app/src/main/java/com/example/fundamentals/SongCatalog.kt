package com.example.fundamentals

class SongCatalog (val title: String, val artist: String, val yearPublished: String){

    //Properties for the title, artist, year published, and play count
    var playCount = 0

    //A property that indicates whether the song is popular.
    var popularSong =  if(playCount < 1000){ "unpopular"} else "popular"

    //A method that prints a song description in this format:
    fun songDescription () {
        println("$title, performed by $artist, was released in $yearPublished ")
    }

}