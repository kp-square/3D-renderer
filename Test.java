import java.io.IOException;
public class Test
{
    public static void main(String[] args) throws IOException{
        LoadModel fuck = new LoadModel("C:\\dev\\java_projects\\cube.txt"); 
        for(int i=0; i< fuck.vertices.size(); i++)
        {
            System.out.println(fuck.vertices.get(i).toString());

        }
        for(int i=0; i< fuck.normals.size(); i++)
        {
            System.out.println(fuck.normals.get(i).toString());
            
        }
        for(int i=0; i< fuck.texCoords.size(); i++)
        {
            System.out.println(fuck.texCoords.get(i).toString());
            
        }
        for(int i=0; i< fuck.indices.size(); i++)
        {
            System.out.println(fuck.indices.get(i).toString());
            
        }
    }
}