import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final int[] _opcoes = new int[]{1,2,3,4,5,6,0};

    //Função para ler entrada do usuário com um texto de ajuda
    public static String LerEntrada(String textoEntrada)
    {

        System.out.print(textoEntrada);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //Mostra o menu para o usuário
    public static OpcaoInserida Menu()
    {
        String opcao = LerEntrada("""

                #####   MENU   #####
                1 - Carregar um arquivo
                2 - Localizar um contato
                3 - Inserir um contato
                4 - Excluir um contato
                5 - Atualizar contato
                6 - Salvar dados
                0 - Sair
                Escolha uma das opções acima:\s""");
        OpcaoInserida ret = new OpcaoInserida();
        ret.textoOpcao = opcao;

        if (IsNum(opcao))
        {
            int opcaoNum = Integer.parseInt(opcao); //Transforma strinn da opção em int
            if (OpcaoValida(opcaoNum)) //Verifica se a opção está dentro das possíveis(1,2,3,4,5,6,0)
            {
                ret.codigoOpcao = opcaoNum; //Código da ação solicitada pelo usuário
            }
            else
            {
                ret.codigoOpcao = 98; //Código de erro para quando é número mas não é uma opção
            }
        }
        else
        {
            ret.codigoOpcao = 99; //Código de erro para quando não for número
        }

        return ret;
    }

    //Verifica se a opção está dentre as possíveis
    private static boolean OpcaoValida(int opcaoNum)
    {
        for (int element : _opcoes) {
            if (element == opcaoNum) {
                return true;
            }
        }
        return false;
    }

    //Verifica se string é um número
    public static boolean IsNum(String str)
    {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (str == null) return false;
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //Calcula o índice da chave na tabela
    public static int CalculaIndiceTabela(String key, int size)
    {
        int soma = 0;
        for (int i = 0; i < key.length(); i++) {
            soma += (int)key.charAt(i) ^16;
        }

        return soma % size;
    }

}
