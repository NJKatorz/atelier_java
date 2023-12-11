package domaine;


/**
 * Factory methods for Query instances.
 * @author Nathan
 * @version 1.0
 *
 */
public interface QueryFactory {

    /**
     * Creates Query instance with no values. They must be set with the setters.
     *
     * @return the empty Query instance
     */
    Query getQuery();
}
