package study;

public class Test {
	private static final int ACTION_PUT = 1 << 0;
    private static final int ACTION_GET = 1 << 1;
    public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(ACTION_GET));
		System.out.println(ACTION_PUT);
		
		
	}
}
