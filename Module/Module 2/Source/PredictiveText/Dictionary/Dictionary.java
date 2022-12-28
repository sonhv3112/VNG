package PredictiveText.Dictionary;

import java.util.List;

public interface Dictionary { 
    public boolean contains(String word); 
    public List<String> searchKNeareastString(String word, int k); 
}
