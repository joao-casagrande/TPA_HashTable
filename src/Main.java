import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        OpcaoInserida opcaoSelecionada = Util.Menu();
        HashTableAgenda tabela = new HashTableAgenda();

        while(true)
        {
            switch (opcaoSelecionada.codigoOpcao)
            {
                case 1:
                    System.out.println("Carregar arquivo");
                    tabela.CarregarArquivoCSV();
                    break;
                case 2:
                    System.out.println("Localizar contato");
                    tabela.LocalizarContatoAgenda();
                    break;
                case 3:
                    System.out.println("Inserir contato");
                    break;
                case 4:
                    System.out.println("Excluir contato");
                    break;
                case 5:
                    System.out.println("Atualizar contato");
                    break;
                case 6:
                    System.out.println("Salvar dados");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    //Thread.sleep(3000);
                    System.exit(0);
                    break;
                case 98:
                    System.out.println("Opção inserida inválida");
                    break;
                case 99:
                    System.out.println(String.format("'%s' Não é um número",opcaoSelecionada.textoOpcao));
                    break;
            }
            opcaoSelecionada = Util.Menu();


        }
    }


}
