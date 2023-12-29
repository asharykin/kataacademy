import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

            System.out.println("Калькулятор умеет работать одновременно только с двумя арабскими (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)");
            System.out.println("или с двумя римскими (I, II, III, IV, V, VI, VII, VIII, IX, X) целыми числами от 1 до 10.");
            System.out.println("Калькулятор поддерживает четыре оператора: a + b, a - b, a * b, a / b");
            System.out.println("Для корректной работы при вводе выражения нужно ставить по одному пробелу до и после оператора");
            System.out.println("Результатом операции деления является целое число, остаток отбрасывается");
            System.out.println("Результатом работы калькулятора с римскими числами могут быть только положительные числа (учитывайте это при вычитании)\n");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение: ");
            String input = scanner.nextLine();
            System.out.println("Значение выражения: " + calc(input));
        }

        public static String calc(String input) {
            String[] elements = input.split(" ");
            if (elements.length != 3) {
                throw new IllegalArgumentException("Недопустимый формат выражения. Возможно, вы попытались ввести только один или более двух операндов.\n" +
                                                   "Возможно, вы не поставили по одному пробелу до и после оператора");
            }

            int a, b;
            try {
                a = Integer.parseInt(elements[0]);
                b = Integer.parseInt(elements[2]);
            } catch (NumberFormatException e) {
                if (Roman.isRoman(elements[0]) && Roman.isRoman(elements[2])) {
                    a = Roman.toArabic(elements[0]);
                    b = Roman.toArabic(elements[2]);
                }
                else throw new IllegalArgumentException("Оба операнда одновременно должны быть или арабским, или римскими целыми числами");
            }

            if (a < 1 || a > 10 || b < 1 || b > 10) throw new IllegalArgumentException("Операнды должны принимать целые значения в диапазоне от 1 до 10 включительно");

            String o = elements[1];
            int result;
            switch (o) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new IllegalArgumentException("Недопустимый оператор. Возможны только +, -, * или /");
            }

            if (Roman.isRoman(elements[0]) && Roman.isRoman(elements[2])) {
                if (result <= 0) throw new IllegalArgumentException("Результат выполнения операции над римскими числами должен быть положительным");
                return Roman.toRoman(result);
            }
            else {
                return Integer.toString(result);
            }
        }
}

class Roman {
    static String[] romanArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                                 "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                                 "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                                 "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                                 "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                                 "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                                 "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                                 "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                                 "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                                 "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (String i: romanArray) {
            if (i.equals(val)) return true;
        }
        return false;
    }

    public static int toArabic(String roman) {
        for (int i = 1; i <= romanArray.length; ++i) {
            if (romanArray[i - 1].equals(roman)) return i;
        }
        return 0;
    }

    public static String toRoman(int arabic) {
        for (int i = 1; i <= romanArray.length; ++i) {
            if (i == arabic) return romanArray[i - 1];
        }
        return "";
    }

}

