package birds;

    abstract public class Bird implements Verebrate{

        private double weight;
        protected final void setWeight(Double weight){
            this.weight = weight;
        }
    public final Double getWeight(){
        return weight;
    }

    public void valk(){

    }

    public void run(){

    }

    public void move(){

    }

    protected final void ingest(){

    }

    protected void digest(){

    }

        abstract public void eat();

}
