package usecase;

import domain.DadosSplunk;
import domain.Relatorio;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MontaRelatorio {

    public MontaRelatorio() {
        try {
            this.executaLeitura();
            this.carregaListaDeConvenios();
            this.geraCSVFinal(cruzaDados());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    List<DadosSplunk> listaSplunk = new ArrayList<>();
    Map<Integer, String> mapaDeConvenios = new HashMap<>();


    FileInputStream fileInputStream;
    String line;

    {
        try {
            //TODO: Ajustar local do arquivo de entrada quando for usar!!!!
            fileInputStream = new FileInputStream("C:\\Desenvolvimento\\maven_alura\\Portabilidade CSV\\src\\main\\resources\\splunk.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("não achei o arquivo");
        }
    }

    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
    BufferedReader br = new BufferedReader(inputStreamReader);


    //método poderia ficar no infra de banco pois tenho detalhes aqui do arquivo etc
    public void executaLeitura() throws IOException {

        //leio e desprezo cabeçalho
        line = br.readLine();

        //lemos a segunda linha e entramos na lógica para ficar mais fácil
        line = br.readLine();
        while (line != null) {

            //Separo itens do csv pela virgula
            String[] vetorItemCSV = line.split(",");

            Integer convenio = Integer.valueOf(vetorItemCSV[0]);
            Integer quantidade = Integer.valueOf(vetorItemCSV[1]);

            DadosSplunk dadosSplunk = new DadosSplunk(convenio, quantidade);
            listaSplunk.add(dadosSplunk);

            line = br.readLine();
        }
        br.close();
    }

    //Inserir todos os convênios nessa lista ! (Cadastrar convênio)
    public void carregaListaDeConvenios() {

        mapaDeConvenios.put(1, "Santos");
        mapaDeConvenios.put(2, "PG");

    }

    public List<Relatorio> cruzaDados() {
        List<Relatorio> listaDeRelatorio = new ArrayList<>();

        listaSplunk.forEach(
                splunk -> {
                    String nomeConvenio = mapaDeConvenios.containsKey(splunk.getConvenio()) ? mapaDeConvenios.get(splunk.getConvenio()) : "desconhecido";
                    listaDeRelatorio.add(new Relatorio(splunk.getConvenio(), nomeConvenio, splunk.getQuantidade()));
                });
        return listaDeRelatorio;
    }

    public void geraCSVFinal(List<Relatorio> relatorios) throws IOException {

        //TODO: Ajustar local do arquivo de saída quando for usar!!!!
        OutputStream fileOutputStream = new FileOutputStream("C:\\Desenvolvimento\\maven_alura\\Portabilidade CSV\\src\\main\\resources\\splunkSaida.csv");
        Writer outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        //Escreve cabeçalho
        bufferedWriter.write("Convenio,Nome,Quantidade");
        bufferedWriter.newLine();

        relatorios.stream().forEach(item -> {
            try {
                bufferedWriter.write(item.getConvenio() + "," + item.getNome() + "," + item.getQuantidade());
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        bufferedWriter.close();
    }

}
