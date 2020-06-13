package DAO;

import java.awt.*;

public class Main
{
	public static void main( String[] arg)
	{
		Menu menu = new Menu();
		PlayListsDAO playlistsDAO = new PlayListsDAO();

		playlistsDAO.loadPlaylist();
		menu.runMenu();

	}
	
} //End class