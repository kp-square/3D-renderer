import java.io.IOException;

public class Mesh
{
    LoadModel myModel;

    public Mesh(String fileName)throws IOException
    {
        myModel = new LoadModel(fileName);

    }

    public void Draw(RenderContext context, Matrix4f viewProjection, Matrix4f transform)
	{
        Matrix4f mvp = viewProjection.mul(transform);
        
        for(int i = 2; i < myModel.indices.size(); i += 3){
            
            
            Vertex v1 = new Vertex(mvp.transform(myModel.vertices.get(myModel.indices.get(i-2).getPosIndex())),
                                myModel.normals.get(myModel.indices.get(i-2).getNormalIndex()));
            
            Vertex v2 = new Vertex(mvp.transform(myModel.vertices.get(myModel.indices.get(i - 1).getPosIndex())),
                                myModel.normals.get(myModel.indices.get(i - 1).getNormalIndex()));
            
            Vertex v3 = new Vertex(mvp.transform(myModel.vertices.get(myModel.indices.get(i ).getPosIndex())),
                                myModel.normals.get(myModel.indices.get(i).getNormalIndex()));
            context.fillTriangle(v1,v2,v3);
		}
	}
}