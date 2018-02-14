package yuy75_SpotifyKnockoff;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	Map<String,Song> albumSongs;
	
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany= recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = UUID.randomUUID().toString();
		albumSongs = new Hashtable<String, Song>();
		
		String sql = "INSERT INTO album(album_id,title,release_date,cover_image_path,recording_company_name,number_of_tracks,PMRC_rating,length)";
		sql += "VALUES ('" + this.albumID + "', '" + this.title + "', '" + this.releaseDate + "', '', '" + this.recordingCompany + "', '" + this.numberOfTracks + "', '" + this.pmrcRating + "'', '" + this.length + "');";
		
		//System.out.println(sql);
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		db = null;	
	}
	public Album(String albumID) {
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.albumID = rs.getString("album_id");
				//System.out.println("Album ID from database: " + this.albumID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordingCompany = rs.getString("recording_company_name");
				this.length = rs.getDouble("length");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				//System.out.println("Album title from database: " + this.title);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteAlbum(String albumID) {
		
	}
	public void addSong(Song song) {
		
	}
	public void deleteSong(String songID) {
		albumSongs = new Hashtable<String, Song>();
		String sql = "DELETE FROM spotify_knockoff.song WHERE artist_id = ?";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordingCompany = rs.getString("recording_company_name");
				this.length = rs.getDouble("length");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void deleteSong(Song song) {
		
	}
	public String getAlbumID() {
		return albumID;
	}
	public String getTitle() {
		return title;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getCoverImagePath() {
		return coverImagePath;
	}
	public String getRecordingCompany() {
		return recordingCompany;
	}
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	public String getPmrcRating() {
		return pmrcRating;
	}
	public double getLength() {
		return length;
	}
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
}
