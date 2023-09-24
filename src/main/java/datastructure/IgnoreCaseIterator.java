package datastructure;

import java.util.Iterator;
import java.util.List;

public class IgnoreCaseIterator implements Iterator<Character> {

    List<Character> characters;
    int index = 0;

    public IgnoreCaseIterator(List<Character> characters) {
        this.characters = characters;
    }

    public boolean hasNext() {
        while (index < characters.size()) {
            if (Character.isUpperCase(characters.get(index))) {
                return true;
            }
        }
        return false;
    }

    public Character next() {
        if (hasNext()) return characters.get(index);
        return null;
    }

}
