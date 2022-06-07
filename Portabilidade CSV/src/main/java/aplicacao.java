import domain.Relatorio;
import usecase.MontaRelatorio;

import java.io.FileNotFoundException;
import java.util.List;

public class aplicacao {
    public static void main(String[] args)  {

        MontaRelatorio relatorio = new MontaRelatorio();

        List<Relatorio> listaFinal = relatorio.cruzaDados();

        listaFinal.forEach(item-> System.out.println(item));

    }
}
