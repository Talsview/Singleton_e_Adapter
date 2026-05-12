public class DiarioDeBordo {

    private static DiarioDeBordo instancia;

    private DiarioDeBordo() {
        System.out.println("Diário de bordo criado.");
    }

    public static DiarioDeBordo getInstancia() {
        if (instancia == null) {
            instancia = new DiarioDeBordo();
        }

        return instancia;
    }

    public void registrar(String mensagem) {
        System.out.println("Registro no diário: " + mensagem);
    }
}