package br.com.farmacia.service;


import br.com.farmacia.dao.ConnectionFactory;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.modelo.DadosCadastroProduto;
import br.com.farmacia.modelo.Produto;

import java.sql.Connection;
import java.util.Set;

public class ProdutoService {
    private ConnectionFactory connectionFactory;

    public ProdutoService() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void abrir(DadosCadastroProduto dadosCadastroProduto){
        Connection connection = connectionFactory.recuperarConexao();
        new ProdutoDAO(connection).salvar(dadosCadastroProduto);
    }

    public Set<Produto> listar(){
        Connection connection = connectionFactory.recuperarConexao();
        return new ProdutoDAO(connection).listar();
    }

    public void alterar(Integer id, Double novoValor){

        Connection connection = connectionFactory.recuperarConexao();
        new ProdutoDAO(connection).alterar(id, novoValor);

    }
    public void deletar(Integer id){
        Connection connection = connectionFactory.recuperarConexao();

        new ProdutoDAO(connection).deletar(id);

    }




}
