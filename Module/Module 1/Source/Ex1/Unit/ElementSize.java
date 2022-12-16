package Ex1.Unit;

import java.util.NoSuchElementException;

public class ElementSize {
    public enum Type {
        B, // bytes 
        K, // kilobytes 
        M, // megabytes
        G, // gigabytes
        T, // terabytes
        P, // petabytes
        E; // exabytes

        public boolean havePrevious() { 
            return ordinal() > 1; 
        }

        public boolean haveNext() { 
            return ordinal() < values().length - 1; 
        }

        public Type previous() { 
            if (ordinal() == 0) 
                throw new NoSuchElementException("Not available previous value"); 
            return values()[ordinal() - 1]; 
        }

        public Type next() { 
            if (ordinal() == values().length - 1) 
                throw new NoSuchElementException("Not available next value");
            return values()[ordinal() + 1]; 
        }

        public static Type parseType(char ch) { 
            switch (ch) { 
                case 'B': 
                    return Type.B;
                case 'K': 
                    return Type.K; 
                case 'M': 
                    return Type.M; 
                case 'G': 
                    return Type.G; 
                case 'T': 
                    return Type.T; 
                case 'P': 
                    return Type.P; 
                case 'E': 
                    return Type.E; 
                default: 
                    throw new NoSuchElementException("Invalid character of type"); 
            }
        }

        public static Type getMin(Type x, Type y) { 
            return x.ordinal() < y.ordinal() ? x : y; 
        }
    }

    private double size; 
    private Type type; 

    ElementSize() { 
        this.size = 0; 
        this.type = Type.B; 
    }

    ElementSize(double size) { 
        this.size = size; 
        this.type = Type.B; 
        this.fix(); 
    }

    ElementSize(double size, Type type) { 
        this.size = size; 
        this.type = type; 
        this.fix(); 
    }

    ElementSize(ElementSize other) { 
        this.size = other.size; 
        this.type = other.type; 
    }

    private void fix() { 
        while (this.type.haveNext() && this.size >= 1024) { 
            this.size /= 1024; 
            this.type = this.type.next(); 
        }
        while (this.type.havePrevious() && this.size < 1) { 
            this.size *= 1024; 
            this.type = this.type.previous(); 
        }
    }

    public String toString() { 
        return String.format("%.3f", this.size) + this.type.toString(); 
    }

    public static ElementSize parseSize(String str) { 
        double size = Double.parseDouble(str.substring(0, str.length() - 1)); 
        Type type = Type.parseType(str.charAt(str.length() - 1)); 
        return new ElementSize(size, type); 
    }

    public ElementSize convertType(Type newType) { 
        ElementSize ret = new ElementSize(this); 
        for (int i = ret.type.ordinal() - 1; i >= newType.ordinal(); --i) { 
            ret.size *= 1024; 
        }
        for (int i = ret.type.ordinal() + 1; i <= newType.ordinal(); ++i) { 
            ret.size /= 1024; 
        }
        ret.type = newType; 
        return ret; 
    }

    public ElementSize add(ElementSize other) { 
        Type minType = Type.getMin(this.type, other.type); 
        ElementSize temp = this; 
        if (temp.type != minType) { 
            temp = this.convertType(minType); 
        }
        if (other.type != minType) { 
            other = other.convertType(minType); 
        }
        this.size = temp.size + other.size;  
        this.type = minType; 
        this.fix(); 
        return this; 
    }
}
