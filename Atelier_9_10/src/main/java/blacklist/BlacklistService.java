package blacklist;

import domaine.Query;

/**
 * This interface represents a basic HTTP query.
 *
 * @author Nathan
 */
public interface BlacklistService {
    /**
     * Check if the query is blacklisted.
     *
     * @param query the query to check
     * @return true if the query is not blacklisted, false otherwise
     */
    boolean
    check(Query query);
}
