package PatronesDeDiseno_Estructurales.Decorator;

public class main {
    interface Coffee {
        String getDescription();
        double cost();
    }
    static class SimpleCoffee implements Coffee {
        public String getDescription() {
            return "Cafe Simple";
        }
        public double cost() {
            return 5.0;
        }
    }
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee;

        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        public String getDescription() {
            return coffee.getDescription();
        }
        public double cost() {
            return coffee.cost();
        }
    }

    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }
        public String getDescription() {return coffee.getDescription() + "con leche";}
        public double cost() {return coffee.cost() + 1.5;}
    }
    public static void main(String[] args) {
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + "$" + myCoffee.cost());

        Coffee myMilk = new MilkDecorator(myCoffee);
        System.out.println(myMilk.getDescription() + "$" + myMilk.cost());
    }
}
