package HTScratch; 

public class PrimeUtils { 
    public static boolean isPrime(int x) { 
        if (x <= 1) 
            return false; 
        if (x <= 3)  
            return true; 
        for (int i = 2; i <= Math.sqrt(x); ++i) { 
            if (x % i == 0) 
                return false; 
        }
        return true; 
    }

    public static int nextPrime(int x) { 
        while (!isPrime(x)) 
            ++x; 
        return x; 
    }
}