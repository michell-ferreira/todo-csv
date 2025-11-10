package org.example.todo.repo;

import org.example.todo.model.Status;
import org.example.todo.model.Tarefa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class TarefaRepository {

    private static final String SEPARADOR = ";";
    private static final String CABECALHO = "id;status;descricao";

    // grava a lista de tarefas no arquivo CSV (sobrescreve tudo)
    public void salvar(Path csvPath, List<Tarefa> tarefas) {

        try (BufferedWriter writer = Files.newBufferedWriter(
                csvPath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, // se o arquivo não existir, ele cria
                StandardOpenOption.TRUNCATE_EXISTING  // se o arquivo existir, apaga tudo antes de escrever
        )) {

            writer.write(CABECALHO);
            writer.newLine();

            // escreve cada tarefa da lista
            for (Tarefa tarefa : tarefas) {
                writer.write(tarefa.toCsvString()); // transforma a tarefa em texto CSV
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
        }
    }

    // lê o arquivo CSV e monta as Tarefas na memória
    public List<Tarefa> carregar(Path csvPath) {

        // lista para colocar as tarefas lidas
        List<Tarefa> tarefas = new ArrayList<>();

        // verifica se o arquivo existe
        if (!Files.exists(csvPath)) {
            System.out.println("DEBUG: Arquivo não existe. Lista vazia.");
            return tarefas;
        }

        try (BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {

            // lê a primeira linha (tem que ser o cabeçalho)
            String linha = reader.readLine();

            // se o cabeçalho não bater, não dá pra confiar no arquivo
            if (linha == null || !linha.equals(CABECALHO)) {
                System.err.println("Erro: cabeçalho inválido.");
                return tarefas;
            }

            // leitura do restante das linhas
            while ((linha = reader.readLine()) != null) {

                // verifica se é vazio
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(SEPARADOR);

                // se não tiver 3 partes, linha tá quebrada
                if (partes.length != 3) {
                    System.err.println("Erro: linha mal formatada (pulando): " + linha);
                    continue;
                }

                try {
                    int id = Integer.parseInt(partes[0].trim());
                    Status status = Status.fromString(partes[1].trim());
                    String descricao = partes[2].trim();

                    if (descricao.isEmpty()) {
                        System.err.println("Erro: descrição vazia (pulando): " + linha);
                        continue;
                    }

                    tarefas.add(new Tarefa(id, status, descricao));

                } catch (NumberFormatException e) {
                    // id não era número
                    System.err.println("Erro: ID inválido (pulando): " + linha);

                } catch (IllegalArgumentException e) {
                    // status inválido para o enum
                    System.err.println("Erro: Status inválido (pulando): " + linha);

                } catch (Exception e) {
                    // qualquer erro fora do esperado
                    System.err.println("Erro inesperado (pulando): " + linha);
                }
            }

        } catch (IOException e) {
            // erro abrir/ler o arquivo
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // devolve tudo que conseguiu carregar
        return tarefas;
    }



}
