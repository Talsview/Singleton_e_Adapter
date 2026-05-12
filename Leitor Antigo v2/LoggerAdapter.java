import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerAdapter implements Registro {

    private Logger logger;

    public LoggerAdapter() {
        logger = Logger.getLogger("DiarioDeBordo");
    }

    @Override
    public void registrar(String setor, String mensagem, int gravidade) {
        Level nivel;

        if (gravidade == 1) {
            nivel = Level.INFO;
        } else if (gravidade == 2) {
            nivel = Level.WARNING;
        } else {
            nivel = Level.SEVERE;
        }

        logger.log(nivel, "[" + setor + "] " + mensagem);
    }
}