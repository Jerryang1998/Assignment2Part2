package yuy75_SpotifyKnockoff;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	public String getArtistID() {
		return artistID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getBandName() {
		return bandName;
	}
	public String getBio() {
		return bio;
	}
	private String bio;
	
	public Artist(String firstName, String lastName, String bandName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.artistID = UUID.randomUUID().toString();
		String sql = "INSERT INTO artist(artist_id, first_name, last_name, band_name, bio)";
		sql += "VALUES ('" + this.artistID + "', '" + this.firstName + "', '" + this.lastName + "', '" + this.bandName + "', '" + this.bio + "');";
	
		//System.out.println(sql);
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		db = null;
	}
	public Artist(String artistID) {
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
				//System.out.println("Artist bio from database: " + this.bio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM spotify_knockoff.artist WHERE artist_id = ?";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
