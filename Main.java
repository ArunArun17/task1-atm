import java.util.HashMap;
import java.util.Scanner;

public class Main{

    private int ano;//accountNumber
    private int pin;//pin
    private double balance=1000;

    private int next;//exit

    private HashMap<Integer,Integer> anoPin=new HashMap<Integer,Integer>();//accountNumber & pin
    private HashMap<Integer,Double> anoBa=new HashMap<Integer,Double>();//accountNumber & balance

    private Scanner scan=new Scanner(System.in);

    public void account()
    {
        System.out.print("Enter your account number : ");
        int anoIn = scan.nextInt();

        if(100001<=anoIn && anoIn<=999999) {
            ano=anoIn;
            System.out.print("Enter your PIN : ");
            int pnoIn = scan.nextInt();

            if(1001<=pnoIn && pnoIn<=9999) {
                next=8;
                pin = pnoIn;
            }else {
                next=6;
                System.out.println("PIN must be between 1001 and 9999. please try again");
            }
        }else {
            next=6;
            System.out.println("account number must be between 100001 and 999999. please try again");
        }
    }

    public void create()
    {
        System.out.println("1.Log in\n" + "2.Sign in");
        System.out.print("Enter your choice : ");
        int choice = scan.nextInt();
        next=choice;

        switch (choice)
        {
            case 1:
                if(!anoPin.containsKey(ano))
                {
                    anoPin.put(ano,pin);
                    anoBa.put(ano,balance);
                    System.out.println("The account was created successfully.");
                    break;
                }else {
                    System.out.println("This account has already been created. ");
                    next=9;
                    break;
                }
            case 2:
                try {

                    if (anoPin.get(ano)==pin) {
                        System.out.println("your account has been signed in successfully");
                        break;
                    }
                    else  {
                        System.out.println("Invalid account. please try again");
                        next=3;
                        break;}

                    }catch (Exception e){
                        System.out.println("Invalid account. please try again");
                        next=3;
                        break;}

            default:
                System.out.println("Invalid choice. please try again");
                break;
        }
    }

    public void menu()
    {
        System.out.println("Account Menu:");
        System.out.println("1.Check balance\n"+"2.Withdraw case\n"+"3.Deposit money\n"+"4.Change PIN\n"+"5.Exit\n");
        System.out.print("Enter your choice : ");
        int choice = scan.nextInt();
        next=choice;

        switch (choice)
        {
            case 1:
                System.out.println("Your current balance : "+anoBa.get(ano));
                break;

            case 2:
                System.out.print("Enter the amount to withdraw : ");
                Double b1=scan.nextDouble();
                if(balance>0 && balance>=b1)
                {
                    Double b2=anoBa.get(ano)-b1;
                    anoBa.replace(ano,anoBa.get(ano),b2);
                    System.out.println("Withdrawal successful ! your current balance : "+anoBa.get(ano));
                    break;}
                else {
                    System.out.println("Invalid amount. please try again");
                    break;}

            case 3:
                System.out.print("Enter the amount to deposit : ");
                Double b3=scan.nextDouble();
                if (b3>0){
                    Double b5=anoBa.get(ano)+b3;
                    anoBa.replace(ano,anoBa.get(ano),b5);
                    System.out.println("Deposit successful ! your current balance : "+anoBa.get(ano));
                    break;}
                else {
                    System.out.println("Invalid amount. please try again");
                    break;}

            case 4:
                System.out.print("Enter your new PIN Number : ");
                Integer pno2=scan.nextInt();

                if (pno2>=1001&&pno2<=9999){
                    anoPin.replace(ano,pin,pno2);
                    System.out.println("You have successfully changed your PIN Number ! your new PIN Number : "+anoPin.get(ano));
                    break;}
                else {
                    next=6;
                    System.out.println("PIN Number must be between 1001 and 9999. please try again");
                    break;
                }

            case 5:
                System.out.println("You have successfully logged out of your account.");
                break;

            default:
                System.out.println("Invalid choice. please try again");
                break;

        }
    }

    public static void main(String[] args)throws Exception
    {
        Main a= new Main();

        while (a.next!=5)
        {
            System.out.println("Welcome to the ATM");
            a.account();
            if (a.next!=6) {
                a.create();
            }
            if(a.next==1||a.next==2) {
                a.menu();
                System.out.println("--------------------------");}
        }

        System.out.println("Thank you for visiting my project.");
    }

}