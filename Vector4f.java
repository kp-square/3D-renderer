public class Vector4f{
	private final float x;
	private final float y;
	private final float z;
	private final float w;

	public Vector4f(float x, float y, float z, float w){
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
	}
	

	public Vector4f(float x,float y,float z)
	{
		this(x,y,z,1.0f);
	}

	public float length()
	{
		return (float)Math.sqrt(x*x + y*y + z*z + w*w);
	}

	public float max()
	{
		return Math.max(Math.max(x,y), Math.max(z,w));
	}

	public float dot(Vector4f vec)
	{
		return (x * vec.getX() + y * vec.getY()
				+ z * vec.getZ() + w * vec.getW());
	}

	public Vector4f cross(Vector4f vec)
	{
		float tx = y * vec.getZ() - z * vec.getY();
		float ty = z * vec.getX() - x * vec.getZ();
		float tz = x * vec.getY() - y * vec.getX();

		return new Vector4f(tx, ty, tz, 0);
	}

	public Vector4f normalize()
	{
		float len = this.length();
		return new Vector4f(x/len, y/len, z/len, w/len);
	}

	public Vector4f add(Vector4f vec)
	{
		return new Vector4f(x + vec.getX(), 
				    y + vec.getY(),
				    z + vec.getZ(),
				    w + vec.getW());
	}

	public Vector4f add(float temp)
	{
		return new Vector4f(x + temp, y + temp, z + temp, w + temp);
	}

	public Vector4f sub(Vector4f vec)
	{
	return new Vector4f(x - vec.getX(), 
			    y - vec.getY(),
			    z - vec.getZ(),
			    w - vec.getW());
	}

	public Vector4f sub(float temp)
	{
		return new Vector4f(x - temp, y - temp, z - temp, w - temp);
	}

	public Vector4f mul(Vector4f vec)
	{
	return new Vector4f(x * vec.getX(), 
			    y * vec.getY(),
			    z * vec.getZ(),
			    w * vec.getW());
	}

	public Vector4f mul(float temp)
	{
		return new Vector4f(x * temp, y * temp, z * temp, w * temp);
	}


	public Vector4f div(Vector4f vec)
	{
	return new Vector4f(x / vec.getX(), 
			    y / vec.getY(),
			    z / vec.getZ(),
			    w / vec.getW());
	}

	public Vector4f div(float temp)
	{
		return new Vector4f(x / temp, y / temp, z / temp, w / temp);
	}

	public Vector4f abs()
	{
		return new Vector4f(Math.abs(x), Math.abs(y),
			 	    Math.abs(z), Math.abs(w));
	}

	public Vector4f lerp(Vector4f dest, float lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public String toString()
	{
		return "(" + x + "," + y + "," + z + "," + "w" + ")";
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getZ()
	{
		return z;
	}

	public float getW()
	{
		return w;
	}

}

