import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Danya on 18.02.2016.
 */
public class Bank{



    private HashMap<String, Account> accounts;



    //===========================
   {
        accounts = new HashMap<>();
        accounts.put("1", new Account());
        accounts.get("1").setAccNumber("1");
        accounts.get("1").setMoney(100000);


        accounts.put("2", new Account());
        accounts.get("2").setAccNumber("2");
        accounts.get("2").setMoney(100000);


        accounts.put("3", new Account());
        accounts.get("3").setAccNumber("3");
        accounts.get("3").setMoney(100000);
    }
    //=======================




    private final Random random = new Random();
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: ����������� �����. ����� ��������� ������ ����� �������.
     * ���� ����� ���������� > 50000, �� ����� ���������� ����������,
     * ��� ������������ �� �������� ������ ������������ � ����������
     * ����� isFraud. ���� ������������ true, �� �������� ����������
     * ������ (��� � �� ���� ����������)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account sent = null;
        Account take = null;

        for (Account acc : accounts.values()) {
            if (acc.getAccNumber().equals(fromAccountNum)) {
                sent = acc;
            }
            if (acc.getAccNumber().equals(toAccountNum)) {
                take = acc;
            }
        }


        System.out.print(new SimpleDateFormat("hh:mm:ss").format(new Date()) + " Account:" + sent.getAccNumber() + "\t");
        if (take.isBlocked() || sent.isBlocked()) {
            System.out.println("Account is blocked.");
        } else if (amount < 50000) {
            sent.moneySent(amount, take);
            System.out.format("Money sent to %s. Amount: %d\n", take.getAccNumber(), amount);



        } else if (amount >= 50000) {

            try {
                if (isFraud(sent.getAccNumber(), take.getAccNumber(), amount)) {
                    sent.blockAcc();
                    take.blockAcc();
                    System.out.println("Fraud! Amount: " + amount);
                } else {
                    sent.moneySent(amount, take);
                    System.out.println("Money sent to " + take.getAccNumber() + ". Amount: " + amount);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * TODO: ����������� �����. ���������� ������� �� �����.
     */
    public synchronized long getBalance(String accountNum)    {

        long balance = 0;
        for (Account acc: accounts.values()){
            if (acc.getAccNumber().equals(accountNum)) {
                balance = acc.getBalance();
                break;
            }
        }
        return balance;
    }

    //==================================================




}
