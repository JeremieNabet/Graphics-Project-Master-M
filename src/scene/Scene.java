package scene;

import elements.*;
import geometries.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * scene class for the background lighting and the 3D shapes.
 *
 * @author Jeremie Nabet and Israel Bellaiche
 */
public class Scene {
    /**
     * scene's name
     */
    public String name;
    /**
     * scene's background
     */
    public Color background = Color.BLACK;
    /**
     * scene's ambient light
     */
    public AmbientLight ambientLight;
    /**
     * List of the shapes in the scene
     */
    public Geometries geometries;
    /**
     * list of light source in the scene
     */
    public List<LightSource> lights;

    /**
     * Constructor
     *
     * @param sceneBuilder
     */
    public Scene(SceneBuilder sceneBuilder) {
        this.name = sceneBuilder._name;
        this.background = sceneBuilder._background;
        this.ambientLight = sceneBuilder._ambientLight;
        this.geometries = sceneBuilder._geometries;
        this.lights = sceneBuilder._lights;
    }

    /**
     * scene builder class for building the scene
     */
    public static class SceneBuilder {
        //scene's name
        public final String _name;
        //scene's background
        public Color _background = Color.BLACK;
        //scene's ambient light
        public AmbientLight _ambientLight;
        // List of the shapes in the scene
        public Geometries _geometries;
        //list of light source in the scene
        public List<LightSource> _lights;

        /**
         * Constructor
         *
         * @param name
         */
        public SceneBuilder(String name) {
            this._ambientLight = new AmbientLight();
            this._background = Color.BLACK;
            this._name = name;
            this._lights = new LinkedList<>();
        }

        /**
         * background setter
         *
         * @param background the given background
         * @return Scene
         */
        public SceneBuilder setBackground(Color background) {
            this._background = background;
            return this;
        }

        /**
         * ambientLight setter
         *
         * @param ambientLight the given ambientLight
         * @return Scene
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this._ambientLight = ambientLight;
            return this;
        }

        /**
         * geometries setter
         *
         * @param geometries the given geometries
         * @return Scene
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this._geometries = geometries;
            return this;
        }

        /**
         * lights setter
         *
         * @param lights given lights list
         * @return this
         */
        public SceneBuilder setLights(List<LightSource> lights) {
            this._lights = lights;
            return this;
        }

        /**
         * build the scene
         *
         * @return the built scene
         */
        public Scene build() {
            return new Scene(this);
        }
    }

}