package br.ufms.util;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class ValidadorString {

    private final String campo;
    private final String valor;
    private final boolean obrigatorio;

    public ValidadorString(String campo, String valor) {
        this(campo, valor, true);
    }

    public ValidadorString(String campo, String valor, Boolean obrigatorio) {
        this.campo = Objects.requireNonNull(campo, "Nome do campo não pode ser nulo");
        this.valor = valor;
        this.obrigatorio = obrigatorio == null || obrigatorio;
    }

    public ValidadorString validarNulo() {
        if (obrigatorio && (valor == null || valor.trim().isEmpty())) {
            throw new IllegalArgumentException(campo + " é obrigatório");
        }
        return this;
    }

    public ValidadorString validarPorExpressao(String regex) {
        return validarPorExpressao(regex, null);
    }

    public ValidadorString validarPorExpressao(String regex, String mensagem) {
        if (regex != null && valor != null && !valor.matches(regex)) {
            String msg = mensagem != null ? mensagem : "Formato inválido";
            throw new IllegalArgumentException(campo + " inválido: " + msg);
        }
        return this;
    }

    public ValidadorString validarTamanho(int min, int max) {
        if (valor != null && (valor.length() < min || valor.length() > max)) {
            throw new IllegalArgumentException(campo + " deve conter entre " + min + " e " + max + " caracteres");
        }
        return this;
    }

    public ValidadorString validarTamanho(int tamanho) {
        if (valor != null && valor.length() != tamanho) {
            throw new IllegalArgumentException(campo + " deve conter exatamente " + tamanho + " caracteres");
        }
        return this;
    }

    public ValidadorString validarValor(Consumer<String> consumer) {
        if (valor != null) {
            consumer.accept(valor);
        }
        return this;
    }

    public String getValor() {
        return valor;
    }

    public String getValor(Function<String, String> transformador) {
        return valor != null ? transformador.apply(valor) : null;
    }

    public static String removerEspacosAdicionais(String str) {
        return str != null ? str.trim().replaceAll("\\s+", " ") : null;
    }

    public static String manterSomenteNumeros(String str) {
        return str != null ? str.replaceAll("\\D", "") : null;
    }
}
