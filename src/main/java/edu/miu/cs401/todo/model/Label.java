package edu.miu.cs401.todo.model;

public enum Label {
	GREEN("#ffff00"),
	YELLOW("#00ff00"),
	RED("#00ff00"),
	BLUE("#00ff00");
	
	private String val;
	
	Label(String val) {
		System.out.println("FIXME:Label.java THE COLORS ARE NOT CORRECT;");
		this.val = val;
	}
	
	public String val() {return this.val;}
	
	@Override
	public String toString() {
		return this.name();
	}
}
