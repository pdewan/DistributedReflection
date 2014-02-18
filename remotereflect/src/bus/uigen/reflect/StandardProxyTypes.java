package bus.uigen.reflect;

import java.util.Enumeration;

import bus.uigen.reflect.remote.RemoteClassProxy;
import bus.uigen.reflect.remote.StandardTypeIDs;

public class StandardProxyTypes {

	public static ClassProxy objectClass () {
		return RemoteSelector.classProxy(Object.class);
	}

	public static ClassProxy integerType() {
		return RemoteSelector.classProxy(Integer.TYPE);
	}

	public static ClassProxy shortType() {
		return RemoteSelector.classProxy(Short.TYPE);
	}

	public static ClassProxy longType() {
		return RemoteSelector.classProxy(Long.TYPE);
	}

	public static ClassProxy characterType() {
		return RemoteSelector.classProxy(Character.TYPE);
	}

	public static ClassProxy booleanType() {
		return RemoteSelector.classProxy(Boolean.TYPE);
	}

	public static ClassProxy doubleType() {
		return RemoteSelector.classProxy(Double.TYPE);
	}

	public static ClassProxy floatType() {
		return RemoteSelector.classProxy(Float.TYPE);
	}

	public static ClassProxy byteType() {
		return RemoteSelector.classProxy(Byte.TYPE);
	}

	public static ClassProxy integerClass() {
		return RemoteSelector.classProxy(Integer.class);
	}

	public static ClassProxy shortClass() {
		return RemoteSelector.classProxy(Short.class);
	}

	public static ClassProxy longClass() {
		return RemoteSelector.classProxy(Long.class);
	}

	public static ClassProxy characterClass() {
		return RemoteSelector.classProxy(Character.class);
	}

	public static ClassProxy booleanClass() {
		return RemoteSelector.classProxy(Boolean.class);
	}

	public static ClassProxy doubleClass() {
		return RemoteSelector.classProxy(Double.class);
	}

	public static ClassProxy floatClass() {
		return RemoteSelector.classProxy(Float.class);
	}

	public static ClassProxy byteClass() {
		return RemoteSelector.classProxy(Byte.class);
	}

	public static ClassProxy stringClass() {
		return RemoteSelector.classProxy(String.class);
	}

	public static ClassProxy enumerationClass() {
		return RemoteSelector.classProxy(Enumeration.class);
	}

	public static ClassProxy voidType() {
		return RemoteSelector.classProxy(Void.TYPE);
	}
	
	// remote ones
	public static ClassProxy integerType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.INTEGER_TYPE);
	}

	public static ClassProxy shortType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.SHORT_TYPE);
	}

	public static ClassProxy longType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.LONG_TYPE);
	}

	public static ClassProxy characterType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.CHARACTER_TYPE);
	}

	public static ClassProxy booleanType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.BOOLEAN_TYPE);
	}

	public static ClassProxy doubleType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.DOUBLE_TYPE);
	}

	public static ClassProxy floatType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.FLOAT_TYPE);
	}

	public static ClassProxy byteType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.BYTE_TYPE);
	}

	public static ClassProxy integerClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.INTEGER_CLASS );
	}

	public static ClassProxy shortClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.SHORT_CLASS );
	}

	public static ClassProxy longClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.LONG_CLASS );
	}

	public static ClassProxy characterClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.CHARACTER_CLASS );
	}

	public static ClassProxy booleanClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.BOOLEAN_CLASS );
	}

	public static ClassProxy doubleClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.DOUBLE_CLASS );
	}

	public static ClassProxy floatClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.FLOAT_CLASS );
	}

	public static ClassProxy byteClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.BYTE_CLASS );
	}

	public static ClassProxy stringClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.STRING_CLASS );
	}

	public static ClassProxy enumerationClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory,  StandardTypeIDs.ENUMERATION_CLASS );
	}

	public static ClassProxy voidType(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory, StandardTypeIDs.Void_TYPE);
	}
	public static ClassProxy objectClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory, StandardTypeIDs.OBJECT_CLASS);
	}
	public static ClassProxy colorClass(Object theFactory) {
		return RemoteClassProxy.classProxy(theFactory, StandardTypeIDs.COLOR_CLASS);
	}

}
