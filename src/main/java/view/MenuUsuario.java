package view;
import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;
import java.util.Optional;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class MenuUsuario {
    public void menu(){
        String[] janela = {" - " , "Inserir" , "Listar" ,
                "Pesquisar pelo ID", "Atualizar", "Sair"};
        String opcao;

        do {
            opcao =(String) showInputDialog(null,
                    "Selecione uma opção",
                    "Menu Usuário",
                    INFORMATION_MESSAGE,
                    null, janela,janela[0]);
            switch (opcao.toLowerCase()){
                case "inserir" -> inserir();
                case "listar" -> listar();
                case "pesquisar pelo id" -> pesquisarID();
                case "atualizar" -> atualizar();


            }


        }while (!opcao.toLowerCase().equals("sair"));
    }

    private void inserir() {
        Usuario usuario = new Usuario();
        String nome = showInputDialog("Nome");
        Integer idade = parseInt(showInputDialog("Idade"));
        Double peso = parseDouble(showInputDialog("Peso(Kg) - use ponto para valores decimais. Ex:72.5"));
        Double altura = parseDouble(showInputDialog("Altura(m) - use ponto para valores decimais. Ex:1.75"));
        usuario.setNome(nome);
        usuario.setIdade(idade);
        usuario.setPeso(peso);
        usuario.setAltura(altura);
        new UsuarioDAO().inserir(usuario);


    }

    private void listar() {
        List <Usuario> lista = new UsuarioDAO().listar();
        String aux = "";
        aux += "ID | Nome | Idade | Peso | Altura\n";
        aux += "-----------------------------------------\n";
        for (Usuario usuario: lista){
            aux += usuario.getId() + " | " + usuario.getNome() + " | " +
            usuario.getIdade() + " | " + usuario.getPeso() + " | " +
                    usuario.getAltura() + "\n";
        }
        showMessageDialog(null, aux);
    }

    private void atualizar(){
        List <Usuario> lista = new UsuarioDAO().listar();
        String aux = "";
        aux += "ID | Nome\n";
        aux += "-------------\n";

        for (Usuario usuario : lista){
            aux += usuario.getId() + " | " + usuario.getNome() + "\n";
        }

        Integer id = parseInt(showInputDialog(aux+"\nDigite o ID do usuário" +
                "que deseja atualizar:"));
        Usuario usuario = new Usuario();
        usuario.setId(id);
        String nome = showInputDialog("Nome");
        Integer idade = parseInt(showInputDialog("Idade"));
        Double peso = parseDouble(showInputDialog("Peso(Kg) - use ponto para valores decimais. Ex:72.5"));
        Double altura = parseDouble(showInputDialog("Altura(m) - use ponto para valores decimais. Ex:1.75"));

        usuario.setNome(nome);
        usuario.setIdade(idade);
        usuario.setPeso(peso);
        usuario.setAltura(altura);
        new  UsuarioDAO().atualizar(usuario);
    }

    private void pesquisarID() {

        Integer id = parseInt(showInputDialog("Digite o ID do usuário que você deseja consultar os dados:"));
        Optional<Usuario> resultado = new UsuarioDAO().pesquisarID(id);
        if (resultado.isPresent()){

            Usuario usuario = resultado.get();

            String aux = "";
            aux += "ID: "+ usuario.getId() + "\n";
            aux += "Nome: "+ usuario.getNome() + "\n";
            aux += "Idade: "+ usuario.getIdade() + "\n";
            aux += "Peso: "+ usuario.getPeso() + "\n";
            aux += "Altura: "+ usuario.getAltura() + "\n";

            showMessageDialog(null,aux);


        }else {
            showMessageDialog(null, "Usuário não encontrado.");
        }


    }

}
