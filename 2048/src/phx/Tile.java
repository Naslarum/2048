package phx;

import static phx.Value._0;
import static phx.Value._2;
import static phx.Value._4;

import java.util.HashMap;

public class Tile {
    private final Value val;

    private final static HashMap<Integer, Tile> cache = new HashMap<>();

    public final static Tile ZERO = new Tile(_0);
    public final static Tile TWO = new Tile(_2);
    public final static Tile FOUR = new Tile(_4);

    static {
        for (Value v : Value.values()) {
            switch (v) {
            case _0:
                cache.put(v.score(), ZERO);
                break;
            case _2:
                cache.put(v.score(), TWO);
                break;
            case _4:
                cache.put(v.score(), FOUR);
                break;
            default:
                cache.put(v.score(), new Tile(v));
                break;
            }
        }
    }

    public Tile(Value v) {
        val = v;
    }


    public static Tile valueOf(int num) {
        return cache.get(num);
    }

    Value value() {
        return val;
    }


    public Tile getDouble() {
        return valueOf(val.score() << 1);
    }


    boolean empty() {
        return val == _0;
    }

    @Override
    public String toString() {
        return String.format("%1$4d", val.score());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((val == null) ? 0 : val.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tile))
            return false;
        Tile other = (Tile) obj;
        if (val != other.val)
            return false;
        return true;
    }

    static Tile randomTile() {
        return Math.random() < 0.15 ? FOUR : TWO;
    }
}
