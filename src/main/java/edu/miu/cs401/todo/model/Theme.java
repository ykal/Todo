package edu.miu.cs401.todo.model;

public enum Theme {
	GREEN("#00ff00"), 
	YELLOW("#00ff00"),
	ORANGE("#00ff00"),
	RED("#00ff00"),
	TEAL("#00ff00"),
	PINK("#00ff00"),
	PURPLE("#00ff00");
	
	private String val;
	
	Theme(String val) {
		System.out.println("FIXME:Theme.java THE COLORS ARE NOT CORRECT;");
		this.val = val;
	}
	
	public String val() {return this.val;}
}
