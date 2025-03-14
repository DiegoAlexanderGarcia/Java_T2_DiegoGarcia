package PatronesDeDiseno_Estructurales.Adapter;

public class main {
    interface Volt12{
        int getVolt12();
    }
    static class Volt120{
        int getVolt120(){
            return 120;
        }
    }

    static class VoltAdapter implements Volt12{
        private Volt120 volt120;
        public VoltAdapter(Volt120 volt120){
            this.volt120 = volt120;
        }
        @Override
        public int getVolt12(){ return volt120.getVolt120()/10;}
    }
    public static void main(String[] args) {
        Volt120 oldSystem = new Volt120();
        Volt12 adapterSystem = new VoltAdapter(oldSystem);
        System.out.println("Voltaje adaptado: " + adapterSystem.getVolt12() + "V");
    }

}
