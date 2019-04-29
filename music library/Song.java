
public class Song {
	private String songName = "";
	private String artistName = "";
	private int songLength = 0;

	// constructors
	public Song(String songName, String artistName, int minutes, int seconds) {
		
		this.artistName = artistName;
		this.songName = songName;
		this.songLength = 60 * minutes + seconds;
	}

	public Song(String songName, String artistName, int seconds) {

		this.artistName = artistName.substring(0, 1).toUpperCase() + artistName.substring(1).toLowerCase();
		this.songName = songName.substring(0, 1).toUpperCase() + songName.substring(1).toLowerCase();	

	}

	// getters
	public String getSongName() {
		return this.songName;
	}

	public String getArtistName() {
		return this.artistName;
	}

	public int getSongLength() {
		return this.songLength;
	}

	// setters
	public void setSongName(String songName) {
		this.songName = songName.substring(0, 1).toUpperCase() + songName.substring(1).toLowerCase();
	}

	public void setAtristName(String artistName) {
		this.artistName = artistName.substring(0, 1).toUpperCase() + artistName.substring(1).toLowerCase();
	}

	public void setSongLength(int minutes, int seconds) {
		this.songLength = 60 * minutes + seconds;
	}

	public void setSongLength(int seconds) {
		this.songLength = seconds;
	}

	// special Methods
	public boolean isSongEqaul(String songName) {
		songName = songName.substring(0, 1).toUpperCase() + songName.substring(1).toLowerCase();

		return songName.equals(this.songName);
	}

	public boolean isAtrtistEqaul(String artistName) {
		artistName = artistName.substring(0, 1).toUpperCase() + artistName.substring(1).toLowerCase();

		return artistName.equals(this.artistName);
	}

	// to String
	public String toString() {

		return "Song Name: " + this.songName + ", Artist Name: " + this.artistName + ", Song Length: " + this.songLength
				+ " seconds";
	}
	
}
