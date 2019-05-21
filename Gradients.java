public class Gradients
{
  private float[] m_texCoordX;
	private float[] m_texCoordY;

	private float m_texCoordXXStep;
	private float m_texCoordXYStep;
	private float m_texCoordYXStep;
	private float m_texCoordYYStep;

	public float getTexCoordX(int loc) { return m_texCoordX[loc]; }
	public float getTexCoordY(int loc) { return m_texCoordY[loc]; }

	public float getTexCoordXXStep() { return m_texCoordXXStep; }
	public float getTexCoordXYStep() { return m_texCoordXYStep; }
	public float getTexCoordYXStep() { return m_texCoordYXStep; }
	public float getTexCoordYYStep() { return m_texCoordYYStep; }


    //Here we do interpolation

    public Gradients(Vertex minYVert, Vertex midYVert, Vertex maxYVert){


        float oneByDx = 1.0f / ( ( (midYVert.getX() - maxYVert.getX())*
                            (minYVert.getY() - maxYVert.getY()) ) + 
                          ( (maxYVert.getX() - minYVert.getX())*
                            (midYVert.getY() - maxYVert.getY()) ) );

        float oneByDy = - oneByDx;
        
        m_texCoordX = new float[3];
		m_texCoordY = new float[3];

		m_texCoordX[0] = minYVert.getTexCoords().getX();
		m_texCoordX[1] = midYVert.getTexCoords().getX();
		m_texCoordX[2] = maxYVert.getTexCoords().getX();

		m_texCoordY[0] = minYVert.getTexCoords().getY();
		m_texCoordY[1] = midYVert.getTexCoords().getY();
		m_texCoordY[2] = maxYVert.getTexCoords().getY();

		m_texCoordXXStep = 
			(((m_texCoordX[1] - m_texCoordX[2]) *
			(minYVert.getY() - maxYVert.getY())) -
			((m_texCoordX[0] - m_texCoordX[2]) *
			(midYVert.getY() - maxYVert.getY()))) * oneByDx;

		m_texCoordXYStep = 
			(((m_texCoordX[1] - m_texCoordX[2]) *
			(minYVert.getX() - maxYVert.getX())) -
			((m_texCoordX[0] - m_texCoordX[2]) *
			(midYVert.getX() - maxYVert.getX()))) * oneByDy;

		m_texCoordYXStep = 
			(((m_texCoordY[1] - m_texCoordY[2]) *
			(minYVert.getY() - maxYVert.getY())) -
			((m_texCoordY[0] - m_texCoordY[2]) *
			(midYVert.getY() - maxYVert.getY()))) * oneByDx;

		m_texCoordYYStep = 
			(((m_texCoordY[1] - m_texCoordY[2]) *
			(minYVert.getX() - maxYVert.getX())) -
			((m_texCoordY[0] - m_texCoordY[2]) *
			(midYVert.getX() - maxYVert.getX()))) * oneByDy;
    }
}