package org.example.todo.repo;

import org.example.todo.model.Tarefa;

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


    // le o arquivo e recria as tarefas
    public List<Tarefa> carregar(Path csvPath) {
        System.out.println("DEBUG: carregar() chamado");
        return new ArrayList<>();
    }

}
