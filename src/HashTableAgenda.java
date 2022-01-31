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
        String nome = Util.LerEntrada("Digite o NOME do contato a localizar: ");
        int indiceTabela = Util.CalculaIndiceTabela(nome, MAX_SIZE);
        int indiceLista = GetIndiceContato(nome, indiceTabela);
        if(indiceLista == -1) System.out.println("Contato não encontrado!");
        else PrintarContato(_tabelaAgenda[indiceTabela].get(indiceLista));

        /*
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
        else System.out.println("Contato não encontrado! 4");*/
    }

    public static void InserirContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o NOME do contato a ser inserido: ");
        String telefone = Util.LerEntrada("Digite o TELEFONE do contato a ser inserido: ");
        String cidade = Util.LerEntrada("Digite a CIDADE do contato a ser inserido: ");
        String pais = Util.LerEntrada("Digite o PAÍS do contato a ser inserido: ");

        Registro novoRegistro = new Registro();
        novoRegistro.NomeCompleto = nome;
        novoRegistro.Telefone = telefone;
        novoRegistro.Cidade = cidade;
        novoRegistro.Pais = pais;
        AdicionarRegistro(novoRegistro);

    }

    public static void ExcluirContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o nome do contato a excluir: ");
        int indiceTabela = Util.CalculaIndiceTabela(nome, MAX_SIZE);
        int indiceLista = GetIndiceContato(nome, indiceTabela);
        if(indiceLista == -1) System.out.println("Contato não encontrado!");
        else {
            _tabelaAgenda[indiceTabela].remove(indiceLista);
            System.out.println("Usuário removido!");
        }
    }

    public static void AtualizarContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o NOME do contato a ser atualizado: ");

        int indiceTabela = Util.CalculaIndiceTabela(nome, MAX_SIZE);
        int indiceLista = GetIndiceContato(nome, indiceTabela);
        if(indiceLista == -1) System.out.println("Contato não encontrado!");
        else
        {
            PrintarContato(_tabelaAgenda[indiceTabela].get(indiceLista));
            String telefone = Util.LerEntrada("Digite o NOVO TELEFONE do contato a ser atualizado: ");
            String cidade = Util.LerEntrada("Digite a NOVA CIDADE do contato a ser atualizado: ");
            String pais = Util.LerEntrada("Digite o NOVO PAÍS do contato a ser atualizado: ");

            _tabelaAgenda[indiceTabela].get(indiceLista).Telefone = telefone;
            _tabelaAgenda[indiceTabela].get(indiceLista).Cidade = cidade;
            _tabelaAgenda[indiceTabela].get(indiceLista).Pais = pais;

        }


    }

    public static void SalvarContatosAgenda()
    {
        //todo
        String pasta = System.getProperty("user.home");
        File arquivo = new File(pasta+"\\"+System.currentTimeMillis()+".csv");

        try (PrintWriter writer = new PrintWriter(arquivo)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nome Completo");
            sb.append(',');
            sb.append("Telefone");
            sb.append(',');
            sb.append("Cidade");
            sb.append(',');
            sb.append("País");
            sb.append('\n');



            for (int i = 0; i < _tabelaAgenda.length; i++) {
                if(_tabelaAgenda[i] != null && _tabelaAgenda[i].size()>0)
                {
                    for (int j = 0; j < _tabelaAgenda[i].size(); j++) {
                        sb.append(_tabelaAgenda[i].get(j).NomeCompleto);
                        sb.append(',');
                        sb.append(_tabelaAgenda[i].get(j).Telefone);
                        sb.append(',');
                        sb.append(_tabelaAgenda[i].get(j).Cidade);
                        sb.append(',');
                        sb.append(_tabelaAgenda[i].get(j).Pais);
                        sb.append('\n');
                    }
                }
            }

            writer.write(sb.toString());


            System.out.println(String.format("Dados salvos em '%s'. Pasta: %s",arquivo.getAbsolutePath() ,"file:///"+ pasta.replace("\\","/")));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
                linha= br.readLine();
            }
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

    private static void AdicionarRegistro(Registro novoRegistro)
    {
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

    private static int GetIndiceContato(String nome, int indiceTabela)
    {
        ArrayList<Registro> localizado = _tabelaAgenda[indiceTabela];
        if(localizado == null) return -1;
        else if (localizado.size() == 1)
        {
            if(localizado.get(0).NomeCompleto.equals(nome)) return 0;
            else return -1;
        }
        else if (localizado.size() > 1)
        {
            for (int i = 0; i < localizado.size(); i++) {
                if(localizado.get(i).NomeCompleto.equals(nome))
                    return i;
            }
            return -1;
        }
        else return -1;
    }

    private static void PrintarContato(Registro contato)
    {
        //todo
        String leftAlignFormat = "| %-30s | %-15s | %-15s | %-15s |%n";
        String linha = "+--------------------------------+-----------------+-----------------+-----------------+%n";
        System.out.format(linha);
        System.out.format(leftAlignFormat, "Nome Completo", "Telefone", "Cidade", "Pais");
        System.out.format(linha);
        System.out.format(leftAlignFormat, contato.NomeCompleto, contato.Telefone, contato.Cidade, contato.Pais);
        System.out.format(linha);
    }


}
