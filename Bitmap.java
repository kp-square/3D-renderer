import java.util.Arrays;

public class Bitmap
{
	private final int m_width;         //width of the image in pixels
	private final int m_height;        //height of the image in pixels
	private final byte m_components[]; //Array to store pixel value ABGR. Data is stored as A0,B0,G0,R0,A1,B1,G1,...

	//Creating the constructor
	public Bitmap(int width, int height)
	{
		m_width  = width;
		m_height = height;
		m_components = new byte[ width * height * 4 ];

	}

	public void clear(byte shade){
		Arrays.fill(m_components, shade); // This sets all components elements = shade

	}

	public void drawPixel(int x, int y, byte a, byte b, byte g, byte r)
	{
		int index = ( x + y * m_width ) * 4 ; // Steps from (x,y) coordinate to (x , y+1 )
		m_components[index    ] = a;
		m_components[index + 1] = b;
		m_components[index + 2] = g;
		m_components[index + 3] = r;
		
	}


	public void copyPixel(int destX, int destY, int srcX, int srcY, Bitmap src)
	{
		int destIndex = (destX + destY * m_width) * 4;
		int srcIndex = (srcX + srcY * src.getWidth()) * 4;
		m_components[destIndex    ] = src.getComponent(srcIndex);
		m_components[destIndex + 1] = src.getComponent(srcIndex + 1);
		m_components[destIndex + 2] = src.getComponent(srcIndex + 2);
		m_components[destIndex + 3] = src.getComponent(srcIndex + 3);
	}


	public void copyToByteArray(byte[] dest)
	{
		for(int i=0 ; i< m_width * m_height ; i++)
		{
			dest[i * 3]     = m_components[i * 4 + 1]; //We are discarding the alpha value
			dest[i * 3 + 1] = m_components[i * 4 + 2]; //We are discarding the alpha value
			dest[i * 3 + 2] = m_components[i * 4 + 3]; //We are discarding the alpha value


		} 

	}

	
	public int getWidth(){
		return m_width;
	}

	public int getHeight(){
		return m_height;	
	}

	public byte getComponent(int index)
	{ 
		return m_components[index];
	}


}
