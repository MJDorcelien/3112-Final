import java.util.ArrayList;
import java.util.List;

public class customer {

    public String name;
    public String email;
    public int id;
    public List<Integer> transactions;

    public customer(){

    }

    public customer(String name, String email, int id, List<Integer> transactions){
        this.name = name;
        this.email = email;
        this.id = id;
        this.transactions = transactions;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public int getID(){
        return id;
    }

    public List<Integer> getTransactions(){
        return transactions;
    }

    public String addCustomer(){
        return "this adds a customer";
    }

    public String removeCustomer(){
        return "this removes a customer";
    }

    public String addTransaction(int index){
        return "new transaction added";
    }

    public String findByName(){
        return "this finds a customer by their name";
    }

    // this is not necessary
    public String findByID(){
        return "this finds a customer by their id";
    }

    // this is not necessary
    public String findByEmail(){
        return "this finds a customer by their email";
    }

    public String printCustomer(){
        return name + " signed up with " + email + "\n";
    }
}
