package bus.uigen.reflect.remote;

import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JTable;
import javax.swing.JTree;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.FieldProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.RemoteSelector;

public class RemoteClassProxy extends CachingRemoteClassProxy implements ClassProxy {
	
	public RemoteClassProxy(int theClassID) {
		super(theClassID);
		if (classID == StandardTypeIDs.DOUBLE_TYPE)
			System.out.println("Double");
		// TODO Auto-generated constructor stub
	}
	
	public RemoteClassProxy(Object theFactory, int theClassID) {
		super(theFactory, theClassID);
		if (classID == StandardTypeIDs.DOUBLE_TYPE)
			System.out.println("Double");
		// TODO Auto-generated constructor stub
	}
	public static transient Hashtable<Integer, RemoteClassProxy> idsToClassProxies = new Hashtable();
	
	public static RemoteClassProxy classProxy (Object theFactory, int classID) {
		if (classID == -1)
			return null;
		
		RemoteClassProxy cp =idsToClassProxies.get(classID);
		if (cp == null) {
			cp = new RemoteClassProxy(theFactory, classID);
			idsToClassProxies.put(classID, cp);			
		}
		return cp;
			
	}
	public static RemoteClassProxy classProxy (int classID) {
		
		RemoteClassProxy cp =idsToClassProxies.get(classID);
		if (cp == null) {
			cp = new RemoteClassProxy(classID);
			idsToClassProxies.put(classID, cp);			
		}
		return cp;
			
	}
	
	public static ClassProxy[] toClassProxies(Object theFactory, int[] ids) {
		ClassProxy[] retVal = new RemoteClassProxy[ids.length];
		for (int i = 0; i < ids.length; i++)
			retVal[i] = RemoteClassProxy.classProxy(theFactory, ids[i]);
		return retVal;			
	}
	/*
	public String getFactoryName() {
		return RemoteJavaObjectProxy.getStaticFactoryName();
	}
	*/
	@Override
	public Annotation remoteGetAnnotation(Class annotationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Annotation[] remoteGetAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String remoteGetCanonicalName() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGetCanonicalName(getFactoryName(), getClassID());
	}

	@Override
	public ClassProxy remoteGetComponentType() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(),ObjectManagerProxy.remoteGetComponentType(getFactoryName(), getClassID()));
	}

	@Override
	public MethodProxy[] remoteGetConstructors() {
		// TODO Auto-generated method stub
		return  RemoteMethodProxy.toConstructorProxies(getFactoryName(), ObjectManagerProxy.remoteGetConstructors(getFactoryName(), getClassID()));
	}

	@Override
	public ClassProxy[] remoteGetDeclaredClasses() {
		// TODO Auto-generated method stub
		return toClassProxies(getFactoryName(), (ObjectManagerProxy.remoteGetDeclaredClasses(getFactoryName(), getClassID())));
	}

	@Override
	public FieldProxy[] remoteGetDeclaredFields() {
		// TODO Auto-generated method stub
		return RemoteFieldProxy.fieldProxies(getFactoryName(), ObjectManagerProxy.remoteGetDeclaredFields(getFactoryName(), getClassID()));
	}

	@Override
	public ClassProxy remoteGetDeclaringClass() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteGetFieldDeclaringClass(getFactoryName(), getClassID()));
	}

	@Override
	public Object[] remoteGetEnumConstants() {
		// TODO Auto-generated method stub
		return RemoteObjectProxy.toObjectProxies(getFactoryName(), ObjectManagerProxy.remoteGetEnumConstants(getFactoryName(), getClassID()));
	}

	@Override
	public FieldProxy remoteGetField(String string) throws SecurityException,
			NoSuchFieldException {
		// TODO Auto-generated method stub
		return RemoteFieldProxy.fieldProxy(getFactoryName(), ObjectManagerProxy.remoteGetField(getFactoryName(), getClassID(), string));
	}

	@Override
	public FieldProxy[] remoteGetFields() {
		// TODO Auto-generated method stub
		return RemoteFieldProxy.fieldProxies(getFactoryName(), ObjectManagerProxy.remoteGetFields(getFactoryName(), getClassID()));
	}

	@Override
	public ClassProxy[] remoteGetInterfaces() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.toClassProxies(getFactoryName(), ObjectManagerProxy.remoteGetInterfaces(getFactoryName(), getClassID()));
	}

	@Override
	public MethodProxy remoteGetMethod(String name,
			CachingRemoteClassProxy... parameterTypes) throws NoSuchMethodException,
			SecurityException {
		// TODO Auto-generated method stub
		return RemoteMethodProxy.methodProxy(getFactoryName(), ObjectManagerProxy.remoteGetMethod(getFactoryName(), getClassID(), name,
				RemoteClassProxy.toIDs(parameterTypes)), true);
	}

	@Override
	public MethodProxy[] remoteGetMethods() {
		// TODO Auto-generated method stub
		return RemoteMethodProxy.toMethodProxies(getFactoryName(), ObjectManagerProxy.remoteGetMethods(getFactoryName(), getClassID()));
	}

	@Override
	public int remoteGetModifiers() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGetModifiers(getFactoryName(), getClassID());
	}

	@Override
	public String remoteGetName() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGetName(getFactoryName(), getClassID());
	}

	@Override
	public String remoteGetSimpleName() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteGetSimpleName(getFactoryName(), getClassID());
	}

	@Override
	public ClassProxy remoteGetSuperclass() {
		// TODO Auto-generated method stub
		return RemoteClassProxy.classProxy(getFactoryName(), ObjectManagerProxy.remoteGetSuperclass(getFactoryName(), getClassID()));
	}

	@Override
	public boolean remoteIsArray() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteIsArray(getFactoryName(), getClassID());
	}

	@Override
	public boolean remoteIsAssignableFrom(CachingRemoteClassProxy cls) {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteIsAssignableFrom(getFactoryName(), getClassID(), cls.getClassID());
	}

	@Override
	public boolean remoteIsEnum() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteIsEnum(getFactoryName(), getClassID());
	}

	@Override
	public boolean remoteIsInstance(ObjectProxy obj) {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteIsInstance(getFactoryName(), getClassID(), obj.getObjectID());
	}

	@Override
	public boolean remoteIsInterface() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteIsInterface(getFactoryName(), getClassID());
	}

	@Override
	public boolean remoteIsPrimitive() {
		// TODO Auto-generated method stub
		return ObjectManagerProxy.remoteIsPrimitive(getFactoryName(), getClassID());
	}

	@Override
	public Object remoteNewInstance() throws InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		return RemoteObjectProxy.objectProxy(getFactoryName(), ObjectManagerProxy.remoteNewInstance(getFactoryName(), getClassID()));
	}

	public String remoteToString() {
		return ObjectManagerProxy.remoteClassToString(getFactoryName(), getClassID());
	}
	// adding types
	public  ClassProxy integerType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.INTEGER_TYPE);
	}

	public  ClassProxy shortType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.SHORT_TYPE);
	}

	public  ClassProxy longType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.LONG_TYPE);
	}

	public  ClassProxy characterType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.CHARACTER_TYPE);
	}

	public  ClassProxy booleanType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.BOOLEAN_TYPE);
	}

	public  ClassProxy doubleType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.DOUBLE_TYPE);
	}

	public  ClassProxy floatType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.FLOAT_TYPE);
	}

	public  ClassProxy byteType() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.BYTE_TYPE);
	}

	public  ClassProxy integerClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.INTEGER_CLASS );
	}

	public  ClassProxy shortClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.SHORT_CLASS );
	}

	public  ClassProxy longClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.LONG_CLASS );
	}

	public  ClassProxy characterClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.CHARACTER_CLASS );
	}

	public  ClassProxy booleanClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.BOOLEAN_CLASS );
	}

	public  ClassProxy doubleClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.DOUBLE_CLASS );
	}

	public  ClassProxy floatClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.FLOAT_CLASS );
	}

	public  ClassProxy byteClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.BYTE_CLASS );
	}

	public  ClassProxy stringClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.STRING_CLASS );
	}

	public  ClassProxy enumerationClass() {
		return RemoteClassProxy.classProxy(getFactoryName(),  StandardTypeIDs.ENUMERATION_CLASS );
	}

	public  ClassProxy voidType() {
		return RemoteClassProxy.classProxy(getFactoryName(), StandardTypeIDs.Void_TYPE);
	}
	public  ClassProxy objectClass() {
		return RemoteClassProxy.classProxy(getFactoryName(), StandardTypeIDs.OBJECT_CLASS);
	}
	
	public  ClassProxy mapClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.MAP_CLASS);
	}
	public  ClassProxy collectionClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.COLLECTION_CLASS );
	}
	public  ClassProxy listClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.LIST_CLASS );
	}
	public  ClassProxy setClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.SET_CLASS);
	}
	
	public  ClassProxy tableClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.TABLE_CLASS );
	}
	public  ClassProxy treeClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.TREE_CLASS );
	}
	public  ClassProxy colorClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.COLOR_CLASS );
	}
	public  ClassProxy strokeClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.STROKE_CLASS );
	}
	public  ClassProxy paintClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.PAINT_CLASS );
	}
	public  ClassProxy fontClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.FONT_CLASS );
	}
	public  ClassProxy vectorClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.VECTOR_CLASS );
	}
	public  ClassProxy proxyClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.PROXY_CLASS );
	}
	public  ClassProxy awtShapeClass() {
		return classProxy(getFactoryName(), StandardTypeIDs.AWT_SHAPE_CLASS );
	}
	public boolean equals(Object c2) {
		if (!(c2 instanceof RemoteClassProxy))
			return false;
		RemoteClassProxy otherClass = (RemoteClassProxy) c2;
		return getClassID() ==  otherClass.getClassID();
			
	}
	public boolean isImmutable() {
		return StandardTypeConverter.isImmutable(getClassID());
	}

	@Override
	public String getPackageName() {
		return ObjectManagerProxy.remoteGetPackageName(getFactoryName(), getClassID());

	}

	

}
