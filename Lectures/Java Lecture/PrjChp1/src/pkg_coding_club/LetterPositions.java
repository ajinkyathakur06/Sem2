package pkg_coding_club;

public class LetterPositions {
    public static String letterPositions(String word) {
        StringBuilder result = new StringBuilder();

        for (char c : word.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(c).append((c - 'A' + 1));
            }
        }
        return result.toString();
    }
}