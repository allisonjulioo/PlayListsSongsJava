package prova;

import java.io.*;
import java.util.Scanner;

public class Menu {

    private Scanner scan = new Scanner(System.in);
    private Playlist playlist = new Playlist();

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
                addSong();
                savePlaylist();
                runMenu();
                break;
            case 2:
                viewPlaylist();
                runMenu();
                break;
            case 3:
                updateSong();
                savePlaylist();
                runMenu();
                break;
            case 4:
                removeSong();
                savePlaylist();
                runMenu();
                break;
            case 5:
                playlist.deletePlaylist();
                runMenu();
                break;
            case 6:
                savePlaylist();
                System.exit(0);
                break;
            default:
                messageError();
                clearScreen();
                runMenu();
        }

    }

    public void addSong() {
        Song newSong = new Song(null, null, null, null, 0);

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
        } while (flag == false);

        playlist.addSong(newSong);
    }

    public void viewPlaylist() {
        if (playlist.getPlaylistSize() == 0) {
            System.out.println("\nNão existe playlist cadastrada!");
        } else {
            for (int i = 0; i < playlist.getPlaylistSize(); i++) {
                System.out.print("\n#" + (i + 1) + " ");
                System.out.print("" + playlist.getArtist(i) + " - ");
                System.out.print("\"" + playlist.getTitle(i) + "\"" + ", ");
                System.out.print(playlist.getLength(i) + ", ");
                System.out.print(playlist.getGenre(i) + ", ");
                for (int j = 0; j < playlist.getRating(i); j++) {
                    System.out.print("★");
                }
            }
            System.out.print("\n");
        }
    }

    public void updateSong() {
        if (playlist.getPlaylistSize() == 0) {
            System.out.println("\nPlaylist vazia!");
        } else {
            viewPlaylist();
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
                    } while (lengthFlag == false);
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
                    } while (ratingFlag == false);
                    break;
                default:
                    messageError();
                    runMenu();
            }
        }
    }

    //Displays Playlist; prompts user to select Song to delete and removes it from Playlist.
    public void removeSong() {
        if (playlist.getPlaylistSize() == 0) {
            System.out.println("\nPlaylist vazia!");
        } else {
            viewPlaylist();
            boolean flag = false;
            do {
                System.out.print("\nQual faixa deseja remover: ");
                int removeSelection = scan.nextInt();

                if (removeSelection < playlist.getPlaylistSize() || removeSelection > playlist.getPlaylistSize()) {
                    messageError();
                } else {
                    playlist.removeSong(--removeSelection);
                    flag = true;
                }
            } while (flag == false);
        }
    }

    //Write the contents of Playlist to a file called playlist.
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

    //Reads the file contents containing to a Playlist.
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