package domaine;

/**
 * This interface represents a basic HTTP query.
 * It contains also a QueryMethod Enum to represent the Method (GET, POST...)
 *
 * @author Nathan
 *
 */
public interface Query {

    /**
     * Get the url of the query.
     *
     * @return the url of the Query
     */
    String getUrl();

    /**
     * Set the url of the query.
     *
     * @param url the url to set
     */
    void setUrl(String url);


    /**
     * Set the method of the query.
     *
     * @param method the method to set
     */
    void setMethod(QueryMethod method);

    /**
     * Set the method of the query.
     *
     * @throws IllegalArgumentException if the method is not GET or POST
     * @param method the string of the method of the Query
     * @deprecated use {@link #setMethod(QueryMethod)} instead
     */
    void setMethod(String method) throws IllegalArgumentException;

    /**
     * Enum that represents the possible Query Methods that can be used.
     */
    enum QueryMethod {
        /**
         * Get method
         */
        GET,
        /**
         * Post method
         */
        POST
    }

}