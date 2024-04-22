public class transactions {
    
    public boolean transactionType;
    public String transactionStore;
    public double transactionCost;
    public int transactionID;
    public int customerID;

    public transactions (boolean transactionType, String transactionStore, double transactionCost, int transactionID, int customerID){
        this.transactionType = transactionType;
        this.transactionStore = transactionStore;
        this.transactionCost = transactionCost;
        this.transactionID = transactionID;
        this.customerID = customerID;
    }

    public boolean getType(){
        return transactionType;
    }

    public String getStore(){
        return transactionStore;
    }

    public double getCost(){
        return transactionCost;
    }

    public int getID(){
        return transactionID;
    }

    public int getCustID(){
        return customerID;
    }

    public String printTransaction() {
        //need to pull in all the budgets
        //look through the budgets and 
        //if the id matches id in transactionbudget print out name

        String typeString = "";
        
        if(transactionType){
            typeString = "Expense";
        }
        else{
            typeString = "Income";
        }

        return "Type: " + typeString + "\n" +
            "Store: " + transactionStore + "\n" + 
            "Cost: $" + transactionCost + "\n";
    }
}
