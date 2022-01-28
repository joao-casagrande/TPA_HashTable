import java.io.*;
import java.util.ArrayList;

public class HashTableAgenda {

    private static ArrayList<Registro>[] _tabelaAgenda;

    private static int _colisoes = 0;

    private static int MAX_SIZE = 559;

    public static ArrayList<Registro>[] tabelaAgenda() {
        return _tabelaAgenda;
    }

    public static int colisoes() {
        return _colisoes;
    }

    public HashTableAgenda()
    {
        this._tabelaAgenda = new ArrayList[MAX_SIZE];
    }

    public static void CarregarArquivoCSV()
    {
        File arquivo = new ChooseFile().GetFile();
        ProcessarArquivoDeEntrada(arquivo);
        /*JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos .csv", "csv");

        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            File arquivo = chooser.getSelectedFile();
            //ProcessarArquivoDeEntrada(arquivo);
        }*/
    }

    public static void LocalizarContatoAgenda()
    {
        System.out.print("Digite o nome do contato: ");
        String nome = Util.LerEntrada();
        int indice = Util.CalculaIndiceTabela(nome, MAX_SIZE);
        ArrayList<Registro> localizado = _tabelaAgenda[indice];
        if(localizado == null) System.out.println("Contato não encontrado!");
        else if (localizado.size() == 1)
        {
            if(localizado.get(0).NomeCompleto.equals(nome))PrintarContato(localizado.get(0));
            else System.out.println("Contato não encontrado! 2");
        }
        else if (localizado.size() > 1)
        {
            boolean achou = false;
            for (int i = 0; i < localizado.size(); i++) {
                if(localizado.get(i).NomeCompleto.equals(nome))
                {
                    PrintarContato(localizado.get(i));
                    achou = true;
                }
            }
            if(!achou) System.out.println("Contato não encontrado! 3");
        }
        else System.out.println("Contato não encontrado! 4");
    }

    private static void PrintarContato(Registro contato)
    {
        //todo
        System.out.println("PrintarContato!!!");
    }


    public static void InserirContatoAgenda()
    {

    }

    public static void ExcluirContatoAgenda()
    {

    }

    public static void AtualizarContatoAgenda()
    {

    }

    public static void SalvarContatosAgenda()
    {

    }

    private static void ProcessarArquivoDeEntrada(File arquivo)
    {
        _tabelaAgenda = new ArrayList[MAX_SIZE];
        _colisoes = 0;

        try{
            InputStream inputFS = new FileInputStream(arquivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            String linha = br.readLine();
            while(linha!= null) {
                AdicionarRegistro(linha);
                //System.out.println(linha);
                linha= br.readLine();
            }
            /*while(br.readLine()!= null) {

            }*/

            br.close();

        } catch (IOException e) {

        }
    }

    private static void AdicionarRegistro(String linha)
    {
        Registro novoRegistro = CriarRegistroFromLinha(linha);
        int indiceNovoRegistro = Util.CalculaIndiceTabela(novoRegistro.NomeCompleto, MAX_SIZE);
        AdicionarNaTabela(novoRegistro, indiceNovoRegistro);
    };

    private static Registro CriarRegistroFromLinha(String linha)
    {
        String[] conteudo = linha.split(",");// a CSV has comma separated lines
        Registro item = new Registro();
        item.NomeCompleto = conteudo[0];
        item.Telefone = conteudo[1];
        item.Cidade = conteudo[2];
        item.Pais = conteudo[3];

        return item;
    }

    private static void AdicionarNaTabela(Registro novoRegistro, int indice)
    {
       if(_tabelaAgenda[indice] == null)
       {
           _tabelaAgenda[indice] = new ArrayList<>();
           _tabelaAgenda[indice].add(novoRegistro);
       }
       else
       {
           _tabelaAgenda[indice].add(novoRegistro);
           _colisoes++;
       }

    }

}
