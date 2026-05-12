public class SistemaNaveLegado {

    private RegistroConsole registro;

    public SistemaNaveLegado() {
        registro = new RegistroConsole();
    }

    public void iniciarMissao() {
        registro.registrar("Motor", "Motor principal ligado", 1);
        registro.registrar("Radar", "Objeto desconhecido detectado", 2);
        registro.registrar("Oxigênio", "Nível crítico de oxigênio", 3);
    }
}