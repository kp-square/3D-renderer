public class Indices{
    private final int posIndex;
    private final int texIndex;
    private final int normalIndex;

    public int getPosIndex(){return posIndex;}
    public int getTexIndex(){return texIndex;}
    public int getNormalIndex(){return normalIndex;}

    public Indices(int a, int b, int c)
    {
        posIndex =a;
        texIndex = b;
        normalIndex = c;
    }

    public String toString()
    {
        return "("+posIndex+","+texIndex+","+normalIndex+")";
    }
}