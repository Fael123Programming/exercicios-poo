package _library_simulation.main;

import _library_simulation.concrete_classes.library.Library;

import _library_simulation.concrete_classes.publication.author.Author;
import _library_simulation.concrete_classes.publication.types.Book;
import _library_simulation.concrete_classes.publication.types.Paper;
import _library_simulation.concrete_classes.publication.types.Thesis;
import _library_simulation.concrete_classes.user_types.CommonUser;
import _library_simulation.types_of_controllers.*;

import _teaching_institution.Institution;

import javax.swing.JOptionPane;
/**
 * This is the project's main class wherein all controller classes perform their operations:
 * -> Each event related to lending (lend, end lending, renew lending, verify due lending and furthermore): LoanController;
 * -> Creation and record of new users: UserController;
 * -> Creation and record of new publications: PublicationController;
 */

public class Main {
    private static final Library LIBRARY = new Library("Super Library","---//---");

    private static final LoanController LOANCONT = new LoanController(Main.LIBRARY);
    private static final UserController USERCONT = new UserController(Main.LIBRARY);
    private static final PublicationController PUBCONT = new PublicationController(Main.LIBRARY);

    public static void main(String[] args) {
        //Creating an author
        Author marcel = new Author("Marcel","Mestre em Tecnologia da Informação");
        //------------------
        //Creating publications
        Book livroDoMarcel = new Book("12/03/2010","Tópicos Gerais De Programação",null,
                marcel,20.30,45671,"IF - Goiano Books lmtd","392849182-34");
        Paper artigoDoMarcel = new Paper("30/08/2015","O que é POO?",livroDoMarcel,marcel,30.50,
                "Introdução aos principais pilares do paradigma de programação mais utilizado no mundo: Orientação" +
                        " a objetos");
        //--------------------
        PUBCONT.addPublication(livroDoMarcel); //Recording those publications to library
        PUBCONT.addPublication(artigoDoMarcel);
        //Creating a new user
        CommonUser rafael = new CommonUser("Rafael Fonseca", "(89) 01243-4567","email@gmail.com","12344233-34");
        //-------------------
        //Adding that user to library
        USERCONT.addUser(rafael);
        //--------------------
        //Making some loans
        if(LOANCONT.lendPublication(livroDoMarcel, rafael, 10)){
            System.out.println("First loan");
            System.out.println("--- Publication ---");
            System.out.println("Title: "+ LOANCONT.getLoan(0).getPublication().getTitle());
            System.out.println("Author: " + LOANCONT.getLoans().get(0).getPublication().getAuthors().get(0).getName());
            System.out.println("-------------------");
            System.out.println("--- Loan ---");
            System.out.println("Loan date and time: " + LOANCONT.getLoan(0).getLoanDateTime());
            System.out.println("Loan delivery date: " + LOANCONT.getLoan(0).getDeliveryDate());
            System.out.println("--- User ---");
            System.out.println("Nome: " + LOANCONT.getLoan(0).getUser().getName());
        }else System.out.println("Something went bad");
        if(LOANCONT.lendPublication(artigoDoMarcel, rafael, 5)){
            System.out.println("Second loan");
            System.out.println("--- Publication ---");
            System.out.println("Title: "+ LOANCONT.getLoan(1).getPublication().getTitle());
            System.out.println("Author: " + LOANCONT.getLoan(1).getPublication().getAuthors().get(0).getName());
            System.out.println("Abstract: " + ((Paper) LOANCONT.getLoan(1).getPublication()).getAbtract());
            System.out.println("-------------------");
            System.out.println("--- Loan ---");
            System.out.println("Loan date and time: " + LOANCONT.getLoan(1).getLoanDateTime());
            System.out.println("Loan delivery date: " + LOANCONT.getLoan(1).getDeliveryDate());
            System.out.println("--- User ---");
            System.out.println("Nome: " + LOANCONT.getLoan(0).getUser().getName());
        }else System.out.println("Something went bad");
        System.out.println("User: " + USERCONT.getUser(0).getName());
        System.out.println("Loans made: " + USERCONT.getUser(0).getTotalOfLoans());
        System.out.println("Maximum: " + USERCONT.getUser(0).getLimitOfRenovations());
        System.out.println("Is Marcel's book available? " + LOANCONT.getLoan(0).getPublication().isAvailable());
        System.out.println("Is Marcel's paper available? " + LOANCONT.getLoan(1).getPublication().isAvailable());
        Thesis teseDoMarcel = new Thesis("05/03/2020","Inteligência Artifical e Redes Neurais",livroDoMarcel,marcel,
                100.00,119,"--//--","08/11/2019", new Institution("IF - Goiano"));
        PUBCONT.addPublication(teseDoMarcel);
        LOANCONT.lendPublication(teseDoMarcel,rafael,20);
        System.out.println("Is marcel's thesis available? " + PUBCONT.getPublication(2).isAvailable());
        System.out.println("Loans Rafael just made: " + USERCONT.getUser(0).getTotalOfLoans());
        Author me = new Author("Rafael Fonseca","Undergraduate");
        Paper myPaper = new Paper("11/08/2021","O futuro da programação",teseDoMarcel,me,50.6,
                "--//--");
        PUBCONT.addPublication(myPaper);
        //Making to cause an error
        LOANCONT.lendPublication(myPaper, rafael,5);// Problem! UserCannotBorrowException (3 of 3 already)
        /*
        while(true){
            switch(Main.menu()){
                case "1":
                    Main.message("Record area");
                    break;
                case "2":
                    Main.message("Loan area");
                    break;
                case "3":
                    Main.message("Delete area");
                    break;
                case "4":
                    Main.message("Information area");
                    break;
                case "5": System.exit(0);
                default:
                    Main.message("Please, pick a valid option");
                    break;
            }
        }*/
    }

    private static String menu(){
        return Main.messageInput("Welcome to "+ Main.LIBRARY.getName()+"'s system\n(1) - Record\n(2) - Lend" +
                "\n(3) - Delete\n(4) - Information\n(5) - Exit", "Main Menu");
    }

    private static void message(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    private static String messageInput(String message, String title){
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }
}