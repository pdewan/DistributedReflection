package bus.uigen.reflect.remote.javaserver;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.JTree;
//represents the remote (non-proxy) end of the server
/*
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.remote.StandardTypeIDs;
*/


import bus.uigen.reflect.remote.StandardTypeConverter;
import bus.uigen.reflect.remote.StandardTypeIDs;




public class JavaObjectManager {
	static boolean initializedClasses = false;
	static long nextObjectID = /*Integer.MAX_VALUE +*/ 1;
	static int nextClassID = bus.uigen.reflect.remote.StandardTypeIDs.MAX_STANDARD_ID; // first few defined for predefiend values
	static int nextMethodID = 0;
	static int nextConstructorID = 0;
	static int nextFieldID = 0;
	static Hashtable<String, Object> objectIDToObject = new Hashtable();
	static Hashtable<Integer, Class> classIDToClass = new Hashtable();
	static Hashtable<Integer, Method> methodIDToMethod = new Hashtable();
	static Hashtable<Integer, Constructor> constructorIDToConstructor = new Hashtable();
	static Hashtable<Integer, Field> fieldIDToField = new Hashtable();
	static Hashtable<Object, String> objectToObjectID = new Hashtable();
	static Hashtable<Class, Integer> classToClassID = new Hashtable();
	static Hashtable<Method, Integer> methodToMethodID = new Hashtable();
	static Hashtable<Constructor, Integer> constructorToConstructorID = new Hashtable();
	static Hashtable<Field, Integer> fieldToFieldID = new Hashtable();
	public static String toID(Object obj) {
		if (obj == null)
			return null;
		return toID(obj, obj.getClass());
		/*
		if (StandardTypeConverter.isImmutable(obj.getClass())) {
			return obj.toString();
		}
		String objectID = objectToObjectID.get(obj);
		if (objectID == null) {
			Long longObjectID = nextObjectID;
			objectID = longObjectID.toString();
			objectToObjectID.put(obj, objectID);
			objectIDToObject.put(objectID, obj);
			nextObjectID++;
		}
		return objectID;
		*/
	}
	public static String toID(Object obj, Class expectedClass) {
		if (obj == null)
			return null;
		if (StandardTypeConverter.isImmutable(expectedClass)) {
			return obj.toString();
		}
		String objectID = objectToObjectID.get(obj);
		if (objectID == null) {
			Long longObjectID = nextObjectID;
			objectID = longObjectID.toString();
			objectToObjectID.put(obj, objectID);
			objectIDToObject.put(objectID, obj);
			nextObjectID++;
		}
		return objectID;
	}
	
	public static Object getObject(String theObjectID) {
		if (theObjectID == null)
			return null;
		return objectIDToObject.get(theObjectID);
	}
	public static Object getObject(String theObjectID, Class cls) {
		int classID = classToClassID.get(cls);
		if (!StandardTypeConverter.isImmutable(classID))
			return objectIDToObject.get(theObjectID);
		else
			return StandardTypeConverter.toStandardObject(theObjectID, classID);
	}
	public static int toID(Class cls) {
		if (cls == null)
			return -1;
		if (!initializedClasses)
			initStandardClasses();
		Integer classID = classToClassID.get(cls);
		if (classID == null) {
			classID = nextClassID;
			classToClassID.put(cls, classID);
			classIDToClass.put(classID, cls);
			nextClassID++;
		}
		return classID;
	}
	
	
	public static Class getClass(int theClassID) {
		if (!initializedClasses)
			initStandardClasses();
		Class retVal = classIDToClass.get(theClassID);
		if (retVal == null) {
			System.out.println("Could not find class for:" + theClassID);
			System.out.println("classIDToClass:");
			System.out.println(classIDToClass);			
		}
		return retVal;
		//return classIDToClass.get(theClassID);
	}
	
	public static int toID(Method method) {
		Integer methodID = methodToMethodID.get(method);
		if (methodID == null) {
			methodID = nextMethodID;
			methodToMethodID.put(method, methodID);
			methodIDToMethod.put(methodID, method);
			nextMethodID++;
		}
		return methodID;
	}
	public static Method getMethod(int theMethodID) {
		return methodIDToMethod.get(theMethodID);
	}
	
	public static int toID(Constructor constructor) {
		Integer constructorID = constructorToConstructorID.get(constructor);
		if (constructorID == null) {
			constructorID = nextConstructorID;
			constructorToConstructorID.put(constructor, constructorID);
			constructorIDToConstructor.put(constructorID, constructor);
			nextMethodID++;
		}
		return constructorID;
	}
	public static Field getField(int theFieldID) {
		return fieldIDToField.get(theFieldID);
	}
	public static int toID(Field field) {
		Integer fieldID = fieldToFieldID.get(field);
		if (fieldID == null) {
			fieldID = nextFieldID;
			fieldToFieldID.put(field, fieldID);
			fieldIDToField.put(fieldID, field);
			nextFieldID++;
		}
		return fieldID;
	}
	public static Constructor getConstructor(int theConstructorID) {
		return constructorIDToConstructor.get(theConstructorID);
	}
	static int[] toIDs(Class[] classes) {
		int[] retVal = new int[classes.length];
		for (int i = 0; i < classes.length; i++)
			retVal[i] = toID(classes[i]);
		return retVal;
	}
	
	static int[] toIDs(Method[] methods) {
		int[] retVal = new int[methods.length];
		for (int i = 0; i < methods.length; i++)
			retVal[i] = toID(methods[i]);
		return retVal;
	}
	
	static int[] toIDs(Constructor[] constructors) {
		int[] retVal = new int[constructors.length];
		for (int i = 0; i < constructors.length; i++)
			retVal[i] = toID(constructors[i]);
		return retVal;
	}
	static int[] toIDs(Field[] fields) {
		int[] retVal = new int[fields.length];
		for (int i = 0; i < fields.length; i++)
			retVal[i] = toID(fields[i]);
		return retVal;
	}
	static String[] toIDs(Object[] objects) {
		if (objects == null)
			return null;
		String[] retVal = new String[objects.length];
		for (int i = 0; i < objects.length; i++)
			retVal[i] = toID(objects[i]);
		return retVal;
	}
	
	// 
	static Class[] getClasses(int[] classes) {
		Class[] retVal = new Class[classes.length];
		for (int i = 0; i < classes.length; i++)
			retVal[i] = getClass(classes[i]);
		return retVal;
	}
	
	static Method[] getMethods(int[] methods) {
		Method[] retVal = new Method[methods.length];
		for (int i = 0; i < methods.length; i++)
			retVal[i] = getMethod(methods[i]);
		return retVal;
	}
	
	static Constructor[] getConstructors(int[] constructors) {
		Constructor[] retVal = new Constructor[constructors.length];
		for (int i = 0; i < constructors.length; i++)
			retVal[i] = getConstructor(constructors[i]);
		return retVal;
	}
	static Field[] getFields(int[] fields) {
		Field[] retVal = new Field[fields.length];
		for (int i = 0; i < fields.length; i++)
			retVal[i] = getField(fields[i]);
		return retVal;
	}
	static Object[] getObjects(String[] objects) {
		Object[] retVal = new Object[objects.length];
		for (int i = 0; i < objects.length; i++)
			retVal[i] = getObject(objects[i]);
		return retVal;
	}
	static Object[] getObjects(String[] objects, Class[] types) {
		Object[] retVal = new Object[objects.length];
		for (int i = 0; i < objects.length; i++)
			retVal[i] = getObject(objects[i], types[i]);
		return retVal;
	}
	 
	public static String remoteGetCanonicalName(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).getCanonicalName();
	}
	
	public static String remoteGetPackageName(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).getPackage().getName();
	}
	 
	public static int remoteGetComponentType(int classID) {
		// TODO Auto-generated method stub
		Class cl = getClass(classID).getComponentType();		
		return toID(cl);
	}
		 
	public static int[] remoteGetConstructors(int classID) {
		// TODO Auto-generated method stub
		Constructor[] constructors = getClass(classID).getConstructors();
		return toIDs(constructors);
	}

	 
	public static int[] remoteGetDeclaredClasses(int classID) {
		Class[] classes = getClass(classID).getDeclaredClasses();
		return toIDs(classes);
	}

	 
	public static int[] remoteGetDeclaredFields(int classID) {
		return toIDs(getClass(classID).getDeclaredFields());
	}

	 
	public static int remoteGetDeclaringClass(int classID) {
		// TODO Auto-generated method stub
		return toID(getClass(classID).getDeclaringClass());
	}

	 
	public static String[] remoteGetEnumConstants(int classID) {
		// TODO Auto-generated method stub
		return toIDs(getClass(classID).getEnumConstants());
	}

	 
	public static int remoteGetField(int classID, String string) throws SecurityException,
			NoSuchFieldException {
		// TODO Auto-generated method stub
		return toID(getClass(classID).getField(string));
	}

	 
	public static int[] remoteGetFields(int classID) {
		// TODO Auto-generated method stub
		return toIDs(getClass(classID).getFields());
	}

	 
	public static int[] remoteGetInterfaces(int classID) {
		// TODO Auto-generated method stub
		return toIDs(getClass(classID).getInterfaces());
	}

	 
	public static int remoteGetMethod(int classID, String name,
			int... parameterTypes) throws NoSuchMethodException,
			SecurityException {
		// TODO Auto-generated method stub
		return toID(getClass(classID).getMethod(name, getClasses(parameterTypes)));
	}

	 
	public static int[] remoteGetMethods(int classID) {
		// TODO Auto-generated method stub
		return toIDs(getClass(classID).getMethods());
	}

	 
	public static int remoteGetModifiers(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).getModifiers();
	}

	 
	public static String remoteGetName(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).getName();
	}

	 
	public static String remoteGetSimpleName(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).getSimpleName();
	}

	 
	public static int remoteGetSuperclass(int classID) {
		// TODO Auto-generated method stub
		return toID(getClass(classID).getSuperclass());
	}

	 
	public static boolean remoteIsArray(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).isArray();
	}

	 
	public static boolean remoteIsAssignableFrom(int classID, int cls) {
		// TODO Auto-generated method stub
		return getClass(classID).isAssignableFrom(getClass(cls));
	}

	 
	public static boolean remoteIsEnum(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).isEnum();
	}
	
	 
	public static boolean remoteIsInstance(int classID, String obj) {
		// TODO Auto-generated method stub
		return getClass(classID).isInstance(getObject(obj));
	}

	 
	public static boolean remoteIsInterface(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).isInterface();
	}

	 
	public static boolean remoteIsPrimitive(int classID) {
		// TODO Auto-generated method stub
		return getClass(classID).isPrimitive();
	}
	
	 
	public static String remoteNewInstance(int classID) throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return toID(getClass(classID).newInstance());
	}
	
	public static int remoteGetClass(String objectID) {
		Object object = objectIDToObject.get(objectID);
		return toID(object.getClass());
	}
	public static int remoteForName(String theName) throws ClassNotFoundException{
				
		return toID(Class.forName(theName));
	}
	 
	// this wil be done by the proxy
	 /*
	public static MethodProxy cloneMethod(int methodID) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	 
	public static Object constructorNewInstance(int constructorID, Object... parameterTypes)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		// TODO Auto-generated method stub
		return toID(getConstructor(constructorID).newInstance(parameterTypes));
	}

	 
	public static String constructorToString(int constructorID) {
		// TODO Auto-generated method stub
		return getConstructor(constructorID).toString();
	}

	/* 
	public static Annotation getConstructorAnnotation(Class annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public static Annotation[] getConstructorAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	 
	public static int getConstructorDeclaringClass(int constructorID) {
		// TODO Auto-generated method stub
		return toID(getConstructor(constructorID).getDeclaringClass());
	}

	 
	public static String getConstructorName(int constructorID ) {
		// TODO Auto-generated method stub
		return getConstructor(constructorID).getName();
	}

	 
	public static int[] getConstructorParameterTypes(int constructorID ) {
		// TODO Auto-generated method stub
		return toIDs(getConstructor(constructorID).getParameterTypes());
	}

	 
	public static int getConstructorReturnType(int constructorID ) {
		// TODO Auto-generated method stub
		return toID(getConstructor(constructorID).getDeclaringClass());
	}

	 /*
	public static Annotation[] getDeclaredConstructorAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public static Annotation[] getDeclaredMethodAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public static Annotation getMethodAnnotation(Class annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public static Annotation[] getMethodAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	 
	public static int getMethodDeclaringClass(int methodID ) {
		// TODO Auto-generated method stub
		return toID(getMethod(methodID).getDeclaringClass());
	}

	 
	public static int getMethodModifiers(int methodID ) {
		// TODO Auto-generated method stub
		return getMethod(methodID).getModifiers();
	}
	
	public static int getConstructorModifiers(int constructorID ) {
		// TODO Auto-generated method stub
		return getConstructor(constructorID).getModifiers();
	}

	 
	public static String getMethodName(int methodID ) {
		// TODO Auto-generated method stub
		return getMethod(methodID).getName();
	}

	 
	public static int[] getMethodParameterTypes(int methodID ) {
		// TODO Auto-generated method stub
		return toIDs(getMethod(methodID).getParameterTypes());
	}

	 
	public static int getMethodReturnType(int methodID) {
		// TODO Auto-generated method stub
		return toID(getMethod(methodID).getReturnType());
	}
	

	/* 
	public static boolean isConstructorAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}
	
	 
	public static boolean isMethodAnnotationPresent(
			Class<? extends Annotation> annotationType) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	 
	public static String methodInvoke(int methodID,  String targetObjectID, String... stringParameters)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		Method method = getMethod(methodID);
		Class[] parameterTypes = method.getParameterTypes();
		Object targetObject = getObject(targetObjectID);
		Object[] parameters = getObjects(stringParameters, parameterTypes);
		Object retVal = method.invoke(targetObject, parameters);
		Class retValType = method.getReturnType();
		return toID(retVal, retValType);
		//return toID(getMethod(methodID).invoke(getObject(targetObjectID), parameters));
	}
	public static String constructorInvoke(int constructorID,  String... parameters)
	throws InvocationTargetException, IllegalAccessException,
	InstantiationException {
// TODO Auto-generated method stub
			return toID(getConstructor(constructorID).newInstance(getObjects(parameters)));
	}

	 
	public static String methodToString(int methodID) {
		// TODO Auto-generated method stub
		return getMethod(methodID).toString();
	}

	 
	

	/* 
	public static ClassProxy[] getParameterTypes(Object methodOrVirtualMethod) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public static boolean isConstructor() {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public static boolean isMethod() {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	
	public static  String remoteGet(int fieldId, String obj) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return toID(getField(fieldId).get(getObject(obj)));
	}

	
	public static  int remoteGetFieldDeclaringClass(int fieldId ) {
		// TODO Auto-generated method stub
		return toID(getField(fieldId).getDeclaringClass());
	}

	
	public static  int remoteGetFieldModifiers(int fieldId ) {
		// TODO Auto-generated method stub
		return getField(fieldId).getModifiers();
	}

	
	public static  String remoteGetFieldName(int fieldId ) {
		// TODO Auto-generated method stub
		return getField(fieldId).getName();
	}

	
	public static  int remoteGetType(int fieldId ) {
		// TODO Auto-generated method stub
		return toID(getField(fieldId).getType());
	}

	
	public static  void remoteSet(int fieldId, String obj, String value)
			throws IllegalArgumentException, IllegalAccessException {
		 getField(fieldId).set(getObject(obj), getObject(value));
		
	}
	
	public static String remoteObjectToString(String objectID) {
		return getObject(objectID).toString();
	}
	public static String remoteClassToString(int classID) {
		return getClass(classID).toString();
	}
	public static String remoteMethodToString(int methodID) {
		return getMethod(methodID).toString();
	}
	public static String remoteFieldToString(int fieldID) {
		return getField(fieldID).toString();
	}
	public static String remoteGetApplicationObject() {
		return toID(applicationObject);
	}
	static Object applicationObject;
	public static void setApplicationObject (Object newVal) {
		applicationObject = newVal;
	}
	public static void register (Class type, int id) {
		classIDToClass.put(id, type);
		classToClassID.put(type, id);
	}
	public static void  initStandardClasses() {
		initializedClasses = true;
		register(Boolean.class, StandardTypeIDs.BOOLEAN_CLASS);
		register(Integer.class, StandardTypeIDs.INTEGER_CLASS);
		register(Short.class, StandardTypeIDs.SHORT_CLASS);
		register(Long.class, StandardTypeIDs.LONG_CLASS);
		register(Float.class, StandardTypeIDs.FLOAT_CLASS);
		register(Double.class, StandardTypeIDs.DOUBLE_CLASS);
		register(Character.class, StandardTypeIDs.CHARACTER_CLASS);
		register(Byte.class, StandardTypeIDs.BYTE_CLASS);
		register(String.class, StandardTypeIDs.STRING_CLASS);
		
		register(Boolean.TYPE, StandardTypeIDs.BOOLEAN_TYPE);
		register(Integer.TYPE, StandardTypeIDs.INTEGER_TYPE);
		register(Short.TYPE, StandardTypeIDs.SHORT_TYPE);
		register(Long.TYPE, StandardTypeIDs.LONG_TYPE);
		register(Float.TYPE, StandardTypeIDs.FLOAT_TYPE);
		register(Double.TYPE, StandardTypeIDs.DOUBLE_TYPE);
		register(Character.TYPE, StandardTypeIDs.CHARACTER_TYPE);
		register(Byte.TYPE, StandardTypeIDs.BYTE_TYPE);
		register(Void.TYPE, StandardTypeIDs.Void_TYPE);
		register(Enumeration.class, StandardTypeIDs.ENUMERATION_CLASS);
		register(Object.class, StandardTypeIDs.OBJECT_CLASS);
		register(List.class, StandardTypeIDs.LIST_CLASS);
		register(Set.class, StandardTypeIDs.SET_CLASS);
		register(Collection.class, StandardTypeIDs.COLLECTION_CLASS);
		register(Map.class, StandardTypeIDs.MAP_CLASS);
		register(JTree.class, StandardTypeIDs.TREE_CLASS);
		register(JTable.class, StandardTypeIDs.TABLE_CLASS);
		register (Color.class, StandardTypeIDs.COLOR_CLASS);
		register (BasicStroke.class, StandardTypeIDs.STROKE_CLASS);
		register (GradientPaint.class, StandardTypeIDs.PAINT_CLASS);
		register (Font.class, StandardTypeIDs.FONT_CLASS);
	}

}
