package org.example.todo.model;

import java.util.Objects;

public class Tarefa {

    private final int id;
    private Status status;
    private String descricao;

    public Tarefa(int id, Status status, String descricao) {
        this.id = id;
        this.status = status;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("[#%d] (%s) %s", id, status, descricao);
    }

    @Override
    public boolean equals(Object o) {
        // se for o mesmo objeto na memória, já é igual
        if (this == o) return true;

        // se for nulo ou de outra classe, já não é igual
        if (o == null || getClass() != o.getClass()) return false;

        // transforma o "o" em Tarefa pra poder comparar
        Tarefa tarefa = (Tarefa) o;

        // duaesms tarefas são iguais se tiverem o mo id
        return this.id == tarefa.id;
    }

    @Override
    public int hashCode() {
        // gera um hash só com base no id
        // usado por HashSet/HashMap pra evitar duplicado
        return Objects.hash(id);
    }

    public String toCsvString() {
        return String.format("%d;%s;%s", id, status, descricao);
    }

}
