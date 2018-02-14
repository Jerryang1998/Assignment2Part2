package yuy75_SpotifyKnockoff;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SpotifyGUI {

	private JFrame frame;
	private JTextField txtSearch;
	private JRadioButton radShowAlbums;
	private JRadioButton radShowArtists;
	private JRadioButton radShowSongs;
	private JTable tblData;
	private DefaultTableModel musicData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpotifyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Spotify");
		frame.setBounds(100, 100, 1000, 700);
		frame.getContentPane().setLayout(null);
		
		JLabel lblViewSelector = new JLabel("Select View");
		lblViewSelector.setBounds(45, 50, 75, 20);
		frame.getContentPane().add(lblViewSelector);
		
		radShowAlbums = new JRadioButton("Albums");
		radShowAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowAlbums.isSelected()){
					System.out.println("Radio button clicked");
				}
			}
		});
		radShowAlbums.setBounds(55, 80, 85, 25);
		radShowAlbums.setSelected(true);
		frame.getContentPane().add(radShowAlbums);
		
		radShowArtists = new JRadioButton("Artists");
		radShowArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowAlbums.isSelected()){
					System.out.println("Radio button clicked");
				}
			}
		});
		radShowArtists.setBounds(55, 130, 150, 25);
		frame.getContentPane().add(radShowArtists);
		
		radShowSongs = new JRadioButton("Songs");
		radShowArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowAlbums.isSelected()){
					System.out.println("Radio button clicked");
				}
			}
		});
		radShowSongs.setBounds(55, 180, 150, 25);
		frame.getContentPane().add(radShowSongs);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(45, 290, 100, 20);
		frame.getContentPane().add(lblSearch);
		
		frame.getContentPane().add(lblViewSelector);
		txtSearch = new JTextField();
		txtSearch.setBounds(45, 315, 200, 30);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		musicData = getAlbumData("");
		tblData = new JTable(getAlbumData(""));
		tblData.setBounds(280, 30, 600, 600);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		frame.getContentPane().add(tblData);

		JButton btnSearch = new JButton("Search");
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowAlbums.isSelected()){
					musicData = getAlbumData(txtSearch.getText());
					tblData.setModel(musicData);
				}
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowArtists.isSelected()){
					musicData = getArtistData(txtSearch.getText());
					tblData.setModel(musicData);
				}
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radShowSongs.isSelected()){
					musicData = getSongData(txtSearch.getText());
					tblData.setModel(musicData);
				}
			}
		});
		
		
		btnSearch.setBounds(100, 380, 100, 30);
		
		frame.getContentPane().add(btnSearch);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private DefaultTableModel getAlbumData(String searchTerm){
		String sql = "SELECT album_id,title,release_date,cover_image_path,recording_company_name,number_of_tracks,PMRC_rating,length FROM album";
		if(!searchTerm.equals("")){
				sql += " WHERE title LIKE '%" + searchTerm + "%';";
		}
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Album ID", "Title", "Release Date", "Cover Image Path", "Recording Company Name", "Number of Tracks", "PMRC Rating", "Length"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Unable to connect to database");
			try {
				ErrorLogger.log(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private DefaultTableModel getArtistData(String searchTerm){
		String sql = "SELECT artist_id, first_name, last_name, band_name FROM artist";
		if(!searchTerm.equals("")){
				sql += " WHERE first_name OR last_name OR band_name LIKE '%" + searchTerm + "%';";
		}
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Artist ID", "First Name", "Last Name", "Band Name"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Unable to connect to database");
			try {
				ErrorLogger.log(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private DefaultTableModel getSongData(String searchTerm){
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song";
		if(!searchTerm.equals("")){
				sql += " WHERE title LIKE '%" + searchTerm + "%';";
		}
		
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Song ID", "Title", "Length", "Release Date", "Record Date"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Unable to connect to database");
			try {
				ErrorLogger.log(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
}
