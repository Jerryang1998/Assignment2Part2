package yuy75_SpotifyKnockoff;

import java.sql.*;
import javax.swing.*;

public class Spotify {
	private static Spotify spotify;
	private Spotify() {
		
	}
	public static Spotify getInstance() {
		if(spotify == null) {
			spotify = new Spotify();
		}
		return spotify;
	}
}
