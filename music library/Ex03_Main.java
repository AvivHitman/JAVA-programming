
//	Student Name: Aviv Tfilin,		Student ID: 313459849

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Ex03_Main {

	public static void main(String[] args) throws FileNotFoundException {

		AlbumSet myAlbums = new AlbumSet("Aviv");

		// phase A
		// -------
		// getting file name and open it
		String f = JOptionPane.showInputDialog("Please Enter Input File Name");
		File file = new File(f);
		Scanner s = new Scanner(file);

		String curLine;
		String[] lines;
		String songName;
		String artistName;
		String time;
		String albumName;

		// reading input file and creating the albums
		while (s.hasNext()) {

			curLine = s.nextLine();
			lines = curLine.split(";");

			songName = lines[0].trim().replaceAll("\\s+", " ");
			artistName = lines[1].trim().replaceAll("\\s+", " ");
			time = lines[2].trim().replaceAll("\\s+", "");

			int minutes = Integer.parseInt(time.substring(0, 1));
			int seconds = Integer.parseInt(time.substring(2));

			albumName = lines[3].trim().replaceAll("\\s+", " ");

			myAlbums.addSongToAlbum(albumName, songName, artistName, minutes, seconds);

		}

		s.close();

		// phase B
		// -------
		// album and song ordering

		myAlbums.sortByAlbumsName();

		for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
			myAlbums.getOneAlbumByIndex(i).sortByArtist();

		}

		// phase C

		System.out.println(myAlbums.toString()); // print album info

		// phase D
		// -------
		int search = JOptionPane.showConfirmDialog(null, "Do You Want To Search a Song?");

		if (search == 0) {
			boolean b = false;

			String chosenSong = JOptionPane.showInputDialog("Please Enter The Song Name For Search:");			
			chosenSong = chosenSong.substring(0, 1).toUpperCase() + chosenSong.substring(1).toLowerCase();

			for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
				int place = myAlbums.getOneAlbumByIndex(i).isSongExist(chosenSong);

				if (place != -1) {
					b = true;
					System.out.println("Song: " + chosenSong + " exists at album:"
							+ myAlbums.getOneAlbumByIndex(i).getAlbumName() + " as song number:" + place);
				}
			}

			if (b == false)
				System.out.println("Song: " + chosenSong + " is not exists at your albums!");
		}

		// phase E
		// ----------
        String file2 =JOptionPane.showInputDialog(null, "Please Enter Output File Name: ");
		File f2 = new File(file2);
		PrintWriter output = new PrintWriter(f2);
		output.print("Total Number of Albums:" + myAlbums.getNumAlbums() + "\r\n");

		for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
			output.print("" + myAlbums.getOneAlbumByIndex(i).toString());

		}

		output.close();

	}
}