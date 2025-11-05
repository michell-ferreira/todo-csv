package org.example.todo.model;

public enum Status {
    PENDENTE,
    CONCLUIDA;

    public static Status fromString(String texto) {
        // tenta converter o texto em um enum Status (ex: "pendente" -> "PENDENTE" -> Status.PENDENTE)
        try {
            // tira espaços, deixa maiúsculo e procura o valor igual no enum (valueOf())
            return Status.valueOf(texto.trim().toUpperCase());
        } catch (Exception e) {
            // se o texto não bater com nenhum valor do enum, mostra erro mais claro
            throw new IllegalArgumentException("Status inválido: " + texto);
        }
    }

    }
