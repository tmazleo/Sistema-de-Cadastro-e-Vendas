package validadores;

import models.PedidoVenda;

import java.util.ArrayList;
import java.util.List;

public class ValidadorPedidoVenda extends Validador{
    private PedidoVenda pedidoVenda;

    public List<IValidadorPedidoVenda> validadoresPedidoVenda = new ArrayList<>();

    public ValidadorPedidoVenda(PedidoVenda pedidoVenda){
        this.pedidoVenda = pedidoVenda;

        this.validadoresPedidoVenda.add(new ValidadorDataValidade());
        this.validadoresPedidoVenda.add(new ValidadorQuantidade());

    }
    @Override
    public boolean eValido() {
        for (IValidadorPedidoVenda validadorPedidoVenda : validadoresPedidoVenda) {
            String erro = validadorPedidoVenda.validar(pedidoVenda);

            if(erro != null) {
                addErro(erro);
            }
        }
        return !(getErros().size() > 0);
    }
}
