package bus.uigen.reflect.remote;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class StandardTypeConverter {
	public static Class[] immutableTypeArray = {
				String.class, 
				Void.TYPE,
				Integer.class,
				Integer.TYPE,
				Double.class,
				Double.TYPE,
				Boolean.class,
				Boolean.TYPE,
				Float.class,
				Float.TYPE,
				Long.class,
				Long.TYPE,
				Short.class,
				Short.TYPE,
				Byte.class,
				Byte.TYPE,
				Color.class
				
	};	
	/*
	public static int ENUMERATION_CLASS = 19;
	public static int OBJECT_CLASS = 20;
	public static int MAP_CLASS = 21;
	public static int SET_CLASS = 22;
	public static int COLLECTION_CLASS = 23;
	public static int LIST_CLASS = 24;
	public static int TREE_CLASS = 25;
	public static int TABLE_CLASS = 26;
	*/
	public static Set immutableTypes = toSet(immutableTypeArray);
	public static Set<Class> toSet (Class[] array) {
		Set retVal = new HashSet();
		for (int i = 0; i < array.length; i++) {
			retVal.add(array[i]);
		}
		return retVal;
		
	}
	public static Object toStandardObject (String objectID, int classID) {
		/*
		if (classID >= StandardTypeIDs.MAX_STANDARD_ID) {
			return Integer.parseInt(objectID);
		}
		*/
		
		switch (classID) {		
		case StandardTypeIDs.STRING_CLASS:
			return objectID;
		case StandardTypeIDs.INTEGER_TYPE:
		case StandardTypeIDs.INTEGER_CLASS:
			return Integer.parseInt(objectID);
		case StandardTypeIDs.DOUBLE_TYPE:
		case StandardTypeIDs.DOUBLE_CLASS:
			return Double.parseDouble(objectID);
		case StandardTypeIDs.FLOAT_TYPE:
		case StandardTypeIDs.FLOAT_CLASS:
			return Float.parseFloat(objectID);
		case StandardTypeIDs.BOOLEAN_TYPE:
		case StandardTypeIDs.BOOLEAN_CLASS:
			return Boolean.parseBoolean(objectID);
		case StandardTypeIDs.SHORT_TYPE:
		case StandardTypeIDs.SHORT_CLASS:
			return Short.parseShort(objectID);
		case StandardTypeIDs.LONG_TYPE:
		case StandardTypeIDs.LONG_CLASS:
			return Long.parseLong(objectID);
		case StandardTypeIDs.CHARACTER_TYPE:
		case StandardTypeIDs.CHARACTER_CLASS:
			return new Character (((String) objectID).charAt(0));
		case StandardTypeIDs.COLOR_CLASS: 
			
			return getColor((String) objectID);
		}
		return null;
	}
	
	public static Color getColor (String colorString) {
		int red = getRed(colorString);
		int green = getGreen(colorString);
		int blue = getBlue(colorString);
		return new Color(red, green, blue);
	}
	
	public static int getComponentColor(String colorString, String marker1, String marker2) {
		try {
		int startIndex = colorString.indexOf(marker1) + 2;
		int endIndex = colorString.indexOf(marker2, startIndex);
		String componentString = colorString.substring(startIndex, endIndex);
		int retVal = Integer.parseInt(componentString);
		return retVal;
		} catch (Exception e) {
			return 0;
		}
	}
	public static int getRed(String colorString) {
		return getComponentColor(colorString, "r=", ",");
	}
	public static int getBlue(String colorString) {
		return getComponentColor(colorString, "b=", "]");
	}
	public static int getGreen(String colorString) {
		return getComponentColor(colorString, "g=", ",");
	}
	
	public static boolean isNonStandardID(int typeID) {
		return typeID >= StandardTypeIDs.MAX_STANDARD_ID;
	}
	public static boolean isNonStandardJavaClass(Class type) {
		return !immutableTypes.contains(type);
	}
	public static boolean isImmutable(int typeID) {
		return typeID <= StandardTypeIDs.MAX_IMMUTABLE_ID;
	}
	public static boolean isImmutable(Class type) {
		return immutableTypes.contains(type);
	}
	
	public static Object toStandardObject (String objectID, Class cls) {
		
		if (cls == String.class)
			return objectID;
		else if (cls == Integer.TYPE || cls == Integer.class)			
			return Integer.parseInt(objectID);
		else if (cls == Double.TYPE | cls == Double.class)
			return Double.parseDouble(objectID);
		else if (cls == Float.TYPE | cls == Float.class)
			return Float.parseFloat(objectID);
		else if (cls == Boolean.TYPE | cls == Boolean.class)
			return Boolean.parseBoolean(objectID);
		else if (cls == Short.TYPE | cls == Short.class)
			return Short.parseShort(objectID);
		else if (cls == Long.TYPE | cls == Long.class)
			return Long.parseLong(objectID);
		else if (cls == Character.TYPE | cls == Character.class)
			return new Character (((String) objectID).charAt(0));
			
		
		return null;
	}

}
