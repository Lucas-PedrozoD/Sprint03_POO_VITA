package dao;

import factory.ConnectionFactory;
import model.Dispositivo;
import model.Usuario;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DispositivoDAO implements GenericDAO <Dispositivo,Integer> {
    @Override
    public void inserir(Dispositivo entidade) {
        String sql = "Insert into java_dispositivo( id_usuario, tipo, modelo, marca, status )" +
                " values(?,?,?,?,?)";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

           ps.setInt(1,entidade.getUsuario().getId());
           ps.setString(2,entidade.getTipo());
           ps.setString(3,entidade.getModelo());
           ps.setString(4,entidade.getMarca());
           ps.setString(5,entidade.getStuatus());
           ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Dispositivo> listar() {
        List<Dispositivo> lista = new ArrayList<>();
        String sql = "Select * from java_dispositivo";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Dispositivo dispositivo = new Dispositivo();
                Usuario usuario = new Usuario();
                dispositivo.setId(rs.getInt("id"));
                usuario.setId(rs.getInt("id_usuario"));
                dispositivo.setTipo(rs.getString("tipo"));
                dispositivo.setModelo(rs.getString("marca"));
                dispositivo.setStuatus(rs.getString("status"));
                dispositivo.setUsuario(usuario);
                lista.add(dispositivo);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
