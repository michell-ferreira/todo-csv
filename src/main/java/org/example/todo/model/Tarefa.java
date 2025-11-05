package org.example.todo.model;

import java.util.Objects;

public class Tarefa {

    private final int id;
    private String descricao;
    private Status status;

    public Tarefa(int id, String descricao, Status status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[#%d] (%s) %s", id, descricao, status);
    }

    @Override
    public boolean equals(Object o) {
        // se for o mesmo objeto na memória, já é igual
        if (this == o) return true;

        // se for nulo ou de outra classe, já não é igual
        if (o == null || getClass() != o.getClass()) return false;

        // transforma o "o" em Tarefa pra poder comparar
        Tarefa tarefa = (Tarefa) o;

        // duas tarefas são iguais se tiverem o mesmo id
        return id == tarefa.id;
    }

    @Override
    public int hashCode() {
        // gera um hash só com base no id
        // usado por HashSet/HashMap pra evitar duplicado
        return Objects.hash(id);
    }

}
