
import db.EstoquesDB;
import db.PedidosVendaDB;
import db.ProdutosDB;
import db.UsuariosDB;
import models.*;
import validadores.ValidadorPedidoVenda;

import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static ProdutosDB produtosDB = new ProdutosDB();
    static UsuariosDB usuariosDB = new UsuariosDB();
    static EstoquesDB estoquesDB = new EstoquesDB();
    static PedidosVendaDB pedidosVendaDB = new PedidosVendaDB();

    public static void main(String[] args) throws Exception {

        System.out.println("---PEDIDO DE VENDAS---");
        int option;
        do {
            System.out.println("1 - Cadastrar Produtos");
            System.out.println("2 - Listar Produtos Cadastrados");
            System.out.println("3 - Cadastrar Usuario ADMINISTRADOR ");
            System.out.println("4 - Cadastrar Usuario CLIENTE");
            System.out.println("5 - Listar Usuarios");
            System.out.println("6 - Cadastrar novo Estoque");
            System.out.println("7 - Listar Estoque");
            System.out.println("8 - Criar pedido de venda");
            System.out.println("9 - Listar pedidos de venda");
            System.out.println("0 - Sair.");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Operação: ");
            option = scanner.nextInt();

            process(option);
        } while (option != 0);


    }

    public static void process(int option) throws Exception {

        switch (option) {
            case 1: {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Descrição do Produto: ");
                String descricao = scanner.nextLine();

                System.out.print("ID do Produto: ");
                int id = scanner.nextInt();

                System.out.print("Valor do Produto: ");
                double preco = scanner.nextDouble();

                System.out.print("Validade do Produto: ");
                String dataString = scanner.next();

                Date dataValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);

                Produto novoProduto = new Produto(id, descricao, preco, dataValidade);

                produtosDB.addNovoProduto(novoProduto);

                //System.out.println("Produto Criado!!");
                //System.out.println("Produto: "+novoProduto.getDescricao()+" #"+novoProduto.getId());
                //System.out.println("Preço: $"+novoProduto.getPreco()+" Validade: "+novoProduto.getDataValidade());
                //System.out.println("---------------------------------------------------------------");
            }
            break;
            case 2: {
                List<Produto> listaDeProdutos = produtosDB.getProdutosList();

                for (Produto produto : listaDeProdutos) {
                    System.out.println("Produto: " + produto.getDescricao() + " #" + produto.getId());
                    System.out.println("Preço: $" + produto.getPreco() + " Validade: " + produto.getDataValidade());
                    System.out.println("---------------------------------------------------------------");
                }
            }
            break;
            case 3: {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Nome ADMINISTRADOR: ");
                String nome = scanner.nextLine();


                Admin novoAdmin = new Admin(nome);
                usuariosDB.addNovoUsuario(novoAdmin);
            }
            break;
            case 4: {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Nome CLIENTE: ");
                String nome = scanner.nextLine();

                Cliente novoCliente = new Cliente(nome);
                usuariosDB.addNovoUsuario(novoCliente);
            }
            break;
            case 5: {
                System.out.println("------------------------------------------------");
                for (Usuario usuario : usuariosDB.getUsuarioList()) {
                    System.out.println("ID: " + usuario.getId());
                    System.out.println("Usuario: " + usuario.getNome());
                    System.out.println("Tipo: " + usuario.getTipoUsuario());
                    System.out.println("------------------------------------------------");
                }
            }
            break;
            case 6: {
                Scanner scanner = new Scanner(System.in);
                System.out.println("-----------------------------------------------");

                System.out.print("Identificardo do estoque: ");
                String id = scanner.next();

                System.out.print("Porduto a adicionar: id#");
                int produtoId = scanner.nextInt();

                Produto produto = produtosDB.getProdutoPorID(produtoId); //passando o valor do id inserido acima por parametro para o metodo
                System.out.println("#" + produto.getId());
                System.out.println(produto.getDescricao());
                System.out.println("Validade: " + produto.getDataValidade());

                System.out.println("Quantidade a ser adicionada: ");
                int quantidade = scanner.nextInt();

                Estoque novoEstoque = new Estoque(id, produto, quantidade);
                estoquesDB.addNovoEstoque(novoEstoque);
            }
            break;
            case 7: {
                System.out.println("------------------------------------------------");
                for (Estoque estoque : estoquesDB.getEstoques()) {
                    System.out.println("ID: " + estoque.getId());
                    System.out.println("Produto: " + estoque.getProduto().getDescricao());
                    System.out.println("Preco: " + estoque.getProduto().getPreco());
                    System.out.println("Quantidade: " + estoque.getQuantidade());
                    System.out.println("------------------------------------------------");
                }

            }break;
            case 8: {
                Scanner scanner = new Scanner(System.in);
                System.out.println("-----------------------------------------------");
                System.out.println("Informar cliente ID:");
                int idCliente = scanner.nextInt();

                Cliente cliente = (Cliente) usuariosDB.getUsuarioPorID(idCliente);
                System.out.println("ID: " + cliente.getId());
                System.out.println("NOME: " + cliente.getNome());
                System.out.println("TIPO: " + cliente.getTipoUsuario());
                System.out.println("------------------------------------------------");

                System.out.println("Informar identificador do estoque:");
                String idEstoque = scanner.next();
                Estoque estoque = estoquesDB.getEstoqueById(idEstoque);
                System.out.println("#" + estoque.getId());
                System.out.println(estoque.getProduto().getDescricao());
                System.out.println("Validade: " + estoque.getProduto().getDataValidade());
                System.out.println("------------------------------------------------");

                System.out.println("Informar quantidade a ser vendida: ");
                int quantidade = scanner.nextInt();
                PedidoVenda novoPedido = new PedidoVenda(cliente, estoque, quantidade);

                ValidadorPedidoVenda validadorPedidoVenda = new ValidadorPedidoVenda(novoPedido);

                if(validadorPedidoVenda.eValido()) {
                    pedidosVendaDB.addNovoPedidoVenda(novoPedido);
                } else {
                    System.out.println(validadorPedidoVenda.getErros());
                }
                pedidosVendaDB.addNovoPedidoVenda(novoPedido);


            }break;
            case 9: {
                System.out.println("------------------------------------------------");
                for (PedidoVenda pedidoVenda : pedidosVendaDB.getPedidosVendas()) {
                    System.out.println("ID: " + pedidoVenda.getId());
                    System.out.println("Cliente: " + pedidoVenda.getCliente().getNome());
                    System.out.println("Produto: " + pedidoVenda.getEstoque().getProduto().getDescricao());
                    System.out.println("Quantidade: " + pedidoVenda.getQuantidade());


                    System.out.println("------------------------------------------------");
                }
            }

        }
    }
}
