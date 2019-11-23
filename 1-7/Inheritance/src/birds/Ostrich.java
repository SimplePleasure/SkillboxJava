package birds;

public class Ostrich extends NotFlying
{

 private double weight;
 public Ostrich() {
  weight = 10+100*Math.random();
  setWeight(weight);
 }



 public void eat(){

 }

 @Override
 public void voice() {
  System.out.println("Ostrich weight is: " + getWeight() + "gramms");
 }


}
