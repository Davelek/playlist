package model;

import java.time.Duration;
import java.time.LocalTime;

public class Song {

    private String title;
    private String artist;
    private int songLengthInSeconds;

    public Song(String title, String artist, int songLengthInSeconds) {
        this.title = title;
        this.artist = artist;
        this.songLengthInSeconds = songLengthInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getSongLengthInSeconds() {
        return songLengthInSeconds;
    }

    /**
     * Convert time to MM:SS
     * @return time in MM:SS format
     */
    public String getSongLengthInMMSS(){
        return LocalTime.MIN.plus(
                Duration.ofMinutes(songLengthInSeconds)
        ).toString();
    }
}
