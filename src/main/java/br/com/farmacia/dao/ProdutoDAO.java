package br.com.farmacia.dao;

import br.com.farmacia.modelo.DadosCadastroProduto;
import br.com.farmacia.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDAO {
    private final Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(DadosCadastroProduto dadosCadastroProduto){

        var produto = new Produto(dadosCadastroProduto.id(), dadosCadastroProduto.preco(), dadosCadastroProduto.nome(),
                dadosCadastroProduto.fabricante());

        String sql = "INSERT INTO  produto (id, preco, nome, fabricante) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, produto.getId());
            ps.setDouble(2, produto.getPreco());
            ps.setString(3, produto.getNome());
            ps.setString(4, produto.getFabricante());

            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public Set<Produto> listar(){
        PreparedStatement ps;
        ResultSet rs;
        Set<Produto> produtos = new HashSet<>();

        String sql = "select * from produto";

        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Integer id = rs.getInt(1);
                Double preco = rs.getDouble(2);
                String nome = rs.getString(3);
                String fabricante = rs.getString(4);

                produtos.add(new Produto(id, preco, nome, fabricante));
            }

            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return produtos;
    }

    public void alterar(Integer id, Double preco){
        PreparedStatement ps;
        String sql = "UPDATE produto SET preco = ? WHERE id = ?";

        try{

            ps = conexao.prepareStatement(sql);

            ps.setDouble(1, preco);
            ps.setInt(2, id);

            ps.execute();
            ps.close();
            conexao.close();
        }catch (SQLException e) {
            try {
                conexao.rollback();
            }catch (SQLException ex){
                throw new RuntimeException(ex);
            }
             throw new RuntimeException(e);
        }

    }

    public void deletar(Integer id){
        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }






}
