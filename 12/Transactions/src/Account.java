/**
 * Created by Danya on 18.02.2016.
 */
public class Account
{
    private long money;
    private String accNumber;
    private Boolean block = false;
    


     void moneySent(long count, Account taker) {
         synchronized(this) {
             money -= count;
             taker.money += count;
         }
     }

    long getBalance () {
        return money;
    }

    String getAccNumber() {
        return accNumber;
    }

    void blockAcc () {
        block = true;
    }

    Boolean isBlocked() {
        return block;
    }




    //===================================
    public void setMoney(long money) {
        this.money = money;
    }
    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;

    }

    //===================================



}
