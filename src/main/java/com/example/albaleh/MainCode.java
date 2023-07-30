package com.example.albaleh;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MainCode {
    static boolean check(String s) {
        if (s == null) // checks if the String is null {
            return false;

        int len = s.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is not a letter
            // if it is not a letter ,it will return false
            if ((Character.isLetter(s.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws SQLException {



        int IDOWNER, IDHOUSE, idadv, IDFLOORSNUMBER, IDAPARTMENTS;
        String ID;
        String Pass;
        Admin admin = new Admin();
        Tenants tenants = new Tenants();
        int com;
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println(" ------------------------------ \t Welcome to the login page  ---------------------- \n\n");
            System.out.print("Enter userID : ");
            ID = scanner.next();

            System.out.print("Enter Password :  ");
            Pass = scanner.next();
           Boolean flagLater = check(ID);

            if (ID.equals("admin") && Pass.equals("admin")) {
                admin.setStatus(true);
                System.out.println("Welcome to your account, Admin\n");

                while (true) {
                    System.out.println("Welcome to the list. Enter the number to be processed\n" +
                            "1-Watching reservations via the system.\n" +
                            "2-Show Ada Waiting Acceptance.\n3-Accept Ads\n4-Refuse Ads.\n" +
                            "5-Show Accepted Ads\n6-logout  ");
                    System.out.print("Enter the Number:");
                    com = scanner.nextInt();

                    if (com == 1) {
                        admin.WatchingReservations();
                    } else if (com == 2) {
                        admin.ShowAdaWaitingِAcceptance();
                    } else if (com == 3) {
                        System.out.print("Enter Number for ID OWNER : ");
                        IDOWNER = scanner.nextInt();
                        System.out.print("Enter Number for ID HOUSE : ");
                        IDHOUSE = scanner.nextInt();
                        System.out.print("Enter Number for ID FLOORSNUMBER : ");
                        IDFLOORSNUMBER = scanner.nextInt();
                        System.out.print("Enter Number for : ID APARTMENTS ");
                        IDAPARTMENTS = scanner.nextInt();
                        System.out.print("Enter Number for id adv: ");
                        idadv = scanner.nextInt();

                        if (admin.AcceptAds(IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS, idadv)) {
                            System.out.println(" Ads accepted ");
                        } else {
                            System.out.println("Error, Check numbers");
                        }
                    } else if (com == 4) {
                        System.out.print("Enter Number for ID OWNER : ");
                        IDOWNER = scanner.nextInt();
                        System.out.print("Enter Number for ID HOUSE : ");
                        IDHOUSE = scanner.nextInt();
                        System.out.print("Enter Number for ID FLOORSNUMBER : ");
                        IDFLOORSNUMBER = scanner.nextInt();
                        System.out.print("Enter Number for : ID APARTMENTS ");
                        IDAPARTMENTS = scanner.nextInt();
                        System.out.print("Enter Number for id adv: ");
                        idadv = scanner.nextInt();

                        if (admin.RefuseAds(IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS, idadv)) {
                            System.out.println(" Ads accepted ");
                        } else {
                            System.out.println("Error, Check numbers");
                        }
                    } else if (com == 5) {
                        admin.ShowAcceptedAds();
                    } else if (com == 6) {
                        admin.setStatus(false);
                        break;
                    }
                }
            }
            else if (!check(ID)){
                if (Integer.parseInt(ID) >= 4000) {
                    tenants.setStatus(tenants.login(ID, Pass));
                    if (tenants.isStatus()) {
                        System.out.println("Welcome to your account, tenant\n");
                        tenants.setUserName(ID);
                        tenants.setPassword(Pass);

                        while (true) {
                            System.out.println("Welcome to the list. Enter the number to be processed\n----------------------------------------\n" +
                                    "1-view the available housing and pictures of housing and know their prices, location, and available services.\n" +
                                    "2-book accommodation via the app.\n3-Show neighbors students\n4-advertise own used furniture for sale.\n" +
                                    "5-control panel after booking\n6-Delete FURNITURE\n" +
                                    "7-Show FURNITURE Advertise\n8-logout  ");
                            System.out.print("Enter the Number:");
                            com = scanner.nextInt();

                            if (com == 1) {
                                tenants.ShowAvailableHouse();
                            } else if (com == 2) {
                                System.out.print("Enter Number for ID OWNER : ");
                                IDOWNER = scanner.nextInt();
                                System.out.print("Enter Number for ID HOUSE : ");
                                IDHOUSE = scanner.nextInt();
                                System.out.print("Enter Number for ID FLOORSNUMBER : ");
                                IDFLOORSNUMBER = scanner.nextInt();
                                System.out.print("Enter Number for : ID APARTMENTS ");
                                IDAPARTMENTS = scanner.nextInt();

                                tenants.bookAccommodation(IDOWNER,IDHOUSE,IDFLOORSNUMBER, IDAPARTMENTS);

                                // Code for option 2
                            } else if (com == 3) {
                                tenants.studentNeighbors();
                            } else if (com == 4) {
                                System.out.print("Enter price : ");
                                int price = scanner.nextInt() ;
                                System.out.print("Enter price DESCRIPTION : ");
                                String dec = scanner.next() ;

                                tenants.InsertFURNITUREAdvertise(price,dec);
                            } else if (com == 5) {
                                tenants.ShowSomeInfoAfterBooking();
                            } else if (com == 6) {
                                System.out.print("Enter ID FURNITURE : ");
                                int IDFURNITURE = scanner.nextInt();
                                tenants.DeleteFURNITURE(IDFURNITURE);
                            }
                            else if (com == 7) {

                                tenants.ShowFURNITUREAdvertise();
                            }
                            else if (com == 8) {

                                break;
                            }


                        }
                    } else {
                        System.out.println("Verify your username or password");
                    }
                }
                else if(Integer.parseInt(ID) >=1 && Integer.parseInt(ID) < 4000) {
//Owner

                }
            }
             else {System.out.println("Error , invalid input"); }
        }
    }
}
