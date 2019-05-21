public class Vertex
{
	private Vector4f m_position;
	private Vector4f m_texCoords;

	//Variables and Constants for lightening calculation
	//private Vector4f m_normal;
	

	public float getX(){ return m_position.getX(); }
	public float getY(){ return m_position.getY(); }
	public float getZ(){ return m_position.getZ(); }
	public Vector4f getTexCoords() { return m_texCoords; }
	public Vector4f getPosition(){ return m_position; }
	//public Vector4f getNormal(){return m_normal; }

	public Vertex(Vector4f position, Vector4f color)
	{
		m_position = position;
		m_texCoords = color;
	}
/*
	public Vertex(Vector4f position, Vector4f normal)
	{
		
		m_position = position;
		m_color = new Vector4f(0.5f,0.15f,0.6f,1.0f);
		m_normal = normal;
		
		
	}
*/

	public Vertex transform(Matrix4f m_transform)
	{
		return new Vertex(m_transform.transform(m_position), m_texCoords);
	}
	
	public Vertex perspectiveDivide()
	{
		return new Vertex(new Vector4f( m_position.getX()/m_position.getW(),
						m_position.getY()/m_position.getW(),
					   	m_position.getZ()/m_position.getW(),
					   	m_position.getW()), m_texCoords);
	}


	public float triangleAreaX2(Vertex b, Vertex c)
	{
		float x1 = b.getX() - m_position.getX();
		float y1 = b.getY() - m_position.getY();

		float x2 = c.getX() - m_position.getX();
		float y2 = c.getY() - m_position.getY();

		//return 2d cross product i.e area of triangle
		return (x1*y2 - x2*y1); 
	}

	public void printVertex()
	{
		System.out.print(m_position.toString());
	}


}
