package renderer;

import elements.*;
import geometries.*;
import primitives.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.*;
import org.xml.sax.*;
import scene.*;
import javax.xml.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
import static java.lang.Double.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
class RenderTests {
	Camera.CameraBuilder cameraBuilder =
			new Camera.CameraBuilder(
					Point3D.ZERO,
					new Vector(0, 0, -1),
					new Vector(0, 1, 0))
					.setDistance(100)
					.setViewPlaneSize(500, 500);
	Camera camera = cameraBuilder.build();

	/**
	 * Produce a scene with basic 3D model and render it into a jpeg image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {

		Geometries geometries = new Geometries();
		geometries.add(new Sphere(new Point3D(0, 0, -100), 50),
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)), // up left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up right
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)), // down left
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down right

		Scene.SceneBuilder sceneBuilder =
				new Scene.SceneBuilder("Test scene")
						.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1))
						.setBackground(new Color(75, 127, 90))
						.setGeometries(geometries);
		Scene scene = sceneBuilder.build();

		ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);

		Render.RenderBuilder renderBuilder =
				new Render.RenderBuilder()
						.setImageWriter(imageWriter)
//                        .setScene(scene)
						.setCamera(camera)
						.setRayTracer(new BasicRayTracer(scene));
		Render render = renderBuilder.build();

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}
// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the bodies
	 * and render it into a png image with a grid
	 */
	@Test
	public void basicRenderMultiColorTest() {

		Geometries geometries = new Geometries();

		geometries.add(new Sphere(new Point3D(0, 0, -100), 50) //
						.setEmission(new Color(java.awt.Color.CYAN)), //
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)) // up left
						.setEmission(new Color(java.awt.Color.GREEN)),
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)), // up right
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)) // down left
						.setEmission(new Color(java.awt.Color.RED)),
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100)) // down right
						.setEmission(new Color(java.awt.Color.BLUE)));
		Scene.SceneBuilder sceneBuilder = new Scene.SceneBuilder("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2))
				.setGeometries(geometries);
		Scene scene=sceneBuilder.build();
		ImageWriter imageWriter = new ImageWriter("color render test", 1000, 1000);
		Render.RenderBuilder renderBuilder = new Render.RenderBuilder() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new BasicRayTracer(scene));
		Render render=renderBuilder.build();
		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.WHITE));
		render.writeToImage();
	}

}
