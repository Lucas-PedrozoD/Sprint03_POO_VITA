package dao;

import factory.ConnectionFactory;
import model.DadoSaude;
import model.Dispositivo;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DadoSaudeDAO implements GenericDAO<DadoSaude,Integer>{
    @Override
    public void inserir(DadoSaude entidade) {
        String sql = "Insert into java_dado_saude( id_dispositivo, freq_cardiaca, oxigenacao, passos, data_hora )" +
                " values(?,?,?,?,?)";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,entidade.getDispositivo().getId());
            ps.setInt(2,entidade.getFreqCardia());
            ps.setDouble(3,entidade.getOxigenacao());
            ps.setInt(4,entidade.getPassos());
            ps.setTimestamp(5,Timestamp.valueOf(entidade.getDataHora()));
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<DadoSaude> listar() {
        List<DadoSaude> lista = new ArrayList<>();
        String sql = "Select  ds.*, d.modelo, u.id AS id_usuario, u.nome "+
                "FROM java_dado_saude ds " +
                "Join java_dispositivo d ON ds.id_dispositivo = d.id "+
                "JOIN java_usuario u ON d.id_usuario = u.id";
        try(Connection connection = ConnectionFactory.obterConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                DadoSaude dadoSaude = new DadoSaude();
                Dispositivo dispositivo = new Dispositivo();
                Usuario usuario = new Usuario();

                dadoSaude.setId(rs.getInt("id"));
                dispositivo.setId(rs.getInt("id_dispositivo"));
                dispositivo.setModelo(rs.getString("modelo"));
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));

                dispositivo.setUsuario(usuario);
                dadoSaude.setDispositivo(dispositivo);

                dadoSaude.setFreqCardia(rs.getInt("freq_cardiaca"));
                dadoSaude.setOxigenacao(rs.getDouble("oxigenacao"));
                dadoSaude.setPassos(rs.getInt("passos"));
                dadoSaude.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());

                lista.add(dadoSaude);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
}
