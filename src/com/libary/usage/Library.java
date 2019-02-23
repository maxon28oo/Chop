package com.libary.usage;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.libary.models.Book;
import com.libary.models.Human;

public class Library {
    //main
    private final static String clFile = "src/com/libary/inf/clients.info";
    private final static String bkFile = "src/com/libary/inf/books.info";
    private static List<Book> books = new ArrayList<>();
    private static List<Human> clients = new ArrayList<>();

    private static boolean logIn = false;

    private static int usrId;


    public static void main(String[] args) {

        bookInit();
        clientInit();


        boolean isOver = false;

        do {
            System.out.println("Chose the client: ");

            do {
                Scanner scr = new Scanner(System.in);
                System.out.println("1) Client list");
                System.out.println("2) Find client");
                System.out.println("3) Register new client");
                System.out.println("4) Show extended menu");
                System.out.println("5) Save changes");
                System.out.println("6) Dechanges");
                System.out.println("7) Exit out of program");

                System.out.print(":: ");

                int anw;

                try {
                    anw = scr.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong");
                    anw = 0;
                }

                switch (anw) {
                    case 1:
                        interactClient(clients);
                        break;
                    case 2:
                        findClient();
                        break;
                    case 3:
                        createClient();
                        break;
                    case 4:
                        extMenu();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        delCl();
                        break;
                    case 7:
                        isOver = true;
                        break;
                }
            } while ((!logIn) && (!isOver));

            while ((logIn) && (!isOver)) {
                Scanner scr = new Scanner(System.in);
                System.out.println("You are signed as " + clients.get(usrId).getAllName());
                System.out.println("1) Book list");
                System.out.println("2) Find book");
                System.out.println("4) Log out");
                System.out.println("5) Exit out of program");

                System.out.print(":: ");

                int csh;

                try {
                    csh = scr.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong");
                    csh = 0;
                }
                switch (csh) {
                    case 1:
                        // bookList();
                        break;
                    case 2:
                        findBook();
                        break;
                    case 4:
                        logIn = false;
                        break;
                    case 5:
                        isOver = true;
                        break;
                }

            }

        } while (!isOver);

    }


    private static void delCl() {
        clients.remove(clients.size()-1);
    }


    // dads

    private static void save() {
       //File file = new File(clFile+"new");
        File file = new File(clFile);
        try {

            FileWriter fb = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fb);

            for (Human e : clients) {
                pw.println(e.infoPrint());
            }

            pw.close();
        } catch (java.io.IOException e) {

        }

    }


    private static void extMenu() {
        Scanner scr = new Scanner(System.in);
        int csh;

        do {
            System.out.println("1) Book list");
            System.out.println("2) Find book");
            System.out.println("3) Add new book");
            System.out.println("4) Exit back");

            System.out.print(":: ");

            csh = scr.nextInt();
            switch (csh) {
                case 1:
                    bkListAc();
                    break;
                case 2:
                    findBook();
                    break;
                case 4:
                    break;
            }

        } while (csh != 4);
    }


    private static void bkListAc() {
        int sch;
        Scanner scr = new Scanner(System.in);
        do {
            System.out.println("Which list do you want to be shown?");
            System.out.println("1) All books");
            System.out.println("2) Only books are available");
            System.out.println("3) Only books aren't available");
            sch = scr.nextInt();
        } while ((sch < 1) || (sch > 4));
        bookList(sch);

    }

    private static void interactClient(List<Human> cl) {

        Scanner scr = new Scanner(System.in);
        int csh;
        do {
            clientsList(cl);
            System.out.println("Chose the client");
            System.out.println("If there no such client, press to create a new one " + (cl.size() + 1));
            System.out.println("To cancel type " + (cl.size() + 2));
            System.out.print(":: ");
            csh = scr.nextInt();
        } while ((csh < 0) || (csh > cl.size() + 2));
        if (csh <= cl.size()) {
            logIn(clients.indexOf(cl.get((csh - 1))));
        } else if (csh == cl.size() + 1) {
            createClient();
        }
    }

    private static void createClient() {
        Scanner j = new Scanner(System.in);
        System.out.print("Type in your name: ");
        String name = j.next();
        System.out.print("Type in your surname: ");
        String surname = j.next();
        System.out.print("Type in your age: ");
        int age = j.nextInt();

        clients.add(new Human(name, surname, age));
    }

    private static void clientsList(List<Human> cl) {
        if (cl.size() != 0) {
            for (int i = 0; i < cl.size(); i++) {
                System.out.println((i + 1) + ") " + cl.get(i).toString());
            }
        } else {
            System.out.println("There are no clients");
        }
    }

    private static void bookList(int s) {
        if (s == 1) {
            for (Book p : books) {
                System.out.println(p.toString());
            }
        } else if (s == 2) {
            for (Book p : books) {
                if (!p.isTaken()) System.out.println(p.toString());
            }
        } else if (s == 3) {
            for (Book p : books) {
                if (p.isTaken()) System.out.println(p.toString());
            }
        }
    }


    private static void findBook() {
        System.out.print("Type in book's name: ");
        Scanner j = new Scanner(System.in);
        String name = j.nextLine();

        List<Book> cl = new ArrayList<>();

        for (Book t : books) {
            if (t.getName().toLowerCase().contains(name.toLowerCase())) {
                cl.add(t);
            }
        }

        if (cl.size() == 0) {
            System.out.println("There is no such books");
        } else {
            for (Book t : cl) {
                System.out.println(t.toString());
            }
        }

    }

    private static void findClient() {
        System.out.print("Type in client's name: ");
        Scanner j = new Scanner(System.in);
        String name = j.nextLine();

        List<Human> cl = new ArrayList<>();

        for (Human t : clients) {
            if (t.toString().toLowerCase().contains(name.toLowerCase())) {
                cl.add(t);
            }
        }
        if (cl.size() == 0) {
            System.out.println("There is no such clients");
        } else {
            interactClient(cl);
        }
    }

    private static void bookInit() {
        books.add(new Book("Star Wars pt1"));
        books.add(new Book("Star Wars pt2"));
        books.add(new Book("Alice's adventures"));
        books.add(new Book("Fay Time"));
        books.add(new Book("Artur Frame"));
        books.add(new Book("Inferno"));
        books.add(new Book("God's Comedy"));
    }


    private static void clientInit() {
        File file = new File(clFile);
        if (file.length()!=0) {
            try {
                FileReader fw = new FileReader(file);
                BufferedReader fr = new BufferedReader(fw);
                String line;
                while ((line = fr.readLine()) != null) {
                    if (line.length() != 0) {
                        int pos = line.indexOf(',');
                        int pos1 = line.indexOf(',', pos + 1);

                        String name = line.substring(0, pos);
                        String surname = line.substring(pos + 1, pos1);
                        int age = Integer.valueOf(line.substring(pos1 + 1));

                        clients.add(new Human(name, surname, age));
                    }
                }
                fr.close();
            } catch (java.io.IOException e) {
                System.out.println("Error");
            }
        }
    }


    private static void logIn(int id) {
        logIn = true;
        usrId = id;
    }

}
