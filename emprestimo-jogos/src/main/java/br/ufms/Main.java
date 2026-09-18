package br.ufms;

import java.time.LocalDate;
import br.ufms.model.Usuario;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Teste de Validação com o Framework Validar ===\n");

        try {
            Usuario usuarioValido = new Usuario(
                    "Kaleb Frei",
                    "kalebskrr@hotmail.com",
                    "kalebskrr",
                    "123456",
                    "111.444.777-35",
                    "67999108611",
                    LocalDate.of(2000, 5, 20));

            System.out.println("✅ Usuário criado com sucesso!");
            System.out.println("   Nome: " + usuarioValido.getNome());
            System.out.println("   E-mail: " + usuarioValido.getEmail());
            System.out.println("   CPF: " + usuarioValido.getCpf());
            System.out.println("   Telefone: " + usuarioValido.getTelefone());
            System.out.println("   Data de Nascimento: " + usuarioValido.getDataNascimento());
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Erro inesperado ao criar usuário válido: " + e.getMessage());
        }
    }
}

