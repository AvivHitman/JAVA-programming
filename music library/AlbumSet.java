public class AlbumSet {

	private String owner;
	private Album[] albums;
	private int numAlbums;
	final int maxAlbums = 20;

	// Constructor
	public AlbumSet(String owner) {

		this.owner = owner.substring(0, 1).toUpperCase() + owner.substring(1).toLowerCase();
		albums = new Album[maxAlbums];
	}

	// getters
	public String getOwner() {
		return this.owner;

	}

	public int getNumAlbums() {
		return this.numAlbums;

	}

	public Album getOneAlbumByIndex(int index) {
		return albums[index];
	}

	// special setter: adding song to album
	public void addSongToAlbum(String albumName, String songName, String artistName, int minutes, int seconds) {
		artistName = artistName.substring(0, 1).toUpperCase() + artistName.substring(1).toLowerCase();
		songName = songName.substring(0, 1).toUpperCase() + songName.substring(1).toLowerCase();

		if (isAlbumExists(albumName) == true) {
			albums[getAlbumIndex(albumName)].addSong(songName, artistName, minutes, seconds);

		}

		else {
			Album newAlbum = new Album(albumName);
			albums[numAlbums] = newAlbum;
			newAlbum.addSong(songName, artistName, minutes, seconds);
			numAlbums++;

		}
	}

	// special Methods
	public boolean isAlbumExists(String albumName) {
		albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();

		for (int i = 0; i < numAlbums; i++) {
			if (albumName.equals(albums[i].getAlbumName()))
				return true;

		}
		return false;

	}

	public int getAlbumIndex(String albumName) {
		int index = 0;
		albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();

		for (int i = 0; i < numAlbums; i++) {
			if (albumName.equals(albums[i].getAlbumName())) {
				index = i;
			}
		}
		return index;

	}

	public void sortByAlbumsName() {

		for (int i = 1; i < numAlbums; i++) {
			Album temp = albums[i];
			int k;
			for (k = i - 1; k > -1 && (albums[k].getAlbumName()).compareTo(temp.getAlbumName()) > 0; k--) {
				albums[k + 1] = albums[k];
			}

			albums[k + 1] = temp;
		}
	}

	public String toString() {
		String s = "Number of albums:" + numAlbums + "\n";

		for (int i = 0; i < numAlbums; i++) {
			s += "\n" + "Album name:" + albums[i].getAlbumName() + ", numbers of songs: " + albums[i].getNumSongs()
					+ ", total songs length:" + albums[i].getAlbumLength() + "(seconds).";

		}
		return s;
	}

}
