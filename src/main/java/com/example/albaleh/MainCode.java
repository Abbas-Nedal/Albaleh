package com.example.albaleh;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static java.lang.System.out;

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
        Boolean TF=false;
        int input1,input2,input3,input4;
        String one,two,three,link;
int flag = 0;


        while (true) {
            out.println(" ------------------------------ \t Welcome to the login page  ---------------------- \n\n");
            out.print("Enter userID : ");
            ID = scanner.next();

            out.print("Enter Password :  ");
            Pass = scanner.next();
           Boolean flagLater = check(ID);

            if (ID.equals("admin") && Pass.equals("admin")) {
                admin.setStatus(true);
                out.println("Welcome to your account, Admin\n");

                while (true) {
                    out.println("Welcome to the list. Enter the number to be processed\n" +
                            "1-Watching reservations via the system.\n" +
                            "2-Show Ada Waiting Acceptance.\n3-Accept Ads\n4-Refuse Ads.\n" +
                            "5-Show Accepted Ads\n6-logout  ");
                    out.print("Enter the Number:");
                    com = scanner.nextInt();

                    if (com == 1) {
                        admin.WatchingReservations();
                    } else if (com == 2) {
                        admin.ShowAdaWaitingِAcceptance();
                    } else if (com == 3) {
                        out.print("Enter Number for ID OWNER : ");
                        IDOWNER = scanner.nextInt();
                        out.print("Enter Number for ID HOUSE : ");
                        IDHOUSE = scanner.nextInt();
                        out.print("Enter Number for ID FLOORSNUMBER : ");
                        IDFLOORSNUMBER = scanner.nextInt();
                        out.print("Enter Number for : ID APARTMENTS ");
                        IDAPARTMENTS = scanner.nextInt();
                        out.print("Enter Number for id adv: ");
                        idadv = scanner.nextInt();

                        if (admin.AcceptAds(IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS, idadv)) {
                            out.println(" Ads accepted ");
                        } else {
                            out.println("Error, Check numbers");
                        }
                    } else if (com == 4) {
                        out.print("Enter Number for ID OWNER : ");
                        IDOWNER = scanner.nextInt();
                        out.print("Enter Number for ID HOUSE : ");
                        IDHOUSE = scanner.nextInt();
                        out.print("Enter Number for ID FLOORSNUMBER : ");
                        IDFLOORSNUMBER = scanner.nextInt();
                        out.print("Enter Number for : ID APARTMENTS ");
                        IDAPARTMENTS = scanner.nextInt();
                        out.print("Enter Number for id adv: ");
                        idadv = scanner.nextInt();

                        if (admin.RefuseAds(IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS, idadv)) {
                            out.println(" Ads RefuseAds success ");
                        } else {
                            out.println("Error, Check numbers");
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
                        out.println("Welcome to your account, tenant\n");
                        tenants.setUserName(ID);
                        tenants.setPassword(Pass);


                        while (true) {
                            out.println("Welcome to the list. Enter the number to be processed\n----------------------------------------\n" +
                                    "1-view the available housing and pictures of housing and know their prices, location, and available services.\n" +
                                    "2-book accommodation via the app.\n3-Show neighbors students\n4-advertise own used furniture for sale.\n" +
                                    "5-control panel after booking\n6-Delete FURNITURE\n" +
                                    "7-Show FURNITURE Advertise\n8-DeleteBook\n9-logout  ");
                            out.print("Enter the Number:");
                            com = scanner.nextInt();

                            if (com == 1) {
                                tenants.ShowAvailableHouse();
                            }
                            else if (com == 2) {
                                out.print("Enter Number for ID OWNER : ");
                                IDOWNER = scanner.nextInt();
                                out.print("Enter Number for ID HOUSE : ");
                                IDHOUSE = scanner.nextInt();
                                out.print("Enter Number for ID FLOORSNUMBER : ");
                                IDFLOORSNUMBER = scanner.nextInt();
                                out.print("Enter Number for : ID APARTMENTS ");
                                IDAPARTMENTS = scanner.nextInt();

                                tenants.bookAccommodation(IDOWNER,IDHOUSE,IDFLOORSNUMBER, IDAPARTMENTS);

                                // Code for option 2
                            }
                            else if (com == 3) {
                                tenants.studentNeighbors();
                            }
                            else if (com == 4) {
                                out.print("Enter price : ");
                                int price = scanner.nextInt() ;
                                out.print("Enter price DESCRIPTION : ");
                                String dec = scanner.next() ;

                                tenants.InsertFURNITUREAdvertise(price,dec);
                            }
                            else if (com == 5) {
                                tenants.ShowSomeInfoAfterBooking();
                            }
                            else if (com == 6) {
                                out.print("Enter ID FURNITURE : ");
                                int IDFURNITURE = scanner.nextInt();
                                tenants.DeleteFURNITURE(IDFURNITURE);

                            }
                            else if (com == 7) {

                                tenants.ShowFURNITUREAdvertise();
                            }
                            else if (com == 8) {

                               if (tenants.DeleteBook()){
                                   out.println("Success");

                               }else {out.println("fiald");}
                            }
                            else if (com == 9) {

                                break;
                            }


                        }
                    } else {
                        out.println("Verify your username or password");
                    }
                }
                else if(Integer.parseInt(ID) >=1 && Integer.parseInt(ID) < 4000) {
//Owner
                    Owners owners=new Owners(Integer.parseInt(ID));
                    owners.setStatus( owners.login(ID,Pass));

                    if(owners.isStatus()) {
                        tenants.setUserName(ID);
                        tenants.setPassword(Pass);
                       while(true) {
                           owners.menu();
                           input1 = scanner.nextInt();
                           owners.menuChoice(input1);


                               if (input1 == 1 ) {




                                   input4 = scanner.nextInt();
                                   owners.witchhouse(input4);
                                   input4 = scanner.nextInt();
                                   owners.witchFloor(input4);

                                   owners.insertApartment();

                                   while (true) {


                                       owners.announcingPrivateResidencesMenu();
                                       input4 = scanner.nextInt();
                                       owners.announcingPrivateResidences(input4);
                                       if (input4 == 1) {
                                           link = scanner.next();
                                           owners.addPhoto(link);
                                       } else if (input4 == 2) {

                                           owners.desc();
                                           one = scanner.nextLine();
                                           owners.location();
                                           two = scanner.nextLine();
                                           owners.limits();
                                           three = scanner.nextLine();
                                           owners.RlocationInfo(one, two, three);
                                       } else if (input4 == 3) {

                                           input3 = scanner.nextInt();
                                           owners.availableService(input3);
                                       } else if (input4 == 4) {

                                           out.println("Inclusive water and electricity or not?Yes/No");

                                           one = scanner.next();
                                           out.print("\nenter the value of mounthly rent :");
                                           two = scanner.next();
                                           owners.monthlyRent(one, two);

                                       } else if (input4 == 5) {

                                           out.print("enter your phone number (if your number exist Print 0 ):");
                                           input3 = scanner.nextInt();
                                           owners.contactInfo(input3);
                                       } else {
                                           break;

                                       }


                                   }
                               }


                           else  if (input1 == 2 ) {
                                   while (true) {
                                       owners.ownerhousemenu();
                                       input3 = scanner.nextInt();
                                       TF = owners.ControlPanelHouse(input3);
                                       if (!TF) {
                                           continue;
                                       }
                                       input2 = scanner.nextInt();
                                       TF = owners.controlpanelFloor(input3, input2);
                                       if (!TF) {
                                           continue;
                                       }
                                       input4 = scanner.nextInt();
                                       TF = owners.dashBoardControlPanel(input3, input2, input4);
                                       if (!TF) {
                                           continue;
                                       }
                                       }
                                   }
                          else if (input1 == 3 ) {

                               out.print("number for house : ");
                               int parts = scanner.nextInt();
                               owners.setHouse(parts);
                               out.print("number for floor : ");
                                parts = scanner.nextInt();
                                owners.setFloor(parts);
                               out.print("number for Apartment: ");
                               parts = scanner.nextInt();
                               owners.setApartment(parts);
                               out.print("number for DESCRIPTION: ");
                               String dec  = scanner.next();

                               if (owners.addAdv(owners.getHouse(),owners.getFloor(),owners.getApartment(),dec)){
                                   out.println("Data inserted successfully.");
                               }


                           }
                           else if (input1 == 4 ) { break;

                           }

                       }
                    }
                    else { out.println("Error , not exist"); }

                }
            }
             else {
                out.println("Error , invalid input"); }
        }

    }

}
