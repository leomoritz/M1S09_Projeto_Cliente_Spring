package utils;

import java.util.UUID;

public class UtilGeradorId {

    private static Integer idCustomer = 0;
    private static Integer idAccount = 0;

    public static int generateSequenceIdCustomer(){
        return idCustomer += 1;
    }

    public static int generateSequenceIdAccount(){
        return idAccount += 1;
    }

}
