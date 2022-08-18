package validadores;

import java.util.ArrayList;
import java.util.List;

public abstract class Validador {
    private final List<String> erros = new ArrayList<>();

    public abstract boolean eValido();

    public List<String> getErros() {
        return erros;
    }

    public void addErro(String erro) {
        this.erros.add(erro);
    }
}
