/**
 * 
 */
package picasso.parser.tokens;

/**
 * 
 * @author William Luik
 */
public class PathToken extends Token {
	int ORDER_VALUE = 4;
	private final String path;

	/**
	 * @param description
	 */
	public PathToken(String path) {
		super("Path Token: " + path);
		this.path = path;
		
	}
    public int getOrderValue() {
        return ORDER_VALUE;
    }

	/** 
	 * Not constantant
	 */
	@Override
	public boolean isConstant() {
		
		return false;
	}

	/** 
	 * Not a function
	 */
	@Override
	public boolean isFunction() {
		
		return false;
	}
	
	public String getPath() {
		return path;
	}
	
	//I had to add these because the tests were failing in ImageClipTests.
	//It wasn't letting me compare tokens that were the same.
	
	 //Compare two path's based on their strings; not their id's
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PathToken other = (PathToken) obj;
        return path.equals(other.path);
    }

    //Generate a hash code based on the path string:
    @Override
    public int hashCode() {
        return path.hashCode();
    }

}
