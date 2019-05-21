import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class LoadModel {

    public ArrayList<Indices> indices;
    public ArrayList<Vector4f> vertices;
    public ArrayList<Vector4f> normals;
    public ArrayList<Vector4f> texCoords;

    public LoadModel(String fileName) throws IOException{
        indices = new ArrayList<Indices>();
        vertices = new ArrayList<Vector4f>();
        normals = new ArrayList<Vector4f>();
        texCoords = new ArrayList<Vector4f>();

        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String line = new String();

        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.charAt(0) == 'v') {
                if (line.charAt(1) == ' ') {
                    line = line.substring(2);
                    String[] parts = line.split(" ");
                    Vector4f obj = new Vector4f(Float.parseFloat(parts[0])/4.0f, Float.parseFloat(parts[1])/4.0f,
                            Float.parseFloat(parts[2])/4.0f, 1);
                    vertices.add(obj);
                }

                else if (line.charAt(1) == 't') {
                    line = line.substring(3);
                    String[] parts = line.split(" ");
                    Vector4f obj1 = new Vector4f(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), 0.0f, 0.0f);
                    texCoords.add(obj1);
                }

                else if (line.charAt(1) == 'n') {
                    line = line.substring(3);
                    String[] parts = line.split(" ");
                    Vector4f obj2 = new Vector4f(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2]), 0);
                    normals.add(obj2);
                }

            } else if (line.charAt(0) == 'f') {
                if (line.charAt(1) == ' ') {
                    line = line.substring(2);
                    String[] parts = line.split(" ");
                    for (int i = 0; i < 3; i++) {
                        String[] subParts = parts[i].split("/");
                        int i1 = Integer.parseInt(subParts[0]);
                        int i2 = Integer.parseInt(subParts[1]);
                        int i3 = Integer.parseInt(subParts[2]);
                        Indices obj3 = new Indices(i1-1, i2-1, i3-1);
                        indices.add(obj3);

                    }

                }
            }

        }
        sc.close();
        
    }


}
