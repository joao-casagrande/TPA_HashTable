import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final int[] _opcoes = new int[]{1,2,3,4,5,6,0};

    public static String LerEntrada(String textoEntrada)
    {
        System.out.print(textoEntrada);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public static OpcaoInserida Menu()
    {
        /*System.out.print("\n" +
                "#####   MENU   #####\n" +
                "1 - Carregar um arquivo\n" +
                "2 - Localizar um contato\n" +
                "3 - Inserir um contato\n"+
                "4 - Excluir um contato\n" +
                "5 - Atualizar contato\n"+
                "6 - Salvar dados\n" +
                "0 - Sair\n" +
                "Escolha uma das opções acima: ");*/

        String opcao = LerEntrada("\n" +
                "#####   MENU   #####\n" +
                "1 - Carregar um arquivo\n" +
                "2 - Localizar um contato\n" +
                "3 - Inserir um contato\n"+
                "4 - Excluir um contato\n" +
                "5 - Atualizar contato\n"+
                "6 - Salvar dados\n" +
                "0 - Sair\n" +
                "Escolha uma das opções acima: ");
        OpcaoInserida ret = new OpcaoInserida();
        ret.textoOpcao = opcao;

        if (IsNum(opcao))
        {
            int opcaoNum = Integer.parseInt(opcao);
            if (OpcaoValida(opcaoNum))
            {
                ret.codigoOpcao = opcaoNum;
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


    private static boolean OpcaoValida(int opcaoNum)
    {
        for (int element : _opcoes) {
            if (element == opcaoNum) {
                return true;
            }
        }
        return false;
    }

    public static boolean IsNum(String str)
    {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        if (str == null) return false;
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static int CalculaIndiceTabela(String key, int size)
    {
        int soma = 0;
        for (int i = 0; i < key.length(); i++) {
            soma += key.charAt(i);
        }

        return soma % size;
    }

}
