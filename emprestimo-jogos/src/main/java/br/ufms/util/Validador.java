package br.ufms.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Validador<T> {

    private final String campo;
    private final T valor;
    private final boolean obrigatorio;

    public Validador(String campo, T valor) {
        this(campo, valor, true);
    }

    public Validador(String campo, T valor, boolean obrigatorio) {
        this.campo = Objects.requireNonNull(campo, "Nome do campo não pode ser nulo");
        this.valor = valor;
        this.obrigatorio = obrigatorio;
    }

    public Validador<T> validar(BiConsumer<String, Optional<T>> biConsumer) {
        if (obrigatorio && valor == null) {
            throw new IllegalArgumentException(campo + " é obrigatório");
        }
        biConsumer.accept(campo, Optional.ofNullable(valor));
        return this;
    }

    public T getValor() {
        if (obrigatorio && valor == null) {
            throw new IllegalArgumentException(campo + " é obrigatório");
        }
        return valor;
    }
}
