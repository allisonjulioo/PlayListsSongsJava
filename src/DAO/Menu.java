package DAO;

import exceptions.InvalidLengthException;
import exceptions.InvalidRatingException;
import models.Playlist;

import java.io.*;
import java.util.Scanner;

public class Menu {

    public Scanner scan = new Scanner(System.in);
    public Playlist playlist = new Playlist();
    public PlayListsDAO playlistDAO = new PlayListsDAO();
    public Songs songs = new Songs();

    public int showMenu() {
        int selection;

        System.out.print("\nO que gostaria de fazer?\n"
                + "\n*- Adicionar uma nova playlist"
                + "\n1- Adicionar uma nova música"
                + "\n*- Buscar"
                + "\n2- Ver playlists"
                + "\n3- Alterar informações de uma música cadastrada"
                + "\n4- Excluir música de uma playlist"
                + "\n5- Excluir playlist"
                + "\n6- Sair\n");


        selection = scan.nextInt();
        System.out.print("\n");

        return selection;
    }

    public void runMenu() {

        int selection = showMenu();

        switch (selection) {
            case 1:
                clearScreen();
                playlistDAO.addSong();
                playlistDAO.savePlaylist();
                runMenu();
                break;
            case 2:
                playlistDAO.viewPlaylist();
                runMenu();
                break;
            case 3:
                songs.updateSong();
                playlistDAO.savePlaylist();
                runMenu();
                break;
            case 4:
                songs.removeSong();
                playlistDAO.savePlaylist();
                runMenu();
                break;
            case 5:
                playlist.deletePlaylist();
                runMenu();
                break;
            case 6:
                playlistDAO.savePlaylist();
                System.exit(0);
                break;
            default:
                messageError();
                clearScreen();
                runMenu();
        }

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void messageError() {
        System.out.println("\n " +
                "_____   _____    _____    _____   _          __ \n" +
                "| ____| |  _  \\  |  _  \\  /  _  \\ | |        / / \n" +
                "| |__   | |_| |  | |_| |  | | | | | |  __   / /  \n" +
                "|  __|  |  _  /  |  _  /  | | | | | | /  | / /   \n" +
                "| |___  | | \\ \\  | | \\ \\  | |_| | | |/   |/ /    \n" +
                "|_____| |_|  \\_\\ |_|  \\_\\ \\_____/ |___/|___/ \n");
    }

}