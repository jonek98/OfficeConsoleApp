package put.poznan;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Office {

    public static void main(String[] args) {


        boolean running = true;
        int choice1;
        int choice2;
        int id = 0;
        Scanner scan = new Scanner(System.in);


        while (running) {

            System.out.println("Choose number of option from the menu: ");

            System.out.println("1. Add new employee.\n" +
                    "2. Delete employee by ID.\n" +
                    "3. Show all the employees.\n" +
                    "4. Show specific type of object.\n" +
                    "5. Show employees containing characters\n" +
                    "6. Execute interface method. ");

            choice1 = scan.nextInt();

            try {
                switch (choice1) {

                    case 1:
                        System.out.println("Who you want to add? \n");
                        System.out.println("1. Chief \n" +
                                "2. IT Worker \n" +
                                "3. Sales Person \n" +
                                "4. Warehouse Worker \n");

                        choice2 = scan.nextInt();

                        scan.nextLine();
                        System.out.println("Enter first name: ");
                        String first_name = scan.nextLine();

                        System.out.println("Enter last name: ");
                        String last_name = scan.nextLine();

                        System.out.println("Enter salary: ");

                        int salary = 0;

                        salary = scan.nextInt();


                        Date current_date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                        switch (choice2) {

                            case 1:

                                System.out.println("Enter type of Chief: \n" +
                                        "1. CEO \n" +
                                        "2. CIO \n" +
                                        "3. CTO \n");

                                scan.nextLine();
                                String type_of_Chief = scan.nextLine();

                                Database.getInstance().getPeople().add(new Chief(first_name, last_name, id++, salary, Chief.Chief_type.valueOf(type_of_Chief)));

                                System.out.println("Employee has been added ");

                                break;

                            case 2:

                                System.out.println("Enter department name: \n" +
                                        "1. DATA_ANALYSIS \n" +
                                        "2. SOFTWARE_DEVELOPMENT \n" +
                                        "3. SECURITY \n");

                                scan.nextLine();
                                String department = scan.nextLine();

                                Database.getInstance().getPeople().add(new IT_worker(first_name, last_name, id++, salary, formatter.format(current_date), IT_worker.Departament_type.valueOf(department)));

                                System.out.println("Employee has been added ");

                                break;

                            case 3:

                                Database.getInstance().getPeople().add(new Warehouse_worker(first_name, last_name, id++, salary, formatter.format(current_date)));
                                System.out.println("Employee has been added ");

                                break;
                        }

                        break;

                    case 2:

                        System.out.println("Type id number of employee to delete: ");
                        int id_ = scan.nextInt();

                        for (int i = 0; i < Database.getInstance().getPeople().size(); i++) {

                            if (Database.getInstance().getPeople().get(i).getId() == id_)
                                Database.getInstance().getPeople().remove(i);
                        }
                        break;

                    case 3:

                        for (Person p : Database.getInstance().getPeople()) {

                            System.out.println(p.toString());

                        }
                        break;

                    case 4:

                        System.out.println("What type of object do you want to show?\n" +
                                "1. Chief\n" +
                                "2. Warehouse worker\n" +
                                "3. Sales person\n" +
                                "4. IT_Worker\n");

                        int choice3 = scan.nextInt();

                        switch (choice3) {

                            case 1:

                                for (Person p : Database.getInstance().getPeople()) {

                                    if (p instanceof Chief) {

                                        System.out.println(p.toString());
                                    }
                                }

                                break;

                            case 2:

                                for (Person p : Database.getInstance().getPeople()) {

                                    if (p instanceof Warehouse_worker) {

                                        System.out.println(p.toString());
                                    }
                                }
                                break;

                            case 3:
                                for (Person p : Database.getInstance().getPeople()) {

                                    if (p instanceof Sales_person) {

                                        System.out.println(p.toString());
                                    }
                                }
                                break;

                            case 4:

                                for (Person p : Database.getInstance().getPeople()) {

                                    if (p instanceof IT_worker) {

                                        System.out.println(p.toString());
                                    }
                                }

                                break;

                            case 5:

                                scan.nextLine();
                                String seq = scan.nextLine();

                                for (Person p : Database.getInstance().getPeople()) {
                                    if (p.getFirst_name().contains(seq) || p.getLast_name().contains(seq))
                                        System.out.println(p);
                                }
                                break;
                        }
                        break;

                    case 5:

                        System.out.println("Type keyword: ");
                        scan.nextLine();
                        String keyword = scan.nextLine();

                        for (Person p : Database.getInstance().getPeople()) {

                            if (p.toString().contains(keyword)) {

                                System.out.println(p.toString());
                            }
                        }

                        break;

                    case 6:

                        for (Person p : Database.getInstance().getPeople()) {

                            if (p instanceof EmployeeInterface)
                                ((EmployeeInterface) p).GiveRaise();
                        }
                }
            } catch (InputMismatchException e) {

                System.err.println("Salary need to be an Integer Value!!!"+"\n"+
                        "Back to menu");
                scan.next();
            }
        }

    }

}
