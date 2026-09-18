package br.ufms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class Validar {

    private static final String NOME_PESSOA_REGEX = "^[a-zA-ZÀ-ÖØ-öø-ÿ -]+$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String USUARIO_REGEX = "^(?!.*([._])\\1)(?!.*\\.$)(?!^\\.)[a-zA-Z0-9_]+(?:[._][a-zA-Z0-9_]+)*_?$";
    private static final String TELEFONE_REGEX = "\\d{2}[1-8]\\d{7}|\\d{2}9\\d{8}|\\(\\d{2}\\)\\s[1-8]\\d{3}-\\d{4}|\\(\\d{2}\\)\\s9\\d{4}-\\d{4}|\\+55\\s\\d{2}\\s[1-8]\\d{3}-\\d{4}|\\+55\\s\\d{2}\\s9\\d{4}-\\d{4}";
    private static final String CHAVE_PIX_REGEX = "\\d{11}|\\d{14}|\\d{2}[1-8]\\d{7}|\\d{2}9\\d{8}|\\+55\\d{2}[1-8]\\d{7}|\\+55\\d{2}9\\d{8}|^\\+(?!55)\\d{2}\\d{6,}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$|^[a-zA-Z0-9]{32}$";

    // --- Nome e Pessoa ---
    public static String nomePessoa(String nome) { return nomePessoa(nome, false); }
    public static String nomePessoa(String nome, boolean completo) {
        return new ValidadorString("Nome", nome).validarNulo().validarPorExpressao(NOME_PESSOA_REGEX, "Caracteres não permitidos").validarTamanho(3, 50)
                .validarValor(n -> { if (completo && n.trim().split("\\s+").length < 2) throw new IllegalArgumentException("Nome incompleto. Informe o sobrenome"); })
                .getValor(ValidadorString::removerEspacosAdicionais);
    }
    public static String nomeEmpresa(String nome) {
        return new ValidadorString("Nome", nome).validarNulo().validarTamanho(1, 50).getValor(ValidadorString::removerEspacosAdicionais);
    }
    public static String razaoSocial(String rs) { return razaoSocial(rs, true); }
    public static String razaoSocial(String rs, boolean ob) {
        return new ValidadorString("Razão social", rs, ob).validarNulo().validarTamanho(3, 80).validarPorExpressao(".*[\\p{L}].*", "Informe ao menos uma letra").getValor(ValidadorString::removerEspacosAdicionais);
    }

    // --- Contato e Credenciais ---
    public static String telefone(String t) { return telefone(t, true); }
    public static String telefone(String t, boolean ob) {
        return new ValidadorString("Telefone", t, ob).validarNulo().validarPorExpressao(TELEFONE_REGEX, "Use somente números ou algum formato de telefone brasileiro válido").getValor(v -> v.replaceAll("\\+55|\\D", ""));
    }
    public static String email(String e) { return email(e, true); }
    public static String email(String e, boolean ob) {
        return new ValidadorString("Email", e, ob).validarNulo().validarTamanho(3, 50).validarPorExpressao(EMAIL_REGEX).getValor();
    }
    public static String usuario(String u) {
        return new ValidadorString("Usuário", u).validarNulo().validarTamanho(3, 30).validarPorExpressao(USUARIO_REGEX).getValor();
    }
    public static String senha(String s) { return senha(s, false); }
    public static String senha(String s, boolean num) {
        return new ValidadorString("Senha", s, null).validarNulo().validarTamanho(6, 30).validarPorExpressao(num ? "^\\d+$" : null).getValor();
    }

    // --- Datas ---
    public static LocalDate dataNascimento(LocalDate d) { return dataNascimento(d, true); }
    public static LocalDate dataNascimento(LocalDate d, boolean ob) {
        return estaEntrePeriodo("Data de nascimento", LocalDateTime.now().minusYears(150), d.atStartOfDay(), LocalDateTime.now(), ob).toLocalDate();
    }
    public static LocalDateTime estaEntrePeriodo(String c, LocalDateTime i, LocalDateTime d, LocalDateTime f) { return estaEntrePeriodo(c, i, d, f, true); }
    public static LocalDateTime estaEntrePeriodo(String campo, LocalDateTime ini, LocalDateTime data, LocalDateTime fim, boolean ob) {
        return new Validador<>(Objects.requireNonNull(campo, "Nome do campo nulo"), data, ob)
                .validar((attr, dt) -> dt.ifPresent(d -> { if (d.isAfter(fim) || d.isBefore(ini)) throw new IllegalArgumentException(attr + " inválida"); })).getValor();
    }

    // --- Documentos (CPF / CNPJ) ---
    public static String cpf(String cpf) {
        return new ValidadorString("CPF", cpf).validarNulo().validarPorExpressao("\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}").validarValor(Validar::validarCPF).getValor(ValidadorString::manterSomenteNumeros);
    }
    private static void validarCPF(String cpf) {
        String num = cpf.replaceAll("\\D", "");
        if (num.matches("^(\\d)\\1*$")) throw new IllegalArgumentException("CPF inválido: " + cpf);
        int[] d = num.chars().map(Character::getNumericValue).toArray();
        if (calcDig(d, 9, 10) != d[9] || calcDig(d, 10, 11) != d[10]) throw new IllegalArgumentException("CPF inválido: " + cpf);
    }
    private static int calcDig(int[] d, int lim, int peso) {
        int s = 0; for (int i = 0; i < lim; i++) s += d[i] * (peso - i);
        int r = s % 11; return (r < 2) ? 0 : 11 - r;
    }

    public static String cnpj(String cnpj) {
        return new ValidadorString("CNPJ", cnpj).validarNulo().validarPorExpressao("\\d{14}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}").validarValor(Validar::validarCNPJ).getValor(ValidadorString::manterSomenteNumeros);
    }
    private static void validarCNPJ(String cnpj) {
        String num = cnpj.replaceAll("\\D", "");
        if (num.matches("^(\\d)\\1*$")) throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
        int[] d = num.chars().map(Character::getNumericValue).toArray();
        if (calcDigCnpj(d, 12) != d[12] || calcDigCnpj(d, 13) != d[13]) throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
    }
    private static int calcDigCnpj(int[] d, int lim) {
        int s = 0, p = 2;
        for (int i = lim - 1; i >= 0; i--) { s += d[i] * p; if (++p == 10) p = 2; }
        int r = s % 11; return (r < 2) ? 0 : 11 - r;
    }

    // --- Dados Bancários e Pix ---
    public static String chaveAleatoriaPix(String c) {
        return new ValidadorString("Chave Pix", c).validarNulo().validarPorExpressao("^[a-zA-Z0-9]+$").validarTamanho(32).getValor();
    }
    public static String chavePix(String c) {
        return new ValidadorString("Chave Pix", c).validarNulo().validarPorExpressao(CHAVE_PIX_REGEX).getValor();
    }
    public static String codigoBanco(int c) {
        if (c < 1 || c > 999) throw new IllegalArgumentException("Código do banco inválido: [" + c + "]");
        return String.format("%03d", c);
    }
    public static String codigoBanco(String c) {
        return codigoBanco(Integer.parseInt(new ValidadorString("Código do banco", c).validarNulo().validarPorExpressao("^[+-]?\\d+$").getValor()));
    }
    public static int numeroAgencia(int n) {
        if (n < 1 || n > 99999) throw new IllegalArgumentException("Número de agência inválido: [" + n + "]");
        return n;
    }
    public static String numeroConta(String n) { return numeroConta(n, null); }
    public static String numeroConta(String n, Function<Integer, Integer> v) {
        return new ValidadorString("Número da conta", n).validarNulo().validarPorExpressao("\\d+-\\d").validarValor(val -> {
            int[] num = Arrays.stream(val.split("-")).mapToInt(Integer::parseInt).toArray();
            numeroConta(num[0], num[1], v);
        }).getValor();
    }
    public static String numeroConta(int n, int d) { return numeroConta(n, d, null); }
    public static String numeroConta(int n, int d, Function<Integer, Integer> v) {
        String nd = String.format("%06d-%d", n, d);
        if (n < 1 || n > 99999999) throw new IllegalArgumentException("Número inválido: [" + nd + "]");
        if ((d < 0 || d > 9) || (v != null && v.apply(n) != d)) throw new IllegalArgumentException("Dígito inválido: [" + nd + "]");
        return nd;
    }
}
