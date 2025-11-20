package beverage;

public abstract class  BeverageBase  implements Beverage {
     private final String name;
     private final double cost;

     protected  BeverageBase(String name, double cost){
         this.name = name;
         this.cost= cost;
     }

     @Override
    public String getName(){
         return name;
     }
     @Override
    public double getCost(){
         return cost;
     }

}

