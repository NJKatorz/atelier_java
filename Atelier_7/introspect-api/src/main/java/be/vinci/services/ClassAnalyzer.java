package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());

        return objectBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info
        objectBuilder.add("name", f.getName());
        if (f.getGenericType() instanceof ParameterizedType) {
            objectBuilder.add("type", f.getGenericType().toString());
        } else {
            objectBuilder.add("type", f.getType().getSimpleName());
        }
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        for (Field f: aClass.getDeclaredFields()) {
            arrayBuilder.add(getField(f));
        }
        return arrayBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        return Modifier.isStatic(f.getModifiers()); // TODO
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        if (Modifier.isPublic(f.getModifiers()))
            return "public";
        else if (Modifier.isPrivate(f.getModifiers())) {
            return "private";
        } else if (Modifier.isProtected(f.getModifiers())) {
            return "protected";
        }else
            return "package";
    }

    // Methods

    /**
     * Get a method, and create a Json Object with all field data.
     * Example :
     * {
     *  name: "setFirstName",
     *  returnType: null,
     *  parameters: ["String"]
     *  visibility : "public" // public, private, protected, package
     *  isStatic: false,
     *  isAbstract: false
     * }
     */
    public JsonObject getMethod(Method m) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info
        objectBuilder.add("name", m.getName());
        if (m.getGenericReturnType() instanceof ParameterizedType) {
            objectBuilder.add("returnType", m.getGenericReturnType().toString());
        } else {
            objectBuilder.add("returnType", m.getReturnType().getSimpleName());
        }
        objectBuilder.add("visibility", getMethodVisibility(m));
        objectBuilder.add("isStatic", isMethodStatic(m));
        objectBuilder.add("isAbstract", isMethodAbstract(m));

        return objectBuilder.build();
    }

    /**
     * Get methods, and create a Json Array with all methods data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getMethods() method to handle each method one by one.
     */
    public JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all methods descriptions to array (use the getMethods() method above)
        for (Method m: aClass.getDeclaredMethods()) {
            arrayBuilder.add(getMethod(m));
        }
        return arrayBuilder.build();
    }

    /**
     * Return whether a method is static or not
     *
     * @param m the method to check
     * @return true if the method is static, false else
     */
    private boolean isMethodStatic(Method m) {
        return Modifier.isStatic(m.getModifiers()); // TODO
    }

    /**
     * Return whether a method is abstract or not
     *
     * @param m the method to check
     * @return true if the method is abstract, false else
     */
    private boolean isMethodAbstract(Method m) {
        return Modifier.isAbstract(m.getModifiers()); // TODO
    }

    /**
     * Get field visibility in a string form
     *
     * @param m the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getMethodVisibility(Method m) {
        if (Modifier.isPublic(m.getModifiers()))
            return "public";
        else if (Modifier.isPrivate(m.getModifiers())) {
            return "private";
        } else if (Modifier.isProtected(m.getModifiers())) {
            return "protected";
        }else
            return "package";
    }


}
