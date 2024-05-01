import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class tracker {
    Scanner scnr = new Scanner(System.in);

    public tracker() {

    }

    // tracker methods
    public ArrayList<transactions> startTrackerAct(){
        ArrayList<transactions> result = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        String path = "transactions.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            int custID = 0;
            int id = 0;
            String store = null;
            double cost = 0.00;
            boolean type = false;

            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }

            for (String action : strings) {
                String[] arrOfStr = action.split(", ");
                custID = Integer.parseInt(arrOfStr[0]);
                id = Integer.parseInt(arrOfStr[1]);
                store = arrOfStr[2];
                cost = Double.parseDouble(arrOfStr[3]);
                type = Boolean.parseBoolean(arrOfStr[4]);
                transactions x = new transactions(type, store, cost, id, custID);
                result.add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<customer> startTrackerCust(){
        ArrayList<customer> result = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        String path = "customers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            int id = 0;
            String name = null;
            String email = null;

            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }

            for (String action : strings) {
                String[] arrOfStr = action.split(", ");
                id = Integer.parseInt(arrOfStr[0]);
                name = arrOfStr[1];
                email = arrOfStr[2];
                customer x = new customer(name, email, id);
                result.add(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String endTracker(ArrayList<transactions> transactions, ArrayList<customer> custs){
        String result = "Thank you for using this tracker!!\n Have a lovely day!!";

        String pathAct = "transactions.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathAct, false))) { // true for append mode
            for (transactions action : transactions) {
                writer.write(action.getCustID() + ", " + action.getID() + ", " + action.getStore() + ", " + action.getCost() + ", " + action.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String pathCust = "customers.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathCust, false))) { // true for append mode
            for (customer cust : custs) {
                writer.write(cust.getID() + ", " + cust.getName() + ", " + cust.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public customer signUp(ArrayList<customer> custs, int oldID){
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

        customer customer = new customer(username, email, oldID+1);
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
    public transactions addTransaction(int id, customer user){
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

        transactions transaction = new transactions(type, store, total, id, user.getID());
        return transaction;
    }

    public ArrayList<transactions> deleteTransaction(ArrayList<transactions> actions, int id){
        for (transactions transaction : actions) {
            if(transaction.getID() == id){
                actions.remove(transaction);
            }
        }

        return actions;
    }

    public ArrayList<transactions> custTransactions(ArrayList<transactions> actions, int custID){
        ArrayList<transactions> result = new ArrayList<>();
        for (transactions action : actions) {
            if(custID == action.getCustID()){
                result.add(action);
            }
        }
        return result;
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

    public ArrayList<String> printCustExpenses(ArrayList<transactions> actions, customer user){
        ArrayList<String> result = new ArrayList<>();

        for (transactions action : actions) {
            if(action.getCustID() == user.getID() && action.getType()){
                String transaction = action.getID() + ": $" + action.getCost() + " spent at " + action.getStore();
                result.add(transaction);
            }
        }

        return result;
    }

    public double custExpensesTotal(ArrayList<transactions> actions, customer user){
        double total = 0.00;

        for (transactions action : actions) {
            if(action.getCustID() == user.getID() && action.getType()){
                total += action.getCost();
            }
        }

        return total;
    }

    public ArrayList<String> printCustIncomes(ArrayList<transactions> actions, customer user){
        ArrayList<String> result = new ArrayList<>();

        for (transactions action : actions) {
            if(action.getCustID() == user.getID() && !action.getType()){
                String transaction = action.getID() + ": $" + action.getCost() + " made at " + action.getStore();
                result.add(transaction);
            }
        }
        
        return result;
    }

    public double custIncomesTotal(ArrayList<transactions> actions, customer user){
        double total = 0.00;

        for (transactions action : actions) {
            if(action.getCustID() == user.getID() && !action.getType()){
                total += action.getCost();
            }
        }

        return total;
    }
}
