public class RegistroConsole {

    public void registrar(String setor, String mensagem, int gravidade) {
        String tipo;

        if (gravidade == 1) {
            tipo = "INFO";
        } else if (gravidade == 2) {
            tipo = "ALERTA";
        } else {
            tipo = "ERRO";
        }

        System.out.println("[" + tipo + "] " + setor + ": " + mensagem);
    }
}