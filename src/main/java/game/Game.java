package game;

import data.Data;
import items.Item;
import player.Input;
import rooms.Room;

import java.util.Scanner;

public class Game implements Input {

    // VAR
    private Data slot = new Data();
    private Scanner sc = new Scanner(System.in);
    private Item item = new Item();
    private Room room = new Room();
    private Action action = new Action();

    // CONSTANT
    private String DISPLAY_ERROR_MESSAGE = "Merci de sélectionner une des options proposées";
    private String EXCEPTION_MESSAGE = "Veuillez taper sur entrée qu'une fois la saisie effectué!";


    public Game() {
        // configuration
        room.configureRoom();
        item.configureItem();
    }


    //******************************************************************************************************************
    //                                                PRINTING
    //******************************************************************************************************************

    private void printMainMenu() {
        System.out.println("\n********************\n" +
                "** MENU PRINCIPAL **\n" +
                "********************\n");

        System.out.println("Nouvelle partie (N)\n"
                + "Continuer la partie (C)\n"
                + "Quitter (Q)");
    }

    private void printActionMenu() {
        System.out.println("Listes des actions possibles :\n\t"
                + "-Se déplacer (D)\n\t"
                + "-Observer la zone (O)\n\t"
                + "-Retour menu principal (R)\n");
    }

    private void printGameMenu() {
        System.out.println("\n******************\n" +
                "** ECRAN DE JEU **\n" +
                "******************\n");
    }


    //******************************************************************************************************************
    //                                                   MENU
    //******************************************************************************************************************


    public void mainMenu() {
        char menuChoice = '\0';
        this.printMainMenu();

        do {
            try {
                menuChoice = sc.nextLine().toUpperCase().charAt(0);
            } catch (IndexOutOfBoundsException n) {
                System.err.println(EXCEPTION_MESSAGE);
            } catch (NullPointerException e) {
                System.err.println(EXCEPTION_MESSAGE + e.getMessage());
            }

            if (checkMainMenuInput(menuChoice))
                System.err.println(DISPLAY_ERROR_MESSAGE);

        } while (checkMainMenuInput(menuChoice));

        //NEW GAME
        if (menuChoice == NEW_GAME) {
            this.newGame();
            //CONTINUE GAME
        } else if (menuChoice == CONTINUE_GAME) {
            this.continueGame();
            //QUIT GAME
        } else {
            System.out.println("Au revoir !");
        }

    }

    protected void actionMenu() {
        char actionChoice = '\0';
        this.printActionMenu();

        do {
            try {
                actionChoice = sc.nextLine().toUpperCase().charAt(0);
            } catch (IndexOutOfBoundsException n) {
                System.out.println(EXCEPTION_MESSAGE);
            } catch (NullPointerException e) {
                System.out.println(EXCEPTION_MESSAGE + e.getMessage());
            }

            if (checkActionMenuInput(actionChoice))
                System.err.println(DISPLAY_ERROR_MESSAGE);

        } while (checkActionMenuInput(actionChoice));
        //MOVE
        if (actionChoice == MOVE_INTO_ROOM) {
            this.action.moveIntoRoom(sc);
            //OBSERVE
        } else if (actionChoice == OBSERVE_ROOM) {
            this.action.observeRoom();
            //BACK MENU
        } else {
            //Go back to the menu when finish the game
            System.out.println(" => Vous retournez au menu principal.");
            this.mainMenu();
        }

    }

    private void newGame() {
        this.printGameMenu();
        //if user select the good input so we launch introduction...
        String intro = "Bienvenue dans le manoir Shikabuki, détective Kovac.\n" +
                "Votre objectif est de mener à bien l'enquête sur le meurtre d'un très grand diginitaire Maths." +
                "\nCe dernier a eu sa stack détruite..." +
                "\nVous devez chercher et trouver le coupable !\n";
        System.out.println(intro);
        //First thing to do check if there is a saving
        if (action.ifSaving()) {
            //if true we empty the file
            slot.saveRoom('\0');
            //initialise position to 0
            Action.position = 0;
        }
        this.actionMenu();

    }

    private void continueGame() {
        //We need to check if the game have a saving first before
        if (!action.ifSaving()) {
            System.out.println("AUCUNE SAUVEGARDE TROUVEE !!! ");
            System.out.println("=> Vous allez débuter une nouvelle partie\n");
            this.newGame();
        } else {
            System.out.println("=> Vous reprenez votre partie !");
            this.actionMenu();

        }
    }

    //******************************************************************************************************************
    //                                                   ACTION
    //******************************************************************************************************************

//    private int checkRoomPlayerChoice(char choice) {
//        char[] availableChoice = this.room.getListRoom()[position].getAvailableChoice();
//        char tmp = '\0';
//
//        //loop into the char array
//        for (char ch : availableChoice) {
//            if (ch == choice) {
//                tmp = ch;
//                break;
//            }
//        }
//        //Comparison between user input and array
//        if (choice == tmp) {
//            position = rooms.Room.characterHashMap.get(tmp);
//            slot.saveRoom(choice);
//            //Prompt the player he is wrong !
//        } else if (choice != RETURN_ACTION_MENU) {
//            System.err.println(BAD_INPUT_ALERT);
//        }
//
//        return position;
//    }

//    private void moveIntoRoom() {
//        char choice = '\0';
//
//        // Check if there's a room saved otherwise launch room by default
//        if (ifSaving()) {
//            int pos = rooms.Room.characterHashMap.get(slot.loadRoom());
//            System.out.println(this.room.getListRoom()[pos].toString());
//        } else {
//            System.out.println(this.room.getListRoom()[position].toString());
//        }
//
//        do {
//            try {
//                choice = sc.nextLine().toUpperCase().charAt(0);
//            } catch (IndexOutOfBoundsException n) {
//                System.out.println(EXCEPTION_MESSAGE);
//            } catch (NullPointerException e) {
//                System.out.println(EXCEPTION_MESSAGE + e.getMessage());
//            }
//
//            if (choice != RETURN_ACTION_MENU)
//                System.out.println(rooms.Room.listRoom[checkRoomPlayerChoice(choice)]);
//
//        } while (choice != RETURN_ACTION_MENU);
//        System.out.println("=> Retour à la liste des actions\n");
//        this.actionMenu();
//    }

//    private void observeRoom() {
//
//        ArrayList<items.ItemList> availableItem = this.item.getAvailableItem();/*room.getListRoom()[position].getAvailableItem();*/
//        if (availableItem.size() > 0) {
//            System.out.println(availableItem);
//        } else {
//            System.out.println("Rien à observer pour l'instant");
//        }
//        //L'indication de la pièce dans laquelle on est
//
//        //Repartition des indices et des personnages au travers d'arrays (enumeration - 15 objets) Aurelia
//        //2 shuffle entre le nombre d'objets et l'objet parmi une liste à afficher dans une pièce //Yann
//
//        //retourne la liste des éléments présent dans la pièce actuelle avec condition
//
//
//        //System.out.println("Rien à observer pour l'instant" + getListRoom()[position].getAvailableItem());
//        this.actionMenu();
//
//    }


}
