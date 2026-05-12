public class Nave {

    private Registro registro;

    public Nave(Registro registro) {
        this.registro = registro;
    }

    public void iniciarMissao() {
        registro.registrar("Motor", "Motor principal ligado", 1);
        registro.registrar("Radar", "Objeto desconhecido detectado", 2);
        registro.registrar("Oxigênio", "Nível crítico de oxigênio", 3);
    }
}