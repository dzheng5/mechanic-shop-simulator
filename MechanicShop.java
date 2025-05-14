/* MIS350 Final Project
   12/13/2024 
   David Zheng, Luis Rocha Fuentes
   This program will simulate a mechanic shop process. It will first ask users to select their vehicle type
   and the services they would like to have performed. It will then display users different options for transportation.
   When everything is confirmed it will print out a receipt including everything the user selected.
*/

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MechanicShop {
   //global variable to change pricing of services depending on vehicle type
   public static double vehiclePriceChange = 0;
   public static String[] selectedServices = new String[6];
   public static double[] selectedServicesPrices = new double[6];
   public static int serviceCount = 0;
   public static String shuttleAddress = "";
   public static double transCharge = 0;

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      //Print welcome message
      System.out.println("Welcome to the Mechanic Shop! Please press enter to begin.");
      input.nextLine();
      
      //call vehicleSelection
      vehicleSelection();
      System.out.println("Vehicle Selection confirmed. Press enter to go to the next step.");
      input.nextLine();
      
      //call serviceSelection
      serviceSelection();
      System.out.println("Service Selection confirmed. Press enter to go to the next step.");
      input.nextLine();
      
      //call transportationSelection
      transCharge = transportationSelection();
      System.out.println("Transportation Selection confirmed. Press enter to have your receipt printed.");
      input.nextLine();
      
      //call receiptPrint
      receiptPrint();
   }
   
   //Vehicle Selection Method
   public static void vehicleSelection() {
      Scanner vIntInput = new Scanner(System.in);
      Scanner vStringInput = new Scanner(System.in);
      boolean keepRunning = true;
      do {
         System.out.println("Please select your vehicle type. (e.g 1, 2, 3 etc)");
         System.out.println("1. Sedan");
         System.out.println("2. SUV");
         System.out.println("3. Truck");
         System.out.println("4. Coupe");
         System.out.println("5. Convertible");
         System.out.println("6. Electric Vehicle");
         System.out.println("7. Motorcycle");
         System.out.println("8. Other");
         int vSelection = vIntInput.nextInt();
         
         //Sedan, Coupe, Convertible, and Electric Vehicle will all pay the same base price for all services
         //SUVs and Trucks will pay $30.00 more than base price for each service
         //Motorcycle will pay $10.00 less than base price for each service
         //"Other" vehicle option will pay $40.00 more for each service due to the custom work required
         
         switch (vSelection) {
            case 1: System.out.println("You selected Sedan."); keepRunning = false; break;
            case 2: System.out.println("You selected SUV."); keepRunning = false; vehiclePriceChange = 30; break;
            case 3: System.out.println("You selected Truck."); keepRunning = false; vehiclePriceChange = 30; break;
            case 4: System.out.println("You selected Coupe."); keepRunning = false; break;
            case 5: System.out.println("You selected Convertible."); keepRunning = false; break;
            case 6: System.out.println("You selected Electric Vehicle."); keepRunning = false; break;
            case 7: System.out.println("You selected Motorcyle"); keepRunning = false; vehiclePriceChange = -10; break;
            case 8:
               System.out.print("Please enter what vehicle you are bringing: "); String vOther = vStringInput.nextLine();
               System.out.println("You entered: " + vOther); keepRunning = false; vehiclePriceChange = 40; break;
            default: System.out.println("Error! That is not a valid choice, please try again."); vIntInput.nextLine();
         }
      } while (keepRunning);
       
   }
   
   //Service selection Method
   public static void serviceSelection() {
      Scanner sInput = new Scanner(System.in);
      //prompt user to select services to perform from a numbered list
     
      int selection;  
      
      // Display services
      System.out.println("Please select what services you would like to have performed on your vehicle.");
      
      do {
         System.out.printf("1. Oil Change: $%.2f\n", 70 + vehiclePriceChange);
         System.out.printf("2. Tire Rotation: $%.2f\n", 60 + vehiclePriceChange);
         System.out.printf("3. Brake Inspection: $%.2f\n", 40 + vehiclePriceChange);
         System.out.printf("4. Battery Replacement: $%.2f\n", 150 + vehiclePriceChange);
         System.out.printf("5. Wheel Alignment: $%.2f\n", 80 + vehiclePriceChange);
         System.out.printf("6. Engine Diagnostic: $%.2f\n", 100 + vehiclePriceChange);
         System.out.println("0. Exit");
         System.out.println();
         
         System.out.print("Enter the service number (e.g 1, 2, 3 etc): ");
         selection = sInput.nextInt();
         
         //Switch statement to track service selection
         switch (selection) {
            case 1: System.out.println("You selected Oil Change.\n"); selectedServices[serviceCount] = "Oil Change"; selectedServicesPrices[serviceCount] = 70 + vehiclePriceChange; serviceCount++; break;
            case 2: System.out.println("You selected Tire Rotation.\n"); selectedServices[serviceCount] = "Tire Rotation"; selectedServicesPrices[serviceCount] = 60 + vehiclePriceChange; serviceCount++; break;
            case 3: System.out.println("You selected Brake Inspection.\n"); selectedServices[serviceCount] = "Brake Inspection"; selectedServicesPrices[serviceCount] = 40 + vehiclePriceChange; serviceCount++; break;
            case 4: System.out.println("You selected Battery Replacement.\n"); selectedServices[serviceCount] = "Battery Replacement"; selectedServicesPrices[serviceCount] = 150 + vehiclePriceChange; serviceCount++; break;
            case 5: System.out.println("You selected Wheel Alignment.\n"); selectedServices[serviceCount] = "Wheel Alignment"; selectedServicesPrices[serviceCount] = 80 + vehiclePriceChange; serviceCount++; break;
            case 6: System.out.println("You selected Engine Diagnostic.\n"); selectedServices[serviceCount] = "Engine Diagnostic"; selectedServicesPrices[serviceCount] = 100 + vehiclePriceChange; serviceCount++; break;
            case 0: System.out.println("Exiting service selection.\n"); break;
            default: System.out.println("Invalid service selection. Please try again!");
         }
         
      } while (selection != 0);
      
      //record and store car type + services
      System.out.println("You have selected the following services:");
      for (int i = 0; i < serviceCount; i++) {
         System.out.printf("- " + selectedServices[i] + ": $%.2f\n", selectedServicesPrices[i]);
      }
      
   }
   
   //Transportation selection method
   public static double transportationSelection() {
      //prompt user to select transportation options from a numbered list
      Scanner tInput = new Scanner(System.in);
      boolean keepRunning = true;
      double charge = 0;
      
      System.out.println("Please select your transportation option while your vehicle is worked on. (e.g 1, 2, 3 etc)");
      
            
      do {
         System.out.println("1. Dropping off vehicle/wait in lobby: No charge");
         System.out.println("2. Courtesy shuttle drop off/pick up: No charge");
         System.out.println("3. Standard sedan rental: $40.00");
         System.out.println("4. Luxury sedan rental: $90.00");
      
         int transSelection = tInput.nextInt();
         tInput.nextLine();
         
         switch (transSelection) {
            case 1: System.out.println("You selected Dropping off vehicle/wait in lobby."); keepRunning = false; break;
            case 2: 
               System.out.print("You selected Courtesy shuttle drop off/pick up.\nPlease enter your home street address (e.g. 1234 Washington Street): "); shuttleAddress = tInput.nextLine(); 
               System.out.printf("You entered: %s. The shuttle will drop you off and then pick you up at this address once work is complete.\n", shuttleAddress); keepRunning = false; break;
            case 3: System.out.println("You selected Standard sedan rental."); charge = 40; keepRunning = false; break;
            case 4: System.out.println("You selected Luxury sedan rental."); charge = 90; keepRunning = false; break;
            default: System.out.println("Error! That is not a valid choice, please try again.");
         }
      } while (keepRunning);
      
      return charge;
      
   } 
   
   //Receipt printing method
   public static void receiptPrint() {
      //Print the receipt showing the cost of each service done + transportation cost
      DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      double total = 0;
      
      System.out.println("          MECHANIC SHOP #350");
      System.out.println("4400 University Dr, Fairfax, VA 22030");
      System.out.println("           " + LocalDateTime.now().format(timeFormat));
      System.out.println("-------------------------------------");
      
      if (transCharge == 90.0) {
         System.out.println("  Services                     Price");
         System.out.println("-------------------------------------");
         System.out.println("- Luxury sedan rental   :     $ 90.00");
         total += transCharge;
      } 
      else if (transCharge == 40.0) {
         System.out.println("  Services                     Price");
         System.out.println("-------------------------------------");
         System.out.println("- Standard sedan rental :     $ 40.00");
         total += transCharge;
      }
      else if (!shuttleAddress.isEmpty()) {
         System.out.printf("Courtesy shuttle address:\n%s\n", shuttleAddress);
         System.out.println("-------------------------------------");
         System.out.println("  Services                     Price");
         System.out.println("-------------------------------------");
      }
      else {
         System.out.println("  Services                     Price");
         System.out.println("-------------------------------------");
      }  
      
      for (int i = 0; i < serviceCount; i++) {
         System.out.printf("- %-22s:     $%6.2f\n", selectedServices[i], selectedServicesPrices[i]);
         total += selectedServicesPrices[i];
      }
      
      
      System.out.println("-------------------------------------");
      System.out.printf("- Grand Total           :     $%.2f\n", total);
      System.out.println("*************************************");
      System.out.println("       THANK YOU, COME AGAIN!");
      System.out.println("*************************************");
      
         
         
   }
}
