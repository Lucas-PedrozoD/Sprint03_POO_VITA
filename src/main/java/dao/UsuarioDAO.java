package dao;

import factory.ConnectionFactory;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements GenericDAO<Usuario,Integer>{

    @Override
    public void inserir(Usuario entidade) {
        String sql = "Insert into java_usuario(nome,idade,peso,altura) values(?,?,?,?)";
        try(Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,entidade.getNome());
            ps.setInt(2,entidade.getIdade());
            ps.setDouble(3,entidade.getPeso());
            ps.setDouble(4,entidade.getAltura());
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "Select * from java_usuario";
        try(Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setIdade(rs.getInt("idade"));
                usuario.setPeso(rs.getDouble("peso"));
                usuario.setAltura(rs.getDouble("altura"));
                lista.add(usuario);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void atualizar (Usuario usuario){
        String sql = "update java_usuario set nome = ?," +
                " idade = ?, peso = ?," +
                " altura = ?   where id = ?";
        try(Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,usuario.getNome());
            ps.setInt(2,usuario.getIdade());
            ps.setDouble(3,usuario.getPeso());
            ps.setDouble(4,usuario.getAltura());
            ps.setInt(5,usuario.getId());
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<Usuario> pesquisarID(Integer id) {
        String sql = "Select * from java_usuario where id = ?";

        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setIdade(rs.getInt("idade"));
                    usuario.setPeso(rs.getDouble("peso"));
                    usuario.setAltura(rs.getDouble("altura"));
                    return Optional.of(usuario);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }


}
