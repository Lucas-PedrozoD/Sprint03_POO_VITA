package view;

import dao.DispositivoDAO;
import dao.UsuarioDAO;
import model.Dispositivo;
import model.Usuario;

import java.util.List;

import static javax.swing.JOptionPane.*;

public class MenuDispositivo {
    public void menu(){
        String[] janela = {" - ", "Inserir", "Listar", "Sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opção",
                    "Menu Dispositivo",
                    INFORMATION_MESSAGE,
                    null, janela, janela[0]);

            switch (opcao.toLowerCase()){
                case "inserir" -> inserir();
                case "listar" -> listar();
            }
        }while (!opcao.toLowerCase().equals("sair"));
    }

    private void listar() {
        List <Dispositivo> lista = new DispositivoDAO().listar();
        String aux = "";
        aux += "ID dispositivo | Id Usuário-Nome | Tipo | Modelo | Marca | Status\n";
        aux += "-----------------------------------------\n";
        for (Dispositivo dispositivo: lista){
            aux += dispositivo.getId() + " | " + dispositivo.getUsuario().getId() + "-" + dispositivo.getUsuario().getNome()+
                    " | " + dispositivo.getTipo() + " | "+ dispositivo.getModelo() + " | " +
                    dispositivo.getMarca() + " | " + dispositivo.getStuatus() + "\n";
        }
        showMessageDialog(null, aux);
    }

    private void inserir() {
        Dispositivo dispositivo = new Dispositivo();

        List<Usuario> lista = new UsuarioDAO().listar();
        String[] opcoes = new String[lista.size() + 1];
        opcoes[0] = " - ";

        for (int i = 0; i < lista.size() ; i++){
            Usuario usuario = lista.get(i);
                opcoes[i + 1] = usuario.getId() + " - " + usuario.getNome();
        }
        String usuarioSelecionado = (String) showInputDialog(null,
                "Selecionar o usuário",
                "Usuário",
                QUESTION_MESSAGE, null,
                opcoes,
                opcoes[0]);
        for (Usuario usuario: lista){
            String opcao = usuario.getId() + " - " + usuario.getNome();

            if (opcao.equals(usuarioSelecionado)){
                dispositivo.setUsuario(usuario);
                break;
            }
        }

        String[] tipos = { " - " , "Relógio", "Pulseira"};
        String tipo = (String) showInputDialog(null,
                "Selecione o tipo",
                "Tipo",
                QUESTION_MESSAGE,
                null, tipos, tipos[0]);

        String[] modelos = { " - ", "Galaxy Watch", "Apple Watch", "Mi Band"};
        String modelo = (String) showInputDialog(null,
                "Selecione o modelo",
                "Modelo",
                QUESTION_MESSAGE,
                null, modelos, modelos[0]);

        String[] marcas = { " - " , "Samsung", "Apple", "Xiaomi"};
        String marca = (String) showInputDialog(null,
                "Selecione a marca",
                "Marca",
                QUESTION_MESSAGE,
                null, marcas, marcas[0]);

        String[] status = { " - " , "Ativo", "Inativo"};
        String statusSelecionado = (String) showInputDialog(null,
                "Selecione o status do dispositivo",
                "Status",
                QUESTION_MESSAGE,
                null, status, status[0]);

        dispositivo.setTipo(tipo);
        dispositivo.setModelo(modelo);
        dispositivo.setMarca(marca);
        dispositivo.setStuatus(statusSelecionado);

        new DispositivoDAO().inserir(dispositivo);
    }



}
