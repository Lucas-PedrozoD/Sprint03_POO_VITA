package view;

import dao.DadoSaudeDAO;
import dao.DispositivoDAO;
import model.DadoSaude;
import model.Dispositivo;

import java.util.List;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuDadoSaude {
    public void menu(){
        String[] janela = {" - ", "Inserir", "Listar", "Sair"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opção",
                    "Menu Dado Saude",
                    INFORMATION_MESSAGE,
                    null, janela, janela[0]);

            switch (opcao.toLowerCase()){
                case "inserir" -> inserir();
                case "listar" -> listar();
            }
        }while (!opcao.toLowerCase().equals("sair"));
    }

    private void listar() {
        List<DadoSaude> lista = new DadoSaudeDAO().listar();
        String aux = "";
        aux += "ID | Dispositivo | Usuário | FC | Oxigenação | Passos | Data/Hora\n";
        aux += "-----------------------------------------\n";
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (DadoSaude dadoSaude: lista){
            aux += dadoSaude.getId() + " | " + dadoSaude.getDispositivo().getModelo() + " | " + dadoSaude.getDispositivo().getUsuario().getNome()+
                    " | " + dadoSaude.getFreqCardia() + " | "+ dadoSaude.getOxigenacao() + "%"
                    + " | " + dadoSaude.getPassos() + " | " + dadoSaude.getDataHora().format(formato) + "\n";
        }
        showMessageDialog(null, aux);
    }

    private void inserir() {
        DadoSaude dadoSaude = new DadoSaude();

        List<Dispositivo> lista = new DispositivoDAO().listar();
        String[] opcoes = new String[lista.size() + 1];
        opcoes[0] = " - ";

        for (int i = 0; i < lista.size() ; i++){
            Dispositivo dispositivo = lista.get(i);
            opcoes[i + 1] = dispositivo.getId() + " - " + dispositivo.getUsuario().getNome();
        }
        String usuarioSelecionado = (String) showInputDialog(null,
                "Selecionar o Dispositivo de onde veio o dado",
                "Dados de Saude",
                QUESTION_MESSAGE, null,
                opcoes,
                opcoes[0]);
        for (Dispositivo dispositivo: lista){
            String opcao = dispositivo.getId() + " - " + dispositivo.getUsuario().getNome();

            if (opcao.equals(usuarioSelecionado)){
                dadoSaude.setDispositivo(dispositivo);
                break;
            }
        }

        Integer freqCardia = parseInt(showInputDialog("Frequência Cardíaca")) ;
        double oxigen = parseDouble(showInputDialog("Oxigenação - use ponto para valores decimais. Ex: 97.5"));
        Integer passos = parseInt(showInputDialog("Quantidade de passos"));
        String dataHora = showInputDialog("Data e hora (dd/MM/yyyy HH:mm)\n Ex: 06/09/2026 14:30");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime data = LocalDateTime.parse(dataHora, formatter);


        dadoSaude.setFreqCardia(freqCardia);
        dadoSaude.setOxigenacao(oxigen);
        dadoSaude.setPassos(passos);
        dadoSaude.setDataHora(data);

        new DadoSaudeDAO().inserir(dadoSaude);
    }
}
