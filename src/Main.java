public class Main {

    public static void main(String[] args) {

        OpcaoInserida opcaoSelecionada = Util.Menu();
        while(true)
        {
            switch (opcaoSelecionada.codigoOpcao) {
                case 1 -> HashTableAgenda.CarregarArquivoCSV();
                case 2 -> HashTableAgenda.LocalizarContatoAgenda();
                case 3 -> HashTableAgenda.InserirContatoAgenda();
                case 4 -> HashTableAgenda.ExcluirContatoAgenda();
                case 5 -> HashTableAgenda.AtualizarContatoAgenda();
                case 6 -> HashTableAgenda.SalvarContatosAgenda();
                case 0 -> {
                    System.out.println("Saindo...");
                    //Thread.sleep(3000);
                    System.exit(0);
                }
                case 98 -> System.out.println("Opção inserida inválida");
                case 99 -> System.out.printf("'%s' Não é um número%n", opcaoSelecionada.textoOpcao);
            }
            opcaoSelecionada = Util.Menu();


        }
    }


}
