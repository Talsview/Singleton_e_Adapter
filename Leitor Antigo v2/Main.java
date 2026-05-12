public class Main {

    public static void main(String[] args) {

        Registro registro = new LoggerAdapter();

        Nave nave = new Nave(registro);

        nave.iniciarMissao();
    }
}