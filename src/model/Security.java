package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Defines a less verbose strong hash alternative to Object.hashCode for Strings.
 *
 * @since 1.0
 */
public final class Security
{
    /* Prevent instantiation of the class. */
    private Security() { }

    /**
     * Performs a SHA-256 hash on the specified string.
     * @param str String to hash.
     * @return hash code bytes corresponding to the input string.
     */
    public static byte[] hash(final String str)
    {
        return sha256.digest(Objects.requireNonNull(str).getBytes(StandardCharsets.UTF_8));
    }
    
    /* SHA-256 Hash to avoid storing raw text for passwords. */
    private static final MessageDigest sha256;
    static
    {
        final String ALGORITHM = "Sha-256";
        MessageDigest temp = null;
        try { temp = MessageDigest.getInstance(ALGORITHM); }
        catch (final NoSuchAlgorithmException e)
        { 
            e.printStackTrace();
        }
        sha256 = temp;
    }
}
