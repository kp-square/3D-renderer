public class Matrix4f{
	
	private float mat[][];

	public Matrix4f(){
		mat = new float[4][4];
	}

	public Matrix4f initIdentity()
	{
		mat[0][0]=1;	mat[0][1]=0;	mat[0][2]=0;	mat[0][3]=0;
		mat[1][0]=0;	mat[1][1]=1;	mat[1][2]=0;	mat[1][3]=0;
		mat[2][0]=0;	mat[2][1]=0;	mat[2][2]=1;	mat[2][3]=0;
		mat[3][0]=0;	mat[3][1]=0;	mat[3][2]=0;	mat[3][3]=1;

		return this;
	}


	/*This matrix is used to map normalized coordinates to
	 * coordinates where origin lies at top left corner of
	 * the screen and position actually corresponds to the
	 * pixel values.
	 */
	public Matrix4f initScreenSpaceTransform(float halfWidth, float halfHeight)
	{
		mat[0][0]=halfWidth;	mat[0][1]=0;		mat[0][2]=0;	mat[0][3]=halfWidth;
		mat[1][0]=0;		mat[1][1]=-halfHeight;	mat[1][2]=0;	mat[1][3]=halfHeight;
		mat[2][0]=0;		mat[2][1]=0;		mat[2][2]=1;	mat[2][3]=0;
		mat[3][0]=0;		mat[3][1]=0;		mat[3][2]=0;	mat[3][3]=1;

		return this;
	}

	public Matrix4f initTranslation(float tx, float ty, float tz)
	{
		mat[0][0]=1;	mat[0][1]=0;	mat[0][2]=0;	mat[0][3]=tx;
		mat[1][0]=0;	mat[1][1]=1;	mat[1][2]=0;	mat[1][3]=ty;
		mat[2][0]=0;	mat[2][1]=0;	mat[2][2]=1;	mat[2][3]=tz;
		mat[3][0]=0;	mat[3][1]=0;	mat[3][2]=0;	mat[3][3]=1;

		return this;
	}


	/*Rotation about unit vector
	 * u = (ux, uy, uz) is the unit vector
	 * angle is in radian format.
	 */
	public Matrix4f initRotation(float ux, float uy, float uz, float angle)
	{
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);

		mat[0][0] = cos + ux *ux * (1-cos);	mat[0][1] = ux*uy*(1-cos) - uz*sin;	
		mat[0][2] = ux*uz*(1-cos) + uy *sin;    mat[0][3] = 0;

		mat[1][0] = ux*uy*(1-cos) + uz *sin;	mat[1][1] = cos + uy*uy*(1-cos);	
		mat[1][2] = uy*uz*(1-cos) - uz *sin;	mat[1][3] = 0;

		mat[2][0] = uz*ux*(1-cos) - uy *sin;	mat[2][1] = uz*uy*(1-cos) + ux*sin;	
		mat[2][2] = cos + uz * uz*(1-cos);	mat[2][3] = 0;

		mat[3][0] = 0;	mat[3][1] = 0;	mat[3][2] = 0;	mat[3][3] = 1;

		return this;
	}

	//Rotation about standard x,y,z axis.
	//x,y,z are the angles in radian
	
	public Matrix4f initRotation(float x, float y, float z)
	{
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		rz.mat[0][0]=(float)Math.cos(z); 	rz.mat[0][1]=-(float)Math.sin(z); rz.mat[0][2]=0; 		 	rz.mat[0][3]=0;
		rz.mat[1][0]=(float)Math.sin(z); 	rz.mat[1][1]= (float)Math.cos(z); rz.mat[1][2]=0; 		 	rz.mat[1][3]=0;
		rz.mat[2][0]=0;			 	rz.mat[2][1]=0;		  	  rz.mat[2][2]=1; 		 	rz.mat[2][3]=0;
		rz.mat[3][0]=0;			 	rz.mat[3][1]=0;			  rz.mat[3][2]=0; 		 	rz.mat[3][3]=1;

		rx.mat[0][0]=1;	       			rx.mat[0][1]=0;	                  rx.mat[0][2]=0;	           	rx.mat[0][3]=0;
		rx.mat[1][0]=0;	                  	rx.mat[1][1]=(float)Math.cos(x);  rx.mat[1][2]=-(float)Math.sin(x); 	rx.mat[1][3]=0;
		rx.mat[2][0]=0;				rx.mat[2][1]=(float)Math.sin(x);  rx.mat[2][2]= (float)Math.cos(x); 	rx.mat[2][3]=0;
		rx.mat[3][0]=0;				rx.mat[3][1]=0;	                  rx.mat[3][2]=0;	           	rx.mat[3][3]=1;

		ry.mat[0][0]=(float)Math.cos(y);  	ry.mat[0][1]=0; 		  ry.mat[0][2]=-(float)Math.sin(y); 	ry.mat[0][3]=0;
		ry.mat[1][0]=0;			  	ry.mat[1][1]=1;                   ry.mat[1][2]=0; 		  	ry.mat[1][3]=0;
		ry.mat[2][0]=(float)Math.sin(y);  	ry.mat[2][1]=0;                   ry.mat[2][2]= (float)Math.cos(y); 	ry.mat[2][3]=0;
		ry.mat[3][0]=0;			  	ry.mat[3][1]=0;                   ry.mat[3][2]=0; 		  	ry.mat[3][3]=1;

		mat =  rz.mul(ry.mul(rx)).getMat();

		return this;
		
	}


	public Matrix4f initScale(float sx, float sy, float sz)
	{

		mat[0][0]=sx;	mat[0][1]=0;	mat[0][2]=0;	mat[0][3]=0;
		mat[1][0]=0;	mat[1][1]=sy;	mat[1][2]=0;	mat[1][3]=0;
		mat[2][0]=0;	mat[2][1]=0;	mat[2][2]=sz;	mat[2][3]=0;
		mat[3][0]=0;	mat[3][1]=0;	mat[3][2]=0;	mat[3][3]=1;

		return this;

	}


	/*fov is an angle in radian
	 * aspectRatio is the ratio of window width and height
	 * zNear = near plane z value
	 * xFar  = far plane z value
	 */
	public Matrix4f initPerspective(float fov, float aspectRatio, float zNear, float zFar)
	{
		float tanHalfFov = (float)Math.tan(fov/2);
		float zRange = zNear - zFar;

		mat[0][0]=1.0f/(tanHalfFov * aspectRatio); mat[0][1]=0;	mat[0][2]=0;				   mat[0][3]=0;
		mat[1][0]=0;				   mat[1][1]=1;	mat[1][2]=0;				   mat[1][3]=0;
		
		mat[2][0]=0;				   mat[2][1]=0;	mat[2][2]= (-zNear - zFar) / zRange;	   mat[2][3]=2 * zFar * zNear / zRange;
		
		mat[3][0]=0;				   mat[3][1]=0;	mat[3][2]=1;				   mat[3][3]=1;

		return this;

	}


	public Vector4f transform(Vector4f vec)
	{
		return new Vector4f(	mat[0][0] * vec.getX() + mat[0][1] * vec.getY() + mat[0][2] * vec.getZ() + mat[0][3] * vec.getW(), 
					mat[1][0] * vec.getX() + mat[1][1] * vec.getY() + mat[1][2] * vec.getZ() + mat[1][3] * vec.getW(), 
					mat[2][0] * vec.getX() + mat[2][1] * vec.getY() + mat[2][2] * vec.getZ() + mat[2][3] * vec.getW(), 
					mat[3][0] * vec.getX() + mat[3][1] * vec.getY() + mat[3][2] * vec.getZ() + mat[3][3] * vec.getW()); 
	}

	public Matrix4f mul(Matrix4f r)
	{
		Matrix4f result = new Matrix4f();
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				result.set(i, j, mat[i][0] * r.get(0,j) +
						 mat[i][1] * r.get(1,j) +
						 mat[i][2] * r.get(2,j) +
						 mat[i][3] * r.get(3,j));
			}
		}

		return result;

	}

	public float[][] getMat()
	{
		float[][] result = new float[4][4];

		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++)
			{
				result[i][j] = mat[i][j];
			}

		}
		return result;

	}

	public float get(int x, int y)
	{
		return mat[x][y];
	}

	public void setMat(float[][] mat)
	{
		this.mat = mat;
	}

	public void set(int x, int y, float value)
	{
		mat[x][y] = value;
	}






	



}

