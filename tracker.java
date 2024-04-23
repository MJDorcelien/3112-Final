import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class tracker {
    private ArrayList<customer> customerList = new ArrayList<>();
    Scanner scnr = new Scanner(System.in);

    public tracker() {

    }

    // tracker methods
    public String startTracker(){
        String result = "NO customer and transaction";

        // will probably have to take in the arraylists

        // need to add customer info
        // need to add transaction info
        // need to make sure they both aren't empty
        // need to change the result string

        return result;
    }

    public String endTracker(){
        String result = "SAME customer and transaction info";

        // will probably have to take in the arraylists

        // need to update customer info
        // need to update transaction info
        // need to make sure they were actually updated
        // need to change the result string

        return result;
    }

    // customer methods
    public customer signIn(ArrayList<customer> custs){
        customer customer = new customer();

        System.out.println("Please enter your username");
        String user = scnr.next();

        System.out.println("Please enter your email");
        String mail = scnr.next();

        for (customer cust : custs) {
            if(user.equals(cust.getName()) && mail.equals(cust.getEmail())){
                return cust;
            }
        }

        return customer;
    }

    public boolean checkUsername(ArrayList<customer> custs, String username){
        boolean matches = false;
        for (customer cust : custs) {
            if (username.equals(cust.getName())) {
                matches = true;
            } 
        }
        return matches;
    }

    public boolean checkEmail(ArrayList<customer> custs, String email){
        boolean matches = false;
        for (customer cust : custs) {
            if(email.equals(cust.getEmail())){
                matches = true;
            }
        }
        return matches;
    }

    public customer signUp(ArrayList<customer> custs, int size){
        System.out.println("Please enter a username");
        String username = scnr.next();

        boolean matches = checkUsername(custs, username);

        while(matches){
            System.out.println("There is already a customer with that username. Please choose another one");
            username = scnr.next();
            matches = checkUsername(custs, username);
        }

        System.out.println("Please enter an email");
        String email = scnr.next();

        matches = checkEmail(custs, email);

        while(matches){
            System.out.println("There is already a customer with that email. Please choose antoher one");
            email = scnr.next();
            matches = checkEmail(custs, email);
        }

        customer customer = new customer(username, email, size+1, null);
        return customer;
    }

    public customer logOut(customer user){
        String name = user.getName();
        user = new customer();
        System.out.println(name + " is now signed out. You must sign in to another user or sign up to create a transaction.\n");
        return user;
    }

    public ArrayList<String> printCustomerNames(ArrayList<customer> custs){
        ArrayList<String> result = new ArrayList<>();

        for (customer cust : custs) {
            result.add(cust.getName());
        }

        return result;
    }

    public ArrayList<String> printCustomerEmails(ArrayList<customer> custs){
        ArrayList<String> result = new ArrayList<>();

        for (customer cust : custs) {
            result.add(cust.getEmail());
        }

        return result;
    }

    // transaction methods
    public transactions addTransaction(int size, customer user){
        boolean type = false;
        System.out.println("Was this transaction an expense? Did you buy something? Enter 1 for yes and 0 for no");
        int expense = scnr.nextInt();
        if (expense == 1){
            type = true;
        }

        System.out.println("What's the name of the store the transaction was at? Please do not add any spaces");
        String store = scnr.next();

        System.out.println("How much did the transacitons cost?");
        double total = scnr.nextDouble();

        System.out.println(size);
        int id = size +1;

        transactions transaction = new transactions(type, store, total, id, user.getID());
        return transaction;
    }

    public ArrayList<transactions> deleteTransaction(ArrayList<transactions> actions, int index){
        index = index-1;
        for (transactions transaction : actions) {
            if(transaction.getID() == index){
                actions.remove(transaction);
            }
        }

        return actions;
    }

    public ArrayList<String> printTransactions(ArrayList<transactions> actions){
        ArrayList<String> result = new ArrayList<>();
        String type = "MADE";

        for (transactions action : actions) {
            if(action.getType()){
                type = "SPENT";
            }
            String transaction = action.getID() + ": " + type + " $" +action.getCost() + " at " + action.getStore();
            result.add(transaction);
            type = "MADE";
        }
        return result;
    }

    public ArrayList<String> printCustTransactions(ArrayList<transactions> actions, customer user){
        ArrayList<String> result = new ArrayList<>();
        String type = "MADE";

        for (transactions action : actions) {
            if(action.getCustID() == user.getID()){
                if(action.getType()){
                    type = "SPENT";
                }
                String transaction = action.getID() + ": " + type + " $" +action.getCost() + " at " + action.getStore();
                result.add(transaction);
                type = "MADE";
            }
        }

        return result;
    }
}
