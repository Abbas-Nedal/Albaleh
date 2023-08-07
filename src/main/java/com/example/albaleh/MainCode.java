package com.example.albaleh;

import java.sql.*;
import java.util.Scanner;


import java.util.logging.Logger;





public class MainCode {
   public static final     String txF = "Enter Number for ID FLOORSNUMBER : ";
     public static final     String txH = "Enter Number for ID HOUSE : ";
     static Logger log = Logger.getLogger(MainCode.class.getName());

    static boolean check(String s) {
        if (s == null)
            return false;

        int len = s.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is not a letter
            // if it is not a letter ,it will return false
            if ((Character.isLetter(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws SQLException {



        int IDOWNER;
        int   IDHOUSE;
        int  idadv;
        int   IDFLOORSNUMBER;
        int  IDAPARTMENTS;
        String ID;
        String Pass;
        Admin admin = new Admin();

        Tenants tenants = new Tenants();
        int com;
        Scanner scanner = new Scanner(System.in);
        Boolean TF=false;
        int input1;
        int      input2;
        int    input3;
        int   input4;
        String one;
        String two;
        String three;
        String link;



        while (true) {
            log.info(" ------------------------------ \t Welcome to the login page  ---------------------- \n\n");
            log.info("Enter userID : ");
            ID = scanner.next();
            if (ID.equals("0")){break;}

            log.info("Enter Password :  ");
            Pass = scanner.next();


            if (ID.equals("admin") && Pass.equals("admin")) {
                admin.setStatus(true);
                log.info("Welcome to your account, Admin\n\n");

                while (true) {
                    log.info("Welcome to the list. Enter the number to be processed\n" +
                            "1-Watching reservations via the system.\n" +
                            "2-Show Ada Waiting Acceptance.\n3-Accept Ads\n4-Refuse Ads.\n" +
                            "5-Show Accepted Ads\n6-logout  \n");
                    log.info("Enter the Number:");
                    com = scanner.nextInt();

                    if (com == 1) {
                        admin.WatchingReservations();
                    } else if (com == 2) {
                        admin.ShowAdaWaitingِAcceptance();
                    } else if (com == 3) {
                        log.info("Enter Number for ID OWNER : ");
                        IDOWNER = scanner.nextInt();
                        log.info(txH);
                        IDHOUSE = scanner.nextInt();
                        log.info(txF);
                        IDFLOORSNUMBER = scanner.nextInt();
                        log.info("Enter Number for  ID APARTMENTS :");
                        IDAPARTMENTS = scanner.nextInt();
                        log.info("Enter Number for id adv: ");
                        idadv = scanner.nextInt();

                        if (admin.AcceptAds(IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS, idadv)) {
                            log.info(" Ads accepted \n");
                        } else {
                            log.info("Error, Check numbers\n");
                        }
                    } else if (com == 4) {
                        log.info("Enter Number for ID OWNER : \n");
                        IDOWNER = scanner.nextInt();
                        log.info(txH);
                        IDHOUSE = scanner.nextInt();
                        log.info(txF);
                        IDFLOORSNUMBER = scanner.nextInt();
                        log.info("Enter Number for  ID APARTMENTS : ");
                        IDAPARTMENTS = scanner.nextInt();
                        log.info("Enter Number for id adv: ");
                        idadv = scanner.nextInt();


                        if (admin.RefuseAds(IDOWNER, IDHOUSE, IDFLOORSNUMBER, IDAPARTMENTS, idadv)) {
                            log.info(" Ads RefuseAds success \n");
                        } else {
                            log.info("Error, Check numbers\n");
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
                        log.info("Welcome to your account, tenant\n\n");
                        tenants.setUserName(ID);
                        tenants.setPassword(Pass);


                        while (true) {
                            log.info("Welcome to the list. Enter the number to be processed\n----------------------------------------\n" +
                                    "1-view the available housing and pictures of housing and know their prices, location, and available services.\n" +
                                    "2-book accommodation via the app.\n3-Show neighbors students\n4-advertise own used furniture for sale.\n" +
                                    "5-control panel after booking\n6-Delete FURNITURE\n" +
                                    "7-Show FURNITURE Advertise\n8-DeleteBook\n9-logout  \n");
                            log.info("Enter the Number:");
                            com = scanner.nextInt();

                            if (com == 1) {
                                tenants.ShowAvailableHouse();
                            }
                            else if (com == 2) {
                                log.info("Enter Number for ID OWNER : ");
                                IDOWNER = scanner.nextInt();
                                log.info(txH);
                                IDHOUSE = scanner.nextInt();
                                log.info(txF);
                                IDFLOORSNUMBER = scanner.nextInt();
                                log.info("Enter Number for : ID APARTMENTS ");
                                IDAPARTMENTS = scanner.nextInt();

                                tenants.bookAccommodation(IDOWNER,IDHOUSE,IDFLOORSNUMBER, IDAPARTMENTS);

                                // Code for option 2
                            }
                            else if (com == 3) {
                                tenants.studentNeighbors();
                            }
                            else if (com == 4) {
                                log.info("Enter price : ");
                                int price = scanner.nextInt() ;
                                log.info("Enter price DESCRIPTION : ");
                                String dec = scanner.next() ;

                                tenants.InsertFURNITUREAdvertise(price,dec);
                            }
                            else if (com == 5) {
                                tenants.ShowSomeInfoAfterBooking();
                            }
                            else if (com == 6) {
                                log.info("Enter ID FURNITURE : ");
                                int IDFURNITURE = scanner.nextInt();
                                tenants.DeleteFURNITURE(IDFURNITURE);

                            }
                            else if (com == 7) {

                                tenants.ShowFURNITUREAdvertise();
                            }
                            else if (com == 8) {

                               if (tenants.DeleteBook()){
                                   log.info("Success\n");

                               }else {  log.info("fiald\n");}
                            }
                            else if (com == 9) {

                                break;
                            }


                        }
                    } else {
                        log.info("Verify your username or password\n");
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

                                           log.info("Inclusive water and electricity or not?Yes/No\n");

                                           one = scanner.next();
                                           log.info("\nenter the value of mounthly rent :");
                                           two = scanner.next();
                                           owners.monthlyRent(one, two);

                                       } else if (input4 == 5) {

                                           log.info("enter your phone number (if your number exist Print 0 ):");
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

                                   log.info("number for house : \n");
                               int parts = scanner.nextInt();
                               owners.setHouse(parts);
                                   log.info("number for floor : \n");
                                parts = scanner.nextInt();
                                owners.setFloor(parts);
                                   log.info("number for Apartment: \n");
                               parts = scanner.nextInt();
                               owners.setApartment(parts);
                                   log.info("number for DESCRIPTION: \n");
                               String dec  = scanner.next();

                               if (owners.addAdv(owners.getHouse(),owners.getFloor(),owners.getApartment(),dec)){
                                  log.info("Data inserted successfully.\n");
                               }


                           }
                           else if (input1 == 4 ) { break;

                           }

                       }
                    }
                    else {  log.info("Error , not exist \n"); }

                }
            }
             else {
                log.info("Error , invalid input\n"); }
        }

    }

}
