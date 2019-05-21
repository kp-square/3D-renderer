public class Edge
{
	private float m_x;//for x position
	private float m_xStep;
	private int m_yStart;
	private int m_yEnd;
	// private Vector4f m_color;
	// private Vector4f colorStep;
	private float m_texCoordX;
	private float m_texCoordXStep;
	private float m_texCoordY;
	private float m_texCoordYStep;

	public float getX(){ return m_x; }
	public int getYStart(){ return m_yStart; }
	public int getYEnd(){ return m_yEnd; }
	//public Vector4f getColor(){ return m_color; }
	public float getTexCoordX(){ return m_texCoordX; }
	public float getTexCoordY(){ return m_texCoordY; }

//DDA Line Drawing algorithm has been applied here
	public Edge(Gradients gradient, Vertex minYVert, Vertex maxYVert, int minYVertIndex)
	{
		m_yStart  = (int)Math.ceil(minYVert.getY());
		m_yEnd = (int)Math.ceil(maxYVert.getY());

		float yDist = maxYVert.getY() - minYVert.getY();
		float xDist = maxYVert.getX() - minYVert.getX();

		float yPreStep = m_yStart - minYVert.getY();

		m_xStep = (float)xDist/(float)yDist;
		m_x = minYVert.getX() + yPreStep * m_xStep;

		float xPreStep = m_x - minYVert.getX();
		//m_color = color of vertex + colorYStep * yPreStep + colorXStep * xPreStep
		//m_color = (gradient.getColor(minVertIndex).add(gradient.getColorYStep().mul(yPreStep).add(gradient.getColorXStep().mul(xPreStep))));
		m_texCoordX = gradient.getTexCoordX(minYVertIndex) + 
					gradient.getTexCoordXXStep() * xPreStep + 
					gradient.getTexCoordXYStep() * yPreStep;
		m_texCoordXStep = gradient.getTexCoordXYStep() + gradient.getTexCoordXXStep() * m_xStep;


		m_texCoordY = gradient.getTexCoordY(minYVertIndex) + 
					gradient.getTexCoordYXStep() * xPreStep + 
					gradient.getTexCoordYYStep() * yPreStep;
		m_texCoordXStep = gradient.getTexCoordYYStep() + gradient.getTexCoordYXStep() * m_xStep;


		//colorStep = gradient.getColorYStep().add(gradient.getColorXStep().mul(m_xStep));
	}

	public void step()
	{
		m_x += m_xStep;
		m_texCoordX += m_texCoordXStep;
		m_texCoordY += m_texCoordYStep;
	}



}

