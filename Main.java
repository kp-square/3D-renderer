import java.io.IOException;

public class Main {
	public static void main(String[] args)throws IOException{
		Display myDisplay = new Display(800,600,"myCar : Lamborgini");
		
		RenderContext target = myDisplay.getFrameBuffer();
		//Stars3D stars = new Stars3D(4096, 64.0f, 20.0f);

		Bitmap texture = new Bitmap(32, 32);
		for(int j = 0; j < texture.getHeight(); j++)
		{
			for(int i = 0; i < texture.getWidth(); i++)
			{
				texture.drawPixel(i, j,
					(byte)(Math.random() * 255.0 + 0.5),
					(byte)(Math.random() * 255.0 + 0.5),
					(byte)(Math.random() * 255.0 + 0.5),
					(byte)(Math.random() * 255.0 + 0.5));
			}
		}


		Vertex minYVert = new Vertex(new Vector4f(-1, -1, 0, 1), 
		                             new Vector4f(1.0f, 0.0f, 0.0f, 0.0f));
		Vertex midYVert = new Vertex(new Vector4f(0, 1, 0, 1), 
		                             new Vector4f(0.0f, 1.0f, 0.0f, 0.0f));
		Vertex maxYVert = new Vertex(new Vector4f(1, -1, 0, 1), 
		                             new Vector4f(0.0f, 0.0f, 1.0f, 0.0f));

		//Mesh cube = new Mesh("C:\\dev\\java_projects\\myCar2.txt");
		Matrix4f projection = new Matrix4f().initPerspective((float)Math.toRadians(70.0f),
				(float)target.getWidth()/(float)target.getHeight(), 0.1f, 100.0f);

		float rotCounter = 0.0f;

		long previousTime = System.nanoTime();
		while(true)
		{
			long currentTime = System.nanoTime();
			float delta = (float)((currentTime - previousTime)/1000000000.0) * 0.5f;
			previousTime = currentTime;

			//stars.updateAndRender(target, delta);
			
			//for(int j= 100; j < 200; j++)
			//{
			//	target.drawScanBuffer(j, 300-j+100, 300+j-100);
			//}
			
			rotCounter += delta;
			Matrix4f translation = new Matrix4f().initTranslation(0.0f,0.0f,3.0f);
			Matrix4f rotation = new Matrix4f().initRotation(0.0f,rotCounter, 0.0f);
			Matrix4f transform = projection.mul(translation.mul(rotation));

			target.clear((byte)0x00);
			
			//cube.Draw(target, projection, transform);
			target.fillTriangle(maxYVert.transform(transform), 
							midYVert.transform(transform), minYVert.transform(transform), texture);


			myDisplay.swapBuffers();
		}
	}
}
