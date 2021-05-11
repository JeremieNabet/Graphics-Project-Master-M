package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/**
 * allows me to determine the scene where my elements are
 */
public class Scene {

    private final String name;

    //according to Dan Zilberstein directives
    public Color background = Color.BLACK;
    public AmbientLight ambientlight = new AmbientLight(new Color(192, 192, 192), 1.d);
    ;
    public Geometries geometries = null;

    /**
     * Constructor
     *
     * @param name the name of my file
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * chaining set methods (this NOT a builder pattern)
     *
     * @param background the background
     * @return the new background
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter function ambient light
     *
     * @param ambientlight the ambient light
     * @return the new ambient light
     */
    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    /**
     * setter function geometries
     *
     * @param geometries have geometries
     * @return new geometries
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}

