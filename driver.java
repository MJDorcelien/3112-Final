import java.util.ArrayList;
import java.util.Scanner;

public class driver {
    public static void main(String[] args){

        Scanner scnr = new Scanner(System.in);
        int exitTicket = 1;

        tracker tracker = new tracker();
        ArrayList<customer> custs = tracker.startTrackerCust();
        ArrayList<transactions> actions = tracker.startTrackerAct();
        customer user = new customer();
        ArrayList<transactions> acts = new ArrayList<>();
        String noUser = "Please either sign in or sign up! You cannot log a transaction without being logged in.";
        
        while (exitTicket != 0){

            System.out.println("Please select from the following options, by typing a number:");
            if(user.getName()==null || user.getEmail()==null){
                System.out.println("\t 1. Sign In");
                System.out.println("\t 2. Sign Up\n");
                System.out.println("\t 0. Exit the budget tracker");
            }
            else {
                System.out.println("\t 3. Log out\n");

                System.out.println("\t 4. Add a transaction");
                System.out.println("\t 5. Delete transaction");
                System.out.println("\t 6. Print all your transactions"); 
                System.out.println("\t 7. Print all EXPENSES");
                System.out.println("\t 8. Print all INCOME\n");

                System.out.println("\t 0. Exit the budget tracker");           
            }

            int choice = scnr.nextInt();

            switch (choice) {
                case 1:
                    user = tracker.signIn(custs);
                    if(user.getName()==null || user.getEmail()==null){
                        System.out.println("Unable to log in. Either the username or the email is incorrect\n");
                    }
                    else {
                        System.out.println(user.getName() + " has signed in\n");
                    }
                    acts = tracker.custTransactions(actions, user.getID());
                    break;
                case 2: 
                    int id = 0;
                    if (custs.size()!=0){
                        id = custs.get(custs.size()-1).getID();                        
                    }
                    else{}
                    customer customer = tracker.signUp(custs, id);
                    custs.add(customer);
                    user = customer;
                    System.out.println("\n" + custs.get(customer.getID()-1).printCustomer());
                    System.out.println(user.getName() + " has signed in\n");
                    acts = tracker.custTransactions(actions, user.getID());
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
                    int newID = 1;
                    if(acts.size()!= 0){
                        newID = acts.get(acts.size()-1).getID()+1;
                    }
                    transactions transaction = tracker.addTransaction(newID, user);
                    acts.add(transaction);
                    actions.add(transaction);
                    System.out.println(actions.get(actions.size()-1).printTransaction());
                    break; 
                case 5:  
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    if(acts.isEmpty()){
                        System.out.println(user.getName() + " does not have any transactions, so they cannot delete any transactions.\n");
                        break;
                    }
                    System.out.println("Below are is a list of all your transactions\n");
                    ArrayList<String> y = tracker.printTransactions(acts);
                    for (String action : y) {
                        System.out.println(action);
                    }
                    System.out.println("\nPlease enter the # of the transaction you would like to delete");
                    int index = scnr.nextInt();
                    
                    System.out.println("Are you sure you would like to delete transaction #" + index + "? Enter 1 for yes and 0 for no.");
                    int checker = scnr.nextInt();
                    if (checker == 1){
                        acts = tracker.deleteUserTransaction(acts, index);
                        actions = tracker.deleteTransaction(actions, index, user.getID());
                    }
                    System.out.println("\n");
                    break;
                case 6: 
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    ArrayList<String> z = tracker.printTransactions(acts);
                    for (String action : z) {
                        System.out.println(action);
                    }
                    if (z.isEmpty()){
                        System.out.println(user.getName() + " has not logged any transactions.");
                    }
                    System.out.println("\n");
                    break;   
                case 7: 
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    ArrayList<String> expenses = tracker.printCustExpenses(actions, user);
                    double expensesTotal = tracker.custExpensesTotal(actions, user);
                    System.out.println(user.getName() + " has spent $" + expensesTotal + "\n");
                    for (String action : expenses) {
                        System.out.println(action);
                    }
                    System.out.println("\n");
                    break; 
                case 8:  
                    if(user.getName() == null){
                        System.out.println(noUser);
                        break;
                    }
                    ArrayList<String> incomes = tracker.printCustIncomes(actions, user);
                    double incomesTotal = tracker.custIncomesTotal(actions, user);
                    System.out.println(user.getName() + " has made $" + incomesTotal + "\n");
                    for (String action : incomes) {
                        System.out.println(action);
                    }
                    System.out.println("\n");
                    break; 
                case 0: 
                    String result = tracker.endTracker(actions, custs);
                    System.out.println(result);
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
