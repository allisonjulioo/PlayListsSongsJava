package DAO;

import exceptions.InvalidLengthException;
import exceptions.InvalidRatingException;
import models.Playlist;
import models.Song;

import java.io.Serializable;
import java.util.Scanner;

public class Songs implements Serializable {
    public Scanner scan = new Scanner(System.in);

    public Playlist playlist = new Playlist();
    // public Menu menu = new Menu();
    public PlayListsDAO playlistDAO = new PlayListsDAO();

    public void addSong() {
        Song newSong = new Song(null, null, null, null, null, 0);

        newSong.setPlaylist(scan.nextLine());
        System.out.print("Qual playlist?: ");
        newSong.setArtist(scan.nextLine());
        System.out.print("Nome do artista: ");
        newSong.setArtist(scan.nextLine());
        System.out.print("Titúlo: ");
        newSong.setTitle(scan.nextLine());
        do {
            try {
                System.out.print("Duração da música: ");
                newSong.setLength(scan.next());
            } catch (InvalidLengthException invalidLength) {
                System.out.print(invalidLength.toString());
                System.out.print("O formato da duração está errada, ela deve seguir este model: \"min:seg\".\n");
            }
        } while (newSong.getLength() == null);

        newSong.setGenre(scan.nextLine());
        System.out.print("Gênero: ");
        newSong.setGenre(scan.nextLine());
        boolean flag = false;
        do {
            try {
                System.out.print("Classificação: ");
                newSong.setRating(scan.nextInt());
                flag = true;
            } catch (InvalidRatingException invalidRating) {
                System.out.print(invalidRating.toString());
            }
        } while (!flag);

        playlist.addSong(newSong);
    }

    public void updateSong() {
        if (playlist.getPlaylistSize() == 0) {
            System.out.println("\nPlaylist vazia!");
        } else {
            playlistDAO.viewPlaylist();
            System.out.print("\nQual faixa deseja atualizar? ");
            int songSelection = scan.nextInt();

            System.out.print("\nO que vai atualizar?\n"
                    + "1- Nome do Artista\n"
                    + "2- Titulo da música\n"
                    + "3- Duração\n"
                    + "4- Gênero\n"
                    + "5- Classificação\n"
                    + "> : ");

            int updateSelection = scan.nextInt();

            switch (updateSelection) {
                case 1:
                    System.out.print("Nome do artista: ");
                    String artist = scan.next();
                    playlist.updateArtist(songSelection, artist);
                    break;
                case 2:
                    System.out.print("Título da música: ");
                    String title = scan.next();
                    playlist.updateTitle(songSelection, title);
                    break;
                case 3:
                    boolean lengthFlag = false;
                    do {
                        try {
                            System.out.print("Duração: ");
                            String length = scan.next();
                            playlist.updateLength(songSelection, length);
                            lengthFlag = true;
                        } catch (InvalidLengthException invalidLength) {
                            System.out.print(invalidLength.toString());
                            System.out.print("O formato da duração deve seguir este model: \"min:seg\".\n");
                        }
                    } while (!lengthFlag);
                    break;
                case 4:
                    System.out.print("Gênero: ");
                    String genre = scan.next();
                    playlist.updateGenre(songSelection, genre);
                    break;
                case 5:
                    boolean ratingFlag = false;
                    do {
                        try {
                            System.out.print("Classificação: ");
                            int rating = scan.nextInt();
                            playlist.updateRating(songSelection, rating);
                            ratingFlag = true;
                        } catch (InvalidRatingException invalidLength) {
                            System.out.print(invalidLength.toString());
                        }
                    } while (!ratingFlag);
                    break;
                default:
                    Menu.messageError();
                    //menu.runMenu();
            }
        }
    }

    public void removeSong() {
        if (playlist.getPlaylistSize() == 0) {
            System.out.println("\nPlaylist vazia!");
        } else {
            playlistDAO.viewPlaylist();
            boolean flag = false;
            do {
                System.out.print("\nQual faixa deseja remover: ");
                int removeSelection = scan.nextInt();

                if (removeSelection < playlist.getPlaylistSize() || removeSelection > playlist.getPlaylistSize()) {
                    Menu.messageError();
                } else {
                    playlist.removeSong(--removeSelection);
                    flag = true;
                }
            } while (!flag);
        }
    }
}
