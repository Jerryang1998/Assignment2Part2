package yuy75_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Song {
	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	Map<String,Artist> songArtist;
	
	public Song(String title, double length, String releaseDate, String recordDate, String albumID) {
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();
		
		songArtist = new Hashtable<String, Artist>();
		
		//System.out.println(this.songID);
		//String sql = "INSERT INTO song(song_id, title, length, file_path, release_date, record_date) ";
		//sql += "VALUES ('" + this.songID + "', '" + this.title + "', '" + this.length + "', '', '" + this.releaseDate + "', '" + this.recordDate + "');";
		String sql = "INSERT INTO song(song_id, title, length, file_path, release_date, record_date, fk_album_id)";
		sql += "VALUES (?,?,?,?,?,?,?);";
		System.out.println(sql);
		//DbUtilities db = new DbUtilities();
		//Connection conn = db.getConn();
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, "");
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		/*db.executeQuery(sql);
		db.closeDbConnection();
		db = null;*/
	}
	
	public Song(String songID, String title, double length, String releaseDate, String recordDate){
		
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = songID;
		
		songArtist = new Hashtable<String, Artist>();
	}

	public Song(String songID) {
		songArtist = new Hashtable<String, Artist>();
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		//System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.songID = rs.getString("song_id");
				//System.out.println("Song ID from database: " + this.songID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
				//System.out.println("Song title from database: " + this.title);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String getSongID() {
		return songID;
	}
	public String getTitle() {
		return title;
	}
	public double getLength() {
		return length;
	}
	public String getFilePath() {
		return filePath;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public Map<String, Artist> getSongArtist() {
		return songArtist;
	}
	public void deleteSong(String songID) {
		
	}
	public void addArtist(Artist artist) {
		songArtist.put(artist.getArtistID(), artist);
		String sql = "INSERT INTO song_artist (fk_song_id,fk_artist_id) VALUES (?, ?);";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, artist.getArtistID());
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = ? WHERE song_id = ?;";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, filePath);
			ps.setString(2, this.songID);
			ps.executeUpdate();
			conn.close();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteArtist(String artistID) {
		songArtist = new Hashtable<String, Artist>();
		String sql = "DELETE FROM spotify_knockoff.song WHERE artist_id = ?";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.songID = rs.getString("song_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void deleteArtist(Artist artist) {
		
	}
	
	Vector<String> getSongRecord(){
		Vector<String> songRecord = new Vector<>();
		songRecord.add(this.songID);
		songRecord.add(this.title);
		songRecord.add(this.filePath);
		songRecord.add(String.valueOf(this.length));
		songRecord.add(this.releaseDate);
		songRecord.add(this.recordDate);
		return songRecord;
	}

}

