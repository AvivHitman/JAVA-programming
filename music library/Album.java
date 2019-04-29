public class Album {
	// class behaviors

	private String albumName;
	private Song[] songs;
	private int totalAlbumLength;
	private int numSongs;
	final int maxSongs = 20;

	// constructor
	public Album(String albumName) {
		this.albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();
		this.songs = new Song[maxSongs];
	}

	// getters
	public String getAlbumName() {
		return this.albumName;
	}

	public Song[] getAlbumSongs() {
		return this.songs;
	}

	public int getAlbumLength() {
		return this.totalAlbumLength;
	}

	public int getNumSongs() {
		return this.numSongs;
	}

	// setters
	public void setAlbumName(String albumName) {
		this.albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();
	}

	// special setters: adding new song
	public void addSong(Song newSong) {

		songs[numSongs] = newSong;
		numSongs++;
		totalAlbumLength += newSong.getSongLength();

	}

	public void addSong(String songName, String artistName, int minutes, int seconds) {
		Song newSong = new Song(songName, artistName, minutes, seconds);
		addSong(newSong);

	}

	// special Methods
	public int isSongExist(String songName) {
		for (int i = 0; i < numSongs; i++) {
			if (songName.equals(songs[i].getSongName()))
				return i + 1;

		}
		return -1;

	}

	public void sortByArtist() {
		for (int i = numSongs -1; i > 0; i--) {
			Song temp;
			int k;

			for (k = 0; k < i; k++) {

				if (songs[k].getArtistName().compareTo(songs[k + 1].getArtistName()) > 0) {
					temp = songs[k];
					songs[k] = songs[k + 1];
					songs[k + 1]= temp;

				}

				else if (songs[k].getArtistName().compareTo(songs[k + 1].getArtistName()) == 0
						&& this.songs[k].getSongLength() > songs[k + 1].getSongLength()) {
					temp = songs[k];
					songs[k] = songs[k + 1];
					songs[k + 1] = temp;

				} 

			}

		}

	}

	// toString
	public String toString() {

		String str = "\r\nAlbum Name: " + this.albumName + ", number of Songs: " + this.numSongs
				+ ", Album Length (in seconds): " + this.totalAlbumLength;
		for (int i = 0; i < this.numSongs; i++)
			str += "\r\n" + this.songs[i];

		return str + "\r\n";
	}

}