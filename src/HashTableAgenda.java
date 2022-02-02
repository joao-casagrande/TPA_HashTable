import java.io.*;
import java.util.ArrayList;

public class HashTableAgenda {

    private static ArrayList<Registro>[] _tabelaAgenda;

    private static int _colisoes = 0;

    public static boolean arquivoCarregado = false;

    private static final int MAX_SIZE = 559;

    public HashTableAgenda()
    {
        _tabelaAgenda = new ArrayList[MAX_SIZE];
    }

    public static void CarregarArquivoCSV()
    {
        File arquivo = new ChooseFile().GetFile(); //Chama a janela para a escolha do arquivo
        System.out.println("Carregando arquivo "+arquivo.getName());
        ProcessarArquivoDeEntrada(arquivo); //Função que processa as linhas
        System.out.println("Arquivo carregado!");
        arquivoCarregado = true; //Variável que habilita o uso das outras funções
    }

    public static void LocalizarContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o NOME do contato a localizar: "); //Lê o nome do contato a ser localizado
        int indiceTabela = Util.CalculaIndiceTabela(nome, MAX_SIZE); //Calcula o índice pelo nome
        int indiceLista = GetIndiceContato(nome, indiceTabela); //Procura o contato no índice da tabela encontrado
        if(indiceLista == -1) System.out.println("Contato não encontrado!"); //Caso não ache o nome, erro
        else PrintarContato(_tabelaAgenda[indiceTabela].get(indiceLista));//Caso ache o nome, printa as informações do registro
    }

    public static void InserirContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o NOME do contato a ser inserido: "); //Lê o nome do contato a ser inserido
        String telefone = Util.LerEntrada("Digite o TELEFONE do contato a ser inserido: "); //Lê o teleone do contato a ser inserido
        String cidade = Util.LerEntrada("Digite a CIDADE do contato a ser inserido: "); //Lê a cidade do contato a ser inserido
        String pais = Util.LerEntrada("Digite o PAÍS do contato a ser inserido: "); //Lê o país do contato a ser inserido

        Registro novoRegistro = new Registro(); //Cria um novo objeto da classe Registro
        novoRegistro.NomeCompleto = nome;
        novoRegistro.Telefone = telefone;
        novoRegistro.Cidade = cidade;
        novoRegistro.Pais = pais;
        AdicionarRegistro(novoRegistro); //Adiciona o registro na tabela
        System.out.println("Usuário inserido com sucesso!");

    }

    public static void ExcluirContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o nome do contato a excluir: "); //Lê o nome do contato a ser excluido
        int indiceTabela = Util.CalculaIndiceTabela(nome, MAX_SIZE);
        int indiceLista = GetIndiceContato(nome, indiceTabela);
        if(indiceLista == -1) System.out.println("Contato não encontrado!"); //Caso o contato não exista, erro
        else {
            _tabelaAgenda[indiceTabela].remove(indiceLista); //Caso exista, é excluído
            System.out.println("Usuário removido!");
        }
    }

    public static void AtualizarContatoAgenda()
    {
        String nome = Util.LerEntrada("Digite o NOME do contato a ser atualizado: ");//Lê o nome do contato a ser atualizado

        int indiceTabela = Util.CalculaIndiceTabela(nome, MAX_SIZE);
        int indiceLista = GetIndiceContato(nome, indiceTabela);
        if(indiceLista == -1) System.out.println("Contato não encontrado!");// Caso não exista, erro
        else
        {
            //Caso exista, lê os campos e atualiza pelo índice
            PrintarContato(_tabelaAgenda[indiceTabela].get(indiceLista));
            String telefone = Util.LerEntrada("Digite o NOVO TELEFONE do contato a ser atualizado: ");//Lê o novo telefone do contato a ser atualizado
            String cidade = Util.LerEntrada("Digite a NOVA CIDADE do contato a ser atualizado: ");//Lê a nova cidade do contato a ser atualizado
            String pais = Util.LerEntrada("Digite o NOVO PAÍS do contato a ser atualizado: ");//Lê o novo país do contato a ser atualizado

            //Atualiza o registro pelos índices
            _tabelaAgenda[indiceTabela].get(indiceLista).Telefone = telefone;
            _tabelaAgenda[indiceTabela].get(indiceLista).Cidade = cidade;
            _tabelaAgenda[indiceTabela].get(indiceLista).Pais = pais;

            System.out.println("Usuário atualizado com sucesso!");

        }


    }

    public static void SalvarContatosAgenda()
    {
        String pasta = System.getProperty("user.home"); //Pasta padrão para salvar os dados('Documentos')
        File arquivo = new File(pasta+"\\"+System.currentTimeMillis()+".csv"); //Cria o nome do arquivo a partir dos milissegundos atuais para evitar sobrescrita

        try (PrintWriter writer = new PrintWriter(arquivo)) {
            StringBuilder sb = new StringBuilder();//Escreve o cabeçalho
            sb.append("Nome Completo");
            sb.append(',');
            sb.append("Telefone");
            sb.append(',');
            sb.append("Cidade");
            sb.append(',');
            sb.append("País");
            sb.append('\n');


            for (ArrayList<Registro> tabelaAgendum : _tabelaAgenda) {
                if (tabelaAgendum != null && tabelaAgendum.size() > 0) {
                    for (Registro registro : tabelaAgendum) {
                        //Pra cada registro, escreve no arquivo, separando por vírgulas e terminando com uma quebra de linha
                        sb.append(registro.NomeCompleto);
                        sb.append(',');
                        sb.append(registro.Telefone);
                        sb.append(',');
                        sb.append(registro.Cidade);
                        sb.append(',');
                        sb.append(registro.Pais);
                        sb.append('\n');
                    }
                }
            }

            writer.write(sb.toString());


            System.out.printf("Dados salvos em '%s'. Pasta: %s%n",arquivo.getAbsolutePath() ,"file:///"+ pasta.replace("\\","/"));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void ProcessarArquivoDeEntrada(File arquivo)
    {
        //****************//
        // Redefinição das variáveis para um arquivo novo, ou seja, um "ambiente" novo
        _tabelaAgenda = new ArrayList[MAX_SIZE];
        _colisoes = 0;
        arquivoCarregado = false;
        //****************//
        try{
            InputStream inputFS = new FileInputStream(arquivo); //Transforma o arquivo em uma stream
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS)); //Cria um leitor de stream
            String linha = br.readLine(); //Lê a primeira linha do arquivo
            while(linha!= null) {
                AdicionarRegistro(linha); //Adiciona a linha como um registro na tabela
                linha= br.readLine(); // Lê a próxima linha e continua a mesma lógica
            }
            br.close(); //Ao fim do arquivo, fechar o leitor;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void AdicionarRegistro(String linha)
    {
        Registro novoRegistro = CriarRegistroFromLinha(linha); //Cria um objeto da classe Registro a partir de uma linha
        int indiceNovoRegistro = Util.CalculaIndiceTabela(novoRegistro.NomeCompleto, MAX_SIZE); //Calcula o indice em que esse registro estará na tabela pelo nome
        AdicionarNaTabela(novoRegistro, indiceNovoRegistro);//Adiciona o registro ao índice calculado
    }

    private static void AdicionarRegistro(Registro novoRegistro)
    {
        int indiceNovoRegistro = Util.CalculaIndiceTabela(novoRegistro.NomeCompleto, MAX_SIZE);
        AdicionarNaTabela(novoRegistro, indiceNovoRegistro);
    }

    private static Registro CriarRegistroFromLinha(String linha)
    {
        String[] conteudo = linha.split(",");// Separa por ',' por ser um CSV
        Registro item = new Registro();
        //Como estamos supondo que a ordem dos valores será a mesma sempre, pois não tem como identifica caso não seja,
        //Fazemos a assossiação pelo índice do conteudo
        item.NomeCompleto = conteudo[0];
        item.Telefone = conteudo[1];
        item.Cidade = conteudo[2];
        item.Pais = conteudo[3];

        return item;
    }

    private static void AdicionarNaTabela(Registro novoRegistro, int indice)
    {
        //Caso o índice da tabela ainda não possua registros, cria-se uma nova lista e adiciona o novo registro a essa lista
       if(_tabelaAgenda[indice] == null)
       {
           _tabelaAgenda[indice] = new ArrayList<>();
           _tabelaAgenda[indice].add(novoRegistro);
       }
       //Caso ja haja registros no índice recebido, adiciona o registro a lista de registros naquele índice e incrementa o número de colisões
       else
       {
           _tabelaAgenda[indice].add(novoRegistro);
           _colisoes++;
       }

    }

    private static int GetIndiceContato(String nome, int indiceTabela)
    {
        ArrayList<Registro> localizado = _tabelaAgenda[indiceTabela];//Retorna o valor do indice na tabela
        if(localizado == null) return -1; //Caso não haja nada
        else if (localizado.size() == 1) //Caso haja apenas um registro
        {
            if(localizado.get(0).NomeCompleto.equals(nome)) return 0; //Caso haja apenas um registroe e este coincide com o nome apresentado
            else return -1; //Caso não coincida
        }
        else if (localizado.size() > 1)
        {
            for (int i = 0; i < localizado.size(); i++) {
                if(localizado.get(i).NomeCompleto.equals(nome)) //Caso haja mais que um registro com o nome apresentado, retorna o primeiro que der match
                    return i;
            }
            return -1;
        }
        else return -1;
    }

    private static void PrintarContato(Registro contato)
    {
        //Printa os dados do registro em forma de tabela
        String leftAlignFormat = "| %-30s | %-15s | %-15s | %-15s |%n";
        String linha = "+--------------------------------+-----------------+-----------------+-----------------+%n";
        System.out.format(linha);
        System.out.format(leftAlignFormat, "Nome Completo", "Telefone", "Cidade", "Pais");
        System.out.format(linha);
        System.out.format(leftAlignFormat, contato.NomeCompleto, contato.Telefone, contato.Cidade, contato.Pais);
        System.out.format(linha);
    }


}
