package birds;

public class Sparrow extends Flying
{

 public double weight;
 public Sparrow() {
  weight = 10+60*Math.random();
 }


 public void eat(){

 }

 @Override
 public void voice() {
  System.out.println("Sparrow weight is: " + weight + " gramms.");

 }


 @Override
 public void fly() {

 }
}
