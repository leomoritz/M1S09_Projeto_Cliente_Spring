package utils;

public class UtilGeradorId {

    private static Integer id = 0;

    public static int gerarIdSequencial(){
        return id += 1;
    }

}
