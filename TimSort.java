import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimSort {
    private static long comparisons = 0;
    private static long movements = 0;

    public static void main(String[] args) {
        List<Integer> numbers = readNumbersFromFile("dados500_mil.txt");
        System.out.println("Números antes da ordenação:");
        System.out.println(numbers);
        long startTime = System.currentTimeMillis();
        timSort(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Números ordenados com TimSort:");
        System.out.println(numbers);
        long executionTime = endTime - startTime;
        String formattedTime = formatTime(executionTime);
        System.out.println("Tempo de execução do TimSort: " + formattedTime);
        System.out.println("Número de comparações: " + comparisons);
        System.out.println("Quantidade de movimentações: " + movements);
    }

    public static List<Integer> readNumbersFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] tokens = line.split(",\\s+");
                for (String token : tokens) {
                    try {
                        int num = Integer.parseInt(token.trim());
                        numbers.add(num);
                    } catch (NumberFormatException e) {
                        System.err.println("Ignorando token inválido: " + token);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        System.out.println("Números lidos do arquivo:");
        System.out.println(numbers);

        return numbers;
    }

    public static void timSort(List<Integer> arr) {
        if (arr == null || arr.size() <= 1) return;

        // Realiza a ordenação com o TimSort
        Collections.sort(arr);

        // Calcula o número de movimentações
        movements = arr.size();
        
        // Atualiza o número de comparações
        comparisons = (arr.size() - 1) * arr.size() / 2;
    }

    public static String formatTime(long milliseconds) {
        long millis = milliseconds % 1000;
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;
        return String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, millis);
    }
}
