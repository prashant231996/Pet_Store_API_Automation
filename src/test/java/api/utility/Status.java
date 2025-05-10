package api.utility;

public enum Status {
	
	CODE_200(200),
	CODE_400(400);
	
	public final int statusCode;
	
    Status(int statusCode)
	{
		this.statusCode=statusCode;
	}
}
