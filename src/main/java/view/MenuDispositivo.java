package view;

import dao.DispositivoDAO;
import model.Dispositivo;

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
            }
        }while (!opcao.toLowerCase().equals("sair"));
    }

    private void inserir() {
        Dispositivo dispositivo = new Dispositivo();
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
