public class Main {

    public static void main(String[] args) {

        CentralEnergia central1 = CentralEnergia.INSTANCIA;
        CentralEnergia central2 = CentralEnergia.INSTANCIA;

        central1.consumirEnergia("Motor principal", 20);
        central2.consumirEnergia("Radar", 15);

        central1.mostrarEnergia();

        central2.recarregarEnergia(10);

        central1.mostrarEnergia();

        if (central1 == central2) {
            System.out.println("As duas variáveis usam a mesma central de energia.");
        }
    }
}