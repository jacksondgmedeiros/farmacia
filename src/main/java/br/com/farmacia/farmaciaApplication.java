package br.com.farmacia;

import br.com.farmacia.modelo.DadosCadastroProduto;
import br.com.farmacia.service.ProdutoService;

import java.util.Scanner;

public class farmaciaApplication {

    private static final ProdutoService service = new ProdutoService();
    private static final Scanner teclado = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        var opcao = exibirMenu();
        while (opcao != 5){
            try {
                switch (opcao){
                    case 1:
                        cadastrar();
                        break;
                    case 2:
                        listarProdutos();
                        break;
                    case 3:
                        alterarPreco();
                        break;
                    case 4:
                        deletarProduto();
                        break;
                }
            } catch (Exception e){
                throw new RuntimeException(e);
            }
            opcao = exibirMenu();
        }
    }
    private static int exibirMenu() {
        System.out.println("""
                FARMACIA - ESCOLHA UMA OPÇÃO:
                1 - Cadastrar Produtos
                2 - Listar Produtos
                3 - Alterar preço
                4 - Deletar produto
                5 - Sair
                """);
        return teclado.nextInt();
    }


    private static void cadastrar() {
        System.out.println("Cadastrar produto:");
        System.out.println("Digite o id:");
        var id = teclado.nextInt();

        System.out.println("Digite o preço:");
        var preco = teclado.nextDouble();

        System.out.println("Digite nome do produto: ");
        var nome = teclado.next();

        System.out.println("Digite o fabricante:");
        var fabricante = teclado.next();

        service.abrir(new DadosCadastroProduto(id, preco, nome, fabricante));

        System.out.println("Produto cadastrado com sucesso!!!");
    }

    private static void listarProdutos(){
        System.out.println("Produtos listados");
        var produtos = service.listar();
        produtos.stream().forEach(System.out::println);

        System.out.println("Pressione qualquer tecla para voltar ao menu principal");
        teclado.next();

    }

    private static void alterarPreco(){
        System.out.println("Digite o número do Id que deseja alterar:");
        var id = teclado.nextInt();

        System.out.println("Digite o novo valor: ");
        var novoValor = teclado.nextDouble();

        service.alterar(id, novoValor);

        System.out.println("Preço alterado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();
    }

    private static void deletarProduto(){
        System.out.println("Digite o id do produto que deseja deletar:");
        var id = teclado.nextInt();

        service.deletar(id);

        System.out.println("Produto deletado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.next();

    }

}
