package course4Week1Assignments;

/**
 * Write a description of interface Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 
    public String getName();
}
