package utils;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import java.util.Arrays;

public class UtilValidaCpf {

    public static boolean validaCfp(String cpf) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            System.out.println("Não foi possível cadastrar o cliente. Motivo: " + e.getMessage());
            return false;
        }
    }

}
