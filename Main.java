import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    // Scanner для ввода данных с клавиатуры
    public static Scanner in = new Scanner(System.in);
    // PrintStream для вывода сообщений на экран
    public static PrintStream out = System.out;

    // Метод вычисления суммы элементов строки
    public static int sumRow(int[] row) {
        int sum = 0;
        // Проходим по всем элементам строки и суммируем их
        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        return sum; // Возвращаем сумму
    }

    // Метод нахождения медианы строки
    public static double medianRow(int[] row) {

        // Создание дубликата массива для сортировки
        int[] sortedRow = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            sortedRow[i] = row[i];
        }

        // Сортировка методом пузырька
        for (int i = 0; i < row.length - 1; i++) {
            for (int j = 0; j < row.length - 1; j++) {
                if (sortedRow[j] > sortedRow[j + 1]) {
                    int z = sortedRow[j];
                    sortedRow[j] = sortedRow[j + 1];
                    sortedRow[j + 1] = z;
                }
            }
        }

        // Если количество элементов нечетное, то медианой будет центральный элемент
        if (sortedRow.length % 2 == 1) {
            return sortedRow[sortedRow.length / 2];
        } else {
            /* Если количество элементов четное, то медиана - среднее арифметическое
             двух центральных элементов */
            return (sortedRow[sortedRow.length / 2 - 1] +
                    sortedRow[sortedRow.length / 2]) / 2;
        }
    }

    // Метод вычисления дисперсии элементов строки
    public static double varianceRow(int[] row) {
        double m = 0;

        // Находим среднее значение строки
        for (int i = 0; i < row.length; i++) {
            m += row[i];
        }
        m /= row.length;

        double variance = 0;
        // Вычисляем дисперсию
        for (int i = 0; i < row.length; i++) {
            variance += (row[i] - m) * (row[i] - m);
        }
        return variance / row.length; // Возвращаем дисперсию
    }

    // Метод перестановки строк на основе суммы, медианы и дисперсии
    public static void sortRows(int[][] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {

                // Вычисляем сумму, медиану и дисперсию для каждой строки
                int sumA = sumRow(array[i]);
                int sumB = sumRow(array[j]);
                double medianA = medianRow(array[i]);
                double medianB = medianRow(array[j]);
                double varianceA = varianceRow(array[i]);
                double varianceB = varianceRow(array[j]);

                // Сортировка по сумме, медиане, дисперсии
                if (sumA > sumB || (sumA == sumB && medianA > medianB) ||
                        (sumA == sumB && medianA == medianB && varianceA > varianceB)) {
                    int[] z = array[i];
                    array[i] = array[j];
                    array[j] = z;
                }
            }
        }
    }

    // Метод нахождения максимума среди минимумов столбцов
    public static int maxOfMin(int[][] array) {
        int maxOfMins = Integer.MIN_VALUE;
        int column = -1;

        // Проходим по каждому столбцу
        for (int j = 0; j < array[0].length; j++) {
            int minInColumn = Integer.MAX_VALUE;
            // Ищем минимальный элемент в столбце
            for (int i = 0; i < array.length; i++) {
                minInColumn = Math.min(minInColumn, array[i][j]);
            }

            // Обновляем максимум среди минимумов
            if (minInColumn > maxOfMins) {
                maxOfMins = minInColumn;
                column = j;
            }
        }
        return column; // Возвращаем индекс столбца с максимальным минимумом
    }

    // Метод вывода массива по спирали
    public static void spiral(int[][] array) {
        int top = 0;
        int left = 0;
        int bottom = array.length - 1;
        int right = array[0].length - 1;

        // Выводим массив по спирали
        while (top <= bottom && left <= right) {

            // Проходим по верхней строке
            for (int i = left; i <= right; i++) {
                out.print(array[top][i] + " ");
            }
            top ++; // Сдвигаем верхнюю границу

            // Проходим по правому столбцу
            for (int i = top; i <= bottom; i++) {
                out.print(array[i][right] + " ");
            }
            right --; // Сдвигаем правую границу

            // Если есть еще строки, то проходим по нижней строке
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    out.print(array[bottom][i] + " ");
                }
                bottom --; // Сдвигаем нижнюю границу
            }

            // Если есть еще столбцы, то проходим по левому столбцу
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    out.print(array[i][left] + " ");
                }
                left ++; // СДвигаем левую границу
            }
        }
    }

    // Метод поворота массива на 90 градусов по часовой стрелке
    public static int[][] rotate(int[][] array) {
        int n = array.length;
        int m = array[0].length;
        int[][] rotated = new int[m][n];

        // Поворот массива
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][n - i - 1] = array[i][j];
            }
        }
        return rotated; // ВОзвращаем повернутый массив
    }

    public static void main(String[] args) {
        // Ввод размеров массива
        out.print("Введите количество строк (n): ");
        int n = in.nextInt();
        out.print("Введите количество столбцов (m): ");
        int m = in.nextInt();

        int[][] array = new int[n][m];

        // Ввод элементов массива
        out.print("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = in.nextInt();
            }
        }

        // Перестановка строк
        sortRows(array);
        out.println("Массив с отсортированными строками: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(array[i][j] + " ");
            }
            out.println();
        }

        // Поиск максимума среди минимумов
        int column = maxOfMin(array);
        out.println("Номер столбца с максимальным минимумом: " + column);

        // Массив по спирали
        out.println("Массив по спирали: ");
        spiral(array);
        out.println();

        // Поворот массива на 90 градусов по часовой стрелке
        int[][] rotated = rotate(array);
        out.println("Массив после поворота на 90◦: ");
        for (int i = 0; i < rotated.length; i++) {
            for (int j = 0; j < rotated[0].length; j++) {
                out.print(rotated[i][j] + " ");
            }
            out.println();
        }
    }
}
