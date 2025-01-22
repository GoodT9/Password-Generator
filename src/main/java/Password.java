
public class Password {
    String Value;
    int Length;

    public Password(String s) {
        Value = s;
        Length = s.length();
    }

    /**
 * Determines the type of a character in a password.
 *
 * @param C The character to be evaluated.
 * @return An integer representing the type of the character:
 *         1 - Uppercase letter
 *         2 - Lowercase letter
 *         3 - Digit
 *         4 - Symbol
 */

public int CharType(char C) {
    int val;

    // Char is Uppercase Letter
    if ((int) C >= 65 && (int) C <= 90)
        val = 1;

    // Char is Lowercase Letter
    else if ((int) C >= 97 && (int) C <= 122) {
        val = 2;
    }

     // Char is Digit
    else if ((int) C >= 48 && (int) C <= 57) {
        val = 3;
}

    // Char is Symbol
    else {
        val = 4;
    }

    return val;
}

    /**
     * Calculates the strength of the password based on various criteria.
     * 
     * This method evaluates the password strength by checking for the presence of:
     * - Uppercase letters
     * - Lowercase letters
     * - Numbers
     * - Symbols
     * It also considers the length of the password.
     * 
     * @return An integer score representing the password strength:
     *         - 1 point for each: uppercase, lowercase, number, and symbol used
     *         - 1 point if length is 8 or more
     *         - 1 additional point if length is 16 or more
     *         The maximum possible score is 6.
     */
    public int PasswordStrength() {
        String s = this.Value;
        boolean UsedUpper = false;
        boolean UsedLower = false;
        boolean UsedNum = false;
        boolean UsedSym = false;
        int type;
        int Score = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            type = CharType(c);

            if (type == 1) UsedUpper = true;
            if (type == 2) UsedLower = true;
            if (type == 3) UsedNum = true;
            if (type == 4) UsedSym = true;
        }

        if (UsedUpper) Score += 1;
        if (UsedLower) Score += 1;
        if (UsedNum) Score += 1;
        if (UsedSym) Score += 1;

        if (s.length() >= 8) Score += 1;
        if (s.length() >= 16) Score += 1;

        return Score;
    }


    public String calculateScore() {
        int Score = this.PasswordStrength();
        String visualization = this.visualizePasswordStrength();
        if (Score == 6) {
            return visualization + "\nThis is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";
        } else if (Score >= 4) {
            return visualization + "\nThis is a good password :) but you can still do better";
        } else if (Score >= 3) {
            return visualization + "\nThis is a medium password :/ try making it better";
        } else {
            return visualization + "\nThis is a weak password :( definitely find a new one";
        }
    }

public String visualizePasswordStrength() {
    int strength = this.PasswordStrength();
    StringBuilder visualization = new StringBuilder("[");
    
    for (int i = 0; i < 6; i++) {
        if (i < strength) {
            visualization.append("█");
        } else {
            visualization.append("░");
        }
    }
    
    visualization.append("] ");
    
    switch (strength) {
        case 1:
        case 2:
            visualization.append("Weak");
            break;
        case 3:
        case 4:
            visualization.append("Medium");
            break;
        case 5:
            visualization.append("Strong");
            break;
        case 6:
            visualization.append("Very Strong");
            break;
    }
    
    return visualization.toString();
}

    @Override
    public String toString() {
        return Value;
    }
}
