package bus.uigen.reflect.remote;

import java.util.Hashtable;

import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.StandardProxyTypes;

public class StandardTypeRegistry {
	static Hashtable<Integer, ClassProxy> idToType = new Hashtable();
	static Hashtable<ClassProxy, Integer> typeToID = new Hashtable();
	/*
	public void init() {
		register(StandardProxyTypes.booleanClass(), StandardTypeIDs.BOOLEAN_CLASS);
		register(StandardProxyTypes.integerClass(), StandardTypeIDs.INTEGER_CLASS);
		register(StandardProxyTypes.shortClass(), StandardTypeIDs.SHORT_CLASS);
		register(StandardProxyTypes.longClass(), StandardTypeIDs.LONG_CLASS);
		register(StandardProxyTypes.floatClass(), StandardTypeIDs.FLOAT_CLASS);
		register(StandardProxyTypes.doubleClass(), StandardTypeIDs.DOUBLE_CLASS);
		register(StandardProxyTypes.characterClass(), StandardTypeIDs.CHARACTER_CLASS);
		register(StandardProxyTypes.byteClass(), StandardTypeIDs.BYTE_CLASS);
		register(StandardProxyTypes.stringClass(), StandardTypeIDs.STRING_CLASS);
		
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.BOOLEAN_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.INTEGER_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.SHORT_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.LONG_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.FLOAT_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.DOUBLE_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.CHARACTER_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.BYTE_TYPE);
		register(StandardProxyTypes.booleanType(), StandardTypeIDs.BOOLEAN_TYPE);
	}
	*/
	
	public void register (ClassProxy type, int id) {
		idToType.put(id, type);
		typeToID.put(type, id);
	}
	
	public Integer getProxyID(ClassProxy type) {
		return typeToID.get(type);
	}
	public ClassProxy getClassProxy(Integer id) {
		return idToType.get(id);
	}
	
}
