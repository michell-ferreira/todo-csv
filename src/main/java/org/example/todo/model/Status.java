package org.example.todo.model;

public enum Status {
    PENDENTE,
    CONCLUIDA;

    public static Status fromString(String texto) {
        try {
            // procura o valor igual no enum (valueOf())
            return Status.valueOf(texto.trim().toUpperCase());
        } catch (Exception e) {
            // padronizei o erro para não deixar exceções aleatórias vazarem / neste caso é válido
            // se o texto não bater com nenhum valor do enum, mostra erro mais claro
            throw new IllegalArgumentException("Status inválido: " + texto);
        }
    }

    }
