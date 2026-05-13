public enum CentralEnergia {

    INSTANCIA;

    private int energia = 100;

    public void consumirEnergia(String sistema, int quantidade) {
        energia -= quantidade;

        if (energia < 0) {
            energia = 0;
        }

        System.out.println(sistema + " consumiu " + quantidade + "% de energia.");
    }

    public void recarregarEnergia(int quantidade) {
        energia += quantidade;

        if (energia > 100) {
            energia = 100;
        }

        System.out.println("Energia recarregada em " + quantidade + "%.");
    }

    public void mostrarEnergia() {
        System.out.println("Energia atual da nave: " + energia + "%");
    }
}
