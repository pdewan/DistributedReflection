package bus.uigen.reflect;

import java.util.Vector;

//import bus.uigen.uiBean;
import bus.uigen.reflect.local.AClassProxy;
import bus.uigen.reflect.local.AVirtualMethod;
import bus.uigen.reflect.remote.StandardTypeIDs;

public class DynamicMethods {

	public static String GET_DYNAMIC_PROPERTY_TYPE = 	"getDynamicPropertyType";
	public static String GET_DYNAMIC_PROPERTY_NAMES = 	"getDynamicProperties";
	public static String DYNAMIC_PROPERTY_PROPERTY = 	"dynamicProperties";
	public static String GET_DYNAMIC_PROPERTY = 	"getDynamicProperty";
	public static String PRE_GET_DYNAMIC_PROPERTY = 	"preGetDynamicProperty";
	public static String SET_DYNAMIC_PROPERTY = 	"setDynamicProperty";
	public static String PRE_SET_DYNAMIC_PROPERTY = 	"preSetDynamicProperty";
	public static String VALIDATE_DYNAMIC_PROPERTY = 	"preSetDynamicProperty";
	public static String GET_VIRTUAL_METHODS = "getVirtualMethods";
	public static String DYNAMIC_COMMAND_PROPERTY = 	"dynamicCommands";
	public static String DYNAMIC_METHOD_PROPERTY = 	"dynamicMethods";
	public static String GET_DYNAMIC_COMMANDS = "getDynamicCommands";
	public static String PRE_DYNAMIC_COMMANDS = "preDynamicCommands";
	public static String GET_DYNAMIC_COMMAND_PARAMETER_TYPES = "getDynamicCommandParameterTypes";
	public static String GET_DYNAMIC_COMMAND_RETURN_TYPE = "getDynamicCommandReturnType";
	public static String INVOKE_DYNAMIC_COMMAND = "invokeDynamicCommand";
	public static String ONLY_DYNAMIC_COMMANDS = "onlyDynamicCommands";
	public static String ONLY_DYNAMIC_PROPERTIES = "onlyDynamicProperties";
	public static String GET_DYNAMIC_COMMAND_ANNOTATIONS = "getDynamicCommandAnnotations";
	//public static String GET_DYNAMIC_PROPERTY_ANNOTATIONS = "getDynamicPropertyAnnotations";
	public static String GET_DYNAMIC_COMMAND_ANNOTATION = "getDynamicCommandAnnotation";
	//public static String GET_DYNAMIC_PROPERTY_ANNOTATION = "getDynamicPropertyAnnotations";
	public static String[] dynamicMethods = {GET_DYNAMIC_PROPERTY_NAMES, 
	PRE_GET_DYNAMIC_PROPERTY, 
	GET_VIRTUAL_METHODS, 
	PRE_DYNAMIC_COMMANDS,
	GET_DYNAMIC_COMMAND_PARAMETER_TYPES,
	GET_DYNAMIC_COMMAND_RETURN_TYPE,
	INVOKE_DYNAMIC_COMMAND,
	ONLY_DYNAMIC_COMMANDS,
	ONLY_DYNAMIC_PROPERTIES,
	GET_DYNAMIC_COMMAND_ANNOTATION
	};
	static Object[] emptyArgs = {};
	static Class[] emptyArgTypes = {};
	static ClassProxy[] nullProxyParams = {};
	public static String[] getDynamicProperties (Object o) {
		if (o == null) return null;
		ClassProxy realClass = RemoteSelector.getClass(o);
		return getDynamicProperties(o, realClass);
		
//		
//		try {
//			MethodProxy m = realClass.getMethod(GET_DYNAMIC_PROPERTY_NAMES);
//			if (m == null) return null;
//			ClassProxy[] argTypes = m.getParameterTypes();
//			if (argTypes.length != 0) return null;
//			//if (argTypes[0] != String[].class) return null;
//			Object[] args = {};
//			String[] retVal =  (String[]) m.invoke(o, args);
//			return retVal;
//			/*
//			ConcreteEnumeration  retVal = 
//				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
//			if (retVal == null) {
//				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
//			}
//			
//			
//			return retVal;
//			*/
//			
//			} catch (Exception e) {
//				return null;
//			}	
		
		
	}
	public static String[] getDynamicProperties (Object o, ClassProxy realClass) {
//		if (o == null) return null;
//		ClassProxy realClass = RemoteSelector.getClass(o);
		
		
		try {
			MethodProxy m = realClass.getMethod(GET_DYNAMIC_PROPERTY_NAMES);
			if (m == null) return null;
			ClassProxy[] argTypes = m.getParameterTypes();
			if (argTypes.length != 0) return null;
			//if (argTypes[0] != String[].class) return null;
			Object[] args = {};
			String[] retVal =  (String[]) m.invoke(o, args);
			return retVal;
			/*
			ConcreteEnumeration  retVal = 
				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			if (retVal == null) {
				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			}
			
			
			return retVal;
			*/
			
			} catch (Exception e) {
				return null;
			}	
		
		
	}
	public static MethodProxy getDynamicPropertyGetter (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(GET_DYNAMIC_PROPERTY, argTypes);
			if (m.getReturnType() == c.voidType()) return null;
			return m;
			
			/*
			ConcreteEnumeration  retVal = 
				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			if (retVal == null) {
				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			}
			
			
			return retVal;
			*/
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getDynamicPropertyPreRead (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(PRE_GET_DYNAMIC_PROPERTY, argTypes);
			if (m.getReturnType() == c.booleanType()) return null;
			return m;
			
			/*
			ConcreteEnumeration  retVal = 
				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			if (retVal == null) {
				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			}
			
			
			return retVal;
			*/
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getDynamicPropertySetter (ClassProxy objectClass, ClassProxy propertyClass) {
		try {
			ClassProxy[] argTypes = {objectClass.stringClass(), propertyClass};
			return  objectClass.getMethod(SET_DYNAMIC_PROPERTY , argTypes);
			
			
			/*
			ConcreteEnumeration  retVal = 
				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			if (retVal == null) {
				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			}
			
			
			return retVal;
			*/
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getDynamicPropertyPreWrite (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(PRE_SET_DYNAMIC_PROPERTY, argTypes);
			if (m.getReturnType() != c.booleanType()) return null;
			return m;
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getDynamicPropertyValidate (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass(), c.objectClass()};
			MethodProxy m = c.getMethod(PRE_SET_DYNAMIC_PROPERTY, argTypes);
			if (m.getReturnType() != c.booleanType()) return null;
			return m;
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getDynamicPropertyIsEditable (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(PRE_SET_DYNAMIC_PROPERTY, argTypes);
			if (m.getReturnType() != c.booleanType()) return null;
			return m;
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static Vector<MethodProxy> getVirtualMethods (Object o) {
		if (o == null) return null;
		ClassProxy realClass = RemoteSelector.getClass(o);
		
		try {
			Object[] args = {};
			MethodProxy m = realClass.getMethod (GET_VIRTUAL_METHODS, nullProxyParams);
			if (m == null) return null;
			Vector<MethodProxy> retVal =  (Vector<MethodProxy>) m.invoke(o, emptyArgs);
			for (int i = 0; i < retVal.size(); i++) {
				retVal.elementAt(i).setDynamiclClass(RemoteSelector.getClass(o));
			}
			
			return retVal;
			/*
			ConcreteEnumeration  retVal = 
				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			if (retVal == null) {
				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			}
			
			
			return retVal;
			*/
			
			} catch (Exception e) {
				return null;
			}	
		
		
	}
	public static MethodProxy getDynamicCommandsMethod (ClassProxy cl) {
		try {
		ClassProxy[] argTypes = {cl.stringClass()};
		MethodProxy m = cl.getMethod(GET_DYNAMIC_COMMANDS, argTypes);
		return AVirtualMethod.virtualMethod(m);
		} catch (Exception e) {
			return null;
		}
		
	}
	public static boolean hasDynamicCommands(ClassProxy cl) {
		try {
		MethodProxy m = cl.getMethod(GET_DYNAMIC_COMMANDS);
		return cl != null;
		} catch (Exception e) {
			return false;
		}
	}
	public static String[] getDynamicCommands (Object o) {
		if (o == null) return null;
		ClassProxy realClass = RemoteSelector.getClass(o);
		
		try {
			MethodProxy m = realClass.getMethod(GET_DYNAMIC_COMMANDS);
			if (m == null) return null;
			ClassProxy[] argTypes = m.getParameterTypes();
			if (argTypes.length != 0) return null;
			//if (argTypes[0] != String[].class) return null;
			Object[] args = {};
			String[] retVal =  (String[]) m.invoke(o, args);
			return retVal;
			/*
			ConcreteEnumeration  retVal = 
				(new EnumToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			if (retVal == null) {
				retVal = (new GenericEnumerationToEnumerationFactory()).toConcreteEnumeration(o.getClass(), o, null);
			}
			
			
			return retVal;
			*/
			
			} catch (Exception e) {
				return null;
			}	
		
		
	}
	public static MethodProxy getDynamicCommandPre (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(PRE_DYNAMIC_COMMANDS, argTypes);
			if (m.getReturnType() != c.booleanType()) return null;
			return AVirtualMethod.virtualMethod(m);
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getGetDynamicCommandParameterTypes (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(GET_DYNAMIC_COMMAND_PARAMETER_TYPES , argTypes);
			//if (m.getReturnType() != Class[].class) return null;
			if (!m.getReturnType().equals(RemoteSelector.classProxy(Class[].class))) return null;
			return m;
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getGetDynamicCommandReturnType (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(GET_DYNAMIC_COMMAND_RETURN_TYPE, argTypes);
			//if (m.getReturnType() != Class.class) return null;
			return m;
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static ClassProxy objectArrayClass() {
		return AClassProxy.classProxy(Object[].class);
	}
	public static MethodProxy getInvokeDynamicCommand (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass(), objectArrayClass()};
			return c.getMethod(INVOKE_DYNAMIC_COMMAND, argTypes);
			
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getGetDynamicCommandAnnotation (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass(), AClassProxy.classProxy(Class.class)};
			return c.getMethod(GET_DYNAMIC_COMMAND_ANNOTATION, argTypes);
			
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static MethodProxy getParameterlessInvokeDynamicCommand (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			//ClassProxy[] argTypes2 = ClassSelector.classProxy(argTypes);
			return c.getMethod(INVOKE_DYNAMIC_COMMAND, argTypes);
			
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}
	public static boolean getOnlyDynamicCommands (Object o) {
		try {
			ClassProxy c = RemoteSelector.getClass(o);
			
			MethodProxy onlyDynamicCommands = c.getMethod(ONLY_DYNAMIC_COMMANDS, nullProxyParams);
			return ((Boolean) onlyDynamicCommands.invoke(o, emptyArgs)).booleanValue();
			
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean getOnlyDynamicProperties (Object o) {
		try {
			ClassProxy c = RemoteSelector.getClass(o);
			
			MethodProxy onlyDynamicProperties = c.getMethod(ONLY_DYNAMIC_PROPERTIES, nullProxyParams);
			return ((Boolean) onlyDynamicProperties.invoke(o, emptyArgs)).booleanValue();
			
		} catch (Exception e) {
			return false;
		}
	}
	public static MethodProxy getDynamicPropertyTypeGetter (ClassProxy c) {
		try {
			ClassProxy[] argTypes = {c.stringClass()};
			MethodProxy m = c.getMethod(GET_DYNAMIC_PROPERTY_TYPE, argTypes);
			//if (m.getReturnType() != c.()) return null;
			return m;
			
			
			
			} catch (Exception e) {
				return null;
			}	
		
	}

}
