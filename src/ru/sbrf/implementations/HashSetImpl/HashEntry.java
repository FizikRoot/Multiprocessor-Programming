package ru.sbrf.implementations.HashSetImpl;

import java.io.Serializable;

/**
 * Created by Fizik on 09.01.2016.
 */
// we could use org/apache/commons/collections4/map/AbstractHashedMap
public class HashEntry implements Serializable
{
    public Object  element;   // the element
    public boolean isActive;  // false if marked deleted

    public HashEntry( Object e )
    {
        this( e, true );
    }

    public HashEntry( Object e, boolean i )
    {
        element  = e;
        isActive = i;
    }
}
