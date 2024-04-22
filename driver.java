import java.util.ArrayList;
import java.util.Scanner;

public class driver {
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        int exitTicket = 1;

        tracker tracker = new tracker();
        ArrayList<customer> custs = new ArrayList<>();
        ArrayList<transactions> actions = new ArrayList<>();
        customer user = new customer();
        String noUser = "Please either sign in or sign up! You cannot log a transaction without being logged in.";
        
        while (exitTicket != 0){

            System.out.println("Please select from the following options, by typing a number:");
            System.out.println("\t User Activity");
            System.out.println("\t 1. Sign In");
            System.out.println("\t 2. Sign Up");
            System.out.println("\t 3. Log out");

            System.out.println("\t Transaction Activity");
            System.out.println("\t 4. Add a transaction");
            System.out.println("\t 5. Delete transaction");
            System.out.println("\t 6. Print all transactions");

            System.out.println("\t 0. Exit the budget tracker");

            int choice = scnr.nextInt();

            switch (choice) {
                case 1:
                    user = tracker.signIn(custs);
                    if(user.getName()==null || user.getEmail()==null){
                        System.out.println("Unable to log in. Either the username or the email is incorrect");
                    }
                    else {
                        System.out.println(user.getName() + " has signed in\n");
                    }
                    break;
                case 2: 
                    customer customer = tracker.signUp(custs, custs.size());
                    custs.add(customer);
                    user = customer;
                    System.out.println(custs.get(customer.getID()-1).printCustomer());
                    System.out.println(user.getName() + " has signed in\n");
                    break;
                case 3:
                    tracker.logOut(user);
                    user = new customer();
                    break;
                case 4:  
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    transactions transaction = tracker.addTransaction(actions.size(), user);
                    actions.add(transaction);
                    System.out.println(actions.get(transaction.getID()-1).printTransaction());
                    break; 
                case 5:  
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    System.out.println("Below are is a list of all your transactions");
                    ArrayList<String> y = tracker.printTransactions(actions);
                    for (String action : y) {
                        System.out.println(action);
                    }
                    System.out.println("Please enter the # of the transaction you would like to delete");
                    int index = scnr.nextInt();
                    
                    System.out.println("Are you sure you would like to delete transaction #" + index + "? Enter 1 for yes and 0 for no.");
                    int checker = scnr.nextInt();
                    if (checker == 1){
                        actions = tracker.deleteTransaction(actions, index);
                        System.out.println(index);
                    }
                    System.out.println("\n");
                    break;
                case 6: 
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    System.out.println(user.getID());
                    ArrayList<String> x = tracker.printTransactions(actions);
                    ArrayList<String> z = tracker.printCustTransactions(actions, user);
                    for (String action : z) {
                        System.out.println(action);
                    }
                    System.out.println("\n");
                    break;        
                case 0: 
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
