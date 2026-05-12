public class Main {

    public static void main(String[] args) {

        DiarioDeBordo diario1 = DiarioDeBordo.getInstancia();
        DiarioDeBordo diario2 = DiarioDeBordo.getInstancia();

        diario1.registrar("A nave iniciou a viagem.");
        diario2.registrar("Sinal estranho detectado no radar.");

        if (diario1 == diario2) {
            System.out.println("Os dois diários são o mesmo objeto.");
        }
    }
}