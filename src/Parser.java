public class Parser {

    public String solve(String line) throws IllegalStateException, IllegalArgumentException {

        State state;

        String errorMessage = "";

        String[] strings = new String[0];

        line = line.replaceAll(" ", "");


        if (line.contains("+")) {

            strings = line.split("\\+");

        }

        if (line.contains("-")) {

            strings = line.split("-");

        }

        if (line.contains("*")) {

            strings = line.split("\\*");

        }

        if (line.contains("/")) {

            strings = line.split("/");

        }


        if (strings.length != 2) {

            throw new IllegalStateException("В выражении не 2 числа, либо не найден знак");

        }


        try {

            Integer.parseInt(strings[0]);

            Integer.parseInt(strings[1]);

            if (Integer.parseInt(strings[0]) > 10 || Integer.parseInt(strings[0] )<= 0) {

                throw new IllegalStateException("Число больше 10 или меньше 0");

            }

            if (Integer.parseInt(strings[1]) > 10 || Integer.parseInt(strings[1] )<= 0) {

                throw new IllegalStateException("Число больше 10 или меньше 0");

            }

            state = State.DECIMAL_STATE;

        } catch (NumberFormatException e) {

            try {

                Roman.romanToArabic(strings[0]);

                Roman.romanToArabic(strings[1]);

                if (Roman.romanToArabic(strings[0]) < 0 || Roman.romanToArabic(strings[0]) > 10) {

                    throw new IllegalStateException("Число больше 10 или меньше 0");

                }

                if (Roman.romanToArabic(strings[1]) < 0 || Roman.romanToArabic(strings[1]) > 10) {

                    throw new IllegalStateException("Число больше 10 или меньше 0");

                }


                state = State.ROMAN_STATE;

            } catch (IllegalArgumentException ex) {


                errorMessage = ex.getMessage();

                state = State.INCORRECT_STATE;

            }

        }

        switch (state) {

            case ROMAN_STATE:

                return romanSolving(line);

            case DECIMAL_STATE:

                return String.valueOf(integerSolving(line));

            case INCORRECT_STATE:

                throw new IllegalStateException("Введенная строка содержит ошибки: " + errorMessage);

        }

        throw new IllegalStateException("Некорректное состояние строки");

    }

    private String romanSolving(String line) throws IllegalArgumentException {

        line = line.replaceAll(" ", "");

        if (line.contains("+")) {

            String[] strings = line.split("\\+");

            return Roman.arabicToRoman(Roman.romanToArabic((strings[0])) + Roman.romanToArabic(strings[1]));

        }

        if (line.contains("-")) {

            String[] strings = line.split("-");

            return Roman.arabicToRoman(Roman.romanToArabic((strings[0])) - Roman.romanToArabic(strings[1]));

        }

        if (line.contains("*")) {

            String[] strings = line.split("\\*");

            return Roman.arabicToRoman( Roman.romanToArabic((strings[0])) * Roman.romanToArabic(strings[1]));

        }

        if (line.contains("/")) {

            String[] strings = line.split("/");

            return Roman.arabicToRoman(Roman.romanToArabic((strings[0])) / Roman.romanToArabic(strings[1]));

        }

        throw new IllegalStateException("Не найден знак действия");

    }

    private int integerSolving(String line) throws NumberFormatException {

        line = line.replaceAll(" ", "");

        if (line.contains("+")) {

            String[] strings = line.split("\\+");

            return Integer.parseInt(strings[0]) + Integer.parseInt(strings[1]);

        }

        if (line.contains("-")) {

            String[] strings = line.split("-");

            return Integer.parseInt(strings[0]) - Integer.parseInt(strings[1]);

        }

        if (line.contains("*")) {

            String[] strings = line.split("\\*");

            return Integer.parseInt(strings[0]) * Integer.parseInt(strings[1]);

        }

        if (line.contains("/")) {

            String[] strings = line.split("/");

            return Integer.parseInt(strings[0]) / Integer.parseInt(strings[1]);

        }

        throw new IllegalStateException("Не найден знак действия");

    }

}
