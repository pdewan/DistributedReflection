package bus.uigen.reflect.remote;

public interface ObjectProxy  extends FactoryName{

	public String getObjectID();

	public CachingRemoteClassProxy getClassProxy();

	public void setClassProxy(CachingRemoteClassProxy retVal);

}