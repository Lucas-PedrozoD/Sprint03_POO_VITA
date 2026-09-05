package view;
import static javax.swing.JOptionPane.*;

public class MenuPrincipal {
    public void menu(){
        String[] janela = {" - " , "Usuário", "Dispositivo", "Dados de Saúde", "Finalizar"};
        String opcao;

        do {
            opcao = (String) showInputDialog(null,
                    "Selecione uma opção",
                    "Menu Pricipal",
                    INFORMATION_MESSAGE,
                    null, janela, janela[0]);
            switch (opcao.toLowerCase()){
                case ("usuário") -> new MenuUsuario().menu();
                case ("dispositivo") -> new MenuDispositivo().menu();


            }
        }while (!opcao.toLowerCase().equals("finalizar"));

    }
}
