WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com
package comp3170.ass1.sceneobjects;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.GL_FLOAT_VEC2;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import comp3170.GLBuffers;
import comp3170.SceneObject;
import comp3170.Shader;
import comp3170.ass1.shaders.ShaderLibrary;

public class Axes extends SceneObject {

	private Vector4f[] xAxis;
	private Vector4f[] yAxis;
	private int xVertexBuffer;
	private int yVertexBuffer;
	
	private float[] xColour = new float[] {1, 0, 0, 1}; // RED
	private float[] yColour = new float[] {0, 1, 0, 1}; // GREEN
	
	final private String VERTEX_SHADER = "simpleVertex.glsl";
	final private String FRAGMENT_SHADER = "simpleFragment.glsl";
	private Shader shader;

	public Axes() {
		shader = ShaderLibrary.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);

		xAxis = new Vector4f[] {
			new Vector4f(0, 0, 0, 1),
			new Vector4f(1, 0, 0, 1),
		};

		yAxis = new Vector4f[] {
			new Vector4f(0, 0, 0, 1),
			new Vector4f(0, 1, 0, 1),
		};
		
		xVertexBuffer = GLBuffers.createBuffer(xAxis);
		yVertexBuffer = GLBuffers.createBuffer(yAxis);
	}
	
	
	
	@Override
	protected void drawSelf(Matrix4f matrix) {
		
		shader.enable();
		shader.setUniform("u_mvpMatrix", matrix);
		
		// X axis
		
	    shader.setAttribute("a_position", xVertexBuffer);
	    shader.setUniform("u_colour", xColour);	   	    
		glDrawArrays(GL_LINES, 0, xAxis.length);           	

		// Y axis
		
	    shader.setAttribute("a_position", yVertexBuffer);
	    shader.setUniform("u_colour", yColour);	   	    
		glDrawArrays(GL_LINES, 0, yAxis.length);           	

	}

}
