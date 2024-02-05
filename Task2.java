import java.io.*;
import java.util.*;

class Account{

    static int Account_number = 1111;
    String Account_holder_name;
    int PIN;
    double Balance;
    String unique_ID;
    int A_no;
    
    void create_Account(){

     A_no = Account_number;
     Scanner sc = new Scanner(System.in);

     System.out.println("Enter Account Holder Name: ");
     Account_holder_name = sc.nextLine();

     System.out.println("Enter User Name: ");
     unique_ID = sc.nextLine();

      int length_PIN = 0;

      do{

        System.out.println("Enter four digit PIN: ");
        PIN = sc.nextInt();
        length_PIN = String.valueOf(PIN).length();
      } while (length_PIN != 4);
      
      System.out.println("Enter Initial Deposite Amount: ");
      Balance = sc.nextDouble();

      System.out.println("Congratulations Account Successfully Created ");
      System.out.println("Account Details:........\n ");
      System.out.println("Account Number:\n " + A_no);
      System.out.println("Account Holder Name:\n" + Account_holder_name);
      System.out.println("Balance:\n " + Balance );
      System.out.println("Thankyou.............\n");

      String fileName = Account_number + ".txt";
      File file = new File(fileName);

      try{
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
       
        writer.write("Account Created\n");
        writer.write("Account Number: "+ Account_number + "\n");
        writer.write("USSER ID: " + unique_ID + "\n");
        writer.write("Account Holder Name: " + Account_holder_name + "\n");
        writer.write("PIN: "+ PIN + "\n");
        writer.write("Ballance: " + Balance + "\n");
        writer.write("Date: " + new Date() + "\n\n\n");
         
        writer.close();

      }  catch(IOException e){

        System.out.println("An error occured while creating the file " + fileName);
        e.printStackTrace();

      }

      try {

        Thread.sleep(   5000);
        
      } catch (InterruptedException e) {
        // TODO: handle exception
        e.printStackTrace();
      }

      Account_number++;
    }
}

class ATM {

  void Withdraw(Account acc){

    Scanner sc = new Scanner(System.in);

    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Withdraw Money mode.\n");
    System.out.println("Enter Money in multiples of 100.");
     
    double Amount = sc.nextDouble();
     
    if(Amount % 100 ==0){

      if(acc.Balance >= Amount){

        acc.Balance -= Amount;
        System.out.println("Your Transaction is Processing\n");

        try {
          
          String fileName = acc.A_no + ".txt";

          FileWriter fileWriter = new FileWriter(fileName, true);
          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

          bufferedWriter.write("Date: " +new Date() + "\n");
          bufferedWriter.write("Withdrawal: " + Amount + "\n");
          bufferedWriter.write("Account Number: " + acc.A_no + "\n");
          bufferedWriter.write("Remaining Balance: " + acc.Balance + "\n\n");
          
          bufferedWriter.close();
          fileWriter.close();

        } catch (IOException e) {
          // TODO: handle exception
          System.out.println("An error occured while writing to the file.");
          e.printStackTrace();
        }
        System.out.println("Thankyou For Banking With us.");

        try {
          Thread.sleep(6000);
        } catch (InterruptedException e) {
          // TODO: handle exception
          e.printStackTrace();

        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
      else{
        System.out.print("Insufficient Funds....");

      }
     }
    else{
      System.out.println("Amount not in multiples of 100....");
      System.out.println("Try Again...");
    }
    
  }

  void Deposite_by_Transfer(Account acc, double Amount){

    acc.Balance +=Amount;

    try {

      String fileName = acc.A_no + ".txt";
      
      FileWriter fileWriter = new FileWriter(fileName, true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      
      bufferedWriter.write("Deposite: " + Amount + "\n");
      bufferedWriter.write("Date: " + new Date() + "\n");
      bufferedWriter.write("Account Number: " + acc.A_no + "\n");
      bufferedWriter.write("Remaining Balance: " + acc.Balance + "\n\n");
      bufferedWriter.close();
      fileWriter.close();
    } catch (IOException e) {
      // TODO: handle exception
      System.out.println("An erroe occured while writing to the file..... ");
      e.printStackTrace();

    }
  }

  void Deposite(Account acc){

    Scanner sc = new Scanner(System.in);

    System.out.println("\033[H\033[2J");
    System.out.flush();
    System.out.println("Money Deposite Mode...");
    System.out.println("Enter Amount you want to Deposite.....");
    double Amount = sc.nextDouble();
    acc.Balance += Amount;
    
    System.out.println("\033[H\033[2J");
    System.out.flush();

    try {

    String fileName = acc.A_no + ".txt";
    System.out.println("The File Name - " + fileName);

    FileWriter fileWriter = new FileWriter(fileName, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      
      bufferedWriter.write("Deposite: " + Amount + "\n");
      bufferedWriter.write("Date: " + new Date() + "\n");
      bufferedWriter.write("Account Number: " + acc.A_no + "\n");
      bufferedWriter.write("Remaining Balance: " + acc.Balance + "\n\n");
      bufferedWriter.close();
      fileWriter.close();
      
    } catch (IOException e) {
      // TODO: handle exception
      System.out.println("An erroe occured while writing to the file..... ");
      e.printStackTrace();
        
    }
     
    System.out.println("Processing Your Request! Please Wait...");

    try {
      Thread.sleep(5000);

    } catch (InterruptedException e) {
      // TODO: handle exception
      e.printStackTrace();

    }

    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Transction Completed Successfully!");
    System.out.println("\n\n Thankyou For Banking With us...");
    System.out.println("----Going to Login Page-----");

    try {
      Thread.sleep(3000);

    } catch (InterruptedException e) {
      // TODO: handle exception
      e.printStackTrace();

    }

  }

  void Transfer(Account acc_1, Account acc_2, double Amount ){

    if(acc_1.Balance >= Amount){
     acc_1.Balance -= Amount;
     ATM a = new ATM();
     a.Deposite_by_Transfer(acc_2, Amount);
     
     try {

     String fileName = acc_1.A_no + ".txt";
     FileWriter fileWriter = new FileWriter(fileName, true);
     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      
      bufferedWriter.write("Transfered: " + Amount + "\n");
      bufferedWriter.write("Date: " + new Date() + "\n");
      bufferedWriter.write("Account Number: " + acc_1.A_no + "\n");
      bufferedWriter.write("Remaining Balance: " + acc_1.Balance + "\n\n");
      bufferedWriter.close();
      fileWriter.close();
      
     } catch (IOException e) {
      // TODO: handle exception
      System.out.println("An error occurred while writing to the file..");
      e.printStackTrace();

     }

     System.out.println("Processing Your Request, Please Wait!\n");

     try {

      Thread.sleep(5000);
      
     } catch (InterruptedException e) {
      // TODO: handle exception
      e.printStackTrace();
     }

     System.out.print("\033[H\033[2J");
     System.out.flush();
     System.out.println("\nAccount Transfer Completed Successfully!!!!!!!!!!!!!\n");
     System.out.println("--------------Going to Login Page-----------------");
     }
     try {
      
      Thread.sleep(5000);

     } catch (InterruptedException e) {
      // TODO: handle exception
      e.printStackTrace();
     
    }
    
  }
  

  void Display_details(Account acc)
  {
     
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Displaying account Details\n");

    try
    {

      Thread.sleep(2000);  

    } catch (InterruptedException e)
      {

       e.printStackTrace();

      }

     String fileName = String.valueOf(acc.A_no) + ".txt";
     File file = new File(fileName);

     try
     {

     FileReader fileReader = new FileReader(file);
     BufferedReader bufferedReader = new BufferedReader(fileReader);
     String line = null;

     while((line = bufferedReader.readLine()) != null)
     {

       System.out.println(line);

     }
    
     bufferedReader.close();

    } catch(FileNotFoundException e )
      {

        System.out.println("File not found: " + e.getMessage());

      } catch(IOException e)
      {

        System.out.println("Error reading file: " + e.getMessage());
    
      }
       System.out.println("Screen will automatically timeout after 20 Seconds.....");

      try 
      {
        
          Thread.sleep(30000);

      } catch (InterruptedException e) 
      {
        // TODO: handle exception
        e.printStackTrace();

      }
    }

   void Quit(){

    System.out.print("Thankyou For Banking With us......!!\n");
    return;

  }

}

class Run_ATM{

  int Account_search_by_uniqueID(Account[] ac, String unique_ID){

     for(int i=0; i<5; i++){

        if(Objects.equals(unique_ID, ac[i].unique_ID))
           return i;
     }
     return -1;

  }

  int Account_search_by_uniqueID(Account[] ac, int Account_number){

    for(int i=0; i<5; i++){

       if(Objects.equals(Account_number, ac[i].A_no))
          return i;

    }
    return -1;

  }

  void Demo(Account[] ac){

    Scanner sc = new Scanner(System.in);
    System.out.print("\n\n\nWELCOME TO ATM.....\n");
    System.out.println("\nEnter Your Card Number/Unique ID ");
    String unique_ID = sc.nextLine();
    int i = Account_search_by_uniqueID(ac, unique_ID);

    if(i == -1){

      System.out.println("Account not Registered Yet!");

      try{
        
        Thread.sleep(3000);;

      } catch(InterruptedException e){

        e.printStackTrace();

      }
      return;
    } else{

      System.out.println("Enter Your PIN......");
      int PIN = sc.nextInt();

      if(PIN == ac[i].PIN){
        
        System.out.println("===================================================");
        System.out.println("Select the options as Given Below!!!!!!!");
        System.out.println("Withdraw?-------1");
        System.out.println("Deposit!-------2");
        System.out.println("Account Trnsfer------3");
        System.out.println("Display Account Details--------4");
        System.out.println("Quit-----5");
        System.out.println("===================================================");

        int ch;

        ATM atm = new ATM();
        ch = sc.nextInt();
        switch(ch){

          case 1 -> atm.Withdraw(ac[i]);
          case 2 -> atm.Deposite(ac[i]);
          case 3 -> {

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.print("Enter the Account Number......");
            int Account_transfer = sc.nextInt();

            int j = Account_search_by_uniqueID(ac, Account_transfer);
            if (j == -1){

              System.out.println("Account Not Registered............");
              return;
            } else{

              System.out.println("Enter Amount for Transfering Funds...........");
              double Amount = sc.nextDouble();
              atm.Transfer(ac[i], ac[j], Amount);
            }

          }
          case 4 -> atm.Display_details(ac[i]);
          case 5 -> atm.Quit();

        }

      }else {

        System.out.println("Wrongt PIN Entered.....");
        System.out.println("PLEASE TRY AGAIN............");
        try {
          
          Thread.sleep(3000);

        } catch (InterruptedException e) {
          // TODO: handle exception
          e.printStackTrace();

        }
        return;
      }
    }
  }

}

public class Task2{

   public static void main(String [] args){

       Scanner sc = new Scanner(System.in);
       Account acc[] = new Account[5];

       for( int i=0; i<5; i++){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Creating Account......");
        acc[i] = new Account();
        acc[i].create_Account();
        System.out.print("\033[H\033[2J");
        System.out.flush();
       }

       Run_ATM  a = new Run_ATM();
       for( ; ; ){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        a.Demo(acc);

       }
   }
}



