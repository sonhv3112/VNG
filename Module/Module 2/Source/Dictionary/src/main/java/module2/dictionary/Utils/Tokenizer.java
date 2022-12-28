package module2.dictionary.Utils;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    String str; 

    public Tokenizer(String str) { 
        this.str = str; 
    }

    public List<String> tokenize(char separator) { 
        ArrayList<String> tokens = new ArrayList<String>(); 
        StringBuilder currentToken = new StringBuilder(); 
        for (int i = 0; i < this.str.length(); ++i) { 
            if (this.str.charAt(i) == separator) { 
                if (currentToken.length() != 0) { 
                    tokens.add(currentToken.toString()); 
                    currentToken.setLength(0);
                }
            } else { 
                currentToken.append(this.str.charAt(i)); 
            }
        }
        if (currentToken.length() != 0) { 
            tokens.add(currentToken.toString()); 
            currentToken.setLength(0);
        }
        return tokens; 
    }

    public List<String> tokenize(char separator, char wrap) { 
        ArrayList<String> tokens = new ArrayList<String>();
        StringBuilder currentToken = new StringBuilder(); 
        int isOpened = 0; 
        for (int i = 0; i < this.str.length(); ++i) { 
            if (this.str.charAt(i) == separator && isOpened == 0) { 
                if (currentToken.length() != 0) { 
                    tokens.add(currentToken.toString()); 
                    currentToken.setLength(0);
                }
            } else { 
                currentToken.append(this.str.charAt(i)); 
                if (this.str.charAt(i) == wrap)
                    isOpened ^= 1; 
            }
        }
        if (currentToken.length() != 0) { 
            tokens.add(currentToken.toString()); 
            currentToken.setLength(0);
        }
        return tokens; 
    }

    public List<String> tokenizeOnlyAlphabetNNumber() { 
        ArrayList<String> tokens = new ArrayList<String>(); 
        StringBuilder currentToken = new StringBuilder(); 
        for (int i = 0; i < this.str.length(); ++i) { 
            char currentCharacter = this.str.charAt(i); 
            if (('a' <= currentCharacter && currentCharacter <= 'z') ||
                ('A' <= currentCharacter && currentCharacter <= 'Z') ||
                ('0' <= currentCharacter && currentCharacter <= '9')) 
                    currentToken.append(currentCharacter); 
            else { 
                if (currentToken.length() != 0) { 
                    tokens.add(currentToken.toString()); 
                    currentToken.setLength(0);
                }   
            }
        }   
        if (currentToken.length() != 0) { 
            tokens.add(currentToken.toString()); 
            currentToken.setLength(0);
        }
        return tokens; 
    }
}
