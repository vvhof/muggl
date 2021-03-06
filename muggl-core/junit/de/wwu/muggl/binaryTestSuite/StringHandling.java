package de.wwu.muggl.binaryTestSuite;

/**
 * Test String functions
 * 
 * @author Max Schulze
 *
 */
public class StringHandling {

	public static String METHOD_StringFromChar = "StringFromChar";

	public static boolean StringFromChar() {
		char[] d = { 'c', 'd' };
		String test = new String(d, 0, 2);
		return test.equals("cd");
	}

	public static String METHOD_StringReferenceEquality = "StringReferenceEquality";

	public static boolean StringReferenceEquality() {
		return "testing" == "testing";
	}

	public static String METHOD_StringEquality = "StringEquality";

	public boolean StringEquality() {
		return "testing".equals("testing");
	}

	public static String METHOD_Substring = "Substring";

	// crashes on wrong implementation of System.Arraycopy
	public boolean Substring() {
		return "testing".substring(3, 5).equals("ti");
	}

	public static String METHOD_StartsWith = "StartsWith";

	public boolean StartsWith() {
		return "testing".startsWith("tes");
	}

	public static String METHOD_StringHashCodeStable = "StringHashCodeStable";

	/**
	 * Muggl implementation of hashCode for Strings should be same as for Java API
	 * @return
	 */
	public static boolean StringHashCodeStable() {
		return "This is a Java string".hashCode() == 586653468;
	}
	
	public static String METHOD_CharLength = "CharLength";

	// inspired by the static fields in java.lang.CharacterDataLatin1 which would previously fail to instantiate
	// (assertions are helpful)
	public static int CharLength() {
		final char B[] = ("\000\001").toCharArray();
		return B.length;
	}

	public static void main(String[] args) {
		System.out.println(CharLength());
		System.out.println(StringFromChar());
	}

}
