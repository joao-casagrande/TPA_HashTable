import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        String pasta = System.getProperty("user.home");
        File arquivo = new File(pasta+"\\SaidaPerformance"+System.currentTimeMillis()+".csv");

        int[] primes = {53,	97,	193,	389,	769,	1543,	3079,	6151,	12289,	24593,	49157,	98317,	196613,	393241,	786433,	1572869,	3145739,	6291469,	12582917,	25165843,	50331653,	100663319};

        try (PrintWriter writer = new PrintWriter(arquivo)) {
            StringBuilder sb = new StringBuilder();//Escreve o cabeçalho
            sb.append("Tamanho Tabela");
            sb.append(',');
            sb.append("Tempo Carregamento(ms)");
            sb.append(',');
            sb.append("Tempo Busca(ms)");
            sb.append(',');
            sb.append("Colisões");
            sb.append('\n');


            //for (int i = primes.length-1; i >=0; i--) {
            for (int prime : primes) {
                HashTableAgenda.SetTableSize(prime);
                System.out.println(HashTableAgenda.GetTableSize());
                long inicioCarregamento = System.currentTimeMillis();
                HashTableAgenda.CarregarArquivoCSV();
                long fimCarregamento = System.currentTimeMillis();
                long TEMPO_CARREGAMENTO = fimCarregamento - inicioCarregamento;

                long inicioBusca = System.currentTimeMillis();
                HashTableAgenda.LocalizarContatoAgenda();
                long fimBusca = System.currentTimeMillis();
                long TEMPO_BUSCA = fimBusca - inicioBusca;


                sb.append(HashTableAgenda.GetTableSize());
                sb.append(',');
                sb.append(TEMPO_CARREGAMENTO);
                sb.append(',');
                sb.append(TEMPO_CARREGAMENTO);
                sb.append(',');
                sb.append(HashTableAgenda.colisoes());
                sb.append('\n');

                //PrintarPerformance(HashTableAgenda.GetTableSize(), TEMPO_CARREGAMENTO, TEMPO_BUSCA, HashTableAgenda.colisoes());

            }
            writer.write(sb.toString());


            System.out.printf("Dados salvos em '%s'. Pasta: %s%n",arquivo.getAbsolutePath() ,"file:///"+ pasta.replace("\\","/"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void PrintarPerformance(int tamanho, long tempoCarregamento, long tempoBusca, long colisoes)
    {
        //Printa os dados do registro em forma de tabela
        String leftAlignFormat = "| %-20s | %-20s | %-20s | %-20s |%n";
        String linha = "+----------------------+----------------------+----------------------+----------------------+%n";
        System.out.format(linha);
        System.out.format(leftAlignFormat, "Tamanho Tabela", "Tempo Carregamento", "Tempo Busca", "Colisões");
        System.out.format(linha);
        System.out.format(leftAlignFormat, tamanho, tempoCarregamento, tempoBusca, colisoes);
        System.out.format(linha);
    }


}
