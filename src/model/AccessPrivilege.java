package model;

/**
 * Defines all possible access privilege groups.
 * Higher access privileges usually yield stronger
 * responsibilities and less restrictive control.
 * 
 * TODO: Flesh out this enumeration further if needed.
 *
 * @see User
 * @since 1.0
 */
public enum AccessPrivilege
{
    MANAGER("Manager"),
    GUEST("Guest");
    
    /* Formal name of the access level. */
    private final String name;

    /* Constructs an access privilege type with a specified name. */
    AccessPrivilege(final String name)
    {
        assert name != null;
        this.name = name;
    }

    /**
     * @return name of the privilege level.
     */
    public String getName()
    {
        return name;
    }
}
