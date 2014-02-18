package bus.uigen.reflect.remote;
//will need to send to the appropriate process. Currently it directly talks to
//the JavaObjectManager assumed to be in the same VM
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
//represents the remote (non-proxy) end of the server

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.remote.StandardTypeIDs;
//import bus.uigen.reflect.remote.javaserver.JavaObjectManager;
import bus.uigen.reflect.remote.javaserver.JavaObjectManager;



public class ObjectManagerProxy {
	
	 
	public static String remoteGetCanonicalName(Object theFactory, int classID) {
		return JavaObjectManager.remoteGetCanonicalName(classID);
	}
	
	public static String remoteGetPackageName(Object theFactory, int classID) {
		return JavaObjectManager.remoteGetPackageName(classID);
	}
	 
	public static int remoteGetComponentType(Object thefactory, int classID) {	
		return JavaObjectManager.remoteGetComponentType(classID);
	}
		 
	public static int[] remoteGetConstructors(Object theFactory,  int classID) {		
		return JavaObjectManager.remoteGetConstructors(classID);
	}

	 
	public static int[] remoteGetDeclaredClasses(Object theFactory,  int classID) {
		return JavaObjectManager.remoteGetDeclaredClasses(classID);
	}

	 
	public static int[] remoteGetDeclaredFields(Object theFactory,  int classID) {
		return JavaObjectManager.remoteGetDeclaredFields(classID);
	}

	 
	public static int remoteGetDeclaringClass(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetDeclaringClass(classID);
	}

	 
	public static String[] remoteGetEnumConstants(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetEnumConstants(classID);
	}

	 
	public static int remoteGetField(Object theFactory,  int classID, String string) throws SecurityException,
			NoSuchFieldException {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetField(classID, string);
	}

	 
	public static int[] remoteGetFields(Object theFactory,  int classID) {
		return JavaObjectManager.remoteGetDeclaredFields(classID);
	}

	 
	public static int[] remoteGetInterfaces(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetInterfaces(classID);
	}

	 
	public static int remoteGetMethod(Object theFactory,  int classID, String name,
			int... parameterTypes) throws NoSuchMethodException,
			SecurityException {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetMethod(classID, name, parameterTypes);
	}

	 	
	public static int[] remoteGetMethods(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetMethods(classID);
	}

	 
	public static int remoteGetModifiers(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetModifiers(classID);
	}

	 
	public static String remoteGetName(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetName(classID);
	}

	 
	public static String remoteGetSimpleName(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetSimpleName(classID);
	}

	 
	public static int remoteGetSuperclass(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetSuperclass(classID);
	}

	 
	public static boolean remoteIsArray(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteIsArray(classID);
	}

	 
	public static boolean remoteIsAssignableFrom(Object theFactory,  int classID, int cls) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteIsAssignableFrom(classID, cls);
	}

	 
	public static boolean remoteIsEnum(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteIsEnum(classID);
	}
	
	 
	public static boolean remoteIsInstance(Object theFactory,  int classID, String obj) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteIsInstance(classID, obj);
	}

	 
	public static boolean remoteIsInterface(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteIsInterface(classID);
	}

	 
	public static boolean remoteIsPrimitive(Object theFactory,  int classID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteIsPrimitive(classID);
	}
	
	 
	public static String remoteNewInstance(Object theFactory,  int classID) throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteNewInstance(classID);
	}
	
	public static int remoteGetClass(String objectID) {
		return JavaObjectManager.remoteGetClass(objectID);
	}
	public static int remoteForName(String theName) throws ClassNotFoundException{
				
		return JavaObjectManager.remoteForName(theName);
	}
	 
	// this wil be done by the proxy
	 /*
	public static MethodProxy cloneMethod(Object theFactory,  int methodID) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	 
	public static Object constructorNewInstance(Object theFactory,  int constructorID, Object... parameterTypes)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		// TODO Auto-generated method stub
		return JavaObjectManager.constructorNewInstance(constructorID, parameterTypes);
	}

	 
	public static String constructorToString(Object theFactory,  int constructorID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.constructorToString(constructorID);
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
	 
	public static int getConstructorDeclaringClass(Object theFactory,  int constructorID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getConstructorDeclaringClass(constructorID);
	}

	 
	public static String getConstructorName(Object theFactory,  int constructorID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getConstructorName(constructorID);
	}

	 
	public static int[] getConstructorParameterTypes(Object theFactory,  int constructorID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getConstructorParameterTypes(constructorID);
	}

	 
	public static int getConstructorReturnType(Object theFactory,  int constructorID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getConstructorReturnType(constructorID);
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
	 
	public static int getMethodDeclaringClass(Object theFactory,  int methodID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getMethodDeclaringClass(methodID);
	}

	 
	public static int getMethodModifiers(Object theFactory,  int methodID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getMethodModifiers(methodID);
	}
	public static int getConstructorModifiers(Object theFactory,  int constructorID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getConstructorModifiers(constructorID);
	}

	 
	public static String getMethodName(Object theFactory,  int methodID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getMethodName(methodID);
	}

	 
	public static int[] getMethodParameterTypes(Object theFactory,  int methodID ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getMethodParameterTypes(methodID);
		}

	 
	public static int getMethodReturnType(Object theFactory,  int methodID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.getMethodReturnType(methodID);
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
	 
	public static String methodInvoke(Object theFactory,  int methodID,  String targetObject, String... parameterTypes)
			throws InvocationTargetException, IllegalAccessException,
			InstantiationException {
		// TODO Auto-generated method stub
		return JavaObjectManager.methodInvoke(methodID, targetObject, parameterTypes);
	}

	 
	public static String methodToString(Object theFactory,  int methodID) {
		// TODO Auto-generated method stub
		return JavaObjectManager.methodToString(methodID);
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
	
	public static  String remoteGet(Object theFactory,  int fieldId, String obj) throws IllegalArgumentException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGet(fieldId, obj);
	}

	
	public static  int remoteGetFieldDeclaringClass(Object theFactory,  int fieldId ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetFieldDeclaringClass(fieldId);
	}

	
	public static  int remoteGetFieldModifiers(Object theFactory,  int fieldId ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetFieldModifiers(fieldId);
	}

	
	public static  String remoteGetFieldName(Object theFactory,  int fieldId ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetFieldName(fieldId);
	}

	
	public static  int remoteGetType(Object theFactory,  int fieldId ) {
		// TODO Auto-generated method stub
		return JavaObjectManager.remoteGetType(fieldId);
	}

	
	public static  void remoteSet(Object theFactory,  int fieldId, String obj, String value)
			throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		 JavaObjectManager.remoteSet(fieldId, obj, value);
	}
	
	public static String remoteObjectToString(String objectID) {
		return JavaObjectManager.remoteObjectToString(objectID);
	}
	public static String remoteClassToString(Object theFactory,  int classID) {
		return JavaObjectManager.remoteClassToString(classID);
	}
	public static String remoteMethodToString(Object theFactory,  int methodID) {
		return JavaObjectManager.remoteMethodToString(methodID);
	}
	public static String remoteFieldToString(Object theFactory,  int fieldID) {
		return JavaObjectManager.remoteFieldToString(fieldID);
	}
	public static String remoteGetApplicationObject() {
		return JavaObjectManager.remoteGetApplicationObject();
	}
	public static String constructorInvoke(Object theFactory,  int constructorID,  String... parameters)
	throws InvocationTargetException, IllegalAccessException,
	InstantiationException {
// TODO Auto-generated method stub
			return JavaObjectManager.constructorInvoke(constructorID, parameters);
	}

}
