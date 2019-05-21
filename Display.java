import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferByte;
import javax.swing.JFrame;


//Creating a window to render the car
public class Display extends Canvas
{
	// The window that is being build
	private final JFrame 		m_frame;
	private final RenderContext 	m_frameBuffer;
	private final byte[] 		m_displayComponents;
	private final BufferedImage 	m_displayImage;
	private final BufferStrategy    m_bufferStrategy;
	private final Graphics 		m_graphics;


	/*
	 * Creates and initializes a new display
	 * width  : width of window
	 * height : height of window
	 * title  : Title of window
	 */

	public Display(int width, int height, String title)
	{
		Dimension size = new Dimension(width,height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);



		m_frameBuffer = new RenderContext(width, height);
		m_displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		m_displayComponents = 
			((DataBufferByte)m_displayImage.getRaster().getDataBuffer()).getData();

		//m_frameBuffer.clear((byte)0x80);

		m_frame = new JFrame();  //Create new jframe object
		m_frame.add(this);  //Add display class to the jframe
		m_frame.pack();    //Resize the jframe to display the canvas
		m_frame.setResizable(false);  //disable the resizing of jframe
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Exit when X button is clicked
		m_frame.setLocationRelativeTo(null);  //Set the frame in the center
		m_frame.setTitle(title);
		m_frame.setSize(width, height);
		m_frame.setVisible(true);

		createBufferStrategy(1);
		m_bufferStrategy = getBufferStrategy();
		m_graphics = m_bufferStrategy.getDrawGraphics();
	}

	public void swapBuffers()
	{
		m_frameBuffer.copyToByteArray(m_displayComponents);
		m_graphics.drawImage(m_displayImage, 0, 0, 
				    m_frameBuffer.getWidth(),m_frameBuffer.getHeight(), null);
		m_bufferStrategy.show();
	}

	public RenderContext getFrameBuffer()
	{
		return m_frameBuffer;
	}
}
