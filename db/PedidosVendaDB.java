package db;

import models.PedidoVenda;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PedidosVendaDB {
    private List<PedidoVenda> pedidosVendas = new ArrayList<>();

    public List<PedidoVenda> getPedidosVendas() {
        return pedidosVendas;
    }

    public void addNovoPedidoVenda(PedidoVenda novoPedido) {
        int id = pedidosVendas.size() + 1;
        novoPedido.setId(id);
        pedidosVendas.add(novoPedido);
    }
}
