package DAO;

import exceptions.InvalidLengthException;
import exceptions.InvalidRatingException;
import models.Playlist;
import models.Song;

import java.io.*;
import java.util.Scanner;

public class PlayListsDAO {
    public Playlist playlist = new Playlist();
    public Scanner scan = new Scanner(System.in);

    public PlayListsDAO() {

    }

    public void loadPlaylist() {
        try {
            FileInputStream fileInputStream = new FileInputStream("playlist");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            playlist = (Playlist) objectInputStream.readObject();

            objectInputStream.close();
        } catch (FileNotFoundException fnfException) {
            System.out.println("Vazio");
        } catch (IOException ioException) {
            System.out.println("Bom");
        } catch (ClassNotFoundException cnfException) {
            System.out.println("Você digitou algo errado aí, essa playlist não existe");
        }

    }

    public void addSong() {
        Song newSong = new Song(null, null, null, null, null, 0);

        newSong.setPlaylist(scan.nextLine());
        System.out.print("Nome da Playlist: ");
        newSong.setPlaylist(scan.nextLine());
        System.out.print("Nome do artista: ");
        newSong.setArtist(scan.nextLine());
        System.out.print("Titulo da musica: ");
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

    public void viewPlaylist() {
        if (playlist.getPlaylistSize() == 0) {
            System.out.println("\nNão existe playlist cadastrada!");
        } else {
            for (int i = 0; i < playlist.getPlaylistSize(); i++) {
                System.out.print("#" + (i + 1) + "-> " + playlist.getPlaylist(i) + " - " + playlist.getPlaylistSize() + " faixas \n");
                System.out.print("Artista:" + playlist.getArtist(i) + "\n");
                System.out.print("Titulo: " + playlist.getTitle(i) + "\n");
                System.out.print("Duração: " + playlist.getLength(i) + "\n");
                System.out.print("Gênero: " + playlist.getGenre(i) + "\n");
                System.out.print("Classificação: ");
                for (int j = 0; j < playlist.getRating(i); j++) {
                    System.out.print("★");
                }
            }
            System.out.print("\n");
        }
    }

    public void savePlaylist() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("playlist");

            ObjectOutputStream outObjectStream = new ObjectOutputStream(fileOutputStream);

            outObjectStream.writeObject(playlist);

            outObjectStream.flush();
            outObjectStream.close();
        } catch (FileNotFoundException fnfException) {
            System.out.println("Vazio");
        } catch (IOException ioException) {
            System.out.println("Opa!");
        }

    }


}
