public class RenderContext extends Bitmap
{
	
	public RenderContext(int width, int height)
	{
		super(width, height);
		
	}

	public void fillTriangle(Vertex v1, Vertex v2, Vertex v3, Bitmap texture)
	{

		Matrix4f screenSpaceTransform = new Matrix4f().initScreenSpaceTransform(getWidth()/2, getHeight()/2);

		Vertex minYVert = v1.transform(screenSpaceTransform).perspectiveDivide();
		Vertex midYVert = v2.transform(screenSpaceTransform).perspectiveDivide();
		Vertex maxYVert = v3.transform(screenSpaceTransform).perspectiveDivide();
		Vertex temp;

		if(minYVert.getY() > midYVert.getY())
		{
			temp     = midYVert;
			midYVert = minYVert;
			minYVert = temp;
		}

		if(midYVert.getY() > maxYVert.getY())
		{
			temp = maxYVert;
			maxYVert = midYVert;
			midYVert = temp;
		}

		if(minYVert.getY() > midYVert.getY())
		{
			temp = midYVert;
			midYVert = minYVert;
			minYVert = temp;
		}

		scanTriangle(minYVert, midYVert, maxYVert, 
			minYVert.triangleAreaX2(maxYVert, midYVert) >= 0, texture);

		//fillShape((int)Math.ceil(minYVert.getY()),(int)Math.ceil(maxYVert.getY()));
	}
	
	private void scanTriangle(Vertex minYVert, Vertex midYVert,
			Vertex maxYVert, boolean handedNess, Bitmap texture)
	{
		Gradients gradient  = new Gradients(minYVert, midYVert, maxYVert);
		Edge topToBottom    = new Edge(gradient, minYVert, maxYVert, 0);
		Edge topToMiddle    = new Edge(gradient, minYVert, midYVert, 0);
		Edge middleToBottom = new Edge(gradient, midYVert, maxYVert, 1);

		scanEdges(gradient, topToBottom, topToMiddle, handedNess, texture);
		scanEdges(gradient, topToBottom, middleToBottom, handedNess, texture);

		
	}


	private void scanEdges(Gradients gradient, Edge a, Edge b, boolean handedNess, Bitmap texture)
	{
		Edge left = a;
		Edge right = b;
		if(handedNess)
		{
			Edge temp = left;
			left = right;
			right = temp;
		}

		int yStart = b.getYStart();
		int yEnd   = b.getYEnd();
		for(int j = yStart; j < yEnd; j++)
		{
			drawScanLine(gradient, left, right, j, texture);
			left.step();
			right.step();
		}
	}


	private void drawScanLine(Gradients gradient, Edge left, Edge right, int j, Bitmap texture)
	{
		
		int xMin = (int)Math.ceil(left.getX());
		int xMax = (int)Math.ceil(right.getX());
		float xPreStep = xMin - left.getX();

		float texCoordX = left.getTexCoordX() + gradient.getTexCoordXXStep() * xPreStep;
		float texCoordY = left.getTexCoordY() + gradient.getTexCoordYXStep() * xPreStep;
		float lerpAmt = 0.0f;
		float lerpStep = 1.0f / (float)(xMax - xMin);

		for(int i=xMin; i<xMax; i++)
		{
			//(maxColor - minColor) * lerpAmt + minColor
			int srcX = (int)(texCoordX * (texture.getWidth() - 1) + 0.5f);

			int srcY = (int)(texCoordY * (texture.getHeight() - 1) + 0.5f);

			copyPixel(i, j, srcX, srcY, texture);
			texCoordX += gradient.getTexCoordXXStep();
			texCoordY += gradient.getTexCoordYXStep();
		}

		
		
	}


}
