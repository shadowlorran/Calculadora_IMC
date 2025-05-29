import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraIMC extends JFrame {

    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel resultadoLabel;

    public CalculadoraIMC() {
        setTitle("Calculadora de IMC - By Lorran T. Camilo");
        setSize(680, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com padding
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(new Color(240, 248, 255)); // cor azul claro

        // Título centralizado
        JLabel titulo = new JLabel("💪 Calculadora de IMC", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(25, 25, 112));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Painel de formulário central
        JPanel painelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        painelFormulario.setBackground(new Color(240, 248, 255));

        painelFormulario.add(new JLabel("Peso (kg):"));
        campoPeso = new JTextField();
        painelFormulario.add(campoPeso);

        painelFormulario.add(new JLabel("Altura (cm):"));
        campoAltura = new JTextField();
        painelFormulario.add(campoAltura);

        painelFormulario.add(new JLabel("Resultado:"));
        resultadoLabel = new JLabel("");
        resultadoLabel.setForeground(new Color(0, 102, 0));
        painelFormulario.add(resultadoLabel);

        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);

        // Botão na parte inferior
        JButton botaoCalcular = new JButton("Calcular IMC");
        botaoCalcular.setBackground(new Color(70, 130, 180));
        botaoCalcular.setForeground(Color.WHITE);
        botaoCalcular.setFocusPainted(false);
        botaoCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        botaoCalcular.addActionListener(e -> calcularIMC());
        painelPrincipal.add(botaoCalcular, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(campoPeso.getText());
            double alturaCm = Double.parseDouble(campoAltura.getText());
            double alturaM = alturaCm / 100.0;

            double imc = peso / (alturaM * alturaM);
            String classificacao = classificarIMC(imc);

            resultadoLabel.setText(String.format("IMC: %.2f - %s", imc, classificacao));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite valores numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String classificarIMC(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        else if (imc < 24.9) return "Peso normal";
        else if (imc < 29.9) return "Sobrepeso";
        else if (imc < 34.9) return "Obesidade Grau I";
        else if (imc < 39.9) return "Obesidade Grau II";
        else return "Obesidade Grau III (Mórbida)";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraIMC().setVisible(true));
    }
}
